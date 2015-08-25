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

import com.yihuacomputer.fish.api.monitor.hardware.IDisk;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
@Entity
@Table(name = "DEV_HARDWARE_DISK")
public class Disk implements IDisk,Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_HARDWARE_DISK")
	@SequenceGenerator(name = "SEQ_DEV_HARDWARE_DISK", sequenceName = "SEQ_DEV_HARDWARE_DISK")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "LABEL",length = 20)
	private String label;
	
	@Column(name = "NAME",length = 30)
	private String name;
	
	@Column(name = "TOTAL_SIZE")
	private long totalSize;
	
	@Column(name = "FREE_SIZE")
	private long freeSize;
	
	@Column(name = "FILE_SYS",length = 15)
	private String fileSys;
	
	@Column(name = "MEMO",length = 30)
	private String memo;
	
	@Column(name = "LABEL_NAME",length = 30)
	private String labelAndname;
	
	@ManyToOne(targetEntity = Hardware.class)
	@JoinColumn(name = "TERMINAL_ID", insertable = false, updatable = false)
	private IHardware hardware;
	
	
	public void setHardware(IHardware hardware){
		this.hardware = hardware;
	}
	
	public IHardware getHardware(){
		return this.hardware;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public void setFreeSize(long freeSize) {
		this.freeSize = freeSize;
	}

	public void setFileSys(String fileSys) {
		this.fileSys = fileSys;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setLabelAndname(String labelAndname) {
		this.labelAndname = labelAndname;
	}

	public String getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name;
	}

	public long getTotalSize() {
		return this.totalSize;
	}

	public long getFreeSize() {
		return this.freeSize;
	}

	public String getFileSys() {
		return this.fileSys;
	}

	public String getMemo() {
		return this.memo;
	}

	public String getLabelAndname() {
		return this.labelAndname;
	}

}
