package com.yihuacomputer.fish.version.service.db;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceListener;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.service.base.DomainDeviceSoftVersionService;

/**
 * 
 * @author xuxigang
 * 
 */
@Service
@Transactional
public class DeviceSoftVersionService extends DomainDeviceSoftVersionService implements IDeviceListener{

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IVersionTypeService versionTypeService;
	
	@Autowired
	private IDeviceService deviceService;
	/**
	 * 收集服务初始化
	 */
	@PostConstruct
	public void init() {
		deviceService.addDeviceListener(this);
	}
	
    @Transactional(readOnly = true)
	public IDeviceSoftVersion get(String terminalId,String typeName){
		StringBuffer hql = new StringBuffer();
		hql.append("from DeviceSoftVersion t where ");
		hql.append("t.terminalId = ? and t.typeName = ? ");
		return dao.findUniqueByHql(hql.toString(), terminalId,typeName);
	}

	@Override
	public IDeviceSoftVersion add(IDeviceSoftVersion version) {
		return dao.save(this.interface2Entity(version, false));
	}

	@Override
	public void update(IDeviceSoftVersion version) {
		dao.update(this.interface2Entity(version, true));
	}

	@Override
	public void delete(long id) {
		dao.delete(id, DeviceSoftVersion.class);
	}
	
	public void delete(IDeviceSoftVersion version) {
		this.delete(version.getId());
	}

	@Override
    @Transactional(readOnly = true)
	public List<IDeviceSoftVersion> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceSoftVersion.class);
	}

	@Override
    @Transactional(readOnly = true)
	public IPageResult<IDeviceSoftVersion> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, DeviceSoftVersion.class);
	}
	
	private DeviceSoftVersion interface2Entity(IDeviceSoftVersion job, boolean load) {
		if (job instanceof DeviceSoftVersion) {
			return (DeviceSoftVersion)job;
		}
		return null;
	}
	
	@Override
    @Transactional(readOnly = true)
	public IVersion findVersion(String typeName, String versionNo) {
		StringBuffer hql = new StringBuffer();
        hql.append("from Version t  where t.versionType.typeName = ? and t.versionNo = ?");
        List<Version> lists = dao.findByHQL(hql.toString(),typeName,versionNo);
        if(lists.size() > 0){
            return lists.get(0);
        }
        return null;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> findByTypeName(String typeName) {
        StringBuffer sql = new StringBuffer();
        sql.append("select dev.id,dsv.TYPE_NAME,dsv.VERSION_NO,dev.TERMINAL_ID");
        sql.append(" from VER_DEVICE_SOFT_VERSION dsv,DEV_INFO dev");
        sql.append(" where dsv.TERMINAL_ID = dev.TERMINAL_ID ");
        sql.append(" and dsv.TYPE_NAME = ?");
        SQLQuery query = dao.getSQLQuery(sql.toString());
        query.setString(0, typeName);
        return query.list();
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

	@Override
	public void afterAdd(IDevice device) {
		Date date = new Date();
		List<IVersionType> list = versionTypeService.listAll();
		for(IVersionType versionType:list){
			IDeviceSoftVersion deviceSoftVersion = make();
			deviceSoftVersion.setTerminalId(device.getTerminalId());
			deviceSoftVersion.setTypeName(versionType.getTypeName());
			deviceSoftVersion.setCreatedTime(date);
			deviceSoftVersion.setLastUpdatedTime(date);
			deviceSoftVersion.setVersionNo("0");
			deviceSoftVersion.setVersionStr("0000000000000000000000000000000");
			this.add(deviceSoftVersion);
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
