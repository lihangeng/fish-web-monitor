package com.yihuacomputer.fish.api.relation;

import java.util.List;
import java.util.Set;

import com.yihuacomputer.fish.api.permission.IPermission;

/**
 * @author YiHua
 *
 */
public interface IRelationService {
	
	/**
	 * 根据用户找到对应的权限
	 * @param userId
	 * @return
	 */
	public Set<IPermission> findPermissonsByUser(long userId);
	/**
	 * 根据用户查找对应的按钮
	 * @param userId
	 * @return
	 */
	public List<String> findButtonsByUser(long userId);
	
}
