package com.ex.ltech.hongwai.view.dslv;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ex.ltech.led.R.styleable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DragSortListView extends ListView
{
  private static final int DRAGGING = 4;
  public static final int DRAG_NEG_X = 2;
  public static final int DRAG_NEG_Y = 8;
  public static final int DRAG_POS_X = 1;
  public static final int DRAG_POS_Y = 4;
  private static final int DROPPING = 2;
  private static final int IDLE = 0;
  private static final int NO_CANCEL = 0;
  private static final int ON_INTERCEPT_TOUCH_EVENT = 2;
  private static final int ON_TOUCH_EVENT = 1;
  private static final int REMOVING = 1;
  private static final int STOPPED = 3;
  private static final int sCacheSize = 3;
  private AdapterWrapper mAdapterWrapper;
  private boolean mAnimate = false;
  private boolean mBlockLayoutRequests = false;
  private MotionEvent mCancelEvent;
  private int mCancelMethod = 0;
  private HeightCache mChildHeightCache = new HeightCache(3);
  private float mCurrFloatAlpha = 1.0F;
  private int mDownScrollStartY;
  private float mDownScrollStartYF;
  private int mDragDeltaX;
  private int mDragDeltaY;
  private float mDragDownScrollHeight;
  private float mDragDownScrollStartFrac = 0.3333333F;
  private boolean mDragEnabled = true;
  private int mDragFlags = 0;
  private DragListener mDragListener;
  private DragScroller mDragScroller;
  private DragSortTracker mDragSortTracker;
  private int mDragStartY;
  private int mDragState = 0;
  private float mDragUpScrollHeight;
  private float mDragUpScrollStartFrac = 0.3333333F;
  private DropAnimator mDropAnimator;
  private DropListener mDropListener;
  private int mFirstExpPos;
  private float mFloatAlpha = 1.0F;
  private Point mFloatLoc = new Point();
  private int mFloatPos;
  private View mFloatView;
  private int mFloatViewHeight;
  private int mFloatViewHeightHalf;
  private boolean mFloatViewInvalidated = false;
  private FloatViewManager mFloatViewManager = null;
  private int mFloatViewMid;
  private boolean mFloatViewOnMeasured = false;
  private boolean mIgnoreTouchEvent = false;
  private boolean mInTouchEvent = false;
  private int mItemHeightCollapsed = 1;
  private boolean mLastCallWasIntercept = false;
  private int mLastX;
  private int mLastY;
  private LiftAnimator mLiftAnimator;
  private boolean mListViewIntercepted = false;
  private float mMaxScrollSpeed = 0.5F;
  private DataSetObserver mObserver;
  private int mOffsetX;
  private int mOffsetY;
  private RemoveAnimator mRemoveAnimator;
  private RemoveListener mRemoveListener;
  private float mRemoveVelocityX = 0.0F;
  private View[] mSampleViewTypes = new View[1];
  private DragScrollProfile mScrollProfile = new DragScrollProfile()
  {
    public float getSpeed(float paramFloat, long paramLong)
    {
      return paramFloat * DragSortListView.this.mMaxScrollSpeed;
    }
  };
  private int mSecondExpPos;
  private float mSlideFrac = 0.0F;
  private float mSlideRegionFrac = 0.25F;
  private int mSrcPos;
  private Point mTouchLoc = new Point();
  private boolean mTrackDragSort = false;
  private int mUpScrollStartY;
  private float mUpScrollStartYF;
  private boolean mUseRemoveVelocity;
  private int mWidthMeasureSpec = 0;
  private int mX;
  private int mY;

  public DragSortListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    int i = 150;
    int j = 150;
    TypedArray localTypedArray;
    if (paramAttributeSet != null)
    {
      localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.DragSortListView, 0, 0);
      this.mItemHeightCollapsed = Math.max(1, localTypedArray.getDimensionPixelSize(0, 1));
      this.mTrackDragSort = localTypedArray.getBoolean(5, false);
      if (this.mTrackDragSort)
        this.mDragSortTracker = new DragSortTracker();
      this.mFloatAlpha = localTypedArray.getFloat(6, this.mFloatAlpha);
      this.mCurrFloatAlpha = this.mFloatAlpha;
      this.mDragEnabled = localTypedArray.getBoolean(10, this.mDragEnabled);
      this.mSlideRegionFrac = Math.max(0.0F, Math.min(1.0F, 1.0F - localTypedArray.getFloat(7, 0.75F)));
      if (this.mSlideRegionFrac <= 0.0F)
        break label617;
    }
    label617: for (boolean bool1 = true; ; bool1 = false)
    {
      this.mAnimate = bool1;
      setDragScrollStart(localTypedArray.getFloat(1, this.mDragUpScrollStartFrac));
      this.mMaxScrollSpeed = localTypedArray.getFloat(2, this.mMaxScrollSpeed);
      i = localTypedArray.getInt(8, i);
      j = localTypedArray.getInt(9, j);
      if (localTypedArray.getBoolean(17, true))
      {
        boolean bool2 = localTypedArray.getBoolean(12, false);
        int k = localTypedArray.getInt(4, 1);
        boolean bool3 = localTypedArray.getBoolean(11, true);
        int m = localTypedArray.getInt(13, 0);
        int n = localTypedArray.getResourceId(14, 0);
        int i1 = localTypedArray.getResourceId(15, 0);
        int i2 = localTypedArray.getResourceId(16, 0);
        int i3 = localTypedArray.getColor(3, -16777216);
        DragSortController localDragSortController = new DragSortController(this, n, m, k, i2, i1);
        localDragSortController.setRemoveEnabled(bool2);
        localDragSortController.setSortEnabled(bool3);
        localDragSortController.setBackgroundColor(i3);
        this.mFloatViewManager = localDragSortController;
        setOnTouchListener(localDragSortController);
      }
      localTypedArray.recycle();
      this.mDragScroller = new DragScroller();
      if (i > 0)
        this.mRemoveAnimator = new RemoveAnimator(0.5F, i);
      if (j > 0)
        this.mDropAnimator = new DropAnimator(0.5F, j);
      this.mCancelEvent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
      this.mObserver = new DataSetObserver()
      {
        private void cancel()
        {
          if (DragSortListView.this.mDragState == 4)
            DragSortListView.this.cancelDrag();
        }

        public void onChanged()
        {
          cancel();
        }

        public void onInvalidated()
        {
          cancel();
        }
      };
      return;
    }
  }

  private void adjustAllItems()
  {
    int i = getFirstVisiblePosition();
    int j = getLastVisiblePosition();
    int k = Math.max(0, getHeaderViewsCount() - i);
    int m = Math.min(j - i, -1 + getCount() - getFooterViewsCount() - i);
    for (int n = k; n <= m; n++)
    {
      View localView = getChildAt(n);
      if (localView == null)
        continue;
      adjustItem(i + n, localView, false);
    }
  }

  private void adjustItem(int paramInt)
  {
    View localView = getChildAt(paramInt - getFirstVisiblePosition());
    if (localView != null)
      adjustItem(paramInt, localView, false);
  }

  private void adjustItem(int paramInt, View paramView, boolean paramBoolean)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    int i;
    if ((paramInt != this.mSrcPos) && (paramInt != this.mFirstExpPos) && (paramInt != this.mSecondExpPos))
    {
      i = -2;
      if (i != localLayoutParams.height)
      {
        localLayoutParams.height = i;
        paramView.setLayoutParams(localLayoutParams);
      }
      if ((paramInt == this.mFirstExpPos) || (paramInt == this.mSecondExpPos))
      {
        if (paramInt >= this.mSrcPos)
          break label154;
        ((DragSortItemView)paramView).setGravity(80);
      }
    }
    while (true)
    {
      int j = paramView.getVisibility();
      int k = this.mSrcPos;
      int m = 0;
      if (paramInt == k)
      {
        View localView = this.mFloatView;
        m = 0;
        if (localView != null)
          m = 4;
      }
      if (m != j)
        paramView.setVisibility(m);
      return;
      i = calcItemHeight(paramInt, paramView, paramBoolean);
      break;
      label154: if (paramInt <= this.mSrcPos)
        continue;
      ((DragSortItemView)paramView).setGravity(48);
    }
  }

  private void adjustOnReorder()
  {
    int i = getFirstVisiblePosition();
    if (this.mSrcPos < i)
    {
      View localView = getChildAt(0);
      int j = 0;
      if (localView != null)
        j = localView.getTop();
      setSelectionFromTop(i - 1, j - getPaddingTop());
    }
  }

  private int adjustScroll(int paramInt1, View paramView, int paramInt2, int paramInt3)
  {
    int i = getChildHeight(paramInt1);
    int j = paramView.getHeight();
    int k = calcItemHeight(paramInt1, i);
    int m = j;
    int n = k;
    if (paramInt1 != this.mSrcPos)
    {
      m -= i;
      n -= i;
    }
    int i1 = this.mFloatViewHeight;
    if ((this.mSrcPos != this.mFirstExpPos) && (this.mSrcPos != this.mSecondExpPos))
      i1 -= this.mItemHeightCollapsed;
    int i3;
    if (paramInt1 <= paramInt2)
    {
      int i4 = this.mFirstExpPos;
      i3 = 0;
      if (paramInt1 > i4)
        i3 = 0 + (i1 - n);
    }
    int i2;
    do
    {
      return i3;
      if (paramInt1 == paramInt3)
      {
        if (paramInt1 <= this.mFirstExpPos)
          return 0 + (m - i1);
        if (paramInt1 == this.mSecondExpPos)
          return 0 + (j - k);
        return 0 + m;
      }
      if (paramInt1 <= this.mFirstExpPos)
        return 0 - i1;
      i2 = this.mSecondExpPos;
      i3 = 0;
    }
    while (paramInt1 != i2);
    return 0 - n;
  }

  private static int buildRunList(SparseBooleanArray paramSparseBooleanArray, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    int j = findFirstSetIndex(paramSparseBooleanArray, paramInt1, paramInt2);
    if (j == -1)
      return 0;
    int k = paramSparseBooleanArray.keyAt(j);
    int m = k + 1;
    int n = j + 1;
    if (n < paramSparseBooleanArray.size())
    {
      int i2 = paramSparseBooleanArray.keyAt(n);
      if (i2 < paramInt2)
      {
        if (!paramSparseBooleanArray.valueAt(n));
        while (true)
        {
          n++;
          break;
          if (i2 == m)
          {
            m++;
            continue;
          }
          paramArrayOfInt1[i] = k;
          paramArrayOfInt2[i] = m;
          i++;
          k = i2;
          m = i2 + 1;
        }
      }
    }
    if (m == paramInt2)
      m = paramInt1;
    paramArrayOfInt1[i] = k;
    paramArrayOfInt2[i] = m;
    int i1 = i + 1;
    if ((i1 > 1) && (paramArrayOfInt1[0] == paramInt1) && (paramArrayOfInt2[(i1 - 1)] == paramInt1))
    {
      paramArrayOfInt1[0] = paramArrayOfInt1[(i1 - 1)];
      i1--;
    }
    return i1;
  }

  private int calcItemHeight(int paramInt1, int paramInt2)
  {
    getDividerHeight();
    int i;
    if ((this.mAnimate) && (this.mFirstExpPos != this.mSecondExpPos))
      i = 1;
    int j;
    int k;
    while (true)
    {
      j = this.mFloatViewHeight - this.mItemHeightCollapsed;
      k = (int)(this.mSlideFrac * j);
      if (paramInt1 != this.mSrcPos)
        break label115;
      if (this.mSrcPos != this.mFirstExpPos)
        break;
      if (i != 0)
      {
        return k + this.mItemHeightCollapsed;
        i = 0;
        continue;
      }
      return this.mFloatViewHeight;
    }
    if (this.mSrcPos == this.mSecondExpPos)
      return this.mFloatViewHeight - k;
    return this.mItemHeightCollapsed;
    label115: if (paramInt1 == this.mFirstExpPos)
    {
      if (i != 0)
        return paramInt2 + k;
      return paramInt2 + j;
    }
    if (paramInt1 == this.mSecondExpPos)
      return paramInt2 + j - k;
    return paramInt2;
  }

  private int calcItemHeight(int paramInt, View paramView, boolean paramBoolean)
  {
    return calcItemHeight(paramInt, getChildHeight(paramInt, paramView, paramBoolean));
  }

  private void clearPositions()
  {
    this.mSrcPos = -1;
    this.mFirstExpPos = -1;
    this.mSecondExpPos = -1;
    this.mFloatPos = -1;
  }

  private void continueDrag(int paramInt1, int paramInt2)
  {
    this.mFloatLoc.x = (paramInt1 - this.mDragDeltaX);
    this.mFloatLoc.y = (paramInt2 - this.mDragDeltaY);
    doDragFloatView(true);
    int i = Math.min(paramInt2, this.mFloatViewMid + this.mFloatViewHeightHalf);
    int j = Math.max(paramInt2, this.mFloatViewMid - this.mFloatViewHeightHalf);
    int k = this.mDragScroller.getScrollDir();
    if ((i > this.mLastY) && (i > this.mDownScrollStartY) && (k != 1))
    {
      if (k != -1)
        this.mDragScroller.stopScrolling(true);
      this.mDragScroller.startScrolling(1);
    }
    do
    {
      return;
      if ((j >= this.mLastY) || (j >= this.mUpScrollStartY) || (k == 0))
        continue;
      if (k != -1)
        this.mDragScroller.stopScrolling(true);
      this.mDragScroller.startScrolling(0);
      return;
    }
    while ((j < this.mUpScrollStartY) || (i > this.mDownScrollStartY) || (!this.mDragScroller.isScrolling()));
    this.mDragScroller.stopScrolling(true);
  }

  private void destroyFloatView()
  {
    if (this.mFloatView != null)
    {
      this.mFloatView.setVisibility(8);
      if (this.mFloatViewManager != null)
        this.mFloatViewManager.onDestroyFloatView(this.mFloatView);
      this.mFloatView = null;
      invalidate();
    }
  }

  private void doActionUpOrCancel()
  {
    this.mCancelMethod = 0;
    this.mInTouchEvent = false;
    if (this.mDragState == 3)
      this.mDragState = 0;
    this.mCurrFloatAlpha = this.mFloatAlpha;
    this.mListViewIntercepted = false;
    this.mChildHeightCache.clear();
  }

  private void doDragFloatView(int paramInt, View paramView, boolean paramBoolean)
  {
    this.mBlockLayoutRequests = true;
    updateFloatView();
    int i = this.mFirstExpPos;
    int j = this.mSecondExpPos;
    boolean bool = updatePositions();
    if (bool)
    {
      adjustAllItems();
      setSelectionFromTop(paramInt, adjustScroll(paramInt, paramView, i, j) + paramView.getTop() - getPaddingTop());
      layoutChildren();
    }
    if ((bool) || (paramBoolean))
      invalidate();
    this.mBlockLayoutRequests = false;
  }

  private void doDragFloatView(boolean paramBoolean)
  {
    int i = getFirstVisiblePosition() + getChildCount() / 2;
    View localView = getChildAt(getChildCount() / 2);
    if (localView == null)
      return;
    doDragFloatView(i, localView, paramBoolean);
  }

  private void doRemoveItem()
  {
    doRemoveItem(this.mSrcPos - getHeaderViewsCount());
  }

  private void doRemoveItem(int paramInt)
  {
    this.mDragState = 1;
    if (this.mRemoveListener != null)
      this.mRemoveListener.remove(paramInt);
    destroyFloatView();
    adjustOnReorder();
    clearPositions();
    if (this.mInTouchEvent)
    {
      this.mDragState = 3;
      return;
    }
    this.mDragState = 0;
  }

  private void drawDivider(int paramInt, Canvas paramCanvas)
  {
    Drawable localDrawable = getDivider();
    int i = getDividerHeight();
    ViewGroup localViewGroup;
    int j;
    int k;
    int m;
    int i1;
    int n;
    if ((localDrawable != null) && (i != 0))
    {
      localViewGroup = (ViewGroup)getChildAt(paramInt - getFirstVisiblePosition());
      if (localViewGroup != null)
      {
        j = getPaddingLeft();
        k = getWidth() - getPaddingRight();
        m = localViewGroup.getChildAt(0).getHeight();
        if (paramInt <= this.mSrcPos)
          break label133;
        i1 = m + localViewGroup.getTop();
        n = i1 + i;
      }
    }
    while (true)
    {
      paramCanvas.save();
      paramCanvas.clipRect(j, i1, k, n);
      localDrawable.setBounds(j, i1, k, n);
      localDrawable.draw(paramCanvas);
      paramCanvas.restore();
      return;
      label133: n = localViewGroup.getBottom() - m;
      i1 = n - i;
    }
  }

  private void dropFloatView()
  {
    this.mDragState = 2;
    if ((this.mDropListener != null) && (this.mFloatPos >= 0) && (this.mFloatPos < getCount()))
    {
      int i = getHeaderViewsCount();
      this.mDropListener.drop(this.mSrcPos - i, this.mFloatPos - i);
    }
    destroyFloatView();
    adjustOnReorder();
    clearPositions();
    adjustAllItems();
    if (this.mInTouchEvent)
    {
      this.mDragState = 3;
      return;
    }
    this.mDragState = 0;
  }

  private static int findFirstSetIndex(SparseBooleanArray paramSparseBooleanArray, int paramInt1, int paramInt2)
  {
    int i = paramSparseBooleanArray.size();
    for (int j = insertionIndexForKey(paramSparseBooleanArray, paramInt1); (j < i) && (paramSparseBooleanArray.keyAt(j) < paramInt2) && (!paramSparseBooleanArray.valueAt(j)); j++);
    if ((j == i) || (paramSparseBooleanArray.keyAt(j) >= paramInt2))
      j = -1;
    return j;
  }

  private int getChildHeight(int paramInt)
  {
    int i = this.mSrcPos;
    int j = 0;
    if (paramInt == i);
    do
    {
      return j;
      View localView1 = getChildAt(paramInt - getFirstVisiblePosition());
      if (localView1 != null)
        return getChildHeight(paramInt, localView1, false);
      j = this.mChildHeightCache.get(paramInt);
    }
    while (j != -1);
    ListAdapter localListAdapter = getAdapter();
    int k = localListAdapter.getItemViewType(paramInt);
    int m = localListAdapter.getViewTypeCount();
    if (m != this.mSampleViewTypes.length)
      this.mSampleViewTypes = new View[m];
    View localView2;
    if (k >= 0)
      if (this.mSampleViewTypes[k] == null)
      {
        localView2 = localListAdapter.getView(paramInt, null, this);
        this.mSampleViewTypes[k] = localView2;
      }
    while (true)
    {
      int n = getChildHeight(paramInt, localView2, true);
      this.mChildHeightCache.add(paramInt, n);
      return n;
      localView2 = localListAdapter.getView(paramInt, this.mSampleViewTypes[k], this);
      continue;
      localView2 = localListAdapter.getView(paramInt, null, this);
    }
  }

  private int getChildHeight(int paramInt, View paramView, boolean paramBoolean)
  {
    int i = this.mSrcPos;
    int j = 0;
    if (paramInt == i);
    View localView;
    do
    {
      return j;
      if ((paramInt < getHeaderViewsCount()) || (paramInt >= getCount() - getFooterViewsCount()));
      for (localView = paramView; ; localView = ((ViewGroup)paramView).getChildAt(0))
      {
        ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
        if ((localLayoutParams == null) || (localLayoutParams.height <= 0))
          break;
        return localLayoutParams.height;
      }
      j = localView.getHeight();
    }
    while ((j != 0) && (!paramBoolean));
    measureItem(localView);
    return localView.getMeasuredHeight();
  }

  private int getItemHeight(int paramInt)
  {
    View localView = getChildAt(paramInt - getFirstVisiblePosition());
    if (localView != null)
      return localView.getHeight();
    return calcItemHeight(paramInt, getChildHeight(paramInt));
  }

  private int getShuffleEdge(int paramInt1, int paramInt2)
  {
    int i = getHeaderViewsCount();
    int j = getFooterViewsCount();
    if ((paramInt1 <= i) || (paramInt1 >= getCount() - j))
      return paramInt2;
    int k = getDividerHeight();
    int m = this.mFloatViewHeight - this.mItemHeightCollapsed;
    int n = getChildHeight(paramInt1);
    int i1 = getItemHeight(paramInt1);
    int i2 = paramInt2;
    if (this.mSecondExpPos <= this.mSrcPos)
      if ((paramInt1 == this.mSecondExpPos) && (this.mFirstExpPos != this.mSecondExpPos))
        if (paramInt1 == this.mSrcPos)
          i2 = paramInt2 + i1 - this.mFloatViewHeight;
    while (paramInt1 <= this.mSrcPos)
    {
      return i2 + (this.mFloatViewHeight - k - getChildHeight(paramInt1 - 1)) / 2;
      i2 = paramInt2 + (i1 - n) - m;
      continue;
      if ((paramInt1 <= this.mSecondExpPos) || (paramInt1 > this.mSrcPos))
        continue;
      i2 = paramInt2 - m;
      continue;
      if ((paramInt1 > this.mSrcPos) && (paramInt1 <= this.mFirstExpPos))
      {
        i2 = paramInt2 + m;
        continue;
      }
      if ((paramInt1 != this.mSecondExpPos) || (this.mFirstExpPos == this.mSecondExpPos))
        continue;
      i2 = paramInt2 + (i1 - n);
    }
    return i2 + (n - k - this.mFloatViewHeight) / 2;
  }

  private static int insertionIndexForKey(SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    int i = 0;
    int j = paramSparseBooleanArray.size();
    while (j - i > 0)
    {
      int k = i + j >> 1;
      if (paramSparseBooleanArray.keyAt(k) < paramInt)
      {
        i = k + 1;
        continue;
      }
      j = k;
    }
    return i;
  }

  private void invalidateFloatView()
  {
    this.mFloatViewInvalidated = true;
  }

  private void measureFloatView()
  {
    if (this.mFloatView != null)
    {
      measureItem(this.mFloatView);
      this.mFloatViewHeight = this.mFloatView.getMeasuredHeight();
      this.mFloatViewHeightHalf = (this.mFloatViewHeight / 2);
    }
  }

  private void measureItem(View paramView)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null)
    {
      localObject = new AbsListView.LayoutParams(-1, -2);
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    int i = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, getListPaddingLeft() + getListPaddingRight(), ((ViewGroup.LayoutParams)localObject).width);
    if (((ViewGroup.LayoutParams)localObject).height > 0);
    for (int j = View.MeasureSpec.makeMeasureSpec(((ViewGroup.LayoutParams)localObject).height, 1073741824); ; j = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i, j);
      return;
    }
  }

  private void printPosData()
  {
    Log.d("mobeta", "mSrcPos=" + this.mSrcPos + " mFirstExpPos=" + this.mFirstExpPos + " mSecondExpPos=" + this.mSecondExpPos);
  }

  private static int rotate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt4 - paramInt3;
    int j = paramInt1 + paramInt2;
    if (j < paramInt3)
      j += i;
    do
      return j;
    while (j < paramInt4);
    return j - i;
  }

  private void saveTouchCoords(MotionEvent paramMotionEvent)
  {
    int i = 0xFF & paramMotionEvent.getAction();
    if (i != 0)
    {
      this.mLastX = this.mX;
      this.mLastY = this.mY;
    }
    this.mX = (int)paramMotionEvent.getX();
    this.mY = (int)paramMotionEvent.getY();
    if (i == 0)
    {
      this.mLastX = this.mX;
      this.mLastY = this.mY;
    }
    this.mOffsetX = ((int)paramMotionEvent.getRawX() - this.mX);
    this.mOffsetY = ((int)paramMotionEvent.getRawY() - this.mY);
  }

  private void updateFloatView()
  {
    if (this.mFloatViewManager != null)
    {
      this.mTouchLoc.set(this.mX, this.mY);
      this.mFloatViewManager.onDragFloatView(this.mFloatView, this.mFloatLoc, this.mTouchLoc);
    }
    int i = this.mFloatLoc.x;
    int j = this.mFloatLoc.y;
    int k = getPaddingLeft();
    int i4;
    if (((0x1 & this.mDragFlags) == 0) && (i > k))
    {
      this.mFloatLoc.x = k;
      int m = getHeaderViewsCount();
      int n = getFooterViewsCount();
      int i1 = getFirstVisiblePosition();
      int i2 = getLastVisiblePosition();
      int i3 = getPaddingTop();
      if (i1 < m)
        i3 = getChildAt(-1 + (m - i1)).getBottom();
      if (((0x8 & this.mDragFlags) == 0) && (i1 <= this.mSrcPos))
        i3 = Math.max(getChildAt(this.mSrcPos - i1).getTop(), i3);
      i4 = getHeight() - getPaddingBottom();
      if (i2 >= -1 + (getCount() - n))
        i4 = getChildAt(-1 + (getCount() - n) - i1).getBottom();
      if (((0x4 & this.mDragFlags) == 0) && (i2 >= this.mSrcPos))
        i4 = Math.min(getChildAt(this.mSrcPos - i1).getBottom(), i4);
      if (j >= i3)
        break label321;
      this.mFloatLoc.y = i3;
    }
    while (true)
    {
      this.mFloatViewMid = (this.mFloatLoc.y + this.mFloatViewHeightHalf);
      return;
      if (((0x2 & this.mDragFlags) != 0) || (i >= k))
        break;
      this.mFloatLoc.x = k;
      break;
      label321: if (j + this.mFloatViewHeight <= i4)
        continue;
      this.mFloatLoc.y = (i4 - this.mFloatViewHeight);
    }
  }

  private boolean updatePositions()
  {
    int i = getFirstVisiblePosition();
    int j = this.mFirstExpPos;
    View localView = getChildAt(j - i);
    if (localView == null)
    {
      j = i + getChildCount() / 2;
      localView = getChildAt(j - i);
    }
    int k = localView.getTop();
    int m = localView.getHeight();
    int n = getShuffleEdge(j, k);
    int i1 = n;
    int i2 = getDividerHeight();
    int i3 = j;
    int i4 = k;
    int i17;
    label117: int i6;
    int i7;
    int i8;
    int i9;
    float f1;
    int i13;
    int i12;
    label181: float f2;
    int i16;
    if (this.mFloatViewMid < n)
      if (i3 >= 0)
      {
        i3--;
        i17 = getItemHeight(i3);
        if (i3 == 0)
          n = i4 - i2 - i17;
      }
      else
      {
        i6 = getHeaderViewsCount();
        i7 = getFooterViewsCount();
        i8 = this.mFirstExpPos;
        i9 = this.mSecondExpPos;
        f1 = this.mSlideFrac;
        if (!this.mAnimate)
          break label561;
        int i11 = Math.abs(n - i1);
        if (this.mFloatViewMid >= n)
          break label489;
        i13 = n;
        i12 = i1;
        int i14 = (int)(0.5F * this.mSlideRegionFrac * i11);
        f2 = i14;
        int i15 = i12 + i14;
        i16 = i13 - i14;
        if (this.mFloatViewMid >= i15)
          break label500;
        this.mFirstExpPos = (i3 - 1);
        this.mSecondExpPos = i3;
        this.mSlideFrac = (0.5F * (i15 - this.mFloatViewMid) / f2);
        label255: if (this.mFirstExpPos >= i6)
          break label576;
        i3 = i6;
        this.mFirstExpPos = i3;
        this.mSecondExpPos = i3;
      }
    while (true)
    {
      int i10;
      if ((this.mFirstExpPos == i8) && (this.mSecondExpPos == i9))
      {
        boolean bool = this.mSlideFrac < f1;
        i10 = 0;
        if (!bool);
      }
      else
      {
        i10 = 1;
      }
      if (i3 != this.mFloatPos)
      {
        if (this.mDragListener != null)
          this.mDragListener.drag(this.mFloatPos - i6, i3 - i6);
        this.mFloatPos = i3;
        i10 = 1;
      }
      return i10;
      i4 -= i17 + i2;
      n = getShuffleEdge(i3, i4);
      if (this.mFloatViewMid >= n)
        break label117;
      i1 = n;
      break;
      int i5 = getCount();
      while (true)
      {
        if (i3 >= i5)
          break label487;
        if (i3 == i5 - 1)
        {
          n = m + (i4 + i2);
          break;
        }
        i4 += i2 + m;
        m = getItemHeight(i3 + 1);
        n = getShuffleEdge(i3 + 1, i4);
        if (this.mFloatViewMid < n)
          break;
        i1 = n;
        i3++;
      }
      label487: break label117;
      label489: i12 = n;
      i13 = i1;
      break label181;
      label500: if (this.mFloatViewMid < i16)
      {
        this.mFirstExpPos = i3;
        this.mSecondExpPos = i3;
        break label255;
      }
      this.mFirstExpPos = i3;
      this.mSecondExpPos = (i3 + 1);
      this.mSlideFrac = (0.5F * (1.0F + (i13 - this.mFloatViewMid) / f2));
      break label255;
      label561: this.mFirstExpPos = i3;
      this.mSecondExpPos = i3;
      break label255;
      label576: if (this.mSecondExpPos < getCount() - i7)
        continue;
      i3 = -1 + (getCount() - i7);
      this.mFirstExpPos = i3;
      this.mSecondExpPos = i3;
    }
  }

  private void updateScrollStarts()
  {
    int i = getPaddingTop();
    int j = getHeight() - i - getPaddingBottom();
    float f = j;
    this.mUpScrollStartYF = (i + f * this.mDragUpScrollStartFrac);
    this.mDownScrollStartYF = (i + f * (1.0F - this.mDragDownScrollStartFrac));
    this.mUpScrollStartY = (int)this.mUpScrollStartYF;
    this.mDownScrollStartY = (int)this.mDownScrollStartYF;
    this.mDragUpScrollHeight = (this.mUpScrollStartYF - i);
    this.mDragDownScrollHeight = (i + j - this.mDownScrollStartYF);
  }

  public void cancelDrag()
  {
    if (this.mDragState == 4)
    {
      this.mDragScroller.stopScrolling(true);
      destroyFloatView();
      clearPositions();
      adjustAllItems();
      if (this.mInTouchEvent)
        this.mDragState = 3;
    }
    else
    {
      return;
    }
    this.mDragState = 0;
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if (this.mDragState != 0)
    {
      if (this.mFirstExpPos != this.mSrcPos)
        drawDivider(this.mFirstExpPos, paramCanvas);
      if ((this.mSecondExpPos != this.mFirstExpPos) && (this.mSecondExpPos != this.mSrcPos))
        drawDivider(this.mSecondExpPos, paramCanvas);
    }
    int i;
    int j;
    float f1;
    if (this.mFloatView != null)
    {
      i = this.mFloatView.getWidth();
      j = this.mFloatView.getHeight();
      int k = this.mFloatLoc.x;
      int m = getWidth();
      if (k < 0)
        k = -k;
      if (k >= m)
        break label217;
      float f2 = (m - k) / m;
      f1 = f2 * f2;
    }
    while (true)
    {
      int n = (int)(f1 * (255.0F * this.mCurrFloatAlpha));
      paramCanvas.save();
      paramCanvas.translate(this.mFloatLoc.x, this.mFloatLoc.y);
      paramCanvas.clipRect(0, 0, i, j);
      paramCanvas.saveLayerAlpha(0.0F, 0.0F, i, j, n, 31);
      this.mFloatView.draw(paramCanvas);
      paramCanvas.restore();
      paramCanvas.restore();
      return;
      label217: f1 = 0.0F;
    }
  }

  public float getFloatAlpha()
  {
    return this.mCurrFloatAlpha;
  }

  public ListAdapter getInputAdapter()
  {
    if (this.mAdapterWrapper == null)
      return null;
    return this.mAdapterWrapper.getAdapter();
  }

  public boolean isDragEnabled()
  {
    return this.mDragEnabled;
  }

  protected void layoutChildren()
  {
    super.layoutChildren();
    if (this.mFloatView != null)
    {
      if ((this.mFloatView.isLayoutRequested()) && (!this.mFloatViewOnMeasured))
        measureFloatView();
      this.mFloatView.layout(0, 0, this.mFloatView.getMeasuredWidth(), this.mFloatView.getMeasuredHeight());
      this.mFloatViewOnMeasured = false;
    }
  }

  public boolean listViewIntercepted()
  {
    return this.mListViewIntercepted;
  }

  public void moveCheckState(int paramInt1, int paramInt2)
  {
    SparseBooleanArray localSparseBooleanArray = getCheckedItemPositions();
    int i = paramInt1;
    int j = paramInt2;
    if (paramInt2 < paramInt1)
    {
      i = paramInt2;
      j = paramInt1;
    }
    int k = j + 1;
    int[] arrayOfInt1 = new int[localSparseBooleanArray.size()];
    int[] arrayOfInt2 = new int[localSparseBooleanArray.size()];
    int m = buildRunList(localSparseBooleanArray, i, k, arrayOfInt1, arrayOfInt2);
    if ((m == 1) && (arrayOfInt1[0] == arrayOfInt2[0]));
    while (true)
    {
      return;
      if (paramInt1 < paramInt2)
      {
        for (int i1 = 0; i1 != m; i1++)
        {
          setItemChecked(rotate(arrayOfInt1[i1], -1, i, k), true);
          setItemChecked(rotate(arrayOfInt2[i1], -1, i, k), false);
        }
        continue;
      }
      for (int n = 0; n != m; n++)
      {
        setItemChecked(arrayOfInt1[n], false);
        setItemChecked(arrayOfInt2[n], true);
      }
    }
  }

  public void moveItem(int paramInt1, int paramInt2)
  {
    if (this.mDropListener != null)
    {
      int i = getInputAdapter().getCount();
      if ((paramInt1 >= 0) && (paramInt1 < i) && (paramInt2 >= 0) && (paramInt2 < i))
        this.mDropListener.drop(paramInt1, paramInt2);
    }
  }

  protected boolean onDragTouchEvent(MotionEvent paramMotionEvent)
  {
    (0xFF & paramMotionEvent.getAction());
    switch (0xFF & paramMotionEvent.getAction())
    {
    default:
    case 3:
    case 1:
    case 2:
    }
    while (true)
    {
      return true;
      if (this.mDragState == 4)
        cancelDrag();
      doActionUpOrCancel();
      continue;
      if (this.mDragState == 4)
        stopDrag(false);
      doActionUpOrCancel();
      continue;
      continueDrag((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mTrackDragSort)
      this.mDragSortTracker.appendState();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2;
    if (!this.mDragEnabled)
      bool2 = super.onInterceptTouchEvent(paramMotionEvent);
    while (true)
    {
      return bool2;
      saveTouchCoords(paramMotionEvent);
      this.mLastCallWasIntercept = true;
      int i = 0xFF & paramMotionEvent.getAction();
      if (i == 0)
      {
        if (this.mDragState != 0)
        {
          this.mIgnoreTouchEvent = true;
          return true;
        }
        this.mInTouchEvent = true;
      }
      if (this.mFloatView != null)
        bool2 = true;
      while ((i == 1) || (i == 3))
      {
        this.mInTouchEvent = false;
        return bool2;
        boolean bool1 = super.onInterceptTouchEvent(paramMotionEvent);
        bool2 = false;
        if (bool1)
        {
          this.mListViewIntercepted = true;
          bool2 = true;
        }
        switch (i)
        {
        case 2:
        default:
          if (bool2)
            this.mCancelMethod = 1;
          break;
        case 1:
        case 3:
          doActionUpOrCancel();
          continue;
          this.mCancelMethod = 2;
        }
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mFloatView != null)
    {
      if (this.mFloatView.isLayoutRequested())
        measureFloatView();
      this.mFloatViewOnMeasured = true;
    }
    this.mWidthMeasureSpec = paramInt1;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    updateScrollStarts();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int j;
    if (this.mIgnoreTouchEvent)
    {
      this.mIgnoreTouchEvent = false;
      j = 0;
    }
    do
    {
      return j;
      if (!this.mDragEnabled)
        return super.onTouchEvent(paramMotionEvent);
      boolean bool1 = this.mLastCallWasIntercept;
      this.mLastCallWasIntercept = false;
      if (!bool1)
        saveTouchCoords(paramMotionEvent);
      if (this.mDragState == 4)
      {
        onDragTouchEvent(paramMotionEvent);
        return true;
      }
      int i = this.mDragState;
      j = 0;
      if (i == 0)
      {
        boolean bool2 = super.onTouchEvent(paramMotionEvent);
        j = 0;
        if (bool2)
          j = 1;
      }
      switch (0xFF & paramMotionEvent.getAction())
      {
      case 2:
      default:
      case 1:
      case 3:
      }
    }
    while (j == 0);
    this.mCancelMethod = 1;
    return j;
    doActionUpOrCancel();
    return j;
  }

  public void removeCheckState(int paramInt)
  {
    SparseBooleanArray localSparseBooleanArray = getCheckedItemPositions();
    if (localSparseBooleanArray.size() == 0);
    while (true)
    {
      return;
      int[] arrayOfInt1 = new int[localSparseBooleanArray.size()];
      int[] arrayOfInt2 = new int[localSparseBooleanArray.size()];
      int i = 1 + localSparseBooleanArray.keyAt(-1 + localSparseBooleanArray.size());
      int j = buildRunList(localSparseBooleanArray, paramInt, i, arrayOfInt1, arrayOfInt2);
      for (int k = 0; k != j; k++)
      {
        if ((arrayOfInt1[k] != paramInt) && ((arrayOfInt2[k] >= arrayOfInt1[k]) || (arrayOfInt2[k] <= paramInt)))
          setItemChecked(rotate(arrayOfInt1[k], -1, paramInt, i), true);
        setItemChecked(rotate(arrayOfInt2[k], -1, paramInt, i), false);
      }
    }
  }

  public void removeItem(int paramInt)
  {
    this.mUseRemoveVelocity = false;
    removeItem(paramInt, 0.0F);
  }

  public void removeItem(int paramInt, float paramFloat)
  {
    if ((this.mDragState == 0) || (this.mDragState == 4))
    {
      if (this.mDragState == 0)
      {
        this.mSrcPos = (paramInt + getHeaderViewsCount());
        this.mFirstExpPos = this.mSrcPos;
        this.mSecondExpPos = this.mSrcPos;
        this.mFloatPos = this.mSrcPos;
        View localView = getChildAt(this.mSrcPos - getFirstVisiblePosition());
        if (localView != null)
          localView.setVisibility(4);
      }
      this.mDragState = 1;
      this.mRemoveVelocityX = paramFloat;
      if (this.mInTouchEvent)
        switch (this.mCancelMethod)
        {
        default:
        case 1:
        case 2:
        }
    }
    while (this.mRemoveAnimator != null)
    {
      this.mRemoveAnimator.start();
      return;
      super.onTouchEvent(this.mCancelEvent);
      continue;
      super.onInterceptTouchEvent(this.mCancelEvent);
    }
    doRemoveItem(paramInt);
  }

  public void requestLayout()
  {
    if (!this.mBlockLayoutRequests)
      super.requestLayout();
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (paramListAdapter != null)
    {
      this.mAdapterWrapper = new AdapterWrapper(paramListAdapter);
      paramListAdapter.registerDataSetObserver(this.mObserver);
      if ((paramListAdapter instanceof DropListener))
        setDropListener((DropListener)paramListAdapter);
      if ((paramListAdapter instanceof DragListener))
        setDragListener((DragListener)paramListAdapter);
      if ((paramListAdapter instanceof RemoveListener))
        setRemoveListener((RemoveListener)paramListAdapter);
    }
    while (true)
    {
      super.setAdapter(this.mAdapterWrapper);
      return;
      this.mAdapterWrapper = null;
    }
  }

  public void setDragEnabled(boolean paramBoolean)
  {
    this.mDragEnabled = paramBoolean;
  }

  public void setDragListener(DragListener paramDragListener)
  {
    this.mDragListener = paramDragListener;
  }

  public void setDragScrollProfile(DragScrollProfile paramDragScrollProfile)
  {
    if (paramDragScrollProfile != null)
      this.mScrollProfile = paramDragScrollProfile;
  }

  public void setDragScrollStart(float paramFloat)
  {
    setDragScrollStarts(paramFloat, paramFloat);
  }

  public void setDragScrollStarts(float paramFloat1, float paramFloat2)
  {
    if (paramFloat2 > 0.5F)
    {
      this.mDragDownScrollStartFrac = 0.5F;
      if (paramFloat1 <= 0.5F)
        break label46;
    }
    label46: for (this.mDragUpScrollStartFrac = 0.5F; ; this.mDragUpScrollStartFrac = paramFloat1)
    {
      if (getHeight() != 0)
        updateScrollStarts();
      return;
      this.mDragDownScrollStartFrac = paramFloat2;
      break;
    }
  }

  public void setDragSortListener(DragSortListener paramDragSortListener)
  {
    setDropListener(paramDragSortListener);
    setDragListener(paramDragSortListener);
    setRemoveListener(paramDragSortListener);
  }

  public void setDropListener(DropListener paramDropListener)
  {
    this.mDropListener = paramDropListener;
  }

  public void setFloatAlpha(float paramFloat)
  {
    this.mCurrFloatAlpha = paramFloat;
  }

  public void setFloatViewManager(FloatViewManager paramFloatViewManager)
  {
    this.mFloatViewManager = paramFloatViewManager;
  }

  public void setMaxScrollSpeed(float paramFloat)
  {
    this.mMaxScrollSpeed = paramFloat;
  }

  public void setRemoveListener(RemoveListener paramRemoveListener)
  {
    this.mRemoveListener = paramRemoveListener;
  }

  public boolean startDrag(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((!this.mInTouchEvent) || (this.mFloatViewManager == null));
    View localView;
    do
    {
      return false;
      localView = this.mFloatViewManager.onCreateFloatView(paramInt1);
    }
    while (localView == null);
    return startDrag(paramInt1, localView, paramInt2, paramInt3, paramInt4);
  }

  public boolean startDrag(int paramInt1, View paramView, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = true;
    if ((this.mDragState != 0) || (!this.mInTouchEvent) || (this.mFloatView != null) || (paramView == null) || (!this.mDragEnabled))
    {
      bool = false;
      return bool;
    }
    if (getParent() != null)
      getParent().requestDisallowInterceptTouchEvent(bool);
    int i = paramInt1 + getHeaderViewsCount();
    this.mFirstExpPos = i;
    this.mSecondExpPos = i;
    this.mSrcPos = i;
    this.mFloatPos = i;
    this.mDragState = 4;
    this.mDragFlags = 0;
    this.mDragFlags = (paramInt2 | this.mDragFlags);
    this.mFloatView = paramView;
    measureFloatView();
    this.mDragDeltaX = paramInt3;
    this.mDragDeltaY = paramInt4;
    this.mDragStartY = this.mY;
    this.mFloatLoc.x = (this.mX - this.mDragDeltaX);
    this.mFloatLoc.y = (this.mY - this.mDragDeltaY);
    View localView = getChildAt(this.mSrcPos - getFirstVisiblePosition());
    if (localView != null)
      localView.setVisibility(4);
    if (this.mTrackDragSort)
      this.mDragSortTracker.startTracking();
    switch (this.mCancelMethod)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      requestLayout();
      if (this.mLiftAnimator == null)
        break;
      this.mLiftAnimator.start();
      return bool;
      super.onTouchEvent(this.mCancelEvent);
      continue;
      super.onInterceptTouchEvent(this.mCancelEvent);
    }
  }

  public boolean stopDrag(boolean paramBoolean)
  {
    this.mUseRemoveVelocity = false;
    return stopDrag(paramBoolean, 0.0F);
  }

  public boolean stopDrag(boolean paramBoolean, float paramFloat)
  {
    if (this.mFloatView != null)
    {
      this.mDragScroller.stopScrolling(true);
      if (paramBoolean)
        removeItem(this.mSrcPos - getHeaderViewsCount(), paramFloat);
      while (true)
      {
        if (this.mTrackDragSort)
          this.mDragSortTracker.stopTracking();
        return true;
        if (this.mDropAnimator != null)
        {
          this.mDropAnimator.start();
          continue;
        }
        dropFloatView();
      }
    }
    return false;
  }

  public boolean stopDragWithVelocity(boolean paramBoolean, float paramFloat)
  {
    this.mUseRemoveVelocity = true;
    return stopDrag(paramBoolean, paramFloat);
  }

  private class AdapterWrapper extends BaseAdapter
  {
    private ListAdapter mAdapter;

    public AdapterWrapper(ListAdapter arg2)
    {
      Object localObject;
      this.mAdapter = localObject;
      this.mAdapter.registerDataSetObserver(new DataSetObserver(DragSortListView.this)
      {
        public void onChanged()
        {
          DragSortListView.AdapterWrapper.this.notifyDataSetChanged();
        }

        public void onInvalidated()
        {
          DragSortListView.AdapterWrapper.this.notifyDataSetInvalidated();
        }
      });
    }

    public boolean areAllItemsEnabled()
    {
      return this.mAdapter.areAllItemsEnabled();
    }

    public ListAdapter getAdapter()
    {
      return this.mAdapter;
    }

    public int getCount()
    {
      return this.mAdapter.getCount();
    }

    public Object getItem(int paramInt)
    {
      return this.mAdapter.getItem(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return this.mAdapter.getItemId(paramInt);
    }

    public int getItemViewType(int paramInt)
    {
      return this.mAdapter.getItemViewType(paramInt);
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView != null)
      {
        localObject = (DragSortItemView)paramView;
        View localView2 = ((DragSortItemView)localObject).getChildAt(0);
        View localView3 = this.mAdapter.getView(paramInt, localView2, DragSortListView.this);
        if (localView3 != localView2)
        {
          if (localView2 != null)
            ((DragSortItemView)localObject).removeViewAt(0);
          ((DragSortItemView)localObject).addView(localView3);
        }
        DragSortListView.this.adjustItem(paramInt + DragSortListView.this.getHeaderViewsCount(), (View)localObject, true);
        return localObject;
      }
      View localView1 = this.mAdapter.getView(paramInt, null, DragSortListView.this);
      if ((localView1 instanceof Checkable));
      for (Object localObject = new DragSortItemViewCheckable(DragSortListView.this.getContext()); ; localObject = new DragSortItemView(DragSortListView.this.getContext()))
      {
        ((DragSortItemView)localObject).setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        ((DragSortItemView)localObject).addView(localView1);
        break;
      }
    }

    public int getViewTypeCount()
    {
      return this.mAdapter.getViewTypeCount();
    }

    public boolean hasStableIds()
    {
      return this.mAdapter.hasStableIds();
    }

    public boolean isEmpty()
    {
      return this.mAdapter.isEmpty();
    }

    public boolean isEnabled(int paramInt)
    {
      return this.mAdapter.isEnabled(paramInt);
    }
  }

  public static abstract interface DragListener
  {
    public abstract void drag(int paramInt1, int paramInt2);
  }

  public static abstract interface DragScrollProfile
  {
    public abstract float getSpeed(float paramFloat, long paramLong);
  }

  private class DragScroller
    implements Runnable
  {
    public static final int DOWN = 1;
    public static final int STOP = -1;
    public static final int UP;
    private float dt;
    private int dy;
    private boolean mAbort;
    private long mCurrTime;
    private int mFirstFooter;
    private int mLastHeader;
    private long mPrevTime;
    private float mScrollSpeed;
    private boolean mScrolling = false;
    private int scrollDir;
    private long tStart;

    public DragScroller()
    {
    }

    public int getScrollDir()
    {
      if (this.mScrolling)
        return this.scrollDir;
      return -1;
    }

    public boolean isScrolling()
    {
      return this.mScrolling;
    }

    public void run()
    {
      if (this.mAbort)
      {
        this.mScrolling = false;
        return;
      }
      int i = DragSortListView.this.getFirstVisiblePosition();
      int j = DragSortListView.this.getLastVisiblePosition();
      int k = DragSortListView.this.getCount();
      int m = DragSortListView.this.getPaddingTop();
      int n = DragSortListView.this.getHeight() - m - DragSortListView.this.getPaddingBottom();
      int i1 = Math.min(DragSortListView.this.mY, DragSortListView.this.mFloatViewMid + DragSortListView.this.mFloatViewHeightHalf);
      int i2 = Math.max(DragSortListView.this.mY, DragSortListView.this.mFloatViewMid - DragSortListView.this.mFloatViewHeightHalf);
      if (this.scrollDir == 0)
      {
        View localView3 = DragSortListView.this.getChildAt(0);
        if (localView3 == null)
        {
          this.mScrolling = false;
          return;
        }
        if ((i == 0) && (localView3.getTop() == m))
        {
          this.mScrolling = false;
          return;
        }
        this.mScrollSpeed = DragSortListView.this.mScrollProfile.getSpeed((DragSortListView.this.mUpScrollStartYF - i2) / DragSortListView.this.mDragUpScrollHeight, this.mPrevTime);
        this.mCurrTime = SystemClock.uptimeMillis();
        this.dt = (float)(this.mCurrTime - this.mPrevTime);
        this.dy = Math.round(this.mScrollSpeed * this.dt);
        if (this.dy < 0)
          break label476;
        this.dy = Math.min(n, this.dy);
      }
      for (int i3 = i; ; i3 = j)
      {
        View localView2 = DragSortListView.this.getChildAt(i3 - i);
        int i4 = localView2.getTop() + this.dy;
        if ((i3 == 0) && (i4 > m))
          i4 = m;
        DragSortListView.access$2602(DragSortListView.this, true);
        DragSortListView.this.setSelectionFromTop(i3, i4 - m);
        DragSortListView.this.layoutChildren();
        DragSortListView.this.invalidate();
        DragSortListView.access$2602(DragSortListView.this, false);
        DragSortListView.this.doDragFloatView(i3, localView2, false);
        this.mPrevTime = this.mCurrTime;
        DragSortListView.this.post(this);
        return;
        View localView1 = DragSortListView.this.getChildAt(j - i);
        if (localView1 == null)
        {
          this.mScrolling = false;
          return;
        }
        if ((j == k - 1) && (localView1.getBottom() <= n + m))
        {
          this.mScrolling = false;
          return;
        }
        this.mScrollSpeed = (-DragSortListView.this.mScrollProfile.getSpeed((i1 - DragSortListView.this.mDownScrollStartYF) / DragSortListView.this.mDragDownScrollHeight, this.mPrevTime));
        break;
        label476: this.dy = Math.max(-n, this.dy);
      }
    }

    public void startScrolling(int paramInt)
    {
      if (!this.mScrolling)
      {
        this.mAbort = false;
        this.mScrolling = true;
        this.tStart = SystemClock.uptimeMillis();
        this.mPrevTime = this.tStart;
        this.scrollDir = paramInt;
        DragSortListView.this.post(this);
      }
    }

    public void stopScrolling(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        DragSortListView.this.removeCallbacks(this);
        this.mScrolling = false;
        return;
      }
      this.mAbort = true;
    }
  }

  public static abstract interface DragSortListener extends DragSortListView.DropListener, DragSortListView.DragListener, DragSortListView.RemoveListener
  {
  }

  private class DragSortTracker
  {
    StringBuilder mBuilder = new StringBuilder();
    File mFile = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
    private int mNumFlushes = 0;
    private int mNumInBuffer = 0;
    private boolean mTracking = false;

    public DragSortTracker()
    {
      if (!this.mFile.exists());
      try
      {
        this.mFile.createNewFile();
        Log.d("mobeta", "file created");
        return;
      }
      catch (IOException localIOException)
      {
        Log.w("mobeta", "Could not create dslv_state.txt");
        Log.d("mobeta", localIOException.getMessage());
      }
    }

    public void appendState()
    {
      if (!this.mTracking);
      do
      {
        return;
        this.mBuilder.append("<DSLVState>\n");
        int i = DragSortListView.this.getChildCount();
        int j = DragSortListView.this.getFirstVisiblePosition();
        this.mBuilder.append("    <Positions>");
        for (int k = 0; k < i; k++)
          this.mBuilder.append(j + k).append(",");
        this.mBuilder.append("</Positions>\n");
        this.mBuilder.append("    <Tops>");
        for (int m = 0; m < i; m++)
          this.mBuilder.append(DragSortListView.this.getChildAt(m).getTop()).append(",");
        this.mBuilder.append("</Tops>\n");
        this.mBuilder.append("    <Bottoms>");
        for (int n = 0; n < i; n++)
          this.mBuilder.append(DragSortListView.this.getChildAt(n).getBottom()).append(",");
        this.mBuilder.append("</Bottoms>\n");
        this.mBuilder.append("    <FirstExpPos>").append(DragSortListView.this.mFirstExpPos).append("</FirstExpPos>\n");
        this.mBuilder.append("    <FirstExpBlankHeight>").append(DragSortListView.this.getItemHeight(DragSortListView.this.mFirstExpPos) - DragSortListView.this.getChildHeight(DragSortListView.this.mFirstExpPos)).append("</FirstExpBlankHeight>\n");
        this.mBuilder.append("    <SecondExpPos>").append(DragSortListView.this.mSecondExpPos).append("</SecondExpPos>\n");
        this.mBuilder.append("    <SecondExpBlankHeight>").append(DragSortListView.this.getItemHeight(DragSortListView.this.mSecondExpPos) - DragSortListView.this.getChildHeight(DragSortListView.this.mSecondExpPos)).append("</SecondExpBlankHeight>\n");
        this.mBuilder.append("    <SrcPos>").append(DragSortListView.this.mSrcPos).append("</SrcPos>\n");
        this.mBuilder.append("    <SrcHeight>").append(DragSortListView.this.mFloatViewHeight + DragSortListView.this.getDividerHeight()).append("</SrcHeight>\n");
        this.mBuilder.append("    <ViewHeight>").append(DragSortListView.this.getHeight()).append("</ViewHeight>\n");
        this.mBuilder.append("    <LastY>").append(DragSortListView.this.mLastY).append("</LastY>\n");
        this.mBuilder.append("    <FloatY>").append(DragSortListView.this.mFloatViewMid).append("</FloatY>\n");
        this.mBuilder.append("    <ShuffleEdges>");
        for (int i1 = 0; i1 < i; i1++)
          this.mBuilder.append(DragSortListView.this.getShuffleEdge(j + i1, DragSortListView.this.getChildAt(i1).getTop())).append(",");
        this.mBuilder.append("</ShuffleEdges>\n");
        this.mBuilder.append("</DSLVState>\n");
        this.mNumInBuffer = (1 + this.mNumInBuffer);
      }
      while (this.mNumInBuffer <= 1000);
      flush();
      this.mNumInBuffer = 0;
    }

    public void flush()
    {
      if (!this.mTracking)
        return;
      boolean bool = true;
      try
      {
        if (this.mNumFlushes == 0)
          bool = false;
        FileWriter localFileWriter = new FileWriter(this.mFile, bool);
        localFileWriter.write(this.mBuilder.toString());
        this.mBuilder.delete(0, this.mBuilder.length());
        localFileWriter.flush();
        localFileWriter.close();
        this.mNumFlushes = (1 + this.mNumFlushes);
        return;
      }
      catch (IOException localIOException)
      {
      }
    }

    public void startTracking()
    {
      this.mBuilder.append("<DSLVStates>\n");
      this.mNumFlushes = 0;
      this.mTracking = true;
    }

    public void stopTracking()
    {
      if (this.mTracking)
      {
        this.mBuilder.append("</DSLVStates>\n");
        flush();
        this.mTracking = false;
      }
    }
  }

  private class DropAnimator extends DragSortListView.SmoothAnimator
  {
    private int mDropPos;
    private float mInitDeltaX;
    private float mInitDeltaY;
    private int srcPos;

    public DropAnimator(float paramInt, int arg3)
    {
      super(paramInt, i);
    }

    private int getTargetY()
    {
      int i = DragSortListView.this.getFirstVisiblePosition();
      int j = (DragSortListView.this.mItemHeightCollapsed + DragSortListView.this.getDividerHeight()) / 2;
      View localView = DragSortListView.this.getChildAt(this.mDropPos - i);
      if (localView != null)
      {
        if (this.mDropPos == this.srcPos)
          return localView.getTop();
        if (this.mDropPos < this.srcPos)
          return localView.getTop() - j;
        return j + localView.getBottom() - DragSortListView.this.mFloatViewHeight;
      }
      cancel();
      return -1;
    }

    public void onStart()
    {
      this.mDropPos = DragSortListView.this.mFloatPos;
      this.srcPos = DragSortListView.this.mSrcPos;
      DragSortListView.access$102(DragSortListView.this, 2);
      this.mInitDeltaY = (DragSortListView.this.mFloatLoc.y - getTargetY());
      this.mInitDeltaX = (DragSortListView.this.mFloatLoc.x - DragSortListView.this.getPaddingLeft());
    }

    public void onStop()
    {
      DragSortListView.this.dropFloatView();
    }

    public void onUpdate(float paramFloat1, float paramFloat2)
    {
      int i = getTargetY();
      int j = DragSortListView.this.getPaddingLeft();
      float f1 = DragSortListView.this.mFloatLoc.y - i;
      float f2 = DragSortListView.this.mFloatLoc.x - j;
      float f3 = 1.0F - paramFloat2;
      if ((f3 < Math.abs(f1 / this.mInitDeltaY)) || (f3 < Math.abs(f2 / this.mInitDeltaX)))
      {
        DragSortListView.this.mFloatLoc.y = (i + (int)(f3 * this.mInitDeltaY));
        DragSortListView.this.mFloatLoc.x = (DragSortListView.this.getPaddingLeft() + (int)(f3 * this.mInitDeltaX));
        DragSortListView.this.doDragFloatView(true);
      }
    }
  }

  public static abstract interface DropListener
  {
    public abstract void drop(int paramInt1, int paramInt2);
  }

  public static abstract interface FloatViewManager
  {
    public abstract View onCreateFloatView(int paramInt);

    public abstract void onDestroyFloatView(View paramView);

    public abstract void onDragFloatView(View paramView, Point paramPoint1, Point paramPoint2);
  }

  private class HeightCache
  {
    private SparseIntArray mMap;
    private int mMaxSize;
    private ArrayList<Integer> mOrder;

    public HeightCache(int arg2)
    {
      int i;
      this.mMap = new SparseIntArray(i);
      this.mOrder = new ArrayList(i);
      this.mMaxSize = i;
    }

    public void add(int paramInt1, int paramInt2)
    {
      int i = this.mMap.get(paramInt1, -1);
      if (i != paramInt2)
      {
        if (i != -1)
          break label77;
        if (this.mMap.size() == this.mMaxSize)
          this.mMap.delete(((Integer)this.mOrder.remove(0)).intValue());
      }
      while (true)
      {
        this.mMap.put(paramInt1, paramInt2);
        this.mOrder.add(Integer.valueOf(paramInt1));
        return;
        label77: this.mOrder.remove(Integer.valueOf(paramInt1));
      }
    }

    public void clear()
    {
      this.mMap.clear();
      this.mOrder.clear();
    }

    public int get(int paramInt)
    {
      return this.mMap.get(paramInt, -1);
    }
  }

  private class LiftAnimator extends DragSortListView.SmoothAnimator
  {
    private float mFinalDragDeltaY;
    private float mInitDragDeltaY;

    public LiftAnimator(float paramInt, int arg3)
    {
      super(paramInt, i);
    }

    public void onStart()
    {
      this.mInitDragDeltaY = DragSortListView.this.mDragDeltaY;
      this.mFinalDragDeltaY = DragSortListView.this.mFloatViewHeightHalf;
    }

    public void onUpdate(float paramFloat1, float paramFloat2)
    {
      if (DragSortListView.this.mDragState != 4)
      {
        cancel();
        return;
      }
      DragSortListView.access$302(DragSortListView.this, (int)(paramFloat2 * this.mFinalDragDeltaY + (1.0F - paramFloat2) * this.mInitDragDeltaY));
      DragSortListView.this.mFloatLoc.y = (DragSortListView.this.mY - DragSortListView.this.mDragDeltaY);
      DragSortListView.this.doDragFloatView(true);
    }
  }

  private class RemoveAnimator extends DragSortListView.SmoothAnimator
  {
    private int mFirstChildHeight = -1;
    private int mFirstPos;
    private float mFirstStartBlank;
    private float mFloatLocX;
    private int mSecondChildHeight = -1;
    private int mSecondPos;
    private float mSecondStartBlank;
    private int srcPos;

    public RemoveAnimator(float paramInt, int arg3)
    {
      super(paramInt, i);
    }

    public void onStart()
    {
      int i = -1;
      this.mFirstChildHeight = i;
      this.mSecondChildHeight = i;
      this.mFirstPos = DragSortListView.this.mFirstExpPos;
      this.mSecondPos = DragSortListView.this.mSecondExpPos;
      this.srcPos = DragSortListView.this.mSrcPos;
      DragSortListView.access$102(DragSortListView.this, 1);
      this.mFloatLocX = DragSortListView.this.mFloatLoc.x;
      if (DragSortListView.this.mUseRemoveVelocity)
      {
        float f1 = 2.0F * DragSortListView.this.getWidth();
        if (DragSortListView.this.mRemoveVelocityX == 0.0F)
        {
          DragSortListView localDragSortListView = DragSortListView.this;
          if (this.mFloatLocX < 0.0F)
            DragSortListView.access$1602(localDragSortListView, f1 * i);
        }
        float f2;
        do
        {
          return;
          i = 1;
          break;
          f2 = f1 * 2.0F;
          if ((DragSortListView.this.mRemoveVelocityX >= 0.0F) || (DragSortListView.this.mRemoveVelocityX <= -f2))
            continue;
          DragSortListView.access$1602(DragSortListView.this, -f2);
          return;
        }
        while ((DragSortListView.this.mRemoveVelocityX <= 0.0F) || (DragSortListView.this.mRemoveVelocityX >= f2));
        DragSortListView.access$1602(DragSortListView.this, f2);
        return;
      }
      DragSortListView.this.destroyFloatView();
    }

    public void onStop()
    {
      DragSortListView.this.doRemoveItem();
    }

    public void onUpdate(float paramFloat1, float paramFloat2)
    {
      float f1 = 1.0F - paramFloat2;
      int i = DragSortListView.this.getFirstVisiblePosition();
      View localView1 = DragSortListView.this.getChildAt(this.mFirstPos - i);
      float f2;
      if (DragSortListView.this.mUseRemoveVelocity)
      {
        f2 = (float)(SystemClock.uptimeMillis() - this.mStartTime) / 1000.0F;
        if (f2 != 0.0F);
      }
      View localView2;
      do
      {
        do
        {
          return;
          float f3 = f2 * DragSortListView.this.mRemoveVelocityX;
          int m = DragSortListView.this.getWidth();
          DragSortListView localDragSortListView = DragSortListView.this;
          float f4 = DragSortListView.this.mRemoveVelocityX;
          if (DragSortListView.this.mRemoveVelocityX > 0.0F);
          for (int n = 1; ; n = -1)
          {
            DragSortListView.access$1602(localDragSortListView, f4 + f2 * n * m);
            this.mFloatLocX = (f3 + this.mFloatLocX);
            DragSortListView.this.mFloatLoc.x = (int)this.mFloatLocX;
            if ((this.mFloatLocX >= m) || (this.mFloatLocX <= -m))
              break;
            this.mStartTime = SystemClock.uptimeMillis();
            DragSortListView.this.doDragFloatView(true);
            return;
          }
          if (localView1 == null)
            continue;
          if (this.mFirstChildHeight == -1)
          {
            this.mFirstChildHeight = DragSortListView.this.getChildHeight(this.mFirstPos, localView1, false);
            this.mFirstStartBlank = (localView1.getHeight() - this.mFirstChildHeight);
          }
          int k = Math.max((int)(f1 * this.mFirstStartBlank), 1);
          ViewGroup.LayoutParams localLayoutParams2 = localView1.getLayoutParams();
          localLayoutParams2.height = (k + this.mFirstChildHeight);
          localView1.setLayoutParams(localLayoutParams2);
        }
        while (this.mSecondPos == this.mFirstPos);
        localView2 = DragSortListView.this.getChildAt(this.mSecondPos - i);
      }
      while (localView2 == null);
      if (this.mSecondChildHeight == -1)
      {
        this.mSecondChildHeight = DragSortListView.this.getChildHeight(this.mSecondPos, localView2, false);
        this.mSecondStartBlank = (localView2.getHeight() - this.mSecondChildHeight);
      }
      int j = Math.max((int)(f1 * this.mSecondStartBlank), 1);
      ViewGroup.LayoutParams localLayoutParams1 = localView2.getLayoutParams();
      localLayoutParams1.height = (j + this.mSecondChildHeight);
      localView2.setLayoutParams(localLayoutParams1);
    }
  }

  public static abstract interface RemoveListener
  {
    public abstract void remove(int paramInt);
  }

  private class SmoothAnimator
    implements Runnable
  {
    private float mA;
    private float mAlpha;
    private float mB;
    private float mC;
    private boolean mCanceled;
    private float mD;
    private float mDurationF;
    protected long mStartTime;

    public SmoothAnimator(float paramInt, int arg3)
    {
      this.mAlpha = paramInt;
      int i;
      this.mDurationF = i;
      float f = 1.0F / (2.0F * this.mAlpha * (1.0F - this.mAlpha));
      this.mD = f;
      this.mA = f;
      this.mB = (this.mAlpha / (2.0F * (this.mAlpha - 1.0F)));
      this.mC = (1.0F / (1.0F - this.mAlpha));
    }

    public void cancel()
    {
      this.mCanceled = true;
    }

    public void onStart()
    {
    }

    public void onStop()
    {
    }

    public void onUpdate(float paramFloat1, float paramFloat2)
    {
    }

    public void run()
    {
      if (this.mCanceled)
        return;
      float f = (float)(SystemClock.uptimeMillis() - this.mStartTime) / this.mDurationF;
      if (f >= 1.0F)
      {
        onUpdate(1.0F, 1.0F);
        onStop();
        return;
      }
      onUpdate(f, transform(f));
      DragSortListView.this.post(this);
    }

    public void start()
    {
      this.mStartTime = SystemClock.uptimeMillis();
      this.mCanceled = false;
      onStart();
      DragSortListView.this.post(this);
    }

    public float transform(float paramFloat)
    {
      if (paramFloat < this.mAlpha)
        return paramFloat * (paramFloat * this.mA);
      if (paramFloat < 1.0F - this.mAlpha)
        return this.mB + paramFloat * this.mC;
      return 1.0F - this.mD * (paramFloat - 1.0F) * (paramFloat - 1.0F);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.dslv.DragSortListView
 * JD-Core Version:    0.6.0
 */