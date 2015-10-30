package com.yihuacomputer.fish.machine.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IDevicePlanRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.IOpenPlanService;
import com.yihuacomputer.fish.machine.entity.DeviceOpenPlan;
import com.yihuacomputer.fish.machine.entity.OpenPlanDetail;

@Service
@Transactional
public class OpenPlanService implements IOpenPlanService {

	@Autowired
	private IGenericDao dao;

	@Override
	public DeviceOpenPlan saveOpenPlan(IDeviceOpenPlan openPlan) {
		if (openPlan instanceof IDeviceOpenPlan) {
			DeviceOpenPlan plan = (DeviceOpenPlan) openPlan;
			return dao.save(plan);
		}
		return null;
	}

	@Override
	public IDeviceOpenPlan getDeviceOpenPlanById(long openPlanId) {
		return dao.get(openPlanId, DeviceOpenPlan.class);
	}

	public IDeviceOpenPlan getDeviceOpenPlanByName(String name) {
		IDeviceOpenPlan openPlan = (IDeviceOpenPlan) dao.getCriteria(DeviceOpenPlan.class).add(Restrictions.eq("name", name)).uniqueResult();
		return openPlan;
	}

	@Override
	public void saveOpenPlanDetail(IOpenPlanDetail openPlanDetial) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IOpenPlanDetail> getOpenPlanDetialById(long openPlanId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OpenPlanDetail o ");
		hql.append("where o.deviceOpenPlan.id =? order by deviceOpenPlan, startTime ");
		List<IOpenPlanDetail> list = dao.findByHQL(hql.toString(), openPlanId);
		return list;
	}

	@Override
	public List<IDeviceOpenPlan> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceOpenPlan.class);
	}

	@Override
	public IPageResult<IDeviceOpenPlan> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, DeviceOpenPlan.class);
	}

	@Override
	public IDeviceOpenPlan updatePlan(IDeviceOpenPlan openPlan) {
		return dao.update(openPlan);
	}

	@Override
	public void deletePlan(long id) {
		dao.delete(this.getDeviceOpenPlanById(id));
	}

	@Override
	public IOpenPlanDetail make() {
		return new OpenPlanDetail();
	}

	@Override
	public List<IDevicePlanRelation> page(IFilter filter) {
		StringBuffer hql = new StringBuffer();

		hql.append("select r from DevicePlanRelation r, DeviceOpenPlan p ");
		hql.append("where r.openPlanId = p.id and ");
		hql.append("p.startDate <= ? and p.endDate >= ?");
		// List<IDeviceOpenPlan> doplist = dao.findByFilter(filter,
		// IDeviceOpenPlan.class);
		// // if(null!=doplist&&doplist.size()>0){
		// for(int i=0;i<doplist.size();i++){
		// hql.append(doplist.get(i).getId());
		// if(i!=doplist.size()-1){
		// hql.append(",");
		// }
		// }
		// hql.append(")");
		// // }
		List<IDevicePlanRelation> list = dao.findByHQL(hql.toString(), (Date) filter.getValue("startDate"), (Date) filter.getValue("endDate"));
		return list;
	}

	@Override
	public int deviceCount(Long id) {
		String hql = "select count(*) from Device device ,DevicePlanRelation r where device.id = r.deviceId and r.openPlanId = ? ";
		List<Object> count = dao.findByHQL(hql, id);
		if(count.size()>0){
			return Integer.parseInt(count.get(0)+"");
		}else{
			return 0;
		}

	}

	@Override
	public List<Object> deviceInfo(Long id){
		String hql = "select dev.terminalId, dev.ip, dev.organization.name, dev.devType.name, dev.devType.devCatalog.name" +
				" from Device dev, DevicePlanRelation r where r.deviceId = dev.id and r.openPlanId = ?";
		List<Object> devInfo = dao.findByHQL(hql, id);
		return devInfo;
	}



}
