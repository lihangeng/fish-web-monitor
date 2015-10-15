package com.yihuacomputer.fish.version.service.db;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceListener;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.version.entity.VersionType;

@Service
@Transactional
public class VersionTypeService implements IVersionTypeService,IDeviceListener {

	@Autowired
	private IGenericDao dao;


	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private MessageSource messageSourceVersion;
	
	@Override
	public IVersionType make() {
		return make(null);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType make(String typeName) {
		return new VersionType(typeName);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType getById(long id) {
		return dao.get(id, VersionType.class);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType getByName(String typeName) {
		IFilter filter = new Filter();
		filter.eq("typeName", typeName);
		return dao.findUniqueByFilter(filter, VersionType.class);
	}

	@Override
	@Transactional
	public IVersionType add(IVersionType versionType) {
		List<IDevice> deviceList = deviceService.list();
		Date date = new Date();
		for(IDevice device:deviceList){
			IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.make();
			deviceSoftVersion.setCreatedTime(date);
			deviceSoftVersion.setDeviceId(device.getId());
			deviceSoftVersion.setTypeName(versionType.getTypeName());
			deviceSoftVersion.setVersionNo("0.0.0.0");
			deviceSoftVersion.setVersionStr("00000000000000000000000000000000");
			deviceSoftVersionService.add(deviceSoftVersion);
		}
		return dao.save(this.interface2Entity(versionType, false));
	}

	@Override
	public void update(IVersionType versionType) {
		dao.update(this.interface2Entity(versionType, true));
	}

	@Override
	public void delete(IVersionType versionType) {
		delete(versionType.getId());
	}

	@Override
	public void delete(long id) {
	    IFilter filter = new Filter();
	    filter.eq("versionType.id", id);
	    List<IVersion> versions = dao.findByFilter(filter, IVersion.class);
	    if(versions.size() > 0){
	        throw new AppException(messageSourceVersion.getMessage("versionType.deleteFailForExistSubVersion",new Object[]{versions.size()}, FishCfg.locale));
	    }else{
	        dao.delete(id, VersionType.class);
	    }
	}

	@Override
    @Transactional(readOnly = true)
	public List<IVersionType> list(IFilter outerFilter) {
	    IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
		filter.eq("display", true);
		return dao.findByFilter(filter, IVersionType.class);
	}
	
    @Transactional(readOnly = true)
	public List<IVersionType> listContainsAdvert(IFilter filter){
	    return dao.findByFilter(filter, IVersionType.class);
	}

	@Override
    @Transactional(readOnly = true)
	public IPageResult<IVersionType> page(int offset, int limit, IFilter outerFilter) {
	    IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
        filter.eq("display", true);
		return dao.page(offset, limit, filter, VersionType.class);
	}

	private VersionType interface2Entity(IVersionType versionType, boolean load) {
		if (versionType instanceof VersionType) {
			return (VersionType) versionType;
		}
		return null;
	}

	@Override
	public String getListenerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeAdd(IDevice device) {
		// TODO Auto-generated method stub
		
	}
	@PostConstruct
    public void init() {
    	deviceService.addDeviceListener(this);
    }
	@Override
	public void afterAdd(IDevice device) {
		//查找所有可见的版本类型
		IFilter filter = new Filter();
		filter.eq("display", true);
		List<IVersionType> versionTypeList = this.list(filter);
		Date date = new Date();
		//每个新增的设备都有一个版本号为0.0.0.0
		for(IVersionType versionType :versionTypeList){
			IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.make();
			deviceSoftVersion.setCreatedTime(date);
			deviceSoftVersion.setDeviceId(device.getId());
			deviceSoftVersion.setTypeName(versionType.getTypeName());
			deviceSoftVersion.setVersionNo("0.0.0.0");
			deviceSoftVersion.setVersionStr("00000000000000000000000000000000");
			deviceSoftVersionService.add(deviceSoftVersion);
		}
	}

	@Override
	public void beforeUpdate(IDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterUpdate(IDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDelete(IDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterDelete(IDevice device) {
		// TODO Auto-generated method stub
		
	}

}
