package com.yihuacomputer.fish.advert.bs.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroup;
import com.yihuacomputer.fish.advert.bs.entity.BsAdvert;
import com.yihuacomputer.fish.api.advert.bs.BsAdvertStatus;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResourceService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.version.VersionCfg;

@Service
@Transactional
public class BsAdvertService implements IBsAdvertService {

	@Autowired
	private IGenericDao dao;
	@Autowired
	private IBsAdvertResourceService bsAdvertResourceService;
	@Autowired
	private IAdvertGroupDeviceRelationService advertGroupDeviceRelationService;
	@Autowired
	private IAdvertGroupService advertGroupService;
	
	@Autowired
	private IParamService paramService;	
	
	@Override
	public IBsAdvert make() {
		return new BsAdvert();
	}

	@Override
	public IBsAdvert save(IBsAdvert advert) {
		return dao.save(advert);
	}

	@Override
	public IBsAdvert update(IBsAdvert advert) {
		return dao.update(advert);
	}

	@Override
	public void deleteById(long id) {
		IBsAdvert advert = getById(id);
		if(null!=advert){
			delete(advert);
		}
	}

	@Override
	public void delete(IBsAdvert advert) {
		dao.delete(advert);
	}

	@Override
	public IBsAdvert getById(long id) {
		return dao.get(id, BsAdvert.class);
	}
	private final static String BsAdvertHql = "select advertGroup,advert from BsAdvert advert,AdvertGroup advertGroup where advert.groupId = advertGroup.id";
	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<Object> page(int start, int limit, IFilter filter) {
		StringBuffer sb = new StringBuffer(BsAdvertHql);
		List<Object> argsList = new ArrayList<Object>();
		Object groupId = filter.getValue("groupId");
		if(groupId!=null){
			sb.append(" and advert.groupId = ? ");
			argsList.add(Long.parseLong(String.valueOf(groupId)));
		}
		Object advertName = filter.getValue("advertName");
		if(advertName!=null){
			sb.append(" and advert.advertName like ? ");
			argsList.add("%"+advertName+"%");
		}
		Object actived = filter.getValue("bsAdvertStatus");
		if(actived!=null){
			sb.append(" and advert.bsAdvertStatus = ? ");
			argsList.add(actived);
		}
		Object orgId = filter.getValue("orgId");
		if(orgId!=null){
			sb.append(" and advertGroup.orgId = ? ");
			argsList.add(orgId);
		}
		Object updateTimeEnd=filter.getValue("updateTimeEnd");

		if(updateTimeEnd!=null){
			sb.append(" and advert.lastTime <= ? ");
			argsList.add(updateTimeEnd);
		}
		Object updateTimeStart=filter.getValue("updateTimeStart");

		if(updateTimeStart!=null){
			sb.append(" and advert.lastTime >= ? ");
			argsList.add(updateTimeStart);
		}
		
		
		return (IPageResult<Object>)dao.page(start, limit, sb.toString(), argsList.toArray());
	}

	@Override
	public List<IBsAdvert> list(IFilter filter) {
		return dao.findByFilter(filter, IBsAdvert.class);
	}

	@Override
	public IBsAdvertResourceService getBsAdvertResourceService() {
		return bsAdvertResourceService;
	}
	
	public List<IBsAdvert> getBsAdvertByNameAndOrgId(long orgId,String advertName){
		StringBuffer hql= new StringBuffer("select bsAdvert from ");
		hql.append(BsAdvert.class.getSimpleName()).append(" bsAdvert, ").
		append(AdvertGroup.class.getSimpleName()).append(" advertGroup ").
		append("where bsAdvert.groupId=advertGroup.id and advertGroup.orgId = ? ").
		append(" and bsAdvert.advertName=?");
		List<IBsAdvert> list = dao.findByHQL(hql.toString(), orgId,advertName);
		return list;
	}
	
	public IBsAdvert actived(IBsAdvert bsAdvert){
		IFilter filter = new Filter();
		filter.eq("groupId", bsAdvert.getGroupId());
		filter.ne("bsAdvertStatus", BsAdvertStatus.UNACTIVE);
		List<IBsAdvert> advertList = this.list(filter);
		for(IBsAdvert bs:advertList){
			bs.setBsAdvertStatus(BsAdvertStatus.UNACTIVE);
			this.update(bs);
		}
		bsAdvert.setBsAdvertStatus(BsAdvertStatus.ACTIVED);
		this.update(bsAdvert);
		//获取Bs广告服务器路径
		IParam param = paramService.getParam("bsAdvertServerPath");
		String path = param.getParamValue()+File.separator+bsAdvert.getId();
		IAdvertGroup advertGroup= advertGroupService.getById(bsAdvert.getGroupId());
		String targetPath = param.getParamValue()+File.separator+advertGroup.getPath();
		IOUtils.copyFileToDirectory(VersionCfg.getBsAdvertDir()+File.separator+bsAdvert.getId()+".zip", path);
		ZipUtils.unZip(path+File.separator+bsAdvert.getId()+".zip", targetPath,"UTF-8" );
		File file = new File(path+File.separator+bsAdvert.getId()+".zip");
		file.delete();
		updateAdvertGroupDeviceRelationService(bsAdvert);
		return bsAdvert;
	}
	
	private void updateAdvertGroupDeviceRelationService(IBsAdvert bsAdvert){
		String sbSql = "update ADV_GROUP_DEVICE_RELATION set ADVERT_ID=? where GROUP_ID=?";
		SQLQuery query = dao.getSQLQuery(sbSql);
		query.setParameter(0, bsAdvert.getId());
		query.setParameter(1, bsAdvert.getGroupId());
		query.executeUpdate();
	}

}
