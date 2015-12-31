package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 
 * 设备上最新的版本服务
 * @author xuxigang
 * 
 */
public interface IDeviceSoftVersionService {
	public IDeviceSoftVersion make();

	public IDeviceSoftVersion add(IDeviceSoftVersion version);

	public void update(IDeviceSoftVersion version);

	public void delete(IDeviceSoftVersion version);

	public void delete(long id);

	public List<IDeviceSoftVersion> list(IFilter filter);

	public IPageResult<IDeviceSoftVersion> page(int offset, int limit, IFilter filter);
	
	public IDeviceSoftVersion get(long terminalId,String typeName);
	
	/**
	 * 根据版本类型名称查找设备对应的版本
	 * @param typeName
	 * @return
	 */
	public List<Object> findDeviceSoftVersions(String typeName);
}
