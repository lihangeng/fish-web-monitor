package com.yihuacomputer.fish.api.device;

/**
 * 设备关注程度
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 上午11:58:40
 */
public enum CareLevel
{
    EMPHASIS(1, "重点"), MEDIUM(2, "中等"), GENERAL(3, "一般");

    private int id;

    private String text;

    private CareLevel(int id, String text)
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

    public static CareLevel getById(int id)
    {
        for (CareLevel each : CareLevel.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
