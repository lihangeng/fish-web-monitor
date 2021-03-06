package com.yihuacomputer.fish.web.report.form;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.SetupType;
import com.yihuacomputer.fish.api.device.WorkType;

/**
 * @author YiHua
 *
 */
public class LinkDeviceForm {
	
	private static Logger logger = LoggerFactory.getLogger(LinkDeviceForm.class);
	
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
	 * 设备安装日期 format(yyyy-MM-dd)
	 */
	private String installDate;

	/**
	 * 非现金标志
	 */
	private String cashType;

	/**
	 * 安装方式
	 */
	private String setupType;

	/**
	 * 运营商
	 */
	private String carrier;

	/**
	 * 网络类型
	 */
	private String netType;

	/**
	 * 在行离行标志
	 */
	private String awayFlag;

	/**
	 * 经营方式
	 */
	private String workType;

	public LinkDeviceForm() {
	}

	/**
	 * 将接口数据保存至本地
	 * 
	 * @param device
	 *            接口
	 * @return
	 */
	public LinkDeviceForm(IDevice device) {
		setAddress(device.getAddress());
		setCashboxLimit(device.getCashboxLimit());
		setAwayFlag(device.getAwayFlag() == null ? null : String.valueOf(device.getAwayFlag().getId()));
		setSetupType(device.getSetupType() == null ? null : String.valueOf(device.getSetupType().getId()));
		setWorkType(device.getWorkType() == null ? null : String.valueOf(device.getWorkType().getId()));
		if (device.getDevService() != null) {
			setDevServiceName(device.getDevService().getName());
			setDevServiceId(device.getDevService().getGuid());
		}

		if (device.getDevType() != null) {
			setDevTypeId(device.getDevType().getId());
			setDevTypeName(device.getDevType().getName());
			setDevCatalogName(device.getDevType().getDevCatalog().getName());
			setDevVendorName(device.getDevType().getDevVendor().getName());
		}
		setId(String.valueOf(device.getId()));
		setIp(device.getIp().toString());
		if (device.getOrganization() != null) {
			setOrgId(device.getOrganization().getGuid());
			setOrgName(device.getOrganization().getName());
		}

		setStatus(device.getStatus() == null ? null : String.valueOf(device.getStatus().getId()));

		setTerminalId(device.getTerminalId());

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
		device.getDevService().setGuid(getDevServiceId());
		device.setIp(new IP(getIp()));
		device.setTerminalId(getTerminalId());
		// 机构
		device.getOrganization().setGuid(getOrgId());
		// 型号
		device.getDevType().setId(getDevTypeId());
		device.setStatus(nullObject(getStatus(), DevStatus.class));
		device.setAwayFlag(nullObject(getAwayFlag(), AwayFlag.class));
		device.setSetupType(nullObject(getSetupType(), SetupType.class));
		device.setWorkType(nullObject(getWorkType(), WorkType.class));
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<LinkDeviceForm> convert(List<IDevice> list) {
		List<LinkDeviceForm> result = new ArrayList<LinkDeviceForm>();
		for (IDevice item : list) {
			result.add(new LinkDeviceForm(item));
		}
		return result;
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

	// public long getDevVendorId()
	// {
	// return devVendorId;
	// }

	// public void setDevVendorId(long devVendorId)
	// {
	// this.devVendorId = devVendorId;
	// }

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

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getSetupType() {
		return setupType;
	}

	public void setSetupType(String setupType) {
		this.setupType = setupType;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
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

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

}
