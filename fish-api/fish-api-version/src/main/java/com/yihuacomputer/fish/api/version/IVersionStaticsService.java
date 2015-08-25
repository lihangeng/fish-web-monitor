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

    long getSuccessTotal(IVersion version);

    long getFailTotal(IVersion version);
    
    public IVersionStatics getVersionStatics(IVersion version,IOrganization org);
    
    public long getDeviceTotal(IOrganization org);

}
