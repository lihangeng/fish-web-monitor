package com.yihuacomputer.fish.web.atm.format;

import java.util.List;

/**
 * 硬件信息
 * 
 * @author YiHua
 * 
 */
public class HardwareMsg {

	private String termId;

	private BiosMsg bios;

	private List<CpuMsg> cpu;

	private MemoryMsg memory;

	private List<DiskMsg> hardDisk;

	private long diskMem;

	private FrimwareMsg frimware;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public BiosMsg getBios() {
		return bios;
	}

	public void setBios(BiosMsg bios) {
		this.bios = bios;
	}

	public List<CpuMsg> getCpu() {
		return cpu;
	}

	public void setCpu(List<CpuMsg> cpu) {
		this.cpu = cpu;
	}

	public MemoryMsg getMemory() {
		return memory;
	}

	public void setMemory(MemoryMsg memory) {
		this.memory = memory;
	}

	public List<DiskMsg> getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(List<DiskMsg> hardDisk) {
		this.hardDisk = hardDisk;
	}

	public long getDiskMem() {
		return diskMem;
	}

	public void setDiskMem(long diskMem) {
		this.diskMem = diskMem;
	}

	public FrimwareMsg getFrimware() {
		return frimware;
	}

	public void setFrimware(FrimwareMsg frimware) {
		this.frimware = frimware;
	}

}
