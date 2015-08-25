package com.yihuacomputer.fish.api.monitor.hardware;
/**
 * 系统内存信息
 * @author YiHua
 *
 */
public interface IMemory {
	public void setMemorySize(long memorySize);

	public void setUsed(long used);

	public void setFree(long free);

	public void setUsedPercent(double usedPercent);

	public long getMemorySize();

	public long getUsed();

	public long getFree();

	public double getUsedPercent();

}
