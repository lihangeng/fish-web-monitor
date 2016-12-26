package com.yihuacomputer.fish.api.monitor.hardware;

/**
 * @author YiHua
 *
 */
public interface IHardwareService {
	/**
	 *  初始化设备硬件信息
	 * @param terminalId
	 */
	public void init(String terminalId);
	
	/**
	 * 创建一个硬件信息
	 * @return
	 */
	public IHardware make();
	
	/**
	 * 保存设备硬件信息
	 * @param hardware
	 */
	public void save(IHardware hardware);
	
	/**
	 * 取设备硬件信息
	 * @param terminalId
	 * @return
	 */
	public IHardware load(String terminalId);
	
	/**
	 * 删除设备硬件信息
	 * @param terminalId
	 */
	public void delete(String terminalId);
	
	/**
	 * 删除设备硬件信息
	 * @param hardware
	 */
	public void delete(IHardware hardware);
	
	/**
	 * 更新设备硬件信息
	 * @param hardware
	 */
	public void update(IHardware hardware);
	
	/**
	 * 删除设备CPU信息
	 * @param terminalId
	 */
	public void deleteCpu(String terminalId);
	
	/**
	 * 删除设备磁盘信息
	 * @param terminalId
	 */
	public void deleteDisk(String terminalId);
}
