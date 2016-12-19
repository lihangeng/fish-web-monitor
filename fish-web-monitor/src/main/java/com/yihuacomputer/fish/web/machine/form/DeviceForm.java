package com.yihuacomputer.fish.web.machine.form;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.WorkType;

public class DeviceForm {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceForm.class);
	
	private String id;

	/**
	 * 设备号
	 */
	private String terminalId;

	/**
	 * 设备IP地址
	 */
	private String ip;

	/**
	 * 所属机构
	 */
	private String orgId;

	private String orgName;

	/**
	 * 设备型号
	 */
	private long devTypeId;

	private String devTypeName;

	private String devVendorName;

	private String devCatalogName;

	/**
	 * 设备状态
	 */
	private String status;
	/**
	 * 设备状态
	 */
	private String statusName;

	/**
	 * 设备维护商name
	 */
	private String devServiceName;

	/**
	 * 设备维护商ID
	 */
	private String devServiceId;

	/**
	 * 设备地址
	 */
	private String address;

	/**
	 * 钱箱报警金额．单位：张数
	 */
	private int cashboxLimit;

	/**
	 * 设备序列号
	 */
	private String serial;

	/**
	 * 安装方式
	 */
	private String setupType;
	/**
	 * 安装方式
	 */
	private String setupTypeName;

	/**
	 * 网络类型
	 */
	private String netType;
	private String netTypeName;

	/**
	 * 在行离行标志
	 */
	private String awayFlag;
	/**
	 * 在行离行标志
	 */
	private String awayFlagName;

	/**
	 * 经营方式
	 */
	private String workType;
	private String workTypeName;


	/**
	 * 虚拟柜员号
	 */
	public String virtual;
	private String registerStatus;	

	/**
	 * 设备安装日期 format(yyyy-MM-dd)
	 */
	private String installDate;

	public DeviceForm() {
	}

	

	/**
	 * 本地数据保存至接口
	 *
	 * @param device
	 *            接口
	 */
	public void translate(IDevice device) {
		device.setAddress(getAddress());
		device.setCashboxLimit(getCashboxLimit());
		device.setIp(new IP(getIp()));
		device.setTerminalId(getTerminalId());
		device.setVirtual(getVirtual());
		device.setAwayFlag(nullObject(getAwayFlag(), AwayFlag.class));
		device.setSetupType(nullObject(getSetupType(), SetupType.class));
		device.setWorkType(nullObject(getWorkType(), WorkType.class));
		device.setSerial(getSerial());
		device.setNetType(nullObject(getNetType(), NetType.class));
		device.setInstallDate((installDate == null || "".equals(installDate)) ? null : DateUtils.getDate(installDate));

	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevServiceName() {
		return devServiceName;
	}

	public void setDevServiceName(String devServiceName) {
		this.devServiceName = devServiceName;
	}

	public String getDevServiceId() {
		return devServiceId;
	}

	public void setDevServiceId(String devServiceId) {
		this.devServiceId = devServiceId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCashboxLimit() {
		return cashboxLimit;
	}

	public void setCashboxLimit(int cashboxLimit) {
		this.cashboxLimit = cashboxLimit;
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

	public long getDevTypeId() {
		return devTypeId;
	}

	public void setDevTypeId(long devTypeId) {
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

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getAwayFlag() {
		return awayFlag;
	}

	public void setAwayFlag(String awayFlag) {
		this.awayFlag = awayFlag;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@SuppressWarnings("unchecked")
	private <T> T nullObject(String value, Class<T> targetClass) {
		T resultObj = null;
		if (value == null || "".equals(value)) {
			return null;
		}
		try {
			Method method = targetClass.getMethod("getById", new Class[] { int.class });
			resultObj = (T) method.invoke(null, new Object[] { Integer.valueOf(value) });
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
		}
		return resultObj;
	}

	public String getVirtual() {
		return virtual;
	}

	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSetupTypeName() {
		return setupTypeName;
	}

	public void setSetupTypeName(String setupTypeName) {
		this.setupTypeName = setupTypeName;
	}

	public String getAwayFlagName() {
		return awayFlagName;
	}

	public void setAwayFlagName(String awayFlagName) {
		this.awayFlagName = awayFlagName;
	}



	public String getNetTypeName() {
		return netTypeName;
	}



	public void setNetTypeName(String netTypeName) {
		this.netTypeName = netTypeName;
	}



	public String getWorkTypeName() {
		return workTypeName;
	}



	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}


	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}
}
