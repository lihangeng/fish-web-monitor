package com.yihuacomputer.fish.person.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.person.service.api.IDomainOrganizationService;
import com.yihuacomputer.fish.system.entity.Organization;

/**
 * 基础的OrganizationService抽象：
 * @author huxiaobao
 *
 */
public abstract class DomainOrganizationService implements IDomainOrganizationService {

	@Autowired
	private IPersonService personService;

	/**
	 * 创建一条机构信息：
	 */
	@Override
	public Organization make() {
		Organization organization = new Organization();
		organization.setLeaf(true);
		organization.setService(this);
		return organization;
	}

	/**
	 * 获得人员信息服务：
	 */
	@Override
	public IPersonService getPersonService() {
		return this.personService;
	}

	public List<IOrganization> listChildren(String parentOrgId,
			OrganizationType orgType) {
		// TODO Auto-generated method stub
		return null;
	}

}