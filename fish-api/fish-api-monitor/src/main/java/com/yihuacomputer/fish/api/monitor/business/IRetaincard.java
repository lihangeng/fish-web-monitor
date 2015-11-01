package com.yihuacomputer.fish.api.monitor.business;

import java.util.Date;

import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * C端应用吞卡信息
 *
 * @author huxiaobao
 *
 */
public interface IRetaincard {
	public long getId();

	public void setId(long id);

	public void setTerminalId(String terminalId);

	public String getTerminalId();

	/**
	 * 卡号
	 *
	 * @param terminalId
	 */
	public void setAccountNo(String accountNo);

	public String getAccountNo();

	/**
	 * 吞卡原因
	 *
	 * @param reason
	 */
	public void setReason(String reason);

	public String getReason();

	public void setRet(String ret);

	public String getRet();

	/**
	 * 处理机构名称
	 *
	 * @param organizationNumber
	 */
	// public void setOrganizationNumber(String organizationNumber);
	//
	// public String getOrganizationNumber();

	public void setTreatmentOrganization(IOrganization treatmentOrganization);

	public IOrganization getTreatmentOrganization();

	/*
	 * public void setOrgGuid(String orgGuid);
	 *
	 * public String getOrgGuid();
	 */

	/**
	 * 吞卡时间
	 *
	 * @param cardRetainTime
	 */
	public void setCardRetainTime(Date cardRetainTime);

	public Date getCardRetainTime();

	/**
	 * 吞卡银行
	 *
	 * @param cardDistributionBank
	 */
	public void setCardDistributionBank(String cardDistributionBank);

	public String getCardDistributionBank();

	/**
	 * 卡片状态
	 *
	 * @param status
	 */
	public void setStatus(CardStatus status);

	public CardStatus getStatus();

	/**
	 * 处理人员
	 *
	 * @param treatmentPeople
	 */
	public void setTreatmentPeople(String treatmentPeople);

	public String getTreatmentPeople();

	/**
	 * 处理时间
	 *
	 * @param treatmentTime
	 */
	public void setTreatmentTime(Date treatmentTime);

	public Date getTreatmentTime();

	/**
	 * 客户姓名
	 *
	 * @param customerName
	 */
	public void setCustomerName(String customerName);

	public String getCustomerName();

	/**
	 * 客户电话
	 *
	 * @param customerPhone
	 */
	public void setCustomerPhone(String customerPhone);

	public String getCustomerPhone();

	/**
	 * 客户证件号
	 *
	 * @param customerPapers
	 */
	public void setCustomerPapers(String customerPapers);

	public String getCustomerPapers();

	/**
	 * 证件类型
	 *
	 * @param cardType
	 */
	public void setCardType(IDCardType cardType);

	/*	*//**
	 * 设备所属机构
	 *
	 * @return
	 */
	/*
	 * public String getSubsidiaryorganId();
	 *
	 * public void setSubsidiaryorganId(String subsidiaryorganId);
	 */

	public IDCardType getCardType();

	public void update(IRetaincard retaincard);

	/**
	 * 移交到机构
	 *
	 * @param handOverOrg
	 */

	public void setHandOverOrg(IOrganization handOverOrg);

	public IOrganization getHandOverOrg();

	public void setCardRetainType(CardRetainType cardRetainType);

	public CardRetainType getCardRetainType();

	/**
	 * 处理地址
	 *
	 * @return
	 */
	public String getTreatmentAddress();

	/**
	 * 处理地址
	 */
	public void setTreatmentAddress(String treatmentAddress);

	/**
	 * 卡片所属客户手机号码
	 *
	 * @return
	 */
	public String getPhone();

	public void setPhone(String phone);
	
	/**
	 * 吞卡创建日期，
	 * 格式yyyymmdd 
	 * 比如20150812
	 * @since 2.0.0.3便于统计使用
	 * @return
	 */
	public Long getRetainDate();
	
	public void setRetainDate(Long retainDate);
}
