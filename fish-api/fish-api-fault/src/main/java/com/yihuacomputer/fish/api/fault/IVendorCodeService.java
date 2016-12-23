package com.yihuacomputer.fish.api.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IVendorCodeService {
	/**
	 * 创建实体
	 * @return
	 */
	public IVendorCode make();
	
	/**
	 * 分页显示
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IVendorCode> page(int offset, int limit, IFilter filter);
	
	/**
	 * 通过厂商找出厂商故障
	 * @param vendorId
	 * @return
	 */
	public List<IVendorCode> getByVendor(long vendorId);
	
	/**
	 * 根据厂商批量删除厂商故障
	 * @param vendorId
	 */
	public void deleteByVendor(long vendorId);
	
	/**
	 * 保存
	 * @param vendorCode
	 */
	public void save(IVendorCode vendorCode);
}
