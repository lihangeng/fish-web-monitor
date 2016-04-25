package com.yihuacomputer.fish.parameter.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.parameter.entity.ParamClassify;
import com.yihuacomputer.fish.parameter.entity.ParamDeviceDetail;
import com.yihuacomputer.fish.parameter.entity.ParamElement;
import com.yihuacomputer.fish.parameter.entity.ParamTemplate;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDetail;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDeviceRelation;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateElementRelation;

/**
 * @author panxin
 *
 */
@Service
@Transactional
public class ParamTemplateService implements IParamTemplateService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IParamTemplate make() {

		return new ParamTemplate();

	}

	@Override
	public IParamTemplate get(long id) {
		return dao.get(id, ParamTemplate.class);
	}

	@Override
	public IParamTemplate get(String name) {
		ParamTemplate template = (ParamTemplate) dao.getCriteria(ParamTemplate.class).add(Restrictions.eq("name", name)).uniqueResult();
		return template;
	}

	@Override
	public IParamTemplate add(IParamTemplate template) {
		return dao.save(template);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, ParamTemplate.class);
	}


	@Override
	public IPageResult<IParamTemplate> page(int offset, int limit,
			IFilter filter) {
		return dao.page(offset, limit, filter, ParamTemplate.class);
	}

	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageUnlinkedDevice(int offset, int limit,
			IParamTemplate template, IFilter filter) {

		StringBuffer hqls = new StringBuffer();
		hqls.append("select DISTINCT d from Device d WHERE ");
		hqls.append(" d.id not in (select dp.deviceId from ParamTemplateDeviceRelation dp where dp.templateId = ?)");

		return (IPageResult<IDevice>) dao.page(offset, limit, filter,
				hqls.toString(), (Long) (template.getId()));
	}

	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit,
			IParamTemplate template, IFilter filter) {
		
		IFilter fi = new Filter();

		StringBuffer hql = new StringBuffer();
		hql.append("select d from Device d ,ParamTemplateDeviceRelation t ");
		hql.append("where d.id = t.deviceId ");

		return (IPageResult<IDevice>) dao.page(offset, limit, filter,
				hql.toString(), null);
	}

	@Override
	public List<IDevice> listDeviceByTemplate(IParamTemplate template) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from Device t ,ParamTemplateDeviceRelation t1 ");
		hql.append("where t.id = t1.deviceId and t1.templateId = ?");
		List<IDevice> devices = dao.findByHQL(hql.toString(), template.getId());
		return devices;
	}

	@Override
	public void link(IParamTemplate template , IDevice device , long timeStamp) {
		
		dao.save(ParamTemplateDeviceRelation.make(template.getId(),device.getId()));
		
	}

	@Override
	public void unlink(IParamTemplate template, IDevice device) {
		Filter filter = new Filter();
		filter.eq("templateId", template.getId());
		filter.eq("deviceId", device.getId());

		ParamTemplateDeviceRelation obj = dao.findUniqueByFilter(filter,ParamTemplateDeviceRelation.class);
				
		if (obj != null) {
			dao.delete(obj.getId(), ParamTemplateDeviceRelation.class);
		}
		
		removeTempDev(template.getId());

	}

	@Override
	public List<IParamElement> listParam(long templateId,long flag) {

		StringBuffer hql = new StringBuffer();

		List<IParamElement> elements = null;
		hql.append("select t from ParamElement t ");
		
		if(flag == 0){
			
			hql.append(",ParamTemplateElementRelation t1 ");
			hql.append("where t.id = t1.elementId ");
			hql.append("and t1.templateId = ?");
			elements = dao.findByHQL(hql.toString(), templateId);
		}else{
			elements = dao.findByHQL(hql.toString());
			
		}
		return elements;
	}
	
	@Override
	public void unlinkTempParam(IParamTemplate template, IParamElement emlement) {
		Filter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("templateId", template.getId()));
		filter.addFilterEntry(FilterFactory.eq("elementId", emlement.getId()));
		ParamTemplateElementRelation obj = dao.findUniqueByFilter(filter,
				ParamTemplateElementRelation.class);
		if (obj != null) {
			dao.delete(obj);
		}
		filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("paramTemplate.id", template.getId()));
		filter.addFilterEntry(FilterFactory.eq("paramElement.id", emlement.getId()));
		ParamTemplateDetail obj2 =  dao.findUniqueByFilter(filter,ParamTemplateDetail.class);
				
		if (obj2 != null) {
			dao.delete(obj2);
		}
	}

	@Override
	public void linkTempParam(IParamTemplate template, IParamElement emlement,String paramValue) {
		dao.save(ParamTemplateElementRelation.make(template.getId(),
				emlement.getId()));
		dao.save(ParamTemplateDetail.make(template, emlement,paramValue));
	}

	@Override
	public List<IParamTemplateDetail> listTemplateDetail(long id) {

		String hql = "select t from ParamTemplateDetail t where t.paramTemplate.id = ?";
		List<IParamTemplateDetail> detail = dao.findByHQL(hql.toString(), id);
		return detail;

	}

	@Override
	public boolean updateTemplateDetail(long templateId,Map<Long, String> newMap) {

		String hql = "select t from ParamTemplateDetail t where t.paramTemplate.id = ? and t.paramElement.id = ?";
		Set<Long> set = newMap.keySet();
		Iterator<Long> it = set.iterator();
		ParamTemplateDetail ptd = null;
		Long eleId = 0L;
		while (it.hasNext()) {
			eleId = it.next();
			ptd = dao.findUniqueByHql(hql, templateId, eleId);
			ptd.setParamValue(newMap.get(eleId));
			dao.update(ptd);
		}

		return true;
	}


	@Override
	public void unlinkAll(long templateId) {
		String hql = "delete from ParamTemplateElementRelation t where t.templateId = ?";
		dao.batchUpdate(hql, templateId);
		
		hql = "delete from ParamTemplateDetail t where t.paramTemplate.id = ?";
		dao.batchUpdate(hql, templateId);
	}


	@Override
	public IParamTemplateDetail getDetailByTemplateId(long templateId, long elementId) {
		Filter filter = new Filter();
		filter.eq("paramTemplate.id", templateId);
		filter.eq("paramElement.id", elementId);

		return dao.findUniqueByFilter(filter,IParamTemplateDetail.class);
	}

	@Override
	public void update(IParamTemplate template) {
		
		dao.update(template);
		
	}

	@Override
	public boolean duplicateTemplateName(String templateName) {
		Filter filter = new Filter();
		filter.eq("name", templateName);
		IParamTemplate template = dao.findUniqueByFilter(filter, IParamTemplate.class);
		if(template!=null){
			return true;
		}
		return false;
	}

	@Override
	public void unlinkAllBeforeDelete(long templateId) {

		String hql = "delete from ParamTemplateElementRelation t where t.templateId = ?";
		dao.batchUpdate(hql, templateId);
		
		hql = "delete from ParamTemplateDetail t where t.paramTemplate.id = ?";
		dao.batchUpdate(hql, templateId);
		
		hql = "delete from ParamTemplateDeviceRelation t where t.templateId = ?";
		dao.batchUpdate(hql, templateId);
		
	}

	@Override
	public void issueTemplate(IParamTemplate template, long timeStamp) {
		insertNewParam(template , timeStamp);
		
		updateTempDev(timeStamp , template.getId());
		
		removeTempDev(template.getId());
		
	}
	
	/**
	 * 更新设备参数表中参数版本
	 * @param timeStamp
	 * @param templateId
	 */
	public void updateTempDev(long timeStamp,long templateId) {
		String hql = "update ParamDeviceDetail t set t.versionTimeStamp = ? where t.device.id in "
				+ "(select t1.deviceId from ParamTemplateDeviceRelation t1 where t1.templateId = ?)"
				+ "and t.paramValue <> (select t1.paramValue from ParamTemplateDetail t1 where t1.paramTemplate.id = ? and t1.paramElement.id = t.element.id)";
		dao.batchUpdate(hql, timeStamp , templateId, templateId);
	}

	/**
	 * 应用版本时更新或插入设备参数表
	 * @param template
	 * @param timeStamp
	 */
	public void insertNewParam(IParamTemplate template , long timeStamp) {
		
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("SELECT T2.DEVICE_ID as deviceId,T1.ELEMENT_ID as elementId,T1.PARAM_VALUE as paramValue");
		sql.append(" FROM PARAM_TEMPLATE_DETAIL T1, PARAM_TEMPLATE_DEVICE_RELATION T2,PARAM_ELEMENT T3 ");
		sql.append("WHERE T1.TEMPLATE_ID = T2.TEMPLATE_ID  AND T3.ID = T1.ELEMENT_ID ");
		sql.append("AND T3.PARAM_VALUE <> T1.PARAM_VALUE AND T1.TEMPLATE_ID ='");
		sql.append(template.getId()+"'");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("deviceId", StandardBasicTypes.LONG);
		query.addScalar("elementId", StandardBasicTypes.LONG);
		query.addScalar("paramValue", StandardBasicTypes.STRING);
		
		List<?> infos = query.list();
		Filter filter = null;
		for(Object object : infos){
			Object[] objs = (Object[]) object;
			filter = new Filter();
			filter.eq("device.id", objs[0]);
			filter.eq("element.id", objs[1]);
			IParamDeviceDetail devDetail = dao.findUniqueByFilter(filter,IParamDeviceDetail.class);
			if(devDetail != null&&(!devDetail.getParamValue().equals((String)objs[2]))){
				devDetail.setParamValue((String)objs[2]);
				devDetail.setVersionTimeStamp(timeStamp);
				dao.update(devDetail);
			}else if (devDetail == null){
				devDetail = new ParamDeviceDetail();
				devDetail.setDevice(dao.get((Long)objs[0], Device.class));
				devDetail.setElement(dao.get((Long)objs[1], ParamElement.class));
				devDetail.setParamValue((String)objs[2]);
				devDetail.setVersionTimeStamp(timeStamp);
				dao.save(devDetail);
			}
			
		}
		
	}

	/**
	 * 删除设备参数表无用参数
	 * @param templateId
	 */
	public void removeTempDev(long templateId) {
		
		String hql = "delete from ParamDeviceDetail t1 "
				+ "where t1.element.id not in (select t2.paramElement.id from ParamTemplateDetail t2 , "
				+ "ParamTemplateDeviceRelation t3 where t2.paramTemplate.id = t3.templateId and t2.paramTemplate.id = ? "
				+ "group by t2.paramElement.id) and t1.device.id in ( select t3.deviceId from "
				+ "ParamTemplateDeviceRelation t3 where t3.templateId = ? group by t3.deviceId)";
		dao.batchUpdate(hql, templateId, templateId);
	}
	
	public List<IParamTemplateDetail> getParamTemplateDetailListByDeviceId(long deviceId){
		StringBuffer sb = new StringBuffer();
		sb.append("select templateDetail from ").append(ParamTemplateDetail.class.getSimpleName()).append(" templateDetail, ");
		sb.append(ParamTemplateDeviceRelation.class.getSimpleName()).append(" deviceDetail ");
		sb.append(" where deviceDetail.deviceId=? and deviceDetail.templateId=templateDetail.paramTemplate.id");
		List<IParamTemplateDetail> list = dao.findByHQL(sb.toString(), new Object[]{deviceId});
		return list;
	}
	
}
