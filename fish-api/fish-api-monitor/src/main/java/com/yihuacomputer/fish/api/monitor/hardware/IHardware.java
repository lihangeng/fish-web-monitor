package com.yihuacomputer.fish.api.monitor.hardware;

import java.util.List;

/**
 * 系统硬件信息
 * 
 * @author YiHua
 * 
 */
public interface IHardware {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public IBios getBios();

	/**
	 * @return
	 */
	public List<ICpu> getCpu();

	/**
	 * @return
	 */
	public IMemory getMemory();

	/**
	 * @return
	 */
	public List<IDisk> getHardDisk();

	/**
	 * @return
	 */
	public long getDiskMem();

	/**
	 * @return
	 */
	public IFrimware getFrimware();

	/**
	 * @param bios
	 */
	public void setBios(IBios bios);

	/**
	 * @param cpuList
	 */
	public void setCpu(List<ICpu> cpuList);

	/**
	 * @param memory
	 */
	public void setMemory(IMemory memory);
	
	/**
	 * @param hardDisk
	 */
	public void setHardDisk(List<IDisk> hardDisk);

	/**
	 * @param diskMem
	 */
	public void setDiskMem(long diskMem);

	/**
	 * @param frimware
	 */
	public void setFrimware(IFrimware frimware);
	
	/**
	 * @return
	 */
	public ICpu makeCpu();
	
	/**
	 * @return
	 */
	public IDisk makeDisk();

}
