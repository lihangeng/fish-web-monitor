package com.yihuacomputer.fish.web.atm.format;

/**
 * 硬件信息
 * @author YiHua
 * 
 */
public class HardwareForm {
	private int frequency;
	private String vendor;
	private String model;
	private long cacheSize;
	private int totalCores;
	private String user;
	private String sys;
	private String idle;
	private String combined;
	private String biosVersion;
	private String biosVendor;
	private String biosReleaseDate;
	private long totalSize;
	private long freeSize;
	private String fileSys;
	private String memo;
	private String labelAndname;
	private long diskMem;
	private long memorySize;
	private long used;
	private long free;
	private double usedPercent;

	public long getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public double getUsedPercent() {
		return usedPercent;
	}

	public void setUsedPercent(double usedPercent) {
		this.usedPercent = usedPercent;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getTotalCores() {
		return totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getIdle() {
		return idle;
	}

	public void setIdle(String idle) {
		this.idle = idle;
	}

	public String getCombined() {
		return combined;
	}

	public void setCombined(String combined) {
		this.combined = combined;
	}

	public String getBiosVersion() {
		return biosVersion;
	}

	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	public String getBiosVendor() {
		return biosVendor;
	}

	public void setBiosVendor(String biosVendor) {
		this.biosVendor = biosVendor;
	}

	public String getBiosReleaseDate() {
		return biosReleaseDate;
	}

	public void setBiosReleaseDate(String biosReleaseDate) {
		this.biosReleaseDate = biosReleaseDate;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getFreeSize() {
		return freeSize;
	}

	public void setFreeSize(long freeSize) {
		this.freeSize = freeSize;
	}

	public String getFileSys() {
		return fileSys;
	}

	public void setFileSys(String fileSys) {
		this.fileSys = fileSys;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLabelAndname() {
		return labelAndname;
	}

	public void setLabelAndname(String labelAndname) {
		this.labelAndname = labelAndname;
	}

	public long getDiskMem() {
		return diskMem;
	}

	public void setDiskMem(long diskMem) {
		this.diskMem = diskMem;
	}

}
