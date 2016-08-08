package com.yihuacomputer.fish.monitor.entity.box;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;

@Entity
@Table(name = "DEV_BOX_DETAIL_INFO")
public class DeviceBoxDetailInfo implements IDeviceBoxDetailInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_BOX_DETAIL_INFO")
	@SequenceGenerator(name = "SEQ_DEV_BOX_DETAIL_INFO", sequenceName = "SEQ_DEV_BOX_DETAIL_INFO")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "CASH_ID")
	private String cashId;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "BILL_VALUE")
	private int value;
	
	@Column(name = "NUMBER")
	private int number;
	
	@Column(name = "BOX_TYPE")
	private String boxType;
	
	@Column(name = "MAXINUM")
	private int maxiNum;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "EFFECT", columnDefinition = "CHAR", length = 1)
	private boolean effect;
	
    @ManyToOne(targetEntity = DeviceBoxInfo.class)
    @JoinColumn(name = "DEV_BOX_INFO_ID", insertable = false, updatable = false)
    private IDeviceBoxInfo deviceBoxInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public int getMaxiNum() {
		return maxiNum;
	}

	public void setMaxiNum(int maxiNum) {
		this.maxiNum = maxiNum;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public IDeviceBoxInfo getDeviceBoxInfo() {
		return deviceBoxInfo;
	}

	public void setDeviceBoxInfo(IDeviceBoxInfo deviceBoxInfo) {
		this.deviceBoxInfo = deviceBoxInfo;
	}

	@Override
	public boolean equals(IDeviceBoxDetailInfo idbdi) {
		if(idbdi.getCashId().equals(this.getCashId())&&
				idbdi.getBoxType().equals(this.getBoxType())&&
				idbdi.getCurrency().equals(this.getCurrency())&&
				(idbdi.getMaxiNum()==this.getMaxiNum())&&
				(idbdi.getNumber()==this.getNumber())&&
				(idbdi.getValue()==this.getValue())){
			return true;
		}
		return false;
	}

}
