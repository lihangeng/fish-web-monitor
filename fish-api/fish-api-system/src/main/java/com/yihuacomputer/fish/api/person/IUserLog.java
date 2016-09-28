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
    public void setId(long id);

    public long getId();

    public void setOperContent(String operContent);

    public String getOperContent();

    public void setOperTime(Date operTime);

    public Date getOperTime();

    public String getOperName();

    public void setOperName(String operName);

    public String getOperCode();

    public void setOperCode(String operCode);

    public String getOperResult();

    public void setOperResult(String operResult);

	/**
	 * 浏览器IP
	 * @return
	 */
	public String getClientIP();
	public void setClientIP(String clientIP);

	/**
	 * 服务器IP
	 * @return
	 */
	public String getServerIp();
	public void setServerIp(String serverIp);
	
	/**
	 * 执行动作所需时间
	 * @return
	 */
	public long getTimes();
	public void setTimes(long times);
}
