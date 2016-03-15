/**
 * 
 */
package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author xuxigang
 * @since 0.15
 */
public interface IVersionStaticsService {

    public long getDeviceTotal();
    
    public long getDeviceTotal(IOrganization org);

}
