package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.hardware.IBios;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.monitor.entity.hardware.Bios;
import com.yihuacomputer.fish.monitor.entity.hardware.Frimware;
import com.yihuacomputer.fish.monitor.entity.hardware.Hardware;
import com.yihuacomputer.fish.monitor.entity.hardware.Memory;

@Service
@Transactional
public class HardwareService implements IHardwareService{

	@Autowired
	private IGenericDao dao;	
	
	@Override
	public IHardware make() {
		Hardware hardware = new Hardware();
		IBios bois = new Bios();
		hardware.setBios(bois);
		Frimware frimware = new Frimware();
		hardware.setFrimware(frimware);
		Memory memory = new Memory();
		hardware.setMemory(memory);
		return hardware;
	}

	@Override
	public void save(IHardware hardware) {
		this.dao.save(hardware)	;
	}

	@Override
	public IHardware load(String terminalId) {
		return dao.findUniqueByHql("from Hardware hw where hw.terminalId = ?", terminalId);
	}

	@Override
	public void delete(String terminalId) {
		IHardware hardware = this.load(terminalId);
		if(hardware!=null){
			this.delete(hardware);
		}		
	}
	public void delete(IHardware hardware){
		if(hardware!=null){
			this.dao.delete(hardware);
		}
	}

	@Override
	public void init(String terminalId) {
		IHardware hardware = this.make();
		hardware.setTerminalId(terminalId);
		this.save(hardware);		
	}

	@Override
	public void update(IHardware hardware) {
		this.dao.saveOrUpdate((Hardware)hardware);		
	}

	@Override
	public void deleteCpu(String terminalId) {
		//this.dao.delete(arg0, arg1)
		
	}

	@Override
	public void deleteDisk(String terminalId) {
		// TODO Auto-generated method stub
		
	}
}
