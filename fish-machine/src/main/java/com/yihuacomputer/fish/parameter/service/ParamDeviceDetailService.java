package com.yihuacomputer.fish.parameter.service;

import java.util.List;

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

	@Override
	public List<DeviceParam> list(IFilter filter, long tabId, long deviceId) {
		StringBuffer hql= new StringBuffer();
		hql.append("select pdd.id,pc.id,pc.name, pe.paramName ,pe.paramValue ,pdd.paramValue ");
		hql.append("FROM ParamClassify pc INNER JOIN ParamElement pe on pc.id = pe.paramClassify.id ");
		hql.append("LEFT JOIN ParamDeviceDetail pdd on pe.id = pdd.element.id ");
		Object paramName=filter.getValue("paramName");
		if(paramName != null){
			hql.append("and pe.paramName = '").append(String.valueOf(paramName)).append("' ");
		}
		Object paramClassifyId = filter.getValue("ClassifyId");
		if(paramClassifyId !=null){
			hql.append("and pe.paramClassify.id = '").append(String.valueOf(paramClassifyId)).append("' ");
		}
		hql.append("AND pe.paramBelongs.id = ? AND pdd.device.id = ? ");
		List<DeviceParam> resultList=dao.findByHQL(hql.toString(), Long.valueOf(tabId),Long.valueOf(deviceId));
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

	@Override
	public List<DeviceParam> paramList(IFilter filter, long tabId, long deviceId) {
		StringBuffer hql=new StringBuffer();
		hql.append("SELECT device.id,classify.ID,classify.name,elemet.id,element.paramName,element.paramValue,detail.paramValue,device.paramValue ");
		hql.append("from ParamClassify classify INNER JOIN ParamElement element ON classify.ID=element.paramClassify.id ");
		hql.append("INNER JOIN ParamTemplateDetail detail on element.id= detail.paramElement.id ");
		hql.append("LEFT JOIN ParamDeviceDetail device on detail.paramElement.id=device.element.id ");
		
		Object paramName=filter.getValue("paramName");
		if(paramName != null){
			hql.append("and element.paramName = '").append(String.valueOf(paramName)).append("' ");
		}
		Object paramClassifyId = filter.getValue("ClassifyId");
		if(paramClassifyId !=null){
			hql.append("and element.paramClassify.id = '").append(String.valueOf(paramClassifyId)).append("' ");
		}
		hql.append("AND element.paramBelongs.id = ? AND device.device.id = ? ");
		hql.append(" group by element.paramName");
		List<DeviceParam> resultList=dao.findByHQL(hql.toString(), Long.valueOf(tabId),Long.valueOf(deviceId));
		return resultList;
	}

}
