package com.yihuacomputer.fish.monitor.entity.hardware;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.hardware.ICpu;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;

@Entity
@Table(name = "DEV_HARDWARE_CPU")
public class Cpu implements ICpu,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_HARDWARE_CPU")
	@SequenceGenerator(name = "SEQ_DEV_HARDWARE_CPU", sequenceName = "SEQ_DEV_HARDWARE_CPU")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "FREQUENCY")
	private int frequency;
	
	@Column(name = "VENDOR",length=50)
	private String vendor;
	
	@Column(name = "MODEL",length=50)
	private String model;
	
	@Column(name = "CACHE_SIZE")
	private long cacheSize;
	
	@Column(name = "TOTAL_CORES")
	private int totalCores;
	
	@Column(name = "CPU_USER",length=10)
	private String user;
	
	@Column(name = "CPU_SYS",length=10)
	private String sys;
	
	@Column(name = "IDLE",length=10)
	private String idle;
	
	@Column(name = "COMBINED",length=50)
	private String combined;

	
	@ManyToOne(targetEntity = Hardware.class)
	@JoinColumn(name = "TERMINAL_ID", insertable = false, updatable = false)
	private IHardware hardware;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setHardware(IHardware hardware){
		this.hardware = hardware;
	}
	
	public IHardware getHardware(){
		return this.hardware;
	}
	
	public int getFrequency() {
		return this.frequency;
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
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getCacheSize() {
		return this.cacheSize;
	}

	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getTotalCores() {
		return this.totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSys() {
		return this.sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getIdle() {
		return this.idle;
	}

	public void setIdle(String idle) {
		this.idle = idle;
	}

	public String getCombined() {
		return this.combined;
	}

	public void setCombined(String combined) {
		this.combined = combined;
	}

}
