package com.yihuacomputer.fish.fault.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.IFaultCode;
import com.yihuacomputer.fish.api.fault.IFaultFilter;
import com.yihuacomputer.fish.api.fault.IFilterCode;

@Service
@Transactional
public class FaultFilter implements IFaultFilter {

	@Autowired
	private IGenericDao dao;
	
	private Map<String,String> filterCodeMap = new HashMap<String,String>();
	
	@Override
	public void initFilterCode() {
		List<IFilterCode> fiterCodeList = dao.loadAll(IFilterCode.class);
		if(fiterCodeList==null||fiterCodeList.isEmpty()){
			return;
		}
		for(IFilterCode filterCode:fiterCodeList){
			filterCodeMap.put(filterCode.getCode(), filterCode.getCode());
		}
	}
	@Override
	public boolean isFilter(IFaultCode faultCode) {
		if(filterCodeMap.get(faultCode.getFaultCode())!=null){
			return true;
		}else if(filterCodeMap.get(faultCode.getHwCode())!=null){
			return true;
		}
		return false;
	}



}
