package com.yihuacomputer.fish.parameter.service;

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
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.parameter.entity.ParamTemplate;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDeviceRelation;

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
		// TODO Auto-generated method stub
		return null;
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
	public void update(IParamTemplate element) {
		// TODO Auto-generated method stub

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

		// 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
		IFilter fi = new Filter();
		for (IFilterEntry entry : filter.entrySet()) {

			if (entry.getValue() == null) {
				continue;
			}

			fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry
					.getValue(), entry.getOperator()));
		}

		// hql拼写
		StringBuffer hqls = new StringBuffer();
		hqls.append("select DISTINCT d from Device d WHERE NOT EXISTS ( ");
		hqls.append(" select dp.deviceId from ParamTemplateDeviceRelation dp where dp.deviceId = d.id and dp.templateId = ? )");
		hqls.append(" and d.id not in (select dp.deviceId from ParamTemplateDeviceRelation dp )");

		// 分页查询
		return (IPageResult<IDevice>) dao.page(offset, limit, fi,
				hqls.toString(), (Long) (template.getId()));
	}

	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit,
			IParamTemplate template, IFilter filter) {

		IFilter fi = new Filter();
		for (IFilterEntry entry : filter.entrySet()) {

			if (entry.getValue() == null) {
				continue;
			}

			fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry
					.getValue(), entry.getOperator()));
		}

		StringBuffer hql = new StringBuffer();
		hql.append("select d from Device d ,ParamTemplateDeviceRelation t ");
		hql.append("where d.id = t.deviceId ");

		return (IPageResult<IDevice>) dao.page(offset, limit, fi,
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
	public void link(IParamTemplate template, IDevice device) {
		dao.save(ParamTemplateDeviceRelation.make(template.getId(),
				device.getId()));

	}

	@Override
	public void unlink(IParamTemplate template, IDevice device) {
		Filter filter = new Filter();
		filter.eq("templateId", template.getId());
		filter.eq("deviceId", device.getId());

		ParamTemplateDeviceRelation obj = dao.findUniqueByFilter(filter,
				ParamTemplateDeviceRelation.class);
		if (obj != null) {
			dao.delete(obj.getId(), ParamTemplateDeviceRelation.class);
		}

	}

	@Override
	public List<IParamElement> listParamByTemplate(long templateId) {

		StringBuffer hql = new StringBuffer();
		
		hql.append("select DISTINCT e from ParamElement e WHERE NOT EXISTS ( ");
		hql.append(" select er.elementId from ParamTemplateElementRelation er where er.elementId = e.id and er.templateId = ? )");
		hql.append(" and e.id not in (select er.elementId from ParamTemplateElementRelation er )");
		
		List<IParamElement> elements = dao.findByHQL(hql.toString(), templateId);
		
		return elements ;
	}
}
