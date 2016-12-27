package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmTypeAtmModuleRelationService;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.machine.entity.AtmTypeAtmModuleRelation;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class AtmTypeAtmModuleRelationService implements IAtmTypeAtmModuleRelationService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IAtmTypeService AtmTypeService;

	@Override
	public void link(long atmTypeId, long atmModuleId) {
		AtmTypeAtmModuleRelation relation=new AtmTypeAtmModuleRelation();
		relation.setAtmModuleId(atmModuleId);
		relation.setAtmTypeId(atmTypeId);
		dao.save(relation);
		
	}

	@Override
	public void unlink(long atmTypeId, long atmModuleId) {
		StringBuffer hql = new StringBuffer();
		hql.append("delete from AtmTypeAtmModuleRelation where atmTypeId = ? and atmModuleId = ?");
		dao.batchUpdate(hql.toString(), atmTypeId,atmModuleId);
		
	}

	@Override
	public List<IAtmModule> findAtmModules(long atmTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select atmModule from AtmTypeAtmModuleRelation relation,AtmModule atmModule ");
		hql.append("where atmModule.id = relation.atmModuleId and relation.atmTypeId = ?");
		return dao.findByHQL(hql.toString(), atmTypeId);
		
	}

	@Override
	public List<Long> findAtmModuleIds(long atmTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select atmModuleId from AtmTypeAtmModuleRelation relation ");
		hql.append("where relation.atmTypeId = ?");
		return dao.findByHQL(hql.toString(), atmTypeId);
	}

	@Override
	public void link(long atmTypeId, List<Long> atmModuleIds) {
		for(Long atmModuleId : atmModuleIds){
			link(atmTypeId,atmModuleId);
		}
		
	}

	@Override
	public List<Long> getAtmModuleIdsByatmTypeId(long atmTypeId) {
		
		return null;
	}

    @Override
    public List<Object> list() {
        
        
        StringBuffer hql = new StringBuffer();
        
        hql.append("select at,am,ar ");
        hql.append(" from AtmType at, AtmModule am, AtmTypeAtmModuleRelation ar ");
        hql.append(" where at.id = ar.atmTypeId and am.id = ar.atmModuleId ");
        
        List<Object> param = new ArrayList<Object>();
        List<Object> result = dao.findByHQL(hql.toString(), param.toArray());
        
        
        return result;
    }
	
}
