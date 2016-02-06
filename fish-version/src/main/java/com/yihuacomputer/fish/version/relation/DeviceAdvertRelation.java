package com.yihuacomputer.fish.version.relation;

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
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.version.relation.IDeviceAdvertRelation;

@Service
@Transactional
public class DeviceAdvertRelation implements IDeviceAdvertRelation{

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;
	
	
	@Override
	public void link(IAdvertGroup advertGroup, IDevice device) {
		
		 dao.save(AdvertGroupDeviceRelation.make(Long.valueOf(advertGroup.getGuid()), device.getId()));
		
	}

	@Override
	public void unlink(IAdvertGroup advertGroup, IDevice device) {
		 Filter filter = new Filter();
	        filter.eq("groupId", Long.valueOf(advertGroup.getGuid()));
	        filter.eq("deviceId", device.getId());

	        AdvertGroupDeviceRelation obj = dao.findUniqueByFilter(filter, AdvertGroupDeviceRelation.class);
	        if (obj != null) {
	            dao.delete(obj.getId(), AdvertGroupDeviceRelation.class);
	        }
		
	}

    @SuppressWarnings("all")
    @Override
    @Transactional(readOnly = true)
	public IPageResult<IDevice> pageUnlinkDeviceByAdvertGroup(int offset,
			int limit, IAdvertGroup advertGroup, IFilter filter,
			String bankOrgId) {

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
        fi.rlike("d.organization.orgFlag", bankOrg.getOrgFlag());

        // hql拼写
        StringBuffer hqls = new StringBuffer();
        hqls.append("select DISTINCT d from Device d WHERE NOT EXISTS ( ");
        hqls.append(" select dp.deviceId from AdvertGroupDeviceRelation dp where dp.deviceId = d.id and dp.groupId = ? )");
        hqls.append(" and d.id not in (select dp.deviceId from AdvertGroupDeviceRelation dp,AdvertGroup ag where dp.groupId = ag.id and ag.orgId = ?)");

        // 分页查询
        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hqls.toString(), Long.valueOf(advertGroup.getGuid()),Long.valueOf(advertGroup.getOrgId()));
    }

	@Override
	public List<IDevice> listDeviceByAdvertGroup(IAdvertGroup advertGroup) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Device t ,AdvertGroupDeviceRelation t1 ");
        hql.append("where t.id = t1.deviceId and t1.groupId = ?");
        List<IDevice> devices = dao.findByHQL(hql.toString(), Long.valueOf(advertGroup.getGuid()));
        return devices;
    }

	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageDeviceByAdvertGroup(int offset, int limit,
			IAdvertGroup advertGroup, IFilter filter) {

        // 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
        IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {

            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        fi.eq("o.groupId", Long.valueOf(advertGroup.getGuid()));

        StringBuffer hql = new StringBuffer();
        hql.append("select d from Device d ,AdvertGroupDeviceRelation o ");
        hql.append("where d.id = o.deviceId ");

        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hql.toString(), null);
    }
	
	@Override
	public List<IAdvertGroup> listAdvertGroupByGroupId(Long groupId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from AdvertGroup t ,AdvertGroupDeviceRelation t1 ");
        hql.append("where t.id=t1.groupId and t1.groupId=? ");
        List<IAdvertGroup> advertGroup = dao.findByHQL(hql.toString(), groupId);
        return advertGroup;
    
	}

}
