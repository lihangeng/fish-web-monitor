package com.yihuacomputer.fish.person.service.db;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;

@Service
@Transactional
public class SrcbDevicePersonRelation {

	@Autowired
	private IGenericDao dao;

	@SuppressWarnings("unchecked")
	public IPageResult<IPerson> selectPage(int offset, int limit, Map<String, String> map) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from Person p ");
		hql.append("where p.id not in (select o.personId from DevicePersonObj o where o.deviceId = ?) and p.organization.orgFlag like ? ");
		long deviceId = Long.parseLong(map.get("deviceId"));
		String type = map.get("type");
		// "%" + org.getOrgFlag()
		String orgFlag = "%" + map.get("orgFlag");
		if (map.get("name") != null) {
			hql.append("and p.name like '").append(map.get("name")).append("' ");
		}
		if (map.get("jobNum") != null) {
			hql.append("and p.jobNum like '").append(map.get("jobNum")).append("' ");
		}
		if (map.get("mobile") != null) {
			hql.append("and p.mobile like '").append(map.get("mobile")).append("' ");
		}
		if (map.get("state") != null) {
			hql.append("and p.state = '").append(map.get("state")).append("' ");
		}
		if ("0".equals(type)) {
			hql.append("and p.type = '").append(PersonType.MANAGE.ordinal()).append("'");
		} else {
			hql.append("and p.type = '").append(PersonType.FIXMAN.ordinal()).append("'");
		}
		IPageResult<IPerson> page = (IPageResult<IPerson>) dao.page(offset, limit, hql.toString(), deviceId, orgFlag);
		return page;
	}
}
