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
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * 卡号
	 *
	 * @param accountNo
	 */
	public void setAccountNo(String accountNo);

	/**
	 * @return
	 */
	public String getAccountNo();

	/**
	 * 吞卡原因
	 *
	 * @param reason
	 */
	public void setReason(String reason);

	/**
	 * @return
	 */
	public String getReason();

	/**
	 * @param ret
	 */
	public void setRet(String ret);

	/**
	 * @return
	 */
	public String getRet();

	/**
	 * 处理机构名称
	 *
	 * @param treatmentOrganization
	 */
	public void setTreatmentOrganization(IOrganization treatmentOrganization);

	/**
	 * @return
	 */
	public IOrganization getTreatmentOrganization();

	/**
	 * 吞卡时间
	 *
	 * @param cardRetainTime
	 */
	public void setCardRetainTime(Date cardRetainTime);

	/**
	 * @return
	 */
	public Date getCardRetainTime();

	/**
	 * 吞卡银行
	 *
	 * @param cardDistributionBank
	 */
	public void setCardDistributionBank(String cardDistributionBank);

	/**
	 * @return
	 */
	public String getCardDistributionBank();

	/**
	 * 卡片状态
	 *
	 * @param status
	 */
	public void setStatus(CardStatus status);

	/**
	 * @return
	 */
	public CardStatus getStatus();

	/**
	 * 处理人员
	 *
	 * @param treatmentPeople
	 */
	public void setTreatmentPeople(String treatmentPeople);

	/**
	 * @return
	 */
	public String getTreatmentPeople();

	/**
	 * 处理时间
	 *
	 * @param treatmentTime
	 */
	public void setTreatmentTime(Date treatmentTime);

	/**
	 * @return
	 */
	public Date getTreatmentTime();

	/**
	 * 客户姓名
	 *
	 * @param customerName
	 */
	public void setCustomerName(String customerName);

	/**
	 * @return
	 */
	public String getCustomerName();

	/**
	 * 客户电话
	 *
	 * @param customerPhone
	 */
	public void setCustomerPhone(String customerPhone);

	/**
	 * @return
	 */
	public String getCustomerPhone();

	/**
	 * 客户证件号
	 *
	 * @param customerPapers
	 */
	public void setCustomerPapers(String customerPapers);

	/**
	 * @return
	 */
	public String getCustomerPapers();

	/**
	 * 证件类型
	 *
	 * @param cardType
	 */
	public void setCardType(IDCardType cardType);

	/**
	 * @return
	 */
	public IDCardType getCardType();

	/**
	 * @param retaincard
	 */
	public void update(IRetaincard retaincard);

	/**
	 * 移交到机构
	 *
	 * @param handOverOrg
	 */

	public void setHandOverOrg(IOrganization handOverOrg);

	/**
	 * @return
	 */
	public IOrganization getHandOverOrg();

	/**
	 * @param cardRetainType
	 */
	public void setCardRetainType(CardRetainType cardRetainType);

	/**
	 * @return
	 */
	public CardRetainType getCardRetainType();

	/**
	 * 处理地址
	 *
	 * @return
	 */
	public String getTreatmentAddress();

	/**
	 * 处理地址
	 * @param treatmentAddress
	 */
	public void setTreatmentAddress(String treatmentAddress);

	/**
	 * 卡片所属客户手机号码
	 *
	 * @return
	 */
	public String getPhone();

	/**
	 * @param phone
	 */
	public void setPhone(String phone);
	
	/**
	 * 吞卡创建日期，
	 * 格式yyyymmdd 
	 * 比如20150812
	 * @since 2.0.0.3便于统计使用
	 * @return
	 */
	public Long getRetainDate();
	
	/**
	 * @param retainDate
	 */
	public void setRetainDate(Long retainDate);
}
