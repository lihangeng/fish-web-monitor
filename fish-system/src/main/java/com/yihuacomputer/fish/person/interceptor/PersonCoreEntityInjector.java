package com.yihuacomputer.fish.person.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.person.service.api.IDomainOrganizationService;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;
import com.yihuacomputer.fish.system.entity.Organization;
import com.yihuacomputer.fish.system.entity.User;

public class PersonCoreEntityInjector implements IEntityInjector
{
    @Autowired
    private IDomainUserService domainUserService;

    @Autowired
    private IDomainOrganizationService organizationService;

    @Override
    public void injectDependencies(Object entity)
    {
        if(entity instanceof User){
            User user = (User)entity;
            user.setService(domainUserService);
        }else if(entity instanceof Organization){
            Organization organization = (Organization)entity;
            organization.setService(organizationService);
        }

    }

}
