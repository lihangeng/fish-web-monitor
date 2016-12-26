package com.yihuacomputer.fish.api.monitor.volume;


/**
 * @author YiHua
 *
 */
public interface IDayTradingVolumeService {
	
	/**
	 * @return
	 */
	IDayTradingVolume make();
	/**
	 * @param dayTradingVolume
	 * @return
	 */
	IDayTradingVolume save(IDayTradingVolume dayTradingVolume);
	/**
	 * @param dayTradingVolume
	 * @return
	 */
	IDayTradingVolume update(IDayTradingVolume dayTradingVolume);
	/**
	 * @param dayTradingVolume
	 */
	void remove(IDayTradingVolume dayTradingVolume);
}
