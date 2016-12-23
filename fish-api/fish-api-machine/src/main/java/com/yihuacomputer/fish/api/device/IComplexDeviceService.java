/**
 * 
 */
package com.yihuacomputer.fish.api.device;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * 加入数据权限控制的设备服务
 * @author xuxigang
 *
 */
public interface IComplexDeviceService {

    /**
     * @param start
     * @param limit
     * @param filter
     * @param currentLoginUser
     * @return
     */
    IPageResult<IDevice> page(int start,int limit,IFilter filter,IUser currentLoginUser);
    
    /**
     * @param start
     * @param limit
     * @param filter
     * @param currentLoginUserId
     * @return
     */
    IPageResult<IDevice> page(int start,int limit,IFilter filter,long currentLoginUserId);
}