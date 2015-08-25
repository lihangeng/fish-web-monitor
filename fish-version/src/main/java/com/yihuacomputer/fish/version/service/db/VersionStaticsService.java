/**
 * 
 */
package com.yihuacomputer.fish.version.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionStatics;
import com.yihuacomputer.fish.api.version.IVersionStaticsService;
import com.yihuacomputer.fish.version.entity.VersionStatics;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceVersionService;

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
    private IDomainDeviceVersionService dvService;
    
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

    @Override
    public long getSuccessTotal(IVersion version) {
        return dvService.getSuccess(version.getId());
    }

    @Override
    public long getFailTotal(IVersion version) {
        return dvService.getFail(version.getId());
    }

    @Override
    public IVersionStatics getVersionStatics(IVersion version,IOrganization org) {
        VersionStatics vs = new VersionStatics(version);
        vs.setDeviceTotal(this.getDeviceTotal(org));
        vs.setSuccessTotal(this.getSuccessTotal(version));
        vs.setFailTotal(this.getFailTotal(version));
        vs.setMayBeDownTotal(downloadService.getMayBeDownDevice(version,org));
        return vs;
    }

}
