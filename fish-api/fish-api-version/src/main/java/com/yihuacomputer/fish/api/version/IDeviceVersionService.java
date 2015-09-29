/**
 * 
 */
package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author xuxigang
 * 
 */
public interface IDeviceVersionService {
    IDeviceVersion make();
    
    IDeviceVersion saveOrUpdateDeviceVersion(long deviceId, long versionId, TaskStatus taskStatus, String reason);

    IDeviceVersion findDeviceVersionContainsRemoved(long deviceId, long versionId);

    IDeviceVersion findDeviceVersion(long deviceId, long versionId);

    void deleteDeviceVersion(long versionId);
    
    List<IDeviceVersion> findDevices(long versionId);

    List<IDeviceVersion> listDeviceVersions();
    
    List<IDeviceVersion> listDeviceVersions(long deviceId);
    
    IDeviceVersion updateDeviceVersionStatus(long deviceId, long versionId, TaskStatus taskStatus);

    IDeviceVersion updateDeviceVersionStatusWithTaskId(long deviceId, long versionId, TaskStatus taskStatus,long taskId);
    IDeviceVersion saveOrUpdateDeviceVersionWithTaskId(long deviceId, long versionId, TaskStatus taskStatus, String reason,long taskId);

}
