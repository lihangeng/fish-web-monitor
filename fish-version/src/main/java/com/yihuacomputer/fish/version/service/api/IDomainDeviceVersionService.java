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
    public int getRelationTaskSize(long deviceId, long versionId);

    public long getSuccess(long versionId);

    public long getFail(long versionId);
}
