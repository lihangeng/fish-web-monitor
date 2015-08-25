package com.yihuacomputer.fish.fault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.fault.IUnusualNotifyMould;
import com.yihuacomputer.fish.api.fault.IsNotify;
import com.yihuacomputer.fish.api.fault.ResponsorType;

/**
 * 异常交易类型配置
 * 
 * @author YiHua
 *
 */
@Entity
@Table(name = "CASE_UNUSUAL_NOTIFY_MOULD")
public class UnusualNotifyMould implements IUnusualNotifyMould{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_UNUSUAL_NOTIFY_MOULD")
	@SequenceGenerator(name = "SEQ_CASE_UNUSUAL_NOTIFY_MOULD", sequenceName = "SEQ_CASE_UNUSUAL_NOTIFY_MOULD")
	@Column(name = "ID")
	private long id; 
	
	@Column(name = "UNUSUAL_TRANSCATION_TYPE_ID",length=30,nullable=false)
	private String unusualTranscationTypeId;
	
	@Column(name = "UNUSUAL_TRANSCATION_TYPE_NAME",length=50,nullable=false)
	private String unusualTranscationTypeName;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name = "IS_NOTIFY", nullable = true, length = 10)
    private IsNotify isNotify;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RESPONSOR_TYPE",length=10,nullable=false)
	private ResponsorType responsorType;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnusualTranscationTypeId() {
		return unusualTranscationTypeId;
	}

	public void setUnusualTranscationTypeId(String unusualTranscationTypeId) {
		this.unusualTranscationTypeId = unusualTranscationTypeId;
	}

	public String getUnusualTranscationTypeName() {
		return unusualTranscationTypeName;
	}

	public void setUnusualTranscationTypeName(String unusualTranscationTypeName) {
		this.unusualTranscationTypeName = unusualTranscationTypeName;
	}
	
	public ResponsorType getResponsorType() {
		return responsorType;
	}

	public void setResponsorType(ResponsorType responsorType) {
		this.responsorType = responsorType;
	}
	
	public IsNotify getIsNotify() {
		return isNotify;
	}

	public void setIsNotify(IsNotify isNotify) {
		this.isNotify = isNotify;
	}
	
}
