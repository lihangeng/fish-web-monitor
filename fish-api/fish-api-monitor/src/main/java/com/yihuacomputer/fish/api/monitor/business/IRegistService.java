package com.yihuacomputer.fish.api.monitor.business;

public interface IRegistService {

	/**
	 * 初始化注册信息
	 * @param terminalId
	 */
	public IDeviceRegister init(String terminalId);
	
	/**
	 * 删除注册信息
	 * @param reg
	 */
	public void delete(IDeviceRegister reg);
	
	/**
	 * 创建注册实体
	 * @return
	 */
	public IDeviceRegister make();
	
	/**
	 * 保存注册信息
	 * @param reg
	 */
	public void save(IDeviceRegister reg);
	
	/**
	 * 获取注册信息
	 * @param terminalId
	 * @return
	 */
	public IDeviceRegister load(String terminalId);
	
	/**
	 * 更新注册信息
	 * @param reg
	 */
	public void update(IDeviceRegister reg);
}
