package com.yihuacomputer.fish.version.service.db;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceSoftVersionService;

/**
 * 
 * @author xuxigang
 * 
 */
@Service
@Transactional
public class DeviceSoftVersionService implements IDomainDeviceSoftVersionService {

	@Autowired
	private IGenericDao dao;
	
	public IDeviceSoftVersion make() {
		return new DeviceSoftVersion(this);
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

	@Override
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public List<Object> findDeviceSoftVersions(String typeName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select dev.id,dsv.VERSION_NO from DEV_INFO dev,VER_DEVICE_SOFT_VERSION dsv ");
		sql.append("where dev.TERMINAL_ID = dsv.TERMINAL_ID and dsv.TYPE_NAME = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0,typeName);
		return query.list();
	}

}
