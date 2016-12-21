package com.yihuacomputer.fish.monitor.entity.hardware;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.hardware.IMemory;
@Embeddable
public class Memory implements IMemory {
	
	@Column(name = "MEMORY_SIZE")
	private long memorySize;
	
	@Column(name = "MEMORY_USED")
	private long used;
	
	@Column(name = "MEMORY_FREE")
	private long free;
	
	@Column(name = "MEMORY_USED_PERCENT")
	private double usedPercent;

	@Override
	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}

	@Override
	public void setUsed(long used) {
		this.used = used;
	}

	@Override
	public void setFree(long free) {
		this.free = free;
	}

	@Override
	public void setUsedPercent(double usedPercent) {
		this.usedPercent = usedPercent;
	}

	@Override
	public long getMemorySize() {
		return this.memorySize;
	}

	@Override
	public long getUsed() {
		return this.used;
	}

	@Override
	public long getFree() {
		return this.free;
	}

	@Override
	public double getUsedPercent() {
		return this.usedPercent;
	}

}
