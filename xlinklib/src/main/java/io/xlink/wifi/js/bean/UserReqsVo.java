package io.xlink.wifi.js.bean;

import java.util.List;

public class UserReqsVo
{
  private List<String> filter;
  private String limit = "10";
  private String offset = "0";
  private OrderEntity order;
  private QueryEntity query;

  public List<String> getFilter()
  {
    return this.filter;
  }

  public String getLimit()
  {
    return this.limit;
  }

  public String getOffset()
  {
    return this.offset;
  }

  public OrderEntity getOrder()
  {
    return this.order;
  }

  public QueryEntity getQuery()
  {
    return this.query;
  }

  public void setFilter(List<String> paramList)
  {
    this.filter = paramList;
  }

  public void setLimit(String paramString)
  {
    this.limit = paramString;
  }

  public void setOffset(String paramString)
  {
    this.offset = paramString;
  }

  public void setOrder(OrderEntity paramOrderEntity)
  {
    this.order = paramOrderEntity;
  }

  public void setQuery(QueryEntity paramQueryEntity)
  {
    this.query = paramQueryEntity;
  }

  public static class OrderEntity
  {
    private String filed1 = "desc";
    private String filed2 = "asc";

    public String getFiled1()
    {
      return this.filed1;
    }

    public String getFiled2()
    {
      return this.filed2;
    }

    public void setFiled1(String paramString)
    {
      this.filed1 = paramString;
    }

    public void setFiled2(String paramString)
    {
      this.filed2 = paramString;
    }
  }

  public static class QueryEntity
  {
    private Filed1Entity filed1;
    private Filed3Entity filed3;

    public Filed1Entity getFiled1()
    {
      return this.filed1;
    }

    public Filed3Entity getFiled3()
    {
      return this.filed3;
    }

    public void setFiled1(Filed1Entity paramFiled1Entity)
    {
      this.filed1 = paramFiled1Entity;
    }

    public void setFiled3(Filed3Entity paramFiled3Entity)
    {
      this.filed3 = paramFiled3Entity;
    }

    public static class Filed1Entity
    {
      private List<String> $in;

      public List<String> get$in()
      {
        return this.$in;
      }

      public void set$in(List<String> paramList)
      {
        this.$in = paramList;
      }
    }

    public static class Filed3Entity
    {
      private String $lt;

      public String get$lt()
      {
        return this.$lt;
      }

      public void set$lt(String paramString)
      {
        this.$lt = paramString;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.UserReqsVo
 * JD-Core Version:    0.6.0
 */