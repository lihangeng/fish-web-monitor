/**
 *
 */
package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.common.IFilter;
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

    List<IDeviceVersion> listDeviceVersions();

    List<IDeviceVersion> listDeviceVersions(long deviceId);

    IDeviceVersion updateDeviceVersionStatus(long deviceId, long versionId, TaskStatus taskStatus);

    List<IDeviceVersion> findDeviceVersionContainsRemoved(long versionId);

    void saveOrUpdateDeviceVersionForList(long deviceId, long versionId, TaskStatus taskStatus, String reason);

    void updateDeviceVersionStatusForList(long deviceId, long versionId, TaskStatus taskStatus);

    /**
     * 根据条件查询设备版本记录
     * 
     * @param filter
     * @return
     */
    List<IDeviceVersion> list(IFilter filter);
}
