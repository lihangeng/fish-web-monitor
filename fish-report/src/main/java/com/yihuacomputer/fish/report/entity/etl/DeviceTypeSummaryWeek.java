package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;

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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public int getNum() {
		return num;
	}

	@Override
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int getAddDevNum() {
		return addDevNum;
	}

	@Override
	public void setAddDevNum(int addDevNum) {
		this.addDevNum = addDevNum;
	}

	@Override
	public int getScrappedDevNum() {
		return scrappedDevNum;
	}

	@Override
	public void setScrappedDevNum(int scrappedDevNum) {
		this.scrappedDevNum = scrappedDevNum;
	}

	@Override
	public int getAllAddDevNum() {
		return allAddDevNum;
	}

	@Override
	public void setAllAddDevNum(int allAddDevNum) {
		this.allAddDevNum = allAddDevNum;
	}

	@Override
	public int getAllScrappedDevNum() {
		return allScrappedDevNum;
	}

	@Override
	public void setAllScrappedDevNum(int allScrappedDevNum) {
		this.allScrappedDevNum = allScrappedDevNum;
	}
}
