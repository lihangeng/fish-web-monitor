package com.yihuacomputer.fish.api.system.sms;


public interface IShortMessageConfig {

	/**
	 * 短信接口机IP地址.
	 * @return ip
	 */
	String getHost();
	
	/**
	 * 短信接口机port.
	 * @return 端口
	 */
	int getPort();
	
	/**
	 * 短信接口机验证码.
	 * @return 验证码
	 */
	String getToken();
	
	/**
	 * 厂商类别
	 * @return
	 */
	String getManufacturerType();

	void setManufacturerType(String manufacturerType) ;

	/**
	 * 源号码
	 * @return
	 */
	String getSourceNum();

	void setSourceNum(String sourceNum) ;

	/**
	 * 版本号
	 * @return
	 */
	String getVersion();

	void setVersion(String version);

	/**
	 * 登陆用户名
	 * @return
	 */
	String getAccount();

	void setAccount(String account);
	
	/**
	 * 登陆密码
	 * @return
	 */
	String getPwd();

	void setPwd(String pwd);

	/**
	 * 企业代码
	 * @return
	 */
	String getCompaCode();

	void setCompaCode(String compaCode);

	/**
	 * 短信网关IP
	 * @param host
	 */
	void setHost(String host);

	void setPort(int port);
	
	/**
	 * 短信验证码
	 * @return
	 */
	String getVerifyCode();
	
	void setVerifyCode(String verifyCode);
}
