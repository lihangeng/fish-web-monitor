package com.yihuacomputer.fish.person.service.base;

import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.person.service.api.IDomainPersonJobService;
import com.yihuacomputer.fish.system.entity.PersonJob;

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