package com.yihuacomputer.fish.api.fault;

/**
 * 故障知识库
 * 
 * @author YiHua
 *
 */
public interface IFaultWiki {

	public long getId();
	
	/**
	 * 设备类型（ATM机型）
	 * @return
	 */
	public String getDeviceType();
	
	/**
	 * 故障描述
	 * @return
	 */
	public String getFaultDescript();
	
	/**
	 * 故障解决方案
	 * @return
	 */
	public String getFaultSolution();
	
	/**
	 * 发布人员联系方式
	 * @return
	 */
	public String getContact();
	
	/**
	 * 有效性排名
	 * @return
	 */
	public String getUsefaul();
}
