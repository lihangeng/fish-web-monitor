package com.yihuacomputer.fish.api.device;

/**
 * 注册状态
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午01:49:00
 */
public enum RegStatus
{
	UNKNOWN(0, "RegStatus.UNKNOWN"),UNREGISTERED(1, "RegStatus.UNREGISTERED"), REGISTRATION(1, "RegStatus.REGISTRATION");
    
    private int id;

    private String text;

    private RegStatus(int id, String text)
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
    
    /**
     * @param id
     * @return
     */
    public static RegStatus getById(int id)
    {
        for (RegStatus each : RegStatus.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
