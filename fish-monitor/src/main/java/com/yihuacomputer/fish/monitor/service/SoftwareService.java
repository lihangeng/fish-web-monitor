package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.monitor.entity.software.Software;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class SoftwareService implements ISoftwareService{

	@Autowired
	private IGenericDao dao;

	@Override
	public ISoftware make() {
		return new Software();
	}

	@Override
	public ISoftware load(String terminalId) {		
		return this.dao.get(terminalId, Software.class);
	}

	@Override
	public void save(ISoftware software) {
		this.dao.save(software);		
	}

	@Override
	public void delete(String terminalId) {
		this.delete(this.load(terminalId));	
	}

	@Override
	public void delete(ISoftware software) {
		this.dao.delete(software);		
	}

	@Override
	public void init(String terminalId) {
		ISoftware software = this.make();
		software.setTerminalId(terminalId);
		this.save(software);		
	}

	@Override
	public void update(ISoftware software) {
		this.dao.update(software);
	}

}
