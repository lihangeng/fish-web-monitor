package com.yihuacomputer.fish.monitor.service.base;

import com.yihuacomputer.fish.api.monitor.software.IDeviceParam;
import com.yihuacomputer.fish.monitor.entity.software.DeviceParam;
import com.yihuacomputer.fish.monitor.service.api.IDomainRuntimeParamService;

public abstract class DomainRuntimeParamService implements
        IDomainRuntimeParamService
{
    @Override
    public IDeviceParam make()
    {
        return new DeviceParam();
    }
}
