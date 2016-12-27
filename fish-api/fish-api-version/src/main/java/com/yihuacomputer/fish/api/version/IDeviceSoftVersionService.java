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
	/**
	 * @return
	 */
	public IDeviceSoftVersion make();

	/**
	 * @param version
	 * @return
	 */
	public IDeviceSoftVersion add(IDeviceSoftVersion version);

	/**
	 * @param version
	 */
	public void update(IDeviceSoftVersion version);

	/**
	 * @param version
	 */
	public void delete(IDeviceSoftVersion version);

	/**
	 * @param id
	 */
	public void delete(long id);

	/**
	 * @param filter
	 * @return
	 */
	public List<IDeviceSoftVersion> list(IFilter filter);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IDeviceSoftVersion> page(int offset, int limit, IFilter filter);
	
	/**
	 * @param terminalId
	 * @param typeName
	 * @return
	 */
	public IDeviceSoftVersion get(String terminalId,String typeName);
	
	/**
	 * @param typeName
	 * @return
	 */
	public List<Object> findByTypeName(String typeName);
	
	/**
	 * 由versionCatalog和设备号唯一确定一个版本名称信息
	 * @param terminalId 设备号
	 * @param versionCatalog   （APP）
	 * @return
	 */
	public IDeviceSoftVersion findVersionByCatlog(String terminalId,VersionCatalog versionCatalog);
}
