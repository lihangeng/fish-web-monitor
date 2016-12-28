package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;

/**
 * 黑名单进程信息
 * @author YiHua
 *
 */
public class ProcessReport {

	/** 用户名 */
	private String user;
	
	/**设备编号*/
	private String terminalId;
	
	/** 进程名称*/
	private String name;
	
	/**CPU利用率*/
	private double cpuRate;
	
	/**内存利用率*/
	private double memoryRate;
	
	/**进程描述*/
	private String description;
	
    /**进程监控时间*/	
	private String date;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(double cpuRate) {
		this.cpuRate = cpuRate;
	}
	public double getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(double memoryRate) {
		this.memoryRate = memoryRate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * @param process
     */
    public ProcessReport(IIllegalProcess process){
		this.user = process.getUser();
		this.name = process.getName();
		this.terminalId = process.getTerminalId();
		this.cpuRate = process.getCpuRate();
		this.memoryRate = process.getMemoryRate();	
		this.date = process.getDate();
	}
}
