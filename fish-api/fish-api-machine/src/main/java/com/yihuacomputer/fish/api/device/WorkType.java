package com.yihuacomputer.fish.api.device;

/**
 * 经营方式
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午01:44:59
 */
public enum WorkType
{
    FROM_OPERATING(1, "自营"), COOPERATION(2, "合作"), OUTSOURCING(3, "外包");

    private int id;

    private String text;

    private WorkType(int id, String text)
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

    public static WorkType getById(int id)
    {
        for (WorkType each : WorkType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
