package com.yihuacomputer.fish.monitor.entity.box;

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

import com.yihuacomputer.fish.api.monitor.box.BoxType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;

/**
 * @author YiHua
 *
 */
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

    @Enumerated(EnumType.STRING)
	@Column(name = "BOX_TYPE")
	private BoxType boxType;
	
	@Column(name = "MAXINUM")
	private int maxiNum;
	
	/**初始化张数*/
	@Column(name = "INIT_COUNT")
	private int initialCount;
	
	/**存入张数*/
	@Column(name = "CASHIN_COUNT")
	private int cashInCount;

	/**
	 *取出张数 
	 */
	@Column(name = "DISPENSER_COUNT")
	private int dispenseCount;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "EFFECT", columnDefinition = "CHAR", length = 1)
	private boolean effect;
	
    @ManyToOne(targetEntity = DeviceBoxInfo.class)
    @JoinColumn(name = "DEV_BOX_INFO_ID", insertable = false, updatable = false)
    private IDeviceBoxInfo deviceBoxInfo;

    @Override
	public long getId() {
		return id;
	}

    @Override
	public void setId(long id) {
		this.id = id;
	}

    @Override
	public String getCashId() {
		return cashId;
	}

    @Override
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

    @Override
	public String getCurrency() {
		return currency;
	}

    @Override
	public void setCurrency(String currency) {
		this.currency = currency;
	}

    @Override
	public int getValue() {
		return value;
	}

    @Override
	public void setValue(int value) {
		this.value = value;
	}

    @Override
	public int getNumber() {
		return number;
	}

    @Override
	public void setNumber(int number) {
		this.number = number;
	}

    @Override
	public BoxType getBoxType() {
		return boxType;
	}

    @Override
	public void setBoxType(BoxType boxType) {
		this.boxType = boxType;
	}

    @Override
	public int getMaxiNum() {
		return maxiNum;
	}

    @Override
	public void setMaxiNum(int maxiNum) {
		this.maxiNum = maxiNum;
	}

    @Override
	public boolean isEffect() {
		return effect;
	}

    @Override
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
    
    @Override
	public IDeviceBoxInfo getDeviceBoxInfo() {
		return deviceBoxInfo;
	}

    @Override
	public void setDeviceBoxInfo(IDeviceBoxInfo deviceBoxInfo) {
		this.deviceBoxInfo = deviceBoxInfo;
	}

    @Override
	public int getInitialCount() {
		return initialCount;
	}

    @Override
	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
	}

    @Override
	public int getCashInCount() {
		return cashInCount;
	}

    @Override
	public void setCashInCount(int cashInCount) {
		this.cashInCount = cashInCount;
	}

    @Override
	public int getDispenseCount() {
		return dispenseCount;
	}

    @Override
	public void setDispenseCount(int dispenseCount) {
		this.dispenseCount = dispenseCount;
	}

	@Override
	public boolean equals(IDeviceBoxDetailInfo idbdi) {
		if(idbdi!=null && idbdi.getCashId().equals(this.getCashId())&&
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
