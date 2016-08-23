package com.yihuacomputer.fish.web.cashbox.form;

/**
 * 设备钞箱信息
 * @author GQ
 *
 */
public class DeviceCashBoxInfoForm {
	private long id;
	/**
	 * 设备型号ID
	 */
	private long devType;
	/**
	 * 设备型号名称
	 */
	private String devTypeName;
	/**
	 * 设备品牌ID
	 */
	private long devCatalogId;
	/**
	 * 设备品牌名称
	 */
	private String devCatalogName;
	/**
	 * 设备归属服务商ID
	 */
	private long devService;
	/**
	 * 设备归属服务商名称
	 */
	private String devServiceName;

	/**
	 * 设备归属机构ID
	 */
	private String organization;
	/**
	 * 设备归属机构名称
	 */
	private String organizationName;
	/**
	 * 是否离行标识
	 */
	private int awayFlag;
	/**
	 * 是否离行名称
	 */
	private String awayFlagName;
	/**
	 * 设备IP
	 */
	private String ip;
	/**
	 * 设备终端号
	 */
	private String terminalId;
	/**
	 * 上线预警
	 */
	private long maxAlarm;
	/**
	 * 取款最大值
	 */
	private long defaultBill;
	/**
	 * 存款最大值
	 */
	private long defaultCashIn;
	/**
	 * 下限预警
	 */
	private long minAlarm;
	public long getDevType() {
		return devType;
	}
	public void setDevType(long devType) {
		this.devType = devType;
	}
	public String getDevTypeName() {
		return devTypeName;
	}
	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}
	public long getDevCatalogId() {
		return devCatalogId;
	}
	public void setDevCatalogId(long devCatalogId) {
		this.devCatalogId = devCatalogId;
	}
	public String getDevCatalogName() {
		return devCatalogName;
	}
	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}
	public long getDevService() {
		return devService;
	}
	public void setDevService(long devService) {
		this.devService = devService;
	}
	public String getDevServiceName() {
		return devServiceName;
	}
	public void setDevServiceName(String devServiceName) {
		this.devServiceName = devServiceName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public int getAwayFlag() {
		return awayFlag;
	}
	public void setAwayFlag(int awayFlag) {
		this.awayFlag = awayFlag;
	}
	public String getAwayFlagName() {
		return awayFlagName;
	}
	public void setAwayFlagName(String awayFlagName) {
		this.awayFlagName = awayFlagName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public long getMaxAlarm() {
		return maxAlarm;
	}
	public void setMaxAlarm(long maxAlarm) {
		this.maxAlarm = maxAlarm;
	}
	public long getMinAlarm() {
		return minAlarm;
	}
	public void setMinAlarm(long minAlarm) {
		this.minAlarm = minAlarm;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDefaultBill() {
		return defaultBill;
	}
	public void setDefaultBill(long defaultBill) {
		this.defaultBill = defaultBill;
	}
	public long getDefaultCashIn() {
		return defaultCashIn;
	}
	public void setDefaultCashIn(long defaultCashIn) {
		this.defaultCashIn = defaultCashIn;
	}
}
