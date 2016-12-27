package com.yihuacomputer.fish.api.system.sms;


/**
 * @author YiHua
 *
 */
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

	/**
	 * @param manufacturerType
	 */
	void setManufacturerType(String manufacturerType) ;

	/**
	 * 源号码
	 * @return
	 */
	String getSourceNum();

	/**
	 * @param sourceNum
	 */
	void setSourceNum(String sourceNum) ;

	/**
	 * 版本号
	 * @return
	 */
	String getVersion();

	/**
	 * @param version
	 */
	void setVersion(String version);

	/**
	 * 登陆用户名
	 * @return
	 */
	String getAccount();

	/**
	 * @param account
	 */
	void setAccount(String account);
	
	/**
	 * 登陆密码
	 * @return
	 */
	String getPwd();

	/**
	 * @param pwd
	 */
	void setPwd(String pwd);

	/**
	 * 企业代码
	 * @return
	 */
	String getCompaCode();

	/**
	 * @param compaCode
	 */
	void setCompaCode(String compaCode);

	/**
	 * 短信网关IP
	 * @param host
	 */
	void setHost(String host);

	/**
	 * @param port
	 */
	void setPort(int port);
	
	/**
	 * 短信验证码
	 * @return
	 */
	String getVerifyCode();
	
	/**
	 * @param verifyCode
	 */
	void setVerifyCode(String verifyCode);
}
