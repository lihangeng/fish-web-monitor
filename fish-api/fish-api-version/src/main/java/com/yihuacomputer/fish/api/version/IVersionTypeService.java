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
	public IVersionType make();

	public IVersionType make(String typeName);

	public IVersionType getById(long id);

	public IVersionType getByName(String typeName);

	public IVersionType add(IVersionType versionType);

	public void update(IVersionType versionType);

	public void delete(IVersionType versionType);

	public void delete(long id);

	public List<IVersionType> list(IFilter filter);
	
	public List<IVersionType> listContainsAdvert(IFilter filter);

	public IPageResult<IVersionType> page(int offset, int limit, IFilter filter);
	 List<IVersionType> listAll(); 
     
}
