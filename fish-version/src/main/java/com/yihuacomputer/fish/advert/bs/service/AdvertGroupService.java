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
	private final static String  AdvertGroupSql = "SELECT B.GROUP_ID AS ID,A.ADVERT_NAME AS ADVERTNAME,B.GROUP_NAME AS GROUPNAME,B.GROUP_TYPE AS GROUPTYPE , B.ORG_ID ORGID,B.RESOURCE_PATH PATH,B.ORG_LEVEL ORGLEVEL,B.ORG_NAME ORGNAME FROM (SELECT ADVBSADVERT.ADVERT_NAME ADVERT_NAME ,ADVBSADVERT.GROUP_ID GROUP_ID FROM ADV_BSADVERT ADVBSADVERT WHERE ADVBSADVERT.ADVERT_STRATUS = 1 ) A RIGHT JOIN (SELECT ADVERTGROUP.ID GROUP_ID,ADVERTGROUP.GROUP_NAME GROUP_NAME ,ADVERTGROUP.GROUP_TYPE GROUP_TYPE ,ADVERTGROUP.ORG_ID ORG_ID ,ADVERTGROUP.RESOURCE_PATH RESOURCE_PATH ,ORG.ORG_LEVEL ORG_LEVEL ,ORG.NAME ORG_NAME FROM ADV_GROUP ADVERTGROUP,SM_ORG ORG WHERE ORG.ID=ADVERTGROUP.ORG_ID ";
	
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
				sb.append(" AND ORG.ORG_FLAG LIKE '");
				sb.append(org.getOrgFlag()+"%'");
			}
		}
		if(null!=orgLevelIdObj){
			sb.append(" AND ORG.ORG_LEVEL = '");
			OrganizationLevel orgLevel = (OrganizationLevel)orgLevelIdObj;
			sb.append(orgLevel.getId()+"'");
		}
		if(null!=groupNameObj){
			sb.append(" AND ADVERTGROUP.GROUP_NAME LIKE '%");
			sb.append(String.valueOf(groupNameObj)+"'");
		}
		if(null!=groupTypeObj){
			sb.append(" AND ADVERTGROUP.GROUP_TYPE = '");
			GroupType groupType = (GroupType)groupTypeObj;
			sb.append(groupType.getId()+"'");
		}
		sb.append(") B ON B.GROUP_ID = A.GROUP_ID GROUP BY B.GROUP_ID ORDER BY B.GROUP_ID DESC");
		
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
		return dao.findUniqueByHql("from BsAdvert bs where bs.groupId = ? and bs.bsAdvertStatus = 1 ", groupId);
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
		sql = sql.append("SELECT COUNT(A.ID) AS TOTAL FROM (").append(sqlStr).append(") A");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("total", StandardBasicTypes.INTEGER);
		List<Integer> lists = query.list();
		return lists.get(0);
	}

	@Override
	public boolean dupGroupName(long orgId,String groupName) {
		
		Object advertGroup =  dao.findUniqueByHql("from AdvertGroup ag where ag.groupName = ? and ag.orgId = ?", groupName , orgId);
		
		if(advertGroup==null){
			return false;
		}
		return true;
	}
	@Override
	public boolean isExist(long groupId,long orgId,String groupName){
		Object advertGroup2=dao.findUniqueByHql("from AdvertGroup ag where ag.groupName = ? and ag.orgId = ? and ag.id != ? ", groupName,orgId,groupId);
		if(advertGroup2==null){
			return false;
		}
			return true;
	}
}
