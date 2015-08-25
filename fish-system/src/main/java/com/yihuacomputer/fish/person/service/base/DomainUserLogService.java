package com.yihuacomputer.fish.person.service.base;

import com.yihuacomputer.fish.person.entity.UserLog;
import com.yihuacomputer.fish.person.service.api.IDomainUserLogService;

public abstract class DomainUserLogService implements IDomainUserLogService {

    @Override
    public UserLog make() {
        return new UserLog(this);
    }

}
