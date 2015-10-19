package com.yihuacomputer.fish.api.device;

/**
 * 布放位置类型
 *
 * @author 刘波
 * @E-mail liubo-nj@yihuacomputer
 * @date 2014-7-4
 */
public enum PlaceType
{
//	PRO_CAP=省会城市
//			PLA_CIT=地级市
//			COU_TOW=县城
//			VIL_TOW=乡镇
//			VILLAGE=行政（自然）村
	PRO_CAP(1, "PlaceType.PRO_CAP"), PLA_CIT(2, "PlaceType.PLA_CIT"), COU_TOW(3, "PlaceType.COU_TOW"), 
	VIL_TOW(4, "PlaceType.VIL_TOW"), VILLAGE(5, "PlaceType.VILLAGE");

    public String getText(){
		return text;
    }
    private int id;

    private String text;

    private PlaceType(int id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public static PlaceType getById(int id)
    {
        for (PlaceType each : PlaceType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
