package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * 系统内存信息
 * @author YiHua
 *
 */
public interface IMemory {
	/**
	 * @param memorySize
	 */
	public void setMemorySize(long memorySize);

	/**
	 * @param used
	 */
	public void setUsed(long used);

	/**
	 * @param free
	 */
	public void setFree(long free);

	/**
	 * @param usedPercent
	 */
	public void setUsedPercent(double usedPercent);

	/**
	 * @return
	 */
	public long getMemorySize();

	/**
	 * @return
	 */
	public long getUsed();

	/**
	 * @return
	 */
	public long getFree();

	/**
	 * @return
	 */
	public double getUsedPercent();

}
