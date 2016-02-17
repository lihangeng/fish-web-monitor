package com.yihuacomputer.fish.advert.bs.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.GroupType;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
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
	private final static String  AdvertGroupSql = "select b.group_id as id,a.advert_name as advertName,b.group_name as groupName,b.group_type as groupType , b.org_id orgId,b.resource_path path,b.org_level orgLevel,b.org_name orgName from (select advbsadvert.advert_name advert_name ,advbsadvert.group_id group_id from adv_bsadvert advbsadvert where advbsadvert.advert_stratus = 2 ) a right join (select advertgroup.id group_id,advertgroup.group_name group_name ,advertgroup.group_type group_type ,advertgroup.org_id org_id ,advertgroup.resource_path resource_path ,org.org_level org_level ,org.name org_name from adv_group advertgroup,sm_org org where org.id=advertgroup.org_id ";
	
	public IPageResult<Object> page(int start, int limit, IFilter filter){
		
		StringBuffer sb = new StringBuffer();
		sb.append(AdvertGroupSql);
		
		Object orgObj = filter.getValue("orgId");
		Object orgLevelIdObj = filter.getValue("orgLevel");
		Object groupNameObj = filter.getValue("groupName");
		Object groupTypeObj = filter.getValue("groupType");
		if(null!=orgObj){
			IOrganization org = organizationService.get(String.valueOf(orgObj));
			if(org!=null){
				sb.append(" and org.ORG_FLAG like '%");
				sb.append(org.getOrgFlag()+"'");
			}
		}
		if(null!=orgLevelIdObj){
			sb.append(" and org.ORG_LEVEL = '");
			OrganizationLevel orgLevel = (OrganizationLevel)orgLevelIdObj;
			sb.append(orgLevel.getId()+"'");
		}
		if(null!=groupNameObj){
			sb.append(" and advertGroup.GROUP_NAME like '%");
			sb.append(String.valueOf(groupNameObj)+"'");
		}
		if(null!=groupTypeObj){
			sb.append(" and advertGroup.GROUP_TYPE = '");
			GroupType groupType = (GroupType)groupTypeObj;
			sb.append(groupType.getId()+"'");
		}
		sb.append(") b on b.group_id = a.group_id group by b.group_id");
		
		int total = getTotal(sb.toString());
		SQLQuery query = dao.getSQLQuery(sb.toString());
		query.addScalar("id", StandardBasicTypes.LONG);
		query.addScalar("advertName", StandardBasicTypes.STRING);
		query.addScalar("groupName", StandardBasicTypes.STRING);
		query.addScalar("groupType", StandardBasicTypes.INTEGER);
		query.addScalar("orgId", StandardBasicTypes.LONG);
		query.addScalar("path", StandardBasicTypes.STRING);
		query.addScalar("orgLevel", StandardBasicTypes.INTEGER);
		query.addScalar("orgName", StandardBasicTypes.STRING);
		
		List<?> infos = query.list();
		List<Object> lists = new ArrayList<Object>();
		
		for (Object item : infos) {
			lists.add(item);
		}
		
		PageResult<Object> page = new PageResult<Object>();
		page.setData(lists);
		page.setTotal(total);
		
		return page;
	}
	
	public List<IAdvertGroup> list(IFilter filter){
		return dao.findByFilter(filter, IAdvertGroup.class);
	}

	@Override
	public IAdvertGroup orgHasAG(long orgId) {
		return dao.findUniqueByHql("from AdvertGroup ag where ag.orgId = ? and ag.groupType= 1 ", orgId);
	}

	@Override
	public IBsAdvert getBsAdvertByGroupId(long groupId) {
		return dao.findUniqueByHql("from BsAdvert bs where bs.groupId = ? and bs.bsAdvertStatus = 2 ", groupId);
	}
	
	@Override
	public List<IAdvertGroup> list(long orgId){
		StringBuffer hql=new StringBuffer("from ");
		hql.append(AdvertGroup.class.getSimpleName()).append(" advertGroup");
		hql.append(" where advertGroup.orgId=? ");
		List<IAdvertGroup> list=dao.findByHQL(hql.toString(), orgId);
		return list;
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	private int getTotal(String sqlStr) {
		StringBuffer sql = new StringBuffer();
		sql = sql.append("select COUNT(a.id) as total from (").append(sqlStr).append(") a");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("total", StandardBasicTypes.INTEGER);
		List<Integer> lists = query.list();
		return lists.get(0);
	}
}
