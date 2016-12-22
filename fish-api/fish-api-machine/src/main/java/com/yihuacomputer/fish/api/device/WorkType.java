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
    FROM_OPERATING(1, "WorkType.FROM_OPERATING"), COOPERATION(2, "WorkType.FROM_OPERATING"), OUTSOURCING(3, "WorkType.FROM_OPERATING");

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

    public String getText(){
		return text;
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
