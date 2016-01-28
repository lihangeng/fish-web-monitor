package com.yihuacomputer.fish.advert.bs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;

@Service
@Transactional
public class AdvertGroupService implements IAdvertGroupService {
	
	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IOrganizationService organizationService;
	
	public IAdvertGroup make(){
		return new AdvertGroup();
	}
	
	public IAdvertGroup save(IAdvertGroup advertGroup){
		return dao.save(advertGroup);
	}
	

	public IAdvertGroup update(IAdvertGroup advertGroup){
		return dao.update(advertGroup);
	}
	
	public void deleteById(long id){
		delete(getById(id));
	}
	
	public void delete(IAdvertGroup advertGroup){
		 dao.delete(advertGroup);
	}
	
	public IAdvertGroup getById(long id){
		return dao.get(id, AdvertGroup.class);
	}
	private final static String  AdvertGroupHql= "select advertGroup,org from AdvertGroup advertGroup,Organization org where org.id=advertGroup.orgId ";
	
	@SuppressWarnings("unchecked")
	public IPageResult<Object> page(int start, int limit, IFilter filter){
		StringBuffer sb = new StringBuffer();
		sb.append(AdvertGroupHql);
		List<Object> argList = new ArrayList<Object>();
		Object orgObj = filter.getValue("orgId");
		Object orgLevelIdObj = filter.getValue("orgLevel");
		Object groupNameObj = filter.getValue("groupName");
		if(null!=orgObj){
			IOrganization org = organizationService.get(String.valueOf(orgObj));
			if(org!=null){
				sb.append(" and org.orgFlag like ? ");
				argList.add(org.getOrgFlag()+"%");
			}
		}
		if(null!=orgLevelIdObj){
			sb.append(" and org.organizationLevel = ? ");
			OrganizationLevel orgLevel = (OrganizationLevel)orgLevelIdObj;
			argList.add(orgLevel);
		}
		if(null!=groupNameObj){
			sb.append(" and advertGroup.groupName like ? ");
			argList.add("%"+String.valueOf(groupNameObj)+"%");
		}
		return (IPageResult<Object>)dao.page(start, limit, sb.toString(), argList.toArray());
	}
	
	public List<IAdvertGroup> list(IFilter filter){
		return dao.findByFilter(filter, IAdvertGroup.class);
	}
	
}