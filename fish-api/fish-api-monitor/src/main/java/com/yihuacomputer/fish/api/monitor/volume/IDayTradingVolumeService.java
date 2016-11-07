package com.yihuacomputer.fish.api.monitor.volume;


public interface IDayTradingVolumeService {
	
	IDayTradingVolume make();
	IDayTradingVolume save(IDayTradingVolume dayTradingVolume);
	IDayTradingVolume update(IDayTradingVolume dayTradingVolume);
	void remove(IDayTradingVolume dayTradingVolume);
}
