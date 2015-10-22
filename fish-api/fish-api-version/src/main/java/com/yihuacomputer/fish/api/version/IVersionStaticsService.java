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
    
    public IVersionStatics getVersionStatics(IVersion version,IOrganization org);
    
    public long getDeviceTotal(IOrganization org);

}
