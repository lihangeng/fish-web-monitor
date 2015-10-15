package com.yihuacomputer.fish.api.device;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午12:16:51
 * @version 类说明
 */
public enum AwayFlag
{
    LINE(1, "AwayFlag.LINE"), FROM_THE_LINE(2, "AwayFlag.FROM_THE_LINE"), STAND_ALONE(3, "AwayFlag.STAND_ALONE");
    public String getText(){
			return text;
    }
    private int id;

    private String text;

    private AwayFlag(int id, String text)
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

    public static AwayFlag getById(int id)
    {
        for (AwayFlag each : AwayFlag.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
