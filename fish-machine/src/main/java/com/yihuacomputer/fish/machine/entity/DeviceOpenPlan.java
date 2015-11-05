package com.yihuacomputer.fish.machine.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.PlanState;
import com.yihuacomputer.fish.api.openplan.PlanStateType;
import com.yihuacomputer.fish.api.openplan.PlanType;

/**
 * 设备运营方案
 * 
 * @author YiHua
 * 
 */

@Entity
@Table(name = "DEV_OPENPLAN")
public class DeviceOpenPlan implements IDeviceOpenPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_OPENPLAN")
    @SequenceGenerator(name = "SEQ_DEV_OPENPLAN", sequenceName = "SEQ_DEV_OPENPLAN")
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "CREATE_DATETIME", length = 30, nullable = false)
    private String createDateTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "STARTDATE", length = 10)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATE", length = 10)
    private Date endDate;

    @Column(name = "REMARK", length = 180)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLANTYPE", length = 16)
    private PlanType planType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLANSTATE", length = 16)
    private PlanState planState;

    @OneToMany(targetEntity = OpenPlanDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "OPENPLAN_ID")
    private List<IOpenPlanDetail> openPlanDetail;

    @Transient
    private PlanStateType planStateType;
    
    @Transient
    private int deviceCount;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public PlanState getPlanState() {
        return planState;
    }

    public void setPlanState(PlanState planState) {
        this.planState = planState;
    }

    @Override
    public List<IOpenPlanDetail> getOpenPlanDetail() {
        return this.openPlanDetail;
    }

    @Override
    public void setOpenPlanDetail(List<IOpenPlanDetail> details) {
        this.openPlanDetail = details;
    }

    @Override
    public String getCreateDateTime() {
        return this.createDateTime;
    }

    @Override
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

	public PlanStateType getPlanStateType() {
		return planStateType;
	}

	public void setPlanStateType(PlanStateType planStateType) {
		this.planStateType = planStateType;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}
    
}
