package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.RegStatus;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class RegistService implements IRegistService {

    @Autowired
	private IGenericDao dao;
    
	@Override
	public IDeviceRegister init(String terminalId) {
		IDeviceRegister reg = this.make();
		reg.setRegStatus(RegStatus.UNKNOWN);
		reg.setTerminalId(terminalId);
		this.save(reg);
		return reg;
	}

	@Override
	public IDeviceRegister make() {
		return new DeviceRegister();
	}

	@Override
	public void save(IDeviceRegister reg) {
		this.dao.save(reg);
	}

	@Override
	public IDeviceRegister load(String terminalId) {
		DeviceRegister reg = this.dao.get(terminalId, DeviceRegister.class);
		return reg;
	}

	@Override
	public void update(IDeviceRegister reg) {
		this.dao.update(reg);
	}

	@Override
	public void delete(IDeviceRegister reg) {	
		this.dao.delete(reg);		
	}
}
