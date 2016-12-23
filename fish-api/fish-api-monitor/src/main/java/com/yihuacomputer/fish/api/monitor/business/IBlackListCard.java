package com.yihuacomputer.fish.api.monitor.business;

import java.util.Date;

/**
 * 黑名单卡信息：
 * 
 * @author shixiaolong
 * 
 */
public interface IBlackListCard {

    /**
     * 主键
     * 
     * @return
     */

    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 交易卡号
     * 
     * @return
     */
    public String getCardNo();

    /**
     * @param cardNo
     */
    public void setCardNo(String cardNo);

    /**
     * 返回用户姓名
     * 
     * @return
     */
    public String getUserName();

    /**
     * 设置用户姓名
     * @param userName
     */
    public void setUserName(String userName);

    /**返回添加日期
     * @return
     */
    public Date getAddDate();

    /**
     * 设置添加日期
     * 
     * @param addDate
     */
    public void setAddDate(Date addDate);
    
    /**
     * 返回所属银行
     * @return
     */
    public String getOrganization();
    
    /**
     * 设置所属银行
     * @param organization
     */
    public void setOrganization(String organization);

}
