package com.yihuacomputer.fish.version.service.db;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.version.entity.VersionType;

@Service
@Transactional
public class VersionTypeService implements IVersionTypeService {

	@Autowired
	private IGenericDao dao;


	@Override
	public IVersionType make() {
		return make(null);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType make(String typeName) {
		return new VersionType(typeName);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType getById(long id) {
		return dao.get(id, VersionType.class);
	}

	@Override
    @Transactional(readOnly = true)
	public IVersionType getByName(String typeName) {
		IFilter filter = new Filter();
		filter.eq("typeName", typeName);
		return dao.findUniqueByFilter(filter, VersionType.class);
	}

	@Override
	public IVersionType add(IVersionType versionType) {
		IVersionType versionTypeValue = dao.save(this.interface2Entity(versionType, false));
		 Date date = new Date();
     	StringBuffer sb = new StringBuffer();
     	sb.append("insert into VER_DEVICE_SOFT_VERSION (CREATED_TIME,LAST_UPDATED_TIME,TERMINAL_ID,TYPE_NAME,VERSION_NO,VERSION_STR,VERSION_TYPE_ID) ");
     	sb.append("select ?,?,TERMINAL_ID,?,?,?,? from DEV_INFO");
     	Query query = dao.getSQLQuery(sb.toString());
     	query.setDate(0,date);
     	query.setDate(1, date);
     	query.setString(2, versionTypeValue.getTypeName());
     	query.setString(3, "");
     	query.setString(4, "000000000000000000000000000000");
     	query.setLong(5, versionTypeValue.getId());
     	query.executeUpdate();
		return dao.save(this.interface2Entity(versionTypeValue, false));
	}

	@Override
	public void update(IVersionType versionType) {
		dao.update(this.interface2Entity(versionType, true));
	}

	@Override
	public void delete(IVersionType versionType) {
		delete(versionType.getId());
	}

	@Override
	public void delete(long id) {
	    IFilter filter = new Filter();
	    filter.eq("versionType.id", id);
	    List<IVersion> versions = dao.findByFilter(filter, IVersion.class);
	    if(versions.size() > 0){
	        throw new AppException(String.format("该软件分类下存在 %s 个版本信息,不能删除",versions.size()));
	    }else{
	    	IVersionType versionType = this.getById(id);
	    	if(null!=versionType){
		        String versionTypeName = versionType.getTypeName();
		        dao.delete(id, VersionType.class);
				String workHome = VersionCfg.getVersionDir();
		    	IOUtils.deleteDir(workHome+File.separatorChar+versionTypeName);
	    	}
	    }
	}

	@Override
    @Transactional(readOnly = true)
	public List<IVersionType> list(IFilter outerFilter) {
	    IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
		filter.eq("display", true);
		return dao.findByFilter(filter, IVersionType.class);
	}
	
    @Transactional(readOnly = true)
	public List<IVersionType> listContainsAdvert(IFilter filter){
	    return dao.findByFilter(filter, IVersionType.class);
	}

	@Override
    @Transactional(readOnly = true)
	public IPageResult<IVersionType> page(int offset, int limit, IFilter outerFilter) {
	    IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
        filter.eq("display", true);
		return dao.page(offset, limit, filter, VersionType.class);
	}

	private VersionType interface2Entity(IVersionType versionType, boolean load) {
		if (versionType instanceof VersionType) {
			return (VersionType) versionType;
		}
		return null;
	}

	@Override
	public List<IVersionType> listAll() {
		// TODO Auto-generated method stub
		return dao.loadAll(IVersionType.class);
	}

}
