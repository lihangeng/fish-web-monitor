package com.yihuacomputer.fish.monitor.entity.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.hardware.IBios;
import com.yihuacomputer.fish.api.monitor.hardware.ICpu;
import com.yihuacomputer.fish.api.monitor.hardware.IDisk;
import com.yihuacomputer.fish.api.monitor.hardware.IFrimware;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.hardware.IMemory;


@Entity
@Table(name = "DEV_HARDWARE")
public class Hardware implements IHardware ,Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_HARDWARE")
	@SequenceGenerator(name = "SEQ_DEV_HARDWARE", sequenceName = "SEQ_DEV_HARDWARE")
	@Column(name = "ID")
	private long id;

	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;

	@Embedded
	private Bios bios;

	@OneToMany(targetEntity = Cpu.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "TERMINAL_ID")
	private List<ICpu> cpu = new ArrayList<ICpu>();

	@Embedded
	private Memory memory;

	@OneToMany(targetEntity = Disk.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "TERMINAL_ID")
	private List<IDisk> hardDisk = new ArrayList<IDisk>();

	@Column(name = "HARDISK")
	private long diskMem;

	@Embedded
	private Frimware frimware;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setBios(IBios bios) {
		this.bios = (Bios) bios;
	}

	public IBios getBios() {
	    if(this.bios == null){
	        this.bios = new Bios();
	    }
		return this.bios;
	}

	public void setCpu(List<ICpu> cpuList) {
		this.cpu.clear();
		this.cpu.addAll(cpuList);
	}

	public List<ICpu> getCpu() {
		return this.cpu;
	}

	public void setMemory(IMemory memory) {
		this.memory = (Memory) memory;
	}

	public IMemory getMemory() {
		return this.memory;
	}

	public void setHardDisk(List<IDisk> hardDisk) {
		this.hardDisk.clear();
		this.hardDisk.addAll(hardDisk);
	}

	public void setDiskMem(long diskMem) {
		this.diskMem = diskMem;
	}

	public long getDiskMem() {
		return this.diskMem;
	}

	public void setFrimware(IFrimware frimware) {
		this.frimware = (Frimware) frimware;
	}

	public IFrimware getFrimware() {
		return this.frimware;
	}

	public void setHardware(IHardware hardware) {
		this.setBios(hardware.getBios());
		this.setDiskMem(hardware.getDiskMem());
		this.setCpu(hardware.getCpu());
		this.setFrimware(hardware.getFrimware());
		this.setMemory(hardware.getMemory());
		this.setHardDisk(hardware.getHardDisk());
	}

	@Override
	public List<IDisk> getHardDisk() {
		return this.hardDisk;
	}

	@Override
	public ICpu makeCpu() {
		return new Cpu();
	}

	@Override
	public IDisk makeDisk() {
		return new Disk();
	}
}
