package com.yihuacomputer.fish.version.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.version.entity.VersionTypeAtmTypeRelation;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class VersionTypeAtmTypeRelationService implements IVersionTypeAtmTypeRelationService{

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IVersionService VersionService;
	
	@Override
	public void link(long versionTypeId, long atmTypeId) {
		VersionTypeAtmTypeRelation relation = new VersionTypeAtmTypeRelation();
		relation.setAtmTypeId(atmTypeId);
		relation.setVersionTypeId(versionTypeId);
		dao.save(relation);
	}

	@Override
	public List<Long> getAtmTypeIdsByVersionId(long versionId){
		IVersion version = VersionService.getById(versionId);
		if(null==version){
			return null;
		}
		return this.findAtmTypeIds(version.getVersionType().getId());
	}
	
	@Override
	public void unlink(long versionTypeId, long atmTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("delete from VersionTypeAtmTypeRelation where atmTypeId = ? and versionTypeId = ?");
		dao.batchUpdate(hql.toString(), atmTypeId,versionTypeId);
	}

	@Override
	public List<IAtmType> findAtmTypes(long versionTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select atmType from VersionTypeAtmTypeRelation relation,AtmType atmType ");
		hql.append("where atmType.id = relation.atmTypeId and relation.versionTypeId = ?");
		return dao.findByHQL(hql.toString(), versionTypeId);
	}
	
	@Override
	public List<Long> findAtmTypeIds(long versionTypeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select atmTypeId from VersionTypeAtmTypeRelation relation ");
		hql.append("where relation.versionTypeId = ?");
		return dao.findByHQL(hql.toString(), versionTypeId);
	}

	@Override
	public void link(long versionTypeId, List<Long> atmTypeIds) {
		for(Long atmTypeId : atmTypeIds){
			link(versionTypeId,atmTypeId);
		}
	}

}
