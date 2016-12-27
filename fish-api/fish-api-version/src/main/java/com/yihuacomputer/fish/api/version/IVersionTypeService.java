/**
 * 
 */
package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 版本类型服务
 * @author xuxigang
 * 
 */
public interface IVersionTypeService {
	/**
	 * @return
	 */
	public IVersionType make();

	/**
	 * @param typeName
	 * @return
	 */
	public IVersionType make(String typeName);

	/**
	 * @param id
	 * @return
	 */
	public IVersionType getById(long id);

	/**
	 * @param typeName
	 * @return
	 */
	public IVersionType getByName(String typeName);

	/**
	 * @param versionType
	 * @return
	 */
	public IVersionType add(IVersionType versionType);

	/**
	 * @param versionType
	 */
	public void update(IVersionType versionType);

	/**
	 * @param versionType
	 */
	public void delete(IVersionType versionType);

	/**
	 * @param id
	 */
	public void delete(long id);

	/**
	 * @param filter
	 * @return
	 */
	public List<IVersionType> list(IFilter filter);
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IVersionType> listContainsAdvert(IFilter filter);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IVersionType> page(int offset, int limit, IFilter filter);
	 /**
	 * @return
	 */
	List<IVersionType> listAll(); 
     
}
