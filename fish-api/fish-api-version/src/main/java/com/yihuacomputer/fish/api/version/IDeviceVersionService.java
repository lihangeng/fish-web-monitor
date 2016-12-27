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
    /**
     * @return
     */
    IDeviceVersion make();

    /**
     * @param deviceId
     * @param versionId
     * @param taskStatus
     * @param reason
     * @return
     */
    IDeviceVersion saveOrUpdateDeviceVersion(long deviceId, long versionId, TaskStatus taskStatus, String reason);

    /**
     * @param deviceId
     * @param versionId
     * @return
     */
    IDeviceVersion findDeviceVersionContainsRemoved(long deviceId, long versionId);

    /**
     * @param deviceId
     * @param versionId
     * @return
     */
    IDeviceVersion findDeviceVersion(long deviceId, long versionId);

    /**
     * @param versionId
     */
    void deleteDeviceVersion(long versionId);

    /**
     * @return
     */
    List<IDeviceVersion> listDeviceVersions();

    /**
     * @param deviceId
     * @return
     */
    List<IDeviceVersion> listDeviceVersions(long deviceId);

    /**
     * @param deviceId
     * @param versionId
     * @param taskStatus
     * @return
     */
    IDeviceVersion updateDeviceVersionStatus(long deviceId, long versionId, TaskStatus taskStatus);

    /**
     * @param versionId
     * @return
     */
    List<IDeviceVersion> findDeviceVersionContainsRemoved(long versionId);

    /**
     * @param deviceId
     * @param versionId
     * @param taskStatus
     * @param reason
     */
    void saveOrUpdateDeviceVersionForList(long deviceId, long versionId, TaskStatus taskStatus, String reason);

    /**
     * @param deviceId
     * @param versionId
     * @param taskStatus
     */
    void updateDeviceVersionStatusForList(long deviceId, long versionId, TaskStatus taskStatus);

    /**
     * 根据条件查询设备版本记录
     * 
     * @param filter
     * @return
     */
    List<IDeviceVersion> list(IFilter filter);
}
