package com.yihuacomputer.fish.api.atm;

/**
 * 品牌状态
 *
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-23 下午04:29:57
 */
public enum VendorStatus
{

    /**
     * 供应
     */
    SUPPLY(1, "VendorStatus.SUPPLY"), 
    /**
     *服务 
     */
    SERVE(3, "VendorStatus.SERVE");

    public String getText(){
		return text;
    }
    private int id;

    private String text;

    private VendorStatus(int id, String text)
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

    public static VendorStatus getById(int id)
    {
        for (VendorStatus each : VendorStatus.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
