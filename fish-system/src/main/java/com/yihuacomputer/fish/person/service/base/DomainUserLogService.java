package com.yihuacomputer.fish.person.service.base;

import com.yihuacomputer.fish.person.service.api.IDomainUserLogService;
import com.yihuacomputer.fish.system.entity.UserLog;

public abstract class DomainUserLogService implements IDomainUserLogService {

    @Override
    public UserLog make() {
        return new UserLog(this);
    }

}
