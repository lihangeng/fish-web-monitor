package com.yihuacomputer.fish.person.service.base;

import com.yihuacomputer.fish.person.entity.Person;
import com.yihuacomputer.fish.person.service.api.IDomainPersonService;

/**
 * 基础的PersonService抽象：
 * @author huxiaobao
 *
 */
public abstract class DomainPersonService implements IDomainPersonService {

	@Override
	public Person make() {
		Person result = new Person();
		result.setService(this);
		return result;
	}

}