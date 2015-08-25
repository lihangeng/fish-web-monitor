/**
 * 
 */
package com.yihuacomputer.fish.version.service.api;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;

/**
 * @author xuxigang
 * 
 */
public interface IDomainTaskService extends ITaskService {
    public IDeviceService getDeviceService();
}
