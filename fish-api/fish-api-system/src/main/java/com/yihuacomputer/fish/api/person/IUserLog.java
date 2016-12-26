package com.yihuacomputer.fish.api.person;

import java.util.Date;

/**
 * 系统操作日志
 * 
 * @author xuxigang
 * @version 0.1
 * @since 0.1
 * @date 2010-8-3
 */
public interface IUserLog {
    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public long getId();

    /**
     * @param operContent
     */
    public void setOperContent(String operContent);

    /**
     * @return
     */
    public String getOperContent();

    /**
     * @param operTime
     */
    public void setOperTime(Date operTime);

    /**
     * @return
     */
    public Date getOperTime();

    /**
     * @return
     */
    public String getOperName();

    /**
     * @param operName
     */
    public void setOperName(String operName);

    /**
     * @return
     */
    public String getOperCode();

    /**
     * @param operCode
     */
    public void setOperCode(String operCode);

    /**
     * @return
     */
    public String getOperResult();

    /**
     * @param operResult
     */
    public void setOperResult(String operResult);

	/**
	 * 浏览器IP
	 * @return
	 */
	public String getClientIP();
	/**
	 * @param clientIP
	 */
	public void setClientIP(String clientIP);

	/**
	 * 服务器IP
	 * @return
	 */
	public String getServerIp();
	/**
	 * @param serverIp
	 */
	public void setServerIp(String serverIp);
	
	/**
	 * 执行动作所需时间
	 * @return
	 */
	public long getTimes();
	/**
	 * @param times
	 */
	public void setTimes(long times);
}
