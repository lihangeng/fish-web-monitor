package com.yihuacomputer.fish.api.monitor.software;

/**
 * @author YiHua
 *
 */
public interface ISoftwareService {

	/**
	 * 初始设备软件信息
	 * @param terminalId
	 */
	public void init(String terminalId);
	
	/**
	 * 创建软件信息
	 * @return
	 */
	public ISoftware make();
	
	/**
	 * 取设备软件信息
	 * @param terminalId
	 * @return
	 */
	public ISoftware load(String terminalId);
	
	/**
	 * 保存设备软件信息
	 * @param software
	 */
	public void save(ISoftware software);
	
	/**
	 * 删除设备软件信息
	 * @param terminalId
	 */
	public void delete(String terminalId);
	
	/**
	 * 删除设备软件信息
	 * @param software
	 */
	public void delete(ISoftware software);
	
	/**
	 * 更新设备软件信息
	 * @param software
	 */
	public void update(ISoftware software);
	

}
