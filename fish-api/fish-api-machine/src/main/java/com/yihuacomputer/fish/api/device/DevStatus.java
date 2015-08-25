package com.yihuacomputer.fish.api.device;

/**
 * 设备状态
 *
 * @author liubo
 * @E-mail liubo-nj@yihuacomputer
 * @date 2014-7-7
 */
//1.启用、2.报废、3.启用待审核、4.报废待审核、5.修改待审核、6.启用审核不通过、7.报废审核不通过、8.修改审核不通过
public enum DevStatus
{
    OPENING(0, "启用"), SCRAP(1, "报废"),OPE_WAI_CHE(2,"启用待审核"),SCR_WAI_CHE(3,"报废待审核"),
    UPD_WAI_CHE(4,"修改待审核"),OPE_CHE_NO(5,"启用审核不通过"),SCR_CHE_NO(6,"报废审核不通过"),UPD_CHE_NO(7,"修改审核不通过");

    private int id;

    private String text;

    private DevStatus(int id, String text)
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

    public static DevStatus getById(int id)
    {
        for (DevStatus each : DevStatus.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
