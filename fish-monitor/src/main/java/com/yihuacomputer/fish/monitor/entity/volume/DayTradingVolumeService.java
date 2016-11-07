package com.yihuacomputer.fish.monitor.entity.volume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolume;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
@Service
@Transactional
public class DayTradingVolumeService implements IDayTradingVolumeService {
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IDayTradingVolume make() {
		return new DayTradingVolume();
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"terminalId","transDate"})
	@Override
	public IDayTradingVolume save(IDayTradingVolume dayTradingVolume) {
		return dao.save(dayTradingVolume);
	}

	@Override
	public IDayTradingVolume update(IDayTradingVolume dayTradingVolume) {
		return dao.update(dayTradingVolume);
	}

	@Override
	public void remove(IDayTradingVolume dayTradingVolume) {
		dao.delete(dayTradingVolume);
	}

}
