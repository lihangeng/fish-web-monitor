package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCount;

@Entity
@Table(name = "CASE_FAULT_MONTH")
public class EveryMonthFaultCount implements IEveryMonthFaultCount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_FAULT_MONTH")
	@SequenceGenerator(name = "SEQ_CASE_FAULT_MONTH", sequenceName = "SEQ_CASE_FAULT_MONTH")
	@Column(name = "ID")
	private long id;

	@Column(name = "VENDOR_NAME", length = 50)
	private String vendorName;

	@Column(name = "DEV_TYPE", length = 30)
	private String devType;
	/**
	 * 设备模块
	 */
	@Column(name = "DEV_MOD", length = 5)
	private String devMod;

	/**
	 * 故障类型
	 */
	@Column(name = "CLASSIFY_ID", length = 10)
	private String classifyId;

	/**
	 * 故障日期
	 */
	@Column(name = "FAULT_DATE", length = 20)
	private long faultDate;

	/**
	 * 故障时次数
	 */
	@Column(name = "FAULT_COUNT", length = 10)
	private long faultCount;
	

	@Column(name = "VENDOR_ID")
	private long vendorId;

	@Column(name = "DEV_TYPE_ID")
	private long devTypeId;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getVendorName() {
		return vendorName;
	}

	@Override
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String getDevType() {
		return devType;
	}

	@Override
	public void setDevType(String devType) {
		this.devType = devType;
	}

	@Override
	public String getDevMod() {
		return devMod;
	}

	@Override
	public void setDevMod(String devMod) {
		this.devMod = devMod;
	}

	@Override
	public String getClassifyId() {
		return classifyId;
	}

	@Override
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	@Override
	public long getFaultDate() {
		return faultDate;
	}

	@Override
	public void setFaultDate(long faultDate) {
		this.faultDate = faultDate;
	}

	@Override
	public long getFaultCount() {
		return faultCount;
	}

	@Override
	public void setFaultCount(long faultCount) {
		this.faultCount = faultCount;
	}
	
	@Override
	public long getVendorId() {
		return vendorId;
	}

	@Override
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	@Override
	public long getDevTypeId() {
		return devTypeId;
	}

	@Override
	public void setDevTypeId(long devTypeId) {
		this.devTypeId = devTypeId;
	}

}
