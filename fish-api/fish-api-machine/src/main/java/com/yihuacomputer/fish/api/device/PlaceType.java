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
	PRO_CAP(1, "省会城市"), PLA_CIT(2, "地级市"), COU_TOW(3, "县城"), VIL_TOW(4, "乡镇"), VILLAGE(5, "行政（自然）村");

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

    public String getText()
    {
        return text;
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
