package com.yihuacomputer.fish.report.entity.etl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Entity
@Table(name = "etl_retain_card_week")
public class RetainCardWeek implements IRetainCardWeek {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_etl_retain_card_week")
	@SequenceGenerator(name = "SEQ_etl_retain_card_week", sequenceName = "SEQ_etl_retain_card_week")
	@Column(name = "ID")
	private long id;

	@Column(name = "STAT_DATE", length = 10)
	private long date;

	@Column(name = "DEV_TYPE_NAME", length = 30)
	private String devType;

	@Column(name = "DEVICE_COUNT")
	private long deviceCount;

	@Column(name = "RETAIN_COUNT")
	private long retainCount;

	@Column(name = "LAST_RETAIN_COUNT")
	private long lastRetainCount;

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getDate() {
		return date;
	}

	@Override
	public void setDate(long date) {
		this.date = date;
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
	public long getDeviceCount() {
		return deviceCount;
	}

	@Override
	public void setDeviceCount(long deviceCount) {
		this.deviceCount = deviceCount;
	}

	@Override
	public long getRetainCount() {
		return retainCount;
	}

	@Override
	public void setRetainCount(long retainCount) {
		this.retainCount = retainCount;
	}

	@Override
	public long getLastRetainCount() {
		return lastRetainCount;
	}

	@Override
	public void setLastRetainCount(long lastRetainCount) {
		this.lastRetainCount = lastRetainCount;
	}

	@Override
	public double getAvgCount() {
		if(this.deviceCount == 0){
			return 0.0;
		}
		return this.retainCount / this.deviceCount;
	}

}
