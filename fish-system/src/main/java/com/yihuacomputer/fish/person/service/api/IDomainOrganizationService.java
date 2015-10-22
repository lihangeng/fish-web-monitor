package com.yihuacomputer.fish.person.service.api;

import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.system.entity.Organization;

/**
 * 领域层Service接口
 * @author xuxigang
 * @version
 * @since  
 * @date 2010-9-21
 */
public interface IDomainOrganizationService extends IOrganizationService {
   
    /**
     * 获得人员服务接口
     * @return
     */
	public IPersonService getPersonService();
	
	/**
	 * 增加一条机构信息
	 * @param code
	 * @param name
	 * @param parent
	 * @return
	 */
	public Organization add(String code,String name,Organization parent);
}
