package com.yihuacomputer.fish.monitor.entity.box;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.machine.entity.Device;

@Entity
@Table(name = "DEV_BOX_INFO")
public class DeviceBoxInfo implements IDeviceBoxInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_BOX_INFO")
	@SequenceGenerator(name = "SEQ_DEV_BOX_INFO", sequenceName = "SEQ_DEV_BOX_INFO")
	@Column(name = "ID")
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Device.class)
	@JoinColumn(name = "DEVICE_ID")
	private IDevice deviceId;
	
	@Column(name = "MAX_ALARM")
	private long maxAlarm;
	
	@Column(name = "MIN_ALARM")
	private long minAlarm;

	@Column(name = "DEFAULT_CASHIN")
	private long defaultCashIn;
	
	@Column(name = "DEFAULT_BILL")
	private long defaultBill;
	
	@Column(name = "CASHIN_VALUE")
	private long cashInValue;
	
	@Column(name = "BILL_VALUE")
	private long billValue;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_CHANGE", columnDefinition = "CHAR", length = 1)
	private boolean boxChange;
	
    @OneToMany(targetEntity = DeviceBoxDetailInfo.class, fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "DEV_BOX_INFO_ID")
    private List<IDeviceBoxDetailInfo> deviceBoxDetails = new ArrayList<IDeviceBoxDetailInfo>();

    @Override
	public long getId() {
		return id;
	}

    @Override
	public void setId(long id) {
		this.id = id;
	}

    @Override
	public IDevice getDeviceId() {
		return deviceId;
	}

    @Override
	public void setDeviceId(IDevice deviceId) {
		this.deviceId = deviceId;
	}

    @Override
	public long getMaxAlarm() {
		return maxAlarm;
	}

    @Override
	public void setMaxAlarm(long maxAlarm) {
		this.maxAlarm = maxAlarm;
	}

    @Override
	public long getMinAlarm() {
		return minAlarm;
	}

    @Override
	public void setMinAlarm(long minAlarm) {
		this.minAlarm = minAlarm;
	}

    @Override
	public boolean isBoxChange() {
		return boxChange;
	}

    @Override
	public void setBoxChange(boolean boxChange) {
		this.boxChange = boxChange;
	}

    @Override
	public List<IDeviceBoxDetailInfo> getDeviceBoxDetails() {
		return deviceBoxDetails;
	}

    @Override
	public void setDeviceBoxDetails(List<IDeviceBoxDetailInfo> deviceBoxDetails) {
		this.deviceBoxDetails = deviceBoxDetails;
	}

    @Override
	public void add(IDeviceBoxDetailInfo deviceBoxDetail) {
		this.deviceBoxDetails.add(deviceBoxDetail);
	}

    @Override
	public long getDefaultCashIn() {
		return defaultCashIn;
	}

    @Override
	public void setDefaultCashIn(long defaultCashIn) {
		this.defaultCashIn = defaultCashIn;
	}

    @Override
	public long getDefaultBill() {
		return defaultBill;
	}

    @Override
	public void setDefaultBill(long defaultBill) {
		this.defaultBill = defaultBill;
	}

    @Override
	public long getCashInValue() {
		return cashInValue;
	}

    @Override
	public void setCashInValue(long cashInValue) {
		this.cashInValue = cashInValue;
	}

    @Override
	public long getBillValue() {
		return billValue;
	}

    @Override
	public void setBillValue(long billValue) {
		this.billValue = billValue;
	}

}
