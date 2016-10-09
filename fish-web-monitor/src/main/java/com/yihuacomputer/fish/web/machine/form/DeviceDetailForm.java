package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.monitor.entity.report.StatusReport;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.version.form.DeviceVersionHistory;
import com.yihuacomputer.fish.web.version.form.VersionDeviceForm;

public class DeviceDetailForm {
	/*private long id;
	private String terminalId;
	private String ip;
	private String orgId;
	private String orgName;
	private String devTypeId;
	private String devTypeName;
	private String devVendorName;
	private String devCatalogName;
	private String address;
	private String status;
	private String statusName;
	private String devServiceName;
	private String virtual;
	private String devServiceId;
	private String cashboxLimit;
	private String serial;
	private String setupType;
	private String setupTypeName;
	private String netType;
	private String netTypeName;
	private String awayFlag;
	private String awayFlagName;
	private String workType;
	private String workTypeName;
	private String installDate;*/
	
	private DeviceForm deviceForm;
	private IStatusReport statusReport;
	
    /** ATMC应用版本号 *//*
    private String appRelease;
    *//** 运行状态 *//*
    private String runStatus;
    private RunStatus run;
    private DeviceStatus mod;
    private NetStatus net;
    private BoxStatus box;
    *//** 钱箱初始金额 *//*
    private String boxInitCount;
    *//** 模块状态 *//*
    private String modStatus;
    *//** 钱箱当前金额 *//*
    private String boxCurrentCount;
    *//** 钱箱状态 *//*
    private String boxStatus;
    *//** 设备吞卡数量 *//*
    private String retainCardCount;
    *//** 忘了状态 *//*
    private String netStatus;
    *//** 注册状态 *//*
    private String registerStatus;
    *//** 读卡器状态 *//*
    private DeviceStatus idcStatus;
    *//** 取款模块状态 *//*
    private DeviceStatus cimStatus;
    *//** 取款模块状态 *//*
    private DeviceStatus cdmStatus;
    *//** 凭条打印机状态 *//*
    private DeviceStatus rprStatus;
    *//** 日志打印机状态 *//*
    private DeviceStatus jprStatus;
    *//** 密码键盘状态 *//*
    private DeviceStatus pinStatus;
    *//** 传感器状态 *//*
    private DeviceStatus siuStatus;
    *//** 文本终端状态 *//*
    private DeviceStatus ttuStatus;
    *//**存折打印模块状态*//*
    private DeviceStatus pbkStatus;
    *//**射频读卡器状态*//*
    private DeviceStatus nfcStauts;
    *//**发卡读卡器状态*//*
    private DeviceStatus iccStatus;
    *//**指纹仪状态*//*
    private DeviceStatus fgpStatus;
    *//**身份证扫描仪*//*
    private DeviceStatus iscStatus;
    *//**摄像头*//*
    private DeviceStatus camStatus;
    *//**条形码扫描*//*
    private DeviceStatus bcrStatus;
    *//**读UKEY模块*//*
    private DeviceStatus ukrStatus;
    *//**发UKEY模块*//*
    private DeviceStatus ukdStatus;*/
	

	private String maxAlarm;
	private String minAlarm;

