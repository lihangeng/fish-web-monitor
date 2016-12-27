package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.OrganizationType;

/**
 * @author YiHua
 *
 */
@Service
@Transactional(readOnly = true)
public class ComplexDeviceService implements IComplexDeviceService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private IGenericDao dao;

    @Override
    @SuppressWarnings("unchecked")
    public IPageResult<IDevice> page(int start, int limit, IFilter filter, IUser currentLoginUser) {
        IOrganization org = currentLoginUser.getOrganization();
        OrganizationType orgType = org.getOrganizationType();
        StringBuffer hql = new StringBuffer("from Device device where 1=1 ");
        List<Object> fixedFilters = new ArrayList<Object>();
        if (orgType != null) {
            hql.append("and device.organization.orgFlag like ? ");
            fixedFilters.add(org.getOrgFlag() + "%");
        }

        IOrganization belongsOrg = getBelongsOrg(filter);
        if (belongsOrg != null) {
            hql.append("and device.organization.orgFlag like ? ");
            fixedFilters.add(belongsOrg.getOrgFlag() + "%");
        }
        hql.append(" order by device.id desc ");

        //还没有区分管机员和维护人员

        return (IPageResult<IDevice>) dao.page(start, limit, filter,hql.toString(),fixedFilters.toArray());
    }

    private IOrganization getBelongsOrg(IFilter outerFilter) {
        IFilter filter = null;
        if (outerFilter == null) {
            filter = new Filter();
        }
        else {
            filter = outerFilter;
        }
        String orgId = null;
        for (IFilterEntry c : filter.entrySet()) {
            if ("device.organization".equals(c.getKey())) {
                orgId = c.getValue().toString();
                filter.entrySet().remove(c);
                break;
            }
        }

        if (orgId != null && !"".equals(orgId)) {
            return orgService.get(orgId);
        }

        return null;
    }

    @Override
    public IPageResult<IDevice> page(int start, int limit, IFilter filter, long currentLoginUserId) {
        IUser user = userService.get(currentLoginUserId);
        return page(start, limit, filter, user);
    }

}
