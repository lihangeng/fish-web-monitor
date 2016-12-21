package com.yihuacomputer.fish.monitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.monitor.entity.business.TransType;

@Service
@Transactional
public class TransTypeService implements ITransTypeService {

    @Autowired
    private IGenericDao dao;

    @Override
    public ITransType make() {
        return new TransType();
    }

    @Override
    public ITransType make(String transCode,String remark) {
        ITransType type = make();
        type.setTransCode(transCode);
        type.setCodeDesc(remark);
        return type;
    }

    @Override
    public ITransType add(ITransType transType) {
        return dao.save(transType);
    }

    @Override
    public void update(ITransType transType) {
        dao.update(transType);
    }

    @Override
    public void delete(long transTypeId) {
        dao.delete(transTypeId, TransType.class);
    }

    @Override
    public List<ITransType> list(IFilter filter) {
        return dao.findByFilter(filter, ITransType.class);
    }
    
    @Override
    public Map<String,ITransType> getTransTypeMap(){
    	List<ITransType> transTypeList =  dao.findByFilter(new Filter(), ITransType.class);
    	Map<String,ITransType> map = new HashMap<String,ITransType> ();
    	for(ITransType transType:transTypeList){
    		map.put(transType.getTransCode(), transType);
    	}
    	return map;
    }

    @Override
    public IPageResult<ITransType> page(int start, int limit, IFilter filter) {
        return dao.page(start, limit, filter, TransType.class);
    }

}
