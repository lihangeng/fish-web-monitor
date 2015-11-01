package com.yihuacomputer.fish.machine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.OpenClose;
import com.yihuacomputer.fish.api.openplan.Week;

/**
 * 运行方案明细
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_OPENPLANDETAIL")
public class OpenPlanDetail implements IOpenPlanDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_OPENPLANDETAIL")
    @SequenceGenerator(name = "SEQ_DEV_OPENPLANDETAIL", sequenceName = "SEQ_DEV_OPENPLANDETAIL")
    @Column(name = "ID")
	private long id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "WEEK", length = 16)
	private Week week;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "OPENCLOSE", length = 16)
	private OpenClose openClose;
	
    @Column(name = "STARTTIME",length = 20)
	private String startTime;
	
    @Column(name = "ENDTIME",length = 20)
	private String endTime;
	
    @ManyToOne(targetEntity = DeviceOpenPlan.class)
    @JoinColumn(name = "OPENPLAN_ID", insertable = false, updatable = false)
	private IDeviceOpenPlan deviceOpenPlan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public OpenClose getOpenClose() {
		return openClose;
	}

	public void setOpenClose(OpenClose openClose) {
		this.openClose = openClose;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public IDeviceOpenPlan getDeviceOpenPlan() {
		return deviceOpenPlan;
	}

	public void setDeviceOpenPlan(IDeviceOpenPlan deviceOpenPlan) {
		this.deviceOpenPlan = deviceOpenPlan;
	}	
}
