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

    /**
     * @return
     */
    public long getDeviceTotal();
    
    /**
     * @param org
     * @return
     */
    public long getDeviceTotal(IOrganization org);

}
