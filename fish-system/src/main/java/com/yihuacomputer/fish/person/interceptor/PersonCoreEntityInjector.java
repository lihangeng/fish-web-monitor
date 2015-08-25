package com.yihuacomputer.fish.person.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.person.entity.Organization;
import com.yihuacomputer.fish.person.entity.Person;
import com.yihuacomputer.fish.person.entity.User;
import com.yihuacomputer.fish.person.service.api.IDomainOrganizationService;
import com.yihuacomputer.fish.person.service.api.IDomainPersonService;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;

public class PersonCoreEntityInjector implements IEntityInjector
{
    @Autowired
    private IDomainUserService domainUserService;

    @Autowired
    private IDomainPersonService personService;

    @Autowired
    private IDomainOrganizationService organizationService;

    @Override
    public void injectDependencies(Object entity)
    {
        if(entity instanceof User){
            User user = (User)entity;
            user.setService(domainUserService);
        }else if(entity instanceof Person){
            Person person = (Person)entity;
            person.setService(personService);
        }else if(entity instanceof Organization){
            Organization organization = (Organization)entity;
            organization.setService(organizationService);
        }

    }

}
