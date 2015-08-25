package com.yihuacomputer.fish.machine.service.base;

import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.machine.entity.device.DeviceExtend;
import com.yihuacomputer.fish.machine.service.api.IDomainDeviceExtendService;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午04:06:36
 * @version 类说明
 */
public abstract class DomainDeviceExtendService implements IDomainDeviceExtendService
{
    @Override
    public IDeviceExtend make()
    {
        return new DeviceExtend();
    }
}
