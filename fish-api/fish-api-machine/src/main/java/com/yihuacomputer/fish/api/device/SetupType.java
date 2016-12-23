package com.yihuacomputer.fish.api.device;


/**
 * 安装方式
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午12:07:13
 */
public enum SetupType
{
    WEAR_WALL(1, "SetupType.WEAR_WALL"), LOBBY(2, "SetupType.LOBBY");
   
    private int id;

    private String text;

    private SetupType(int id, String text)
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
    public static SetupType getById(int id)
    {
        for (SetupType each : SetupType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
