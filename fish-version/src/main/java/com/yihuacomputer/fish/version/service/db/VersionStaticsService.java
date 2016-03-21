/**
 * 
 */
package com.yihuacomputer.fish.version.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionStaticsService;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional(readOnly = true)
public class VersionStaticsService implements IVersionStaticsService {

    @Autowired
    private IDeviceService deviceService;   
    
    @Autowired
    private IVersionDownloadService downloadService;
    
    @Override
    public long getDeviceTotal() {
        return deviceService.getOpeningDeviceTotal();
    }  
    
    @Override
    public long getDeviceTotal(IOrganization org) {
        return deviceService.getOpeningDeviceTotal(org);
    }



}
