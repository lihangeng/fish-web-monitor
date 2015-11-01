package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.device.ITempDevInfo;
import com.yihuacomputer.fish.api.device.ITempDevInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;
import com.yihuacomputer.fish.machine.entity.TempDevInfo;

@Service
@Transactional
public class TempDevInfoService implements ITempDevInfoService{	
	
		@Autowired
		private IGenericDao dao;

		@Autowired
		private IOrganizationService orgService;
		
		
	    public ITempDevInfo make(){
	    	
			return new  TempDevInfo();
		  }

		@Transactional(readOnly = true)
		public ITempDevInfo get(long id) {
			return dao.get(id, TempDevInfo.class);
		}

		
		@Transactional(readOnly = true)
		public ITempDevInfo get(String code) {
			ITempDevInfo device = (ITempDevInfo) dao.getCriteria(ITempDevInfo.class)
					.add(Restrictions.eq("terminalId", code)).uniqueResult();
			return device;
		}

		
		@SuppressWarnings("unchecked")
		@Transactional(readOnly = true)
		public IPageResult<ITempDevInfo> page(int offset, int limit, IFilter filter, String orgId) {
			StringBuffer hql = new StringBuffer();
			List<Object> fixedFilters = new ArrayList<Object>();
			hql.append("from TempDevInfo tempDevice where 1=1 ");

			if (orgId != null && !"".equals(orgId)) {
				hql.append(" and tempDevice.organization.orgFlag like ? ");
				IOrganization org = orgService.get(orgId);
				fixedFilters.add("%" + org.getOrgFlag());
			}
			

			return (IPageResult<ITempDevInfo>) dao.page(offset, limit, filter, hql.toString(), fixedFilters.toArray());
		}
		
		
		public ITempDevInfo add(ITempDevInfo device) {
			return dao.save(device);
		}

		
		@Transactional(readOnly = true)
		public List<ITempDevInfo> list(IFilter filter) {
			List<ITempDevInfo> deviceList = dao
					.findByFilter(filter, ITempDevInfo.class);
			return deviceList;
		}
		
		public void remove(long id) {
			dao.delete(id, TempDevInfo.class);
			System.out.println("delete");
		}

		public void remove(String code) {
			remove(this.get(code).getId());
		}

		public void update(ITempDevInfo device) {
			dao.update(device);
		}

		
	   @Transactional(readOnly = true)
	    public List<ITempDevInfo> list(String hql, List<Object> fixedFilters) {
		      return dao.findByHQL(hql, fixedFilters.toArray());
		  }




	
}
