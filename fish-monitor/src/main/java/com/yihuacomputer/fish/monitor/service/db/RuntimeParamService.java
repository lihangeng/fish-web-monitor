package com.yihuacomputer.fish.monitor.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.software.IDeviceParam;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.software.DeviceParam;
import com.yihuacomputer.fish.monitor.service.base.DomainRuntimeParamService;

@Service
@Transactional
public class RuntimeParamService extends DomainRuntimeParamService {
    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Override
    public void save(IDeviceParam param) {
        dao.save(param);
    }

    @Override
    public IDeviceParam get(long id) {
        return dao.get(id, DeviceParam.class);
    }

    @Override
    public void update(IDeviceParam param) {
        dao.update(param);
    }

    @Override
    public IDeviceParam get(String terminalId) {
        // IDeviceParam deviceParam = (IDeviceParam)
        // dao.getCriteria(DeviceParam.class)
        // .add(Restrictions.eq("terminalId", terminalId)).uniqueResult();
        return null;
    }

    @Override
    public List<IDeviceParam> list() {
        return list(new Filter());
    }

    @Override
    public List<IDeviceParam> list(IFilter filter) {
        return dao.findByFilter(filter, IDeviceParam.class);
    }

    @Override
    public PageResult<IDeviceParam> page(int offset, int limit, IFilter filter) {
        return new PageResult<IDeviceParam>(list(filter), offset, limit);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void remove(String terminalId) {
        IFilter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("terminalId", terminalId));
        List<IDeviceParam> list = list(filter);
        for (IDeviceParam param : list) {
            remove(param.getId());
        }
    }

    @Override
    public void remove(long id) {
        dao.delete(id, DeviceParam.class);
    }

    @Override
    public int count(String terminalId) {

        String hql = "select count(ID) from DeviceParam where terminalId = '" + terminalId + "'";

        Number number = dao.findUniqueByHql(hql);

        return number.intValue();
    }

    @Override
    public List<IDeviceParam> listGroupByTerminalId(String terminalId) {
        return listGroupByTerminalId(terminalId, null);
    }

    @Override
    public List<IDeviceParam> listGroupByTerminalId(String terminalId, String orgId) {
        StringBuffer hql = new StringBuffer();

        if (orgId == null || orgId.isEmpty()) {
            hql.append("select p.terminalId from DeviceParam p");
        } else {
            IOrganization org = orgService.get(orgId);
            hql.append("select p.terminalId from Device d, Organization o, DeviceParam p where d.organization = o.id and d.terminalId = p.terminalId and o.orgFlag like '%").append(org.getOrgFlag()).append("' ");
        }

        if (!(terminalId == null || terminalId.isEmpty())) {
            hql.append(" and p.terminalId like '%").append(terminalId).append("%'");
        }
        hql.append(" group by p.terminalId ");

//       select p.TERMINAL_ID from dev_info d, sm_org o, dev_runtimeparam p where d.org_id = o.id and d.terminal_id = p.terminal_id and o.org_flag like '%21-17-3-1' group by p.terminal_id;

        List<String> list = dao.findByHQL(hql.toString());

        List<IDeviceParam> result = new ArrayList<IDeviceParam>();
        IDeviceParam iDeviceParam = null;
        long id = 1;
        for (String param : list) {
            iDeviceParam = new DeviceParam();
            iDeviceParam.setId(id);
            iDeviceParam.setTerminalId(param);
            result.add(iDeviceParam);
            id++;
        }
        return result;
    }
}
