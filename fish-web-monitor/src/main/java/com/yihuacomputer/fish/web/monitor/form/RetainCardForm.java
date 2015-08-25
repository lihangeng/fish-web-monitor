package com.yihuacomputer.fish.web.monitor.form;

import java.lang.reflect.Method;
import java.util.Date;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.CardRetainType;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IDCardType;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.person.IOrganizationService;

/**
 * 吞卡信息
 * 
 * @author YiHua
 * 
 */
public class RetainCardForm {
	private long id;
	/**
	 * 设备号
	 */
	private String terminalId;
	/**
	 * 处理机构名称
	 */
	// private String organizationNumber;

	private String orgGuid;

	private String orgName;
	/**
	 * 卡号
	 */
	private String accountNo;
	/**
	 * 吞卡时间
	 */
	private String cardRetainTime;
	/**
	 * 发卡银行
	 */
	private String cardDistributionBank;
	/**
	 * 卡片状态
	 */
	private String status;
	/**
	 * 吞卡原因
	 */
	private String reason;
	/**
	 * 处理人员
	 */
	private String treatmentPeople;
	/**
	 * 处理时间
	 */
	private String treatmentTime;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 客户电话
	 */
	private String customerPhone;
	/**
	 * 客户证件号
	 */
	private String customerPapers;

	/**
	 * 证件类型
	 */
	private String cardType;

	/**
	 * 所属机构
	 */
	private String subsidiaryorganId;

	private String subsidiaryorganName;

	/**
	 * 移交到的机构
	 */

	private String handOverOrgId;

	private String handOverOrgName;

	private String userOrgId;

	/**
	 * 吞卡类型(自动/手动)
	 */
	private String cardRetainType;

	public RetainCardForm() {

	}

	public RetainCardForm(IRetaincard retaincard, IOrganizationService orgService, IDeviceService deviceService) {
		this.setId(retaincard.getId());
		this.setAccountNo(retaincard.getAccountNo());
		this.setReason(retaincard.getReason());
		this.setTerminalId(retaincard.getTerminalId());
		this.setCardDistributionBank(retaincard.getCardDistributionBank());
		this.setCustomerName(retaincard.getCustomerName());
		this.setCustomerPapers(retaincard.getCustomerPapers());
		this.setCustomerPhone(retaincard.getCustomerPhone());

		// 处理retaincard.getTreatmentOrganization()为空的情况
		if (retaincard.getTreatmentOrganization() != null) {
			this.setOrgGuid(retaincard.getTreatmentOrganization().getGuid());
			this.setOrgName(retaincard.getTreatmentOrganization().getName());
		}

		if (retaincard.getHandOverOrg() != null) {
			this.setHandOverOrgId(retaincard.getHandOverOrg().getGuid());
			this.setHandOverOrgName(retaincard.getHandOverOrg().getName());
		}
		this.setStatus(String.valueOf(retaincard.getStatus().getId()));
		this.setTreatmentPeople(retaincard.getTreatmentPeople());
		this.setCardRetainTime(nullString(retaincard.getCardRetainTime()));
		// 此处证件类型可能为空
		this.setCardType(retaincard.getCardType() == null ? null : String.valueOf(retaincard.getCardType().getId()));
		this.setTreatmentTime(nullString(retaincard.getTreatmentTime()));

		// 设备所属机构
		IDevice device = deviceService.get(retaincard.getTerminalId());
		this.setSubsidiaryorganName(device.getOrganization().getName());
		this.setSubsidiaryorganId(device.getOrganization().getGuid());
		this.setCardRetainType(retaincard.getCardRetainType() == null ? null : String.valueOf(retaincard.getCardRetainType().getId()));
	}

	public void translate(IRetaincard retaincard) {
		retaincard.setAccountNo(getAccountNo());
		retaincard.setReason(getReason());
		retaincard.setTerminalId(getTerminalId());
		retaincard.setCardDistributionBank(getCardDistributionBank());
		retaincard.setCustomerName(getCustomerName());
		retaincard.setCustomerPapers(getCustomerPapers());
		retaincard.setCustomerPhone(getCustomerPhone());
		if (retaincard.getTreatmentOrganization() != null && getOrgGuid() != null) {
			retaincard.getTreatmentOrganization().setGuid(getOrgGuid());
		}
		if (retaincard.getHandOverOrg() != null && getHandOverOrgId() != null) {
			retaincard.getHandOverOrg().setGuid(getHandOverOrgId());
		}
		retaincard.setStatus(CardStatus.getById(Integer.parseInt(getStatus())));
		retaincard.setTreatmentPeople(getTreatmentPeople());
		retaincard.setCardRetainTime(nullDate(getCardRetainTime()));
		retaincard.setCardType(nullObject(getCardType(), IDCardType.class));
		retaincard.setTreatmentTime(nullDate(getTreatmentTime()));
		retaincard.setCardRetainType(nullObject(getCardRetainType(), CardRetainType.class));
		
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCustomerPapers() {
		return customerPapers;
	}

	public void setCustomerPapers(String customerPapers) {
		this.customerPapers = customerPapers;
	}

	public long getId() {
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

	public String getAccountNo() {
		return accountNo;
	}

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCardRetainTime() {
		return cardRetainTime;
	}

	public void setCardRetainTime(String cardRetainTime) {
		this.cardRetainTime = cardRetainTime;
	}

	public String getCardDistributionBank() {
		return cardDistributionBank;
	}

	public void setCardDistributionBank(String cardDistributionBank) {
		this.cardDistributionBank = cardDistributionBank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTreatmentPeople() {
		return treatmentPeople;
	}

	public void setTreatmentPeople(String treatmentPeople) {
		this.treatmentPeople = treatmentPeople;
	}

	public String getTreatmentTime() {
		return treatmentTime;
	}

	public void setTreatmentTime(String treatmentTime) {
		this.treatmentTime = treatmentTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getSubsidiaryorganId() {
		return subsidiaryorganId;
	}

	public void setSubsidiaryorganId(String subsidiaryorganId) {
		this.subsidiaryorganId = subsidiaryorganId;
	}

	public String getSubsidiaryorganName() {
		return subsidiaryorganName;
	}

	public void setSubsidiaryorganName(String subsidiaryorganName) {
		this.subsidiaryorganName = subsidiaryorganName;
	}

	public String getHandOverOrgId() {
		return handOverOrgId;
	}

	public void setHandOverOrgId(String handOverOrgId) {
		this.handOverOrgId = handOverOrgId;
	}

	public String getHandOverOrgName() {
		return handOverOrgName;
	}

	public void setHandOverOrgName(String handOverOrgName) {
		this.handOverOrgName = handOverOrgName;
	}

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getCardRetainType() {
		return cardRetainType;
	}

	public void setCardRetainType(String cardRetainType) {
		this.cardRetainType = cardRetainType;
	}

	/**
	 * 解决为空的情况
	 * 
	 * @param string
	 * @return
	 */
	private Date nullDate(String string) {
		if (string == null || "".equals(string)) {
			return null;
		}
		return DateUtils.getTimestamp(string);
	}

	private String nullString(Date date) {
		if (date == null) {
			return null;
		}
		return DateUtils.getTimestamp(date);
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
			e.printStackTrace();
		}
		return resultObj;
	}

}
