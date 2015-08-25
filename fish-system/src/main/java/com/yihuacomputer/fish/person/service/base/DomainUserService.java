package com.yihuacomputer.fish.person.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.person.entity.User;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;

public abstract class DomainUserService implements IDomainUserService {

	@Autowired
	private IPersonService personService;

	@Override
	public User make() {
		return new User(this);
	}

	/**
	 * 迁移到目标组织
	 */
	@Override
	public void move(IUser user, IOrganization organization) {
		user.getPerson().setOrganization(organization);
		personService.update(user.getPerson());
	}

	/**
	 * 得到人员信息服务接口
	 */
	@Override
	public IPersonService getPersonService() {
		return this.personService;
	}

	/**
	 * 设置人员信息服务接口
	 * @param userService
	 */
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
}