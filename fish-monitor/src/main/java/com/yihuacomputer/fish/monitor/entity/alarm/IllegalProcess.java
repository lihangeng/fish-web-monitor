package com.yihuacomputer.fish.monitor.entity.alarm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
/**
 * 设备黑名单进程信息
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_ILLEGAL_PROCESS")
public class IllegalProcess implements IIllegalProcess{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_ILLEGAL_PROCESS")
	@SequenceGenerator(name = "SEQ_DEV_ILLEGAL_PROCESS", sequenceName = "SEQ_DEV_ILLEGAL_PROCESS")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Column(name = "NAME",nullable=false,length = 50)
	private String name;
	
	@Column(name = "PROCESS_USER",length = 30)
	private String user;
	
	@Column(name = "CPU_RATE")
	private double cpuRate;
	
	@Column(name = "MEMORY_RATE")
	private long memoryRate;
	
	@Column(name = "PROCESS_DATE",length = 20)
	private String date;
	
	public long getId(){
		return this.id;
	}
	public void setId(long id){
		this.id = id;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(double cpuRate) {
		this.cpuRate = cpuRate;
	}
	public long getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(long memoryRate) {
		this.memoryRate = memoryRate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
