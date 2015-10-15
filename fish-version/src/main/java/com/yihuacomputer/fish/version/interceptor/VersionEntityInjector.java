/**
 * 
 */
package com.yihuacomputer.fish.version.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceSoftVersionService;
import com.yihuacomputer.fish.version.service.api.IDomainTaskService;
import com.yihuacomputer.fish.version.service.api.IDomainVersionService;

/**
 * @author xuxigang 在实体类中注入服务
 */
@Component("versionEntityInjector")
public class VersionEntityInjector implements IEntityInjector {

    @Autowired
    private IDomainTaskService taskService;

    @Autowired
    private IDomainDeviceSoftVersionService dsvService;
    
    @Autowired
    private MessageSource messageSourceVersion;

    @Autowired
    private IDomainVersionService versionService;
    @Override
    public void injectDependencies(Object entity) {
        if (entity instanceof Task) {
            Task task = (Task) entity;
            task.setTaskService(taskService);
            task.setMessageSourceVersion(messageSourceVersion);
        }
        else if (entity instanceof DeviceSoftVersion) {
            DeviceSoftVersion dsv = (DeviceSoftVersion) entity;
            dsv.setDsvService(dsvService);
        }else if(entity instanceof Version){
            Version v = (Version)entity;
            v.setVersionService(versionService);
            v.setMessageSourceVersion(messageSourceVersion);
        }
    }

}