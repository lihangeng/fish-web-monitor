package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public interface ITransactionColor {
    /**
     * ID主键
     *
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 主机返回码
     *
     * @return
     */
    public String getHostRet();

    /**
     * @param hostRet
     */
    public void setHostRet(String hostRet);

    /**
     * 背景颜色
     *
     * @return
     */
    public String getBackgroundColor();

    /**
     * @param backgroundColor
     */
    public void setBackgroundColor(String backgroundColor);

    /**
     * 字体颜色
     *
     * @return
     */
    public String getFontColor();

    /**
     * @param fontColor
     */
    public void setFontColor(String fontColor);

    

    /**
     * 主机返回码描述
     *
     * @return
     */
    public String getHostRetDes();

    /**
     * @param hostRetDes
     */
    public void setHostRetDes(String hostRetDes);
    
    /**
     * 修改时间
     *
     * @return
     */
    public String getUpdateDateTime();

    /**
     * @param updateDateTime
     */
    public void setUpdateDateTime(String updateDateTime);

    /**
     * 备注
     *
     * @return
     */
    public String getRemark();

    /**
     * @param remark
     */
    public void setRemark(String remark);

    /**
     * 用户名称
     *
     * @return
     */
    public String getUserName();

    /**
     * @param userName
     */
    public void setUserName(String userName);

    /**
     * 本地返回码
     *
     * @return
     */
    public String getLocalRet();

    /**
     * @param localRet
     */
    public void setLocalRet(String localRet);

    /**
     * 本地背景颜色
     *
     * @return
     */
    public String getLocalBackgroundColor();

    /**
     * @param localBackgroundColor
     */
    public void setLocalBackgroundColor(String localBackgroundColor);

    /**
     * 本地字体颜色
     *
     * @return
     */
    public String getLocalFontColor();

    /**
     * @param localFontColor
     */
    public void setLocalFontColor(String localFontColor);
    

    /**
     * 本地返回码描述
     *
     * @return
     */
    public String getLocalRetDes();

    /**
     * @param localRetDes
     */
    public void setLocalRetDes(String localRetDes);
}
