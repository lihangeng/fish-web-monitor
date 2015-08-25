package com.yihuacomputer.fish.monitor.entity.report;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.api.monitor.alarm.ISoftwareFailure;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;

public class DeviceReport implements IDeviceReport{
	private String deviceId;
	private IDevice device;
	private IDeviceRegister deviceRegister;
	private IXfsStatus xfsStatus;
	private IHardware hardware;
	private ISoftware software;
	private IRunInfo runInfo;
	private ITransaction transInfo;
	private List<IIllegalProcess> process;
	private ICounterFeitMoney counterFeitMoney;
	private List<CounterFeitMoneyForms> forms;

	private IRetaincard retaincard;
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public IDevice getDevice() {
		return this.device;
	}

	public void setDevice(IDevice device){
		this.device = device;
	}
	public void setTransaction(ITransaction transInfo){
		this.transInfo = transInfo;
	}
	public ITransaction getTransaction(){
		return this.transInfo;
	}

	public List<IIllegalProcess> getProcess() {
		return process;
	}

	public void setProcess(List<IIllegalProcess> process) {
		this.process = process;
	}

	public IHardware getHardware() {
		return this.hardware;
	}

	public ISoftware getSoftware() {
		return this.software;
	}

	public IRunInfo getRunInfo() {
		return this.runInfo;
	}

	public List<IProcess> listSchindlerAlarm() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ISoftwareFailure> listSoftwareFailure(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public IXfsStatus getXfsStatus() {
		return this.xfsStatus;
	}
	public void setXfsStatus(XfsStatus xfsStatus){
		this.xfsStatus = xfsStatus;
	}
	public IDeviceRegister getDeviceRegister() {
		return this.deviceRegister;
	}
	public void setDeviceRegister(DeviceRegister deviceRegister){
		this.deviceRegister = deviceRegister;
	}
	public void setHardware(IHardware hardware){
		this.hardware = hardware;
	}
	public void setSofteare(ISoftware software){
		this.software = software;
	}
	public void setRunInfo(IRunInfo runInfo) {
		this.runInfo = runInfo;
	}

	@Override
	public ICounterFeitMoney getCounterFeitMoney() {
		return this.counterFeitMoney;
	}

	public void setCounterFeitMoney(ICounterFeitMoney counterFeitMoney){
		this.counterFeitMoney = counterFeitMoney;
	}


	@Override
	public IRetaincard getRetaincard() {
		return this.retaincard;
	}

	public void setRetaincard(IRetaincard retaincard) {
		this.retaincard = retaincard;
	}
	
	//后台转换疑问币假币信息
	public List<CounterFeitMoneyForms> getForms() {
		return forms;
	}

	public void setCounterFeitMoneyForms(List<CounterFeitMoneyForms> forms) {
		this.forms = forms;
	}
}
