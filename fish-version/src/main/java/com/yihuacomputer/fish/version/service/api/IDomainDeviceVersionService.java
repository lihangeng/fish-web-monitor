/**
 *
 */
package com.yihuacomputer.fish.version.service.api;

import com.yihuacomputer.fish.api.version.IDeviceVersionService;

/**
 * @author xuxigang
 *
 */
public interface IDomainDeviceVersionService extends IDeviceVersionService {
    /**
     * @param deviceId
     * @param versionId
     * @return
     */
    public int getRelationTaskSize(long deviceId, long versionId);

    /**
     * @param versionId
     * @return
     */
    public long getSuccess(long versionId);

    /**
     * @param versionId
     * @return
     */
    public long getFail(long versionId);

}
