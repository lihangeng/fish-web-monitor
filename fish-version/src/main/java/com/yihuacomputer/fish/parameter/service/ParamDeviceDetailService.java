package com.yihuacomputer.fish.parameter.service;

import java.text.SimpleDateFormat;
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
		
		sql.append("SELECT t1.peid elementId,t1.pcid classifyId,t1.pcname classifyName,t1.paramName paramName, ");
		sql.append("t1.paramvalue paramvalue,t1.peType eleParamType,t1.peRights eleparamRights,t1.peModifyTime paramModifyTime,pdd.id pddid,pdd.param_value pddparamValue,pdd.VERSION_TIMESTAMP devparamModifyTime ");
		sql.append("FROM (select pc.ID pcid,pc.NAME pcname,pe.ID peid,pe.PARAM_NAME paramName, ");
		sql.append("pe.PARAM_VALUE paramvalue,pe.PARAM_TYPE peType,pe.PARAM_RIGHTS peRights,pe.LAST_MODIFY_TIME peModifyTime ");
		sql.append("FROM PARAM_CLASSIFY pc,PARAM_ELEMENT pe where pc.ID = pe.PARAM_CLASSIFY ");
		Object paramName=filter.getValue("paramName");
		if(paramName != null){
			sql.append("and pe.PARAM_NAME like '%").append(String.valueOf(paramName)).append("%' ");
		}
		Object paramClassifyId = filter.getValue("ClassifyId");
		if(paramClassifyId !=null){
			sql.append("and pe.PARAM_CLASSIFY = '").append(String.valueOf(paramClassifyId)).append("' ");
		}
		sql.append("AND pe.PARAM_BELONGS = '").append(tabId+"'");
		sql.append(" ) t1 left join PARAM_DEVICE_DETAIL pdd on  t1.peid = pdd.ELEMENT_ID");
		sql.append(" AND pdd.DEVICE_ID = '").append(deviceId+"'");
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
			dp.setEleParamType(objs[5]==null?"":String.valueOf(objs[5]));
			dp.setEleParamRights(objs[6]==null?"":String.valueOf(objs[6]));
			dp.setEleModifyTime(objs[7]==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objs[7]));
			dp.setDeviceParamId(Long.parseLong(objs[8]==null ? "0":String.valueOf(objs[8])));
			dp.setParamValue(objs[9]==null?"":String.valueOf(objs[9]));
			dp.setDevParamModifyTime(objs[10]==null?"":String.valueOf(objs[10]));
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

	
	@Override
	public IParamDeviceDetail make() {
		return new ParamDeviceDetail();
	}

	@Override
	public IParamDeviceDetail add(IParamDeviceDetail paramDeviceDetail) {
		return dao.save(paramDeviceDetail);
	}

	@Override
	public List<IParamDeviceDetail> list(IFilter filter) {
		return dao.findByFilter(filter, IParamDeviceDetail.class);
	}

	@Override
	public Iterable<IParamDeviceDetail> loadAll() {
		return dao.loadAll(IParamDeviceDetail.class);
	}

	@Override
	public IParamDeviceDetail getById(long elementId, long deviceId) {
		StringBuffer hql =new StringBuffer();
		hql.append("SELECT deviceDetail FROM ParamDeviceDetail deviceDetail WHERE deviceDetail.element.id= ?");
		hql.append(" AND deviceDetail.device.id =?");
		return dao.findUniqueByHql(hql.toString(), Long.valueOf(elementId),Long.valueOf(deviceId));
	}

}
