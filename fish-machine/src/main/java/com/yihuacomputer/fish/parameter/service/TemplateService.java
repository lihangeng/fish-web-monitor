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
import com.yihuacomputer.fish.api.parameter.ITemplate;
import com.yihuacomputer.fish.api.parameter.ITemplateService;
import com.yihuacomputer.fish.parameter.entity.Template;
import com.yihuacomputer.fish.parameter.entity.TemplateDeviceRelation;

@Service
@Transactional
public class TemplateService implements ITemplateService {
	
	@Autowired
	private IGenericDao dao;


	@Override
	public ITemplate make() {
		
		return new Template();
	
	}

	@Override
	public ITemplate get(long id) {
		return dao.get(id, Template.class);
	}

	@Override
	public ITemplate get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITemplate add(ITemplate template) {
		
		return dao.save(template);
			
	}

	@Override
	public void remove(long id) {
		dao.delete(id,Template.class);
	}

	@Override
	public void update(ITemplate element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPageResult<ITemplate> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, Template.class);
	}
	
	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageUnlinkedDevice(int offset, int limit,ITemplate template, IFilter filter) {

        // 由于不知道传过来的filter参数没有设置表别名，所以重新处理加上表别名
        IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {

            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        // hql拼写
        StringBuffer hqls = new StringBuffer();
        hqls.append("select DISTINCT d from Device d WHERE NOT EXISTS ( ");
        hqls.append(" select dp.deviceId from TemplateDeviceRelation dp where dp.deviceId = d.id and dp.templateId = ? )");
        hqls.append(" and d.id not in (select dp.deviceId from TemplateDeviceRelation dp )");

        // 分页查询
        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hqls.toString(), (Long)(template.getId()));
    }

	
	@SuppressWarnings("all")
	@Override
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit,ITemplate template, IFilter filter) {
			
		IFilter fi = new Filter();
        for (IFilterEntry entry : filter.entrySet()) {

            if (entry.getValue() == null) {
                continue;
            }

            fi.addFilterEntry(new FilterEntry("d." + entry.getKey(), entry.getValue(), entry.getOperator()));
        }

        StringBuffer hql = new StringBuffer();
        hql.append("select d from Device d ,TemplateDeviceRelation t ");
        hql.append("where d.id = t.deviceId ");

        return (IPageResult<IDevice>) dao.page(offset, limit, fi, hql.toString(), null);
	}

	@Override
	public List<IDevice> listDeviceByTemplate(ITemplate template) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Device t ,TemplateDeviceRelation t1 ");
        hql.append("where t.id = t1.deviceId and t1.templateId = ?");
        List<IDevice> devices = dao.findByHQL(hql.toString(),template.getId());
        return devices;
    }

	@Override
	public void link(ITemplate template, IDevice device) {
		 dao.save(TemplateDeviceRelation.make(template.getId(), device.getId()));
		
	}

	@Override
	public void unlink(ITemplate template, IDevice device) {
		 	Filter filter = new Filter();
	        filter.eq("templateId", template.getId());
	        filter.eq("deviceId", device.getId());

	        TemplateDeviceRelation obj = dao.findUniqueByFilter(filter, TemplateDeviceRelation.class);
	        if (obj != null) {
	            dao.delete(obj.getId(), TemplateDeviceRelation.class);
	        }
		
	}}
