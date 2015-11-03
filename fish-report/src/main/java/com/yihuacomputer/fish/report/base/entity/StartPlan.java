package com.yihuacomputer.fish.report.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.api.report.IStartPlanService;


@Entity
@Table(name = "DEV_STARTPLAN")
public class StartPlan implements IStartPlan{
	
	
	
	@Transient
    private IStartPlanService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_STARTPLAN")
    @SequenceGenerator(name = "SEQ_DEV_STARTPLAN", sequenceName = "SEQ_DEV_STARTPLAN")
    @Column(name = "ID")
    private long id;

    /**
     * 方案名称
     */
    @Column(name = "NAME", length = 30)
    private String name;
    
    /**
     * 有效开始时间
     */
    @Column(name = "START_DATE",length=20)
	private String startDate;
	
    /**
     * 有效结束时间
     */
	@Column(name = "END_DATE",length=20)
	private String endDate;
    
    /**
     * 描述
     */
    @Column(name = "NOTE", length = 30)
    private String note;
    
    public StartPlan()
    {
    }

    public StartPlan(IStartPlanService service)
    {
        this.service = service;
    }

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void update(IStartPlan startPlan)
    {
		setName(startPlan.getName());
		setStartDate(startPlan.getStartDate());
		setEndDate(startPlan.getEndDate());
        setNote(startPlan.getNote());
    }
	
}