	private List<PersonForm> personList = new ArrayList<PersonForm>();
	private List<DeviceVersionHistory> versionDeviceList= new ArrayList<DeviceVersionHistory>();
	/*public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDevTypeId() {
		return devTypeId;
	}
	public void setDevTypeId(String devTypeId) {
		this.devTypeId = devTypeId;
	}
	public String getDevTypeName() {
		return devTypeName;
	}
	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}
	public String getDevVendorName() {
		return devVendorName;
	}
	public void setDevVendorName(String devVendorName) {
		this.devVendorName = devVendorName;
	}
	public String getDevCatalogName() {
		return devCatalogName;
	}
	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDevServiceName() {
		return devServiceName;
	}
	public void setDevServiceName(String devServiceName) {
		this.devServiceName = devServiceName;
	}
	public String getVirtual() {
		return virtual;
	}
	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}
	public String getDevServiceId() {
		return devServiceId;
	}
	public void setDevServiceId(String devServiceId) {
		this.devServiceId = devServiceId;
	}
	public String getCashboxLimit() {
		return cashboxLimit;
	}
	public void setCashboxLimit(String cashboxLimit) {
		this.cashboxLimit = cashboxLimit;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getSetupType() {
		return setupType;
	}
	public void setSetupType(String setupType) {
		this.setupType = setupType;
	}
	public String getSetupTypeName() {
		return setupTypeName;
	}
	public void setSetupTypeName(String setupTypeName) {
		this.setupTypeName = setupTypeName;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getNetTypeName() {
		return netTypeName;
	}
	public void setNetTypeName(String netTypeName) {
		this.netTypeName = netTypeName;
	}
	public String getAwayFlag() {
		return awayFlag;
	}
	public void setAwayFlag(String awayFlag) {
		this.awayFlag = awayFlag;
	}
	public String getAwayFlagName() {
		return awayFlagName;
	}
	public void setAwayFlagName(String awayFlagName) {
		this.awayFlagName = awayFlagName;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getWorkTypeName() {
		return workTypeName;
	}
	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getAppRelease() {
		return appRelease;
	}
	public void setAppRelease(String appRelease) {
		this.appRelease = appRelease;
	}
	public String getRunStatus() {
		return runStatus;
	}
	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}
	public RunStatus getRun() {
		return run;
	}
	public void setRun(RunStatus run) {
		this.run = run;
	}
	public DeviceStatus getMod() {
		return mod;
	}
	public void setMod(DeviceStatus mod) {
		this.mod = mod;
	}
	public NetStatus getNet() {
		return net;
	}
	public void setNet(NetStatus net) {
		this.net = net;
	}
	public BoxStatus getBox() {
		return box;
	}
	public void setBox(BoxStatus box) {
		this.box = box;
	}
	public String getBoxInitCount() {
		return boxInitCount;
	}
	public void setBoxInitCount(String boxInitCount) {
		this.boxInitCount = boxInitCount;
	}
	public String getModStatus() {
		return modStatus;
	}
	public void setModStatus(String modStatus) {
		this.modStatus = modStatus;
	}
	public String getBoxCurrentCount() {
		return boxCurrentCount;
	}
	public void setBoxCurrentCount(String boxCurrentCount) {
		this.boxCurrentCount = boxCurrentCount;
	}
	public String getBoxStatus() {
		return boxStatus;
	}
	public void setBoxStatus(String boxStatus) {
		this.boxStatus = boxStatus;
	}
	public String getRetainCardCount() {
		return retainCardCount;
	}
	public void setRetainCardCount(String retainCardCount) {
		this.retainCardCount = retainCardCount;
	}
	public String getNetStatus() {
		return netStatus;
	}
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}
	public String getRegisterStatus() {
		return registerStatus;
	}
	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}
	public DeviceStatus getIdcStatus() {
		return idcStatus;
	}
	public void setIdcStatus(DeviceStatus idcStatus) {
		this.idcStatus = idcStatus;
	}
	public DeviceStatus getCimStatus() {
		return cimStatus;
	}
	public void setCimStatus(DeviceStatus cimStatus) {
		this.cimStatus = cimStatus;
	}
	public DeviceStatus getCdmStatus() {
		return cdmStatus;
	}
	public void setCdmStatus(DeviceStatus cdmStatus) {
		this.cdmStatus = cdmStatus;
	}
	public DeviceStatus getRprStatus() {
		return rprStatus;
	}
	public void setRprStatus(DeviceStatus rprStatus) {
		this.rprStatus = rprStatus;
	}
	public DeviceStatus getJprStatus() {
		return jprStatus;
	}
	public void setJprStatus(DeviceStatus jprStatus) {
		this.jprStatus = jprStatus;
	}
	public DeviceStatus getPinStatus() {
		return pinStatus;
	}
	public void setPinStatus(DeviceStatus pinStatus) {
		this.pinStatus = pinStatus;
	}
	public DeviceStatus getSiuStatus() {
		return siuStatus;
	}
	public void setSiuStatus(DeviceStatus siuStatus) {
		this.siuStatus = siuStatus;
	}
	public DeviceStatus getTtuStatus() {
		return ttuStatus;
	}
	public void setTtuStatus(DeviceStatus ttuStatus) {
		this.ttuStatus = ttuStatus;
	}
	public DeviceStatus getPbkStatus() {
		return pbkStatus;
	}
	public void setPbkStatus(DeviceStatus pbkStatus) {
		this.pbkStatus = pbkStatus;
	}
	public DeviceStatus getNfcStauts() {
		return nfcStauts;
	}
	public void setNfcStauts(DeviceStatus nfcStauts) {
		this.nfcStauts = nfcStauts;
	}
	public DeviceStatus getIccStatus() {
		return iccStatus;
	}
	public void setIccStatus(DeviceStatus iccStatus) {
		this.iccStatus = iccStatus;
	}
	public DeviceStatus getFgpStatus() {
		return fgpStatus;
	}
	public void setFgpStatus(DeviceStatus fgpStatus) {
		this.fgpStatus = fgpStatus;
	}
	public DeviceStatus getIscStatus() {
		return iscStatus;
	}
	public void setIscStatus(DeviceStatus iscStatus) {
		this.iscStatus = iscStatus;
	}
	public DeviceStatus getCamStatus() {
		return camStatus;
	}
	public void setCamStatus(DeviceStatus camStatus) {
		this.camStatus = camStatus;
	}
	public DeviceStatus getBcrStatus() {
		return bcrStatus;
	}
	public void setBcrStatus(DeviceStatus bcrStatus) {
		this.bcrStatus = bcrStatus;
	}
	public DeviceStatus getUkrStatus() {
		return ukrStatus;
	}
	public void setUkrStatus(DeviceStatus ukrStatus) {
		this.ukrStatus = ukrStatus;
	}
	public DeviceStatus getUkdStatus() {
		return ukdStatus;
	}
	public void setUkdStatus(DeviceStatus ukdStatus) {
		this.ukdStatus = ukdStatus;
	}*/
	public String getMaxAlarm() {
		return maxAlarm;
	}
	public void setMaxAlarm(String maxAlarm) {
		this.maxAlarm = maxAlarm;
	}
	public String getMinAlarm() {
		return minAlarm;
	}
	public void setMinAlarm(String minAlarm) {
		this.minAlarm = minAlarm;
	}
	public List<PersonForm> getPersonList() {
		return personList;
	}
	public void setPersonList(List<PersonForm> personList) {
		this.personList = personList;
	}
	public List<DeviceVersionHistory> getVersionDeviceList() {
		return versionDeviceList;
	}
	public void setVersionDeviceList(List<DeviceVersionHistory> versionDeviceList) {
		this.versionDeviceList = versionDeviceList;
	}
	public DeviceForm getDeviceForm() {
		return deviceForm;
	}
	public void setDeviceForm(DeviceForm deviceForm) {
		this.deviceForm = deviceForm;
	}
	public IStatusReport getStatusReport() {
		return statusReport;
	}
	public void setStatusReport(IStatusReport statusReport) {
		this.statusReport = statusReport;
	}
	
}
