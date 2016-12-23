package com.yihuacomputer.fish.api.device;


/**
 * 
 * 非现金标志
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午12:04:35
 */
public enum CashType
{
    CASH(1, "CashType.CASH"), NOT_CASH(2, "CashType.NOT_CASH");
    
    private int id;

    private String text;

    private CashType(int id, String text)
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
    public static CashType getById(int id)
    {
        for (CashType each : CashType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
