package com.yihuacomputer.fish.api.monitor.business;

public interface ITransactionColor {
    /**
     * ID主键
     *
     * @return
     */
    public long getId();

    public void setId(long id);

    /**
     * 主机返回码
     *
     * @return
     */
    public String getHostRet();

    public void setHostRet(String hostRet);

    /**
     * 背景颜色
     *
     * @return
     */
    public String getBackgroundColor();

    public void setBackgroundColor(String backgroundColor);

    /**
     * 字体颜色
     *
     * @return
     */
    public String getFontColor();

    public void setFontColor(String fontColor);

    

    /**
     * 主机返回码描述
     *
     * @return
     */
    public String getHostRetDes();

    public void setHostRetDes(String hostRetDes);
    
    /**
     * 修改时间
     *
     * @return
     */
    public String getUpdateDateTime();

    public void setUpdateDateTime(String updateDateTime);

    /**
     * 备注
     *
     * @return
     */
    public String getRemark();

    public void setRemark(String remark);

    /**
     * 用户名称
     *
     * @return
     */
    public String getUserName();

    public void setUserName(String userName);

    /**
     * 本地返回码
     *
     * @return
     */
    public String getLocalRet();

    public void setLocalRet(String localRet);

    /**
     * 本地背景颜色
     *
     * @return
     */
    public String getLocalBackgroundColor();

    public void setLocalBackgroundColor(String localBackgroundColor);

    /**
     * 本地字体颜色
     *
     * @return
     */
    public String getLocalFontColor();

    public void setLocalFontColor(String localFontColor);
    

    /**
     * 本地返回码描述
     *
     * @return
     */
    public String getLocalRetDes();

    public void setLocalRetDes(String localRetDes);
}
