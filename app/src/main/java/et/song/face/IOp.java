package et.song.face;

import et.song.db.ETDB;

public abstract interface IOp
{
  public abstract void Delete(ETDB paramETDB);

  public abstract int GetCount();

  public abstract Object GetItem(int paramInt);

  public abstract void Inster(ETDB paramETDB);

  public abstract void Load(ETDB paramETDB);

  public abstract void Update(ETDB paramETDB);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.face.IOp
 * JD-Core Version:    0.6.0
 */