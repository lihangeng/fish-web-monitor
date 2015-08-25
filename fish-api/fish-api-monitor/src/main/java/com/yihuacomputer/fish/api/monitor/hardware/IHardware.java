package com.yihuacomputer.fish.api.monitor.hardware;

import java.util.List;

/**
 * 系统硬件信息
 * 
 * @author YiHua
 * 
 */
public interface IHardware {
	
	public long getId();

	public String getTerminalId();
	
	public void setTerminalId(String terminalId);

	public IBios getBios();

	public List<ICpu> getCpu();

	public IMemory getMemory();

	public List<IDisk> getHardDisk();

	public long getDiskMem();

	public IFrimware getFrimware();

	public void setBios(IBios bios);

	public void setCpu(List<ICpu> cpuList);

	public void setMemory(IMemory memory);
	
	public void setHardDisk(List<IDisk> hardDisk);

	public void setDiskMem(long diskMem);

	public void setFrimware(IFrimware frimware);
	
	public ICpu makeCpu();
	
	public IDisk makeDisk();

}
