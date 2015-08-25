package com.yihuacomputer.fish.machine.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceListener;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.machine.service.base.DomainDeviceCheckService;

/**
 * @author liubo
 * @E-mail liubo-nj@yihuacomputer
 * @date 20140708
 */
@Service
@Transactional
public class DeviceCheckService extends DomainDeviceCheckService {
    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private IDevicePersonRelation devicePersonRelation;

    @Autowired
    private IQuittingNoticeService quittingNoticeService;

    public List<IDeviceListener> deviceListeners = new ArrayList<IDeviceListener>();

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IDevice> pageCheck(int offset, int limit, IFilter filter, String orgId,long userId) {
		StringBuffer hql = new StringBuffer();
        List<Object> fixedFilters = new ArrayList<Object>();
        hql.append("from Device device where 1=1 ");

        if (orgId != null && !"".equals(orgId)) {
            hql.append(" and device.organization.orgFlag like ? ");
            IOrganization org = orgService.get(orgId);
            fixedFilters.add("%" + org.getOrgFlag());
        }
        hql.append(" and device.status in (?,?,?)");
        fixedFilters.add(Status.OPE_WAI_CHE);
        fixedFilters.add(Status.SCR_WAI_CHE);
        fixedFilters.add(Status.UPD_WAI_CHE);
        hql.append(" and device.applyId <> "+userId);
        return (IPageResult<IDevice>) dao.page(offset, limit, filter, hql.toString(), fixedFilters.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IDevice> pageCheckYes(int offset, int limit,
			IFilter filter, String orgId) {
		StringBuffer hql = new StringBuffer();
        List<Object> fixedFilters = new ArrayList<Object>();
        hql.append("from Device device where 1=1 ");

        if (orgId != null && !"".equals(orgId)) {
            hql.append(" and device.organization.orgFlag like ? ");
            IOrganization org = orgService.get(orgId);
            fixedFilters.add("%" + org.getOrgFlag());
        }

        hql.append(" and device.status in (?,?)");
        fixedFilters.add(Status.OPEN);
        fixedFilters.add(Status.SCRAPPED);
        return (IPageResult<IDevice>) dao.page(offset, limit,hql.toString(), fixedFilters.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IDevice> pageCheckNo(int offset, int limit,
			IFilter filter, String orgId) {
		StringBuffer hql = new StringBuffer();
        List<Object> fixedFilters = new ArrayList<Object>();
        hql.append("from Device device where 1=1 ");

        if (orgId != null && !"".equals(orgId)) {
            hql.append(" and device.organization.orgFlag like ? ");
            IOrganization org = orgService.get(orgId);
            fixedFilters.add("%" + org.getOrgFlag());
        }

        hql.append(" and device.status in (?,?,?)");
        fixedFilters.add(Status.OPE_CHE_NO);
        fixedFilters.add(Status.SCR_CHE_NO);
        fixedFilters.add(Status.UPD_CHE_NO);
        return (IPageResult<IDevice>) dao.page(offset, limit, hql.toString(), fixedFilters.toArray());
	}


}
