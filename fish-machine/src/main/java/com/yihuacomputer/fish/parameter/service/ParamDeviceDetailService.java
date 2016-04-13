package com.yihuacomputer.fish.parameter.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.DeviceParam;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.parameter.entity.ParamDeviceDetail;

@Service
@Transactional
public class ParamDeviceDetailService implements IParamDeviceDetailService {
	
	@Autowired
	private IGenericDao dao;

	@Override
	public IParamDeviceDetail get(long id) {
		return dao.get(id, ParamDeviceDetail.class);
	}

	@Override
	public void update(IParamDeviceDetail paramDeviceDetail) {
		dao.update(paramDeviceDetail);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<DeviceParam> list(IFilter filter, long tabId, long deviceId) {
		StringBuffer sql= new StringBuffer();
		
		sql.append("select pdd.id ppdid,pc.id pcid,pc.name, pe.PARAM_NAME ,pe.PARAM_VALUE pevalue,pdd.PARAM_VALUE  pddvalue ");
		sql.append("FROM PARAM_CLASSIFY pc inner join param_element pe on pc.id = pe.PARAM_CLASSIFY ");
		sql.append("left join param_device_detail pdd on pe.id = pdd.ELEMENT_ID ");
		Object paramName=filter.getValue("paramName");
		if(paramName != null){
			sql.append("and pe.PARAM_NAME = '").append(String.valueOf(paramName)).append("' ");
		}
		Object paramClassifyId = filter.getValue("ClassifyId");
		if(paramClassifyId !=null){
			sql.append("and pe.PARAM_CLASSIFY = '").append(String.valueOf(paramClassifyId)).append("' ");
		}
		sql.append("AND pe.PARAM_BELONGS = '").append(tabId+"'");
		sql.append(" AND pdd.DEVICE_ID = '").append(deviceId+"'");
		sql.append(" group by pe.PARAM_NAME");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<Object> infos = query.list();
		List<DeviceParam> resultList=new ArrayList<DeviceParam>();
		for(Object object : infos){
			Object[] objs = (Object[]) object;
			DeviceParam dp=new DeviceParam();
			dp.setId(Long.parseLong(objs[0]==null?"0":String.valueOf(objs[0])));
			dp.setParamClassifyId(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1])));
			dp.setParamClassify(objs[2]==null?"":String.valueOf(objs[2]));
			dp.setParamName(objs[3]==null?"":String.valueOf(objs[3]));
			dp.setElementParamValue(objs[4]==null?"":String.valueOf(objs[4]));
			dp.setParamValue(objs[5]==null?"":String.valueOf(objs[5]));
			resultList.add(dp);
		}
		
		return resultList;
	}

	@Override
	public Iterable<IParamDeviceDetail> list() {
		return dao.loadAll(IParamDeviceDetail.class);
	}

	@Override
	public List<IParamDeviceDetail> getVersionTimeStampData(long id, long timestamp) {
		StringBuffer hql1= new StringBuffer();
		StringBuffer hql2= new StringBuffer();
		hql1.append("select pdd from ParamDeviceDetail pdd where pdd.device.id = ");
		hql1.append(id);
		hql1.append(" and pdd.versionTimeStamp > ");// and pdd.element.paramBelongs.id = 1 
		hql1.append(timestamp);
		List<IParamDeviceDetail> result = dao.findByHQL(hql1.toString());
		hql2.append("select pdd from ParamDeviceDetail pdd where pdd.device.id = ");
		hql2.append(id);
		List<IParamDeviceDetail> resultList = dao.findByHQL(hql2.toString());
		if(result.size()==0){
			return null;
		}else{
			return resultList;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<DeviceParam> paramList(IFilter filter, long tabId, long deviceId) {
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT device.id pddid,classify.id pcid,classify.name,element.id peid,element.param_Name,element.param_Value peValue,detail.param_Value templateValue,device.param_Value deviceValue ");
		sql.append("from param_classify classify join param_element element on classify.ID=element.PARAM_CLASSIFY ");
		sql.append("join param_template_detail detail on element.id= detail.ELEMENT_ID ");
		sql.append("left join param_device_detail device on detail.ELEMENT_ID=device.ELEMENT_ID  ");
		
		Object paramName=filter.getValue("paramName");
		if(paramName != null){
			sql.append("and element.param_Name = '").append(String.valueOf(paramName)).append("' ");
		}
		Object paramClassifyId = filter.getValue("ClassifyId");
		if(paramClassifyId !=null){
			sql.append("and element.PARAM_CLASSIFY = '").append(String.valueOf(paramClassifyId)).append("' ");
		}
		sql.append("AND element.PARAM_BELONGS = '").append(tabId+"'");
		sql.append(" AND device.DEVICE_ID = '").append(deviceId+"'");
		sql.append(" group by element.param_Name");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<Object> infos = query.list();
		List<DeviceParam> resultList=new ArrayList<DeviceParam>();
		for(Object object : infos){
			Object[] objs = (Object[]) object;
			DeviceParam dp=new DeviceParam();
			dp.setId(Long.valueOf(objs[0]==null?"0":String.valueOf(objs[0])));
			dp.setParamClassifyId(Long.parseLong(objs[1]==null?"0":String.valueOf(objs[1])));
			dp.setParamClassify(objs[2]==null?"":String.valueOf(objs[2]));
			dp.setElementId(Long.parseLong(objs[3]==null?"0":String.valueOf(objs[3])));
			dp.setParamName(objs[4]==null?"":String.valueOf(objs[4]));
			dp.setElementParamValue(objs[5]==null?"":String.valueOf(objs[5]));
			dp.setTemplateParamValue(objs[6]==null?"":String.valueOf(objs[6]));
			dp.setParamValue(objs[7]==null?"":String.valueOf(objs[7]));
			resultList.add(dp);
		}
		
		return resultList;
	}

	@Override
	public IParamDeviceDetail make() {
		return new ParamDeviceDetail();
	}

	@Override
	public IParamDeviceDetail add(IParamDeviceDetail paramDeviceDetail) {
		return dao.save(paramDeviceDetail);
	}
}
