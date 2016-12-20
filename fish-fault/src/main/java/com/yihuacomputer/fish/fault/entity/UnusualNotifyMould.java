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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getUnusualTranscationTypeId() {
		return unusualTranscationTypeId;
	}

	@Override
	public void setUnusualTranscationTypeId(String unusualTranscationTypeId) {
		this.unusualTranscationTypeId = unusualTranscationTypeId;
	}

	@Override
	public String getUnusualTranscationTypeName() {
		return unusualTranscationTypeName;
	}

	@Override
	public void setUnusualTranscationTypeName(String unusualTranscationTypeName) {
		this.unusualTranscationTypeName = unusualTranscationTypeName;
	}
	
	@Override
	public ResponsorType getResponsorType() {
		return responsorType;
	}

	@Override
	public void setResponsorType(ResponsorType responsorType) {
		this.responsorType = responsorType;
	}
	
	@Override
	public IsNotify getIsNotify() {
		return isNotify;
	}

	@Override
	public void setIsNotify(IsNotify isNotify) {
		this.isNotify = isNotify;
	}
	
}
