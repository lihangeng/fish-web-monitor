package com.yihuacomputer.fish.analysis.entity.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.analysis.device.IDeviceTypeSummaryWeek;

@Entity
@Table(name="DEV_TYPE_SUMMARY_WEEK")
public class DeviceTypeSummaryWeek implements IDeviceTypeSummaryWeek {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_TYPE_SUMMARY_WEEK")
	@SequenceGenerator(name = "SEQ_DEV_TYPE_SUMMARY_WEEK", sequenceName = "SEQ_DEV_TYPE_SUMMARY_WEEK")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "DEV_TYPE")
	private String devType;

	@Column(name = "DEV_NUM")
	private int num;
	
	@Column(name = "SUMMARY_DATE")
	private String date;

	@Column(name = "ADD_NUM")
	private int addDevNum;
	
	@Column(name = "SCRAPPED_NUM")
	private int scrappedDevNum;
	
	@Column(name = "ALL_NEW")
	private int allAddDevNum;
	
	@Column(name = "ALL_SCRAPPED")
	private int allScrappedDevNum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAddDevNum() {
		return addDevNum;
	}

	public void setAddDevNum(int addDevNum) {
		this.addDevNum = addDevNum;
	}

	public int getScrappedDevNum() {
		return scrappedDevNum;
	}

	public void setScrappedDevNum(int scrappedDevNum) {
		this.scrappedDevNum = scrappedDevNum;
	}

	public int getAllAddDevNum() {
		return allAddDevNum;
	}

	public void setAllAddDevNum(int allAddDevNum) {
		this.allAddDevNum = allAddDevNum;
	}

	public int getAllScrappedDevNum() {
		return allScrappedDevNum;
	}

	public void setAllScrappedDevNum(int allScrappedDevNum) {
		this.allScrappedDevNum = allScrappedDevNum;
	}
}
