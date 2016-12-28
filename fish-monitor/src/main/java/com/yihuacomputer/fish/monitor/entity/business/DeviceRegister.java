package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.device.RegStatus;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
/**
 * 设备注册签到信息
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_REGISTER")
public class DeviceRegister implements IDeviceRegister{
	
	@Id
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;

	@Column(name = "SERIAL_NUMBER",length=40)
	private String serialNumber;
	
	@Column(name = "REG_TIMES")
	private int regTimes;
	
	@Column(name = "SIGN_DATE",length=20)
	private String signTime;
	
	@Column(name = "SIGN_TIMES")
	private int signTimes;
	
	@Column(name = "ATMC_VERSION",length=20)
	private String atmcVersion;	

	@Enumerated(EnumType.STRING)
    @Column(name = "REG_STATUS", length = 15)
    private RegStatus regStatus;
	
	/**
	 * 初始化
	 */
	public DeviceRegister(){
		this.signTimes = -1;
		this.regTimes = -1;
		this.regStatus = RegStatus.UNREGISTERED;
	}
	
	@Override
	public void setTerminalId(String terminalId){
		this.terminalId = terminalId;
	}
	
	@Override
	public String getTerminalId(){
		return this.terminalId;
	}
	
	@Override
	public String getAtmcVersion() {
		return atmcVersion;
	}
	
	@Override
	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}
	
	@Override
	public RegStatus getRegStatus() {
		return regStatus;
	}

	@Override
	public void setRegStatus(RegStatus regStatus) {
		this.regStatus = regStatus;
	}
	
	@Override
	public void setRegTimes(int regTimes){
		this.regTimes = regTimes;
	}
	
	@Override
	public int getRegTimes(){
		return this.regTimes;
	}
	
	@Override
	public void setSignTimes(int signTimes){
		this.signTimes = signTimes;
	}
	
	@Override
	public int getSignTimes(){
		return this.signTimes;
	}
	
	@Override
	public void setRegisterSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	@Override
	public String getRegisterSerialNumber() {
		return this.serialNumber;
	}

	@Override
	public boolean isDeviceRegisted() {
		return serialNumber != null;		
	}

	@Override
	public boolean isDeivceSigned() {
		return signTime != null;
	}
}
