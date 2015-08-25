package com.yihuacomputer.fish.person.service.base;

import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.person.entity.PersonJob;
import com.yihuacomputer.fish.person.service.api.IDomainPersonJobService;

/**
 * 基础的PersonJobService抽象：
 *
 * @author pengwenchao
 *
 */
public abstract class DomainPersonJobService implements IDomainPersonJobService {

    @Override
    public IPersonJob make() {
        IPersonJob result = new PersonJob();
        return result;
    }

}