package com.yihuacomputer.fish.machine.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.machine.entity.DevicePersonObj;

/**
 * 人员和设备关联的服务：
 *
 * @author huxiaobao
 *
 */
@Service
@Transactional
public class DevicePersonRelation implements IDevicePersonRelation {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    /**
     * 将设备和人员进行关联：
     */
    @Override
    public void link(IPerson person, IDevice device) {
        dao.save(DevicePersonObj.make(Long.valueOf(person.getGuid()), device.getId()));
    }

    /**
     * 设备和人员进行解除关联关系：
     */
    @Override
    public void unlink(IPerson person, IDevice device) {

        Filter filter = new Filter();
        filter.eq("personId", Long.valueOf(person.getGuid()));
        filter.eq("deviceId", device.getId());

        DevicePersonObj obj = dao.findUniqueByFilter(filter, DevicePersonObj.class);
        if (obj != null) {
            dao.delete(obj);
        }
    }

    /**
     * 获得所有该人员关联的设备：
     */
    @Override
    @Transactional(readOnly = true)
    public List<IDevice> listDeviceByPerson(IPerson person) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Device t ,DevicePersonObj t1 ");
        hql.append("where t.id = t1.deviceId and t1.personId = ?");
        List<IDevice> devices = dao.findByHQL(hql.toString(), Long.valueOf(person.getGuid()));
        return devices;
    }

    /**
     * 分页显示获得条件下的该人员关联的设备：
     */
    @SuppressWarnings("all")
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IDevice> pageDeviceByPerson(int offset, int limit, IPerson person, IFilter filter) {

        // 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
        IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {

            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        fi.eq("o.personId", Long.valueOf(person.getGuid()));

        StringBuffer hql = new StringBuffer();
        hql.append("select d from Device d ,DevicePersonObj o ");
        hql.append("where d.id = o.deviceId ");

        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hql.toString(), null);
    }

    /**
     * 分页显示获得条件下的该人员关联的设备：
     */
    @SuppressWarnings("all")
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IDevice> pageDeviceByTypePerson(int offset, int limit, IPerson person, IFilter filter,
            String orgId, boolean flag) {

        // 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
        IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        // 添加条件
        IOrganization org = orgService.get(orgId);

        if (flag) {
            fi.llike("d.organization.orgFlag", org.getOrgFlag());
        } else {
            fi.llike("d.devService.orgFlag", org.getOrgFlag());
        }

        fi.eq("o.personId", Long.valueOf(person.getGuid()));

        // hql拼写
        StringBuffer hqls = new StringBuffer();
        hqls.append(" select d from Device d, DevicePersonObj o ");
        hqls.append(" where d.id = o.deviceId ");

        // 分页查询
        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hqls.toString(), null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPerson> listPersonByDevice(String terminalId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Person t ,Device d,DevicePersonObj t1 ");
        hql.append("where d.terminalId=? and t1.deviceId = d.id and t.id = t1.personId");
        List<IPerson> persons = dao.findByHQL(hql.toString(), terminalId);
        return persons;
    }

    @SuppressWarnings("all")
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IDevice> pageUnlinkDeviceByPerson(int offset, int limit, IPerson person, IFilter filter,
            String bankOrgId, String serOrgId) {

        // 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
        IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {

            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        // 添加条件
        IOrganization bankOrg = orgService.get(bankOrgId);
        IOrganization serOrg = orgService.get(serOrgId);
        fi.llike("d.organization.orgFlag", bankOrg.getOrgFlag());
        fi.llike("d.devService.orgFlag", serOrg.getOrgFlag());

        // hql拼写
        StringBuffer hqls = new StringBuffer();
        hqls.append("select DISTINCT d from Device d WHERE NOT EXISTS ( ");
        hqls.append(" select dp.deviceId from DevicePersonObj dp where dp.deviceId = d.id and dp.personId = ? )");

        // 分页查询
        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hqls.toString(), Long.valueOf(person.getGuid()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IPerson> listAdminMaintainPersonByDevice(String terminalId, PersonType personType) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Person t ,Device d,DevicePersonObj t1 ");
        hql.append("where d.terminalId=? and t1.deviceId = d.id and t.type=? and t.id = t1.personId");
        List<IPerson> persons = dao.findByHQL(hql.toString(), terminalId, personType);
        return persons;
    }

}
