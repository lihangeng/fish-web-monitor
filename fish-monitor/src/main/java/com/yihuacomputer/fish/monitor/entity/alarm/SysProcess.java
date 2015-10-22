package com.yihuacomputer.fish.monitor.entity.alarm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.alarm.IProcess;

/**
 * 进程白名单信息
 * 
 * @author YiHua
 * 
 */
@Entity
@Table(name = "DEV_WHITE_PROCESS")
public class SysProcess implements IProcess {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_WHITE_PROCESS")
	@SequenceGenerator(name = "SEQ_DEV_WHITE_PROCESS", sequenceName = "SEQ_DEV_WHITE_PROCESS")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "PROCESS_USER", length = 30)
	private String user;

	@Column(name = "CPU_USE")
	private double cpuRate;

	@Column(name = "MEN_USE")
	private long memoryRate;

	@Column(name = "NOTE", length = 50)
	private String description;

	@Column(name = "PROCESS_DATE", length = 20)
	private String date;

	public SysProcess() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getCpuRate() {
		return this.cpuRate;
	}

	public void setCpuRate(double cpuRate) {
		this.cpuRate = cpuRate;
	}

	public long getMemoryRate() {
		return this.memoryRate;
	}

	public void setMemoryRate(long memoryRate) {
		this.memoryRate = memoryRate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDate() {
		return this.date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	public String toString() {
		return "Process [name=" + name + ", user=" + user + ", cpuRate=" + cpuRate + ", memoryRate=" + memoryRate + ", description=" + description + ", date=" + date + "]";
	}

	public void update(IProcess process) {
		setName(process.getName());
		setUser(process.getUser());
		setCpuRate(process.getCpuRate());
		setMemoryRate(process.getMemoryRate());
		setDescription(process.getDescription());
		setDate(process.getDate());
	}

}
