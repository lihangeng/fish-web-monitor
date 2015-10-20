package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;
import com.yihuacomputer.fish.api.monitor.business.IHostRetService;
import com.yihuacomputer.fish.monitor.entity.business.HostRet;

@Service
@Transactional
public class HostRetService implements IHostRetService {

    @Autowired
    private IGenericDao dao;
    
	@Override
	public IHostRet make() {
		return new HostRet();
	}

	@Override
	public void save(IHostRet hostRet) {
		dao.save(hostRet);		
	}

}
