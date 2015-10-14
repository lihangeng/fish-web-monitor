package com.yihuacomputer.fish.monitor.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.business.CardRetainType;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IDCardType;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.monitor.service.base.DomainRetaincardService;

@Entity
@Table(name = "ATMC_RETAIN_CARD")
public class Retaincard implements IRetaincard {

	@Transient
	private DomainRetaincardService service;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_RETAIN_CARD")
	@SequenceGenerator(name = "SEQ_ATMC_RETAIN_CARD", sequenceName = "SEQ_ATMC_RETAIN_CARD")
	@Column(name = "ID")
	private long id;

	/**
	 * 吞卡设备号
	 */
	@Column(name = "TERMINAL_ID", length = 20, nullable = false)
	private String terminalId;

	/**
	 * 卡号
	 */
	@Column(name = "ACCOUNT_NO", length = 20)
	private String accountNo;

	/**
	 * 吞卡原因
	 */
	@Column(name = "REASON", length = 50)
	private String reason;

	/**
	 * 返回码
	 */
	@Column(name = "RET", length = 5)
	private String ret;

	/**
	 * 处理机构
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "ORG_ID")
	private IOrganization treatmentOrganization;

	/**
	 * 吞卡时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CARD_RETAIN_TIME")
	private Date cardRetainTime;

	/**
	 * 卡片所属银行
	 */
	@Column(name = "DISTRIBUTION_BANK", length = 40)
	private String cardDistributionBank;

	/**
	 * 状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 20)
	private CardStatus status;

	/**
	 * 处理人
	 */
	@Column(name = "TREATMENT_PEOPLE", length = 20)
	private String treatmentPeople;

	/**
	 * 处理时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TREATMENT_TIME")
	private Date treatmentTime;

	/**
	 * 客户姓名
	 */
	@Column(name = "CUST_NAME", length = 20)
	private String customerName;

	/**
	 * 客户联系电话
	 */
	@Column(name = "CUST_PHONE", length = 20)
	private String customerPhone;

	/**
	 * 客户证件号
	 */
	@Column(name = "CUST_PAPERS", length = 80)
	private String customerPapers;

	/**
	 * 客户证件类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CARD_TYPE", length = 30)
	private IDCardType cardType;

	/**
	 * 吞卡记录方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CARD_RETAIN_TYPE", length = 30)
	private CardRetainType cardRetainType;

	/**
	 * 移交到的机构
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "HANDOVER_ORG_ID")
	private IOrganization handOverOrg;

	/**
	 * 处理地址
	 */
	@Column(name = "TREATMENT_ADDRESS")
	private String treatmentAddress;

	/**
	 * 卡片所属客户手机号码
	 */
	@Column(name = "PHONE")
	private String phone;

	public Retaincard() {

	}

	public Retaincard(DomainRetaincardService service) {
		this.service = service;
	}

	public DomainRetaincardService getService() {
		return service;
	}

	public void setService(DomainRetaincardService service) {
		this.service = service;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getRet() {
		return this.ret;
	}

	public String toString() {
		return "Retaincard [accountNo=" + accountNo + ", reason=" + reason + ", ret=" + ret + "]";
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CardRetainType getCardRetainType() {
		return cardRetainType;
	}

	public void setCardRetainType(CardRetainType cardRetainType) {
		this.cardRetainType = cardRetainType;
	}

	public void update(IRetaincard retaincard) {
		setAccountNo(retaincard.getAccountNo());
		setReason(retaincard.getReason());
		setTerminalId(retaincard.getTerminalId());
		setRet(retaincard.getRet());
		setCardDistributionBank(retaincard.getCardDistributionBank());
		setCardRetainTime(retaincard.getCardRetainTime());
		setCustomerName(retaincard.getCustomerName());
		setCustomerPapers(retaincard.getCustomerPapers());
		setCustomerPhone(retaincard.getCustomerPhone());
		setTreatmentOrganization(retaincard.getTreatmentOrganization());
		setStatus(retaincard.getStatus());
		setTreatmentPeople(retaincard.getTreatmentPeople());
		setTreatmentTime(retaincard.getTreatmentTime());
		setCardType(retaincard.getCardType());
		setCardRetainType(retaincard.getCardRetainType());

		setPhone(retaincard.getPhone());

	}

	public void setCardRetainTime(Date cardRetainTime) {
		this.cardRetainTime = cardRetainTime;
	}

	public Date getCardRetainTime() {
		return cardRetainTime;
	}

	public IOrganization getTreatmentOrganization() {
		return treatmentOrganization;
	}

	public void setTreatmentOrganization(IOrganization treatmentOrganization) {
		this.treatmentOrganization = treatmentOrganization;
	}

	public void setCardDistributionBank(String cardDistributionBank) {
		this.cardDistributionBank = cardDistributionBank;
	}

	public String getCardDistributionBank() {

		return cardDistributionBank;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public void setTreatmentPeople(String treatmentPeople) {
		this.treatmentPeople = treatmentPeople;
	}

	public String getTreatmentPeople() {
		return treatmentPeople;
	}

	public void setTreatmentTime(Date treatmentTime) {
		this.treatmentTime = treatmentTime;
	}

	public Date getTreatmentTime() {
		return treatmentTime;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;

	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;

	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPapers(String customerPapers) {
		this.customerPapers = customerPapers;

	}

	public String getCustomerPapers() {
		return customerPapers;
	}

	public void setCardType(IDCardType cardType) {
		this.cardType = cardType;

	}

	public IDCardType getCardType() {

		return cardType;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;

	}

	public String getTerminalId() {
		return this.terminalId;
	}

	@Override
	public void setHandOverOrg(IOrganization handOverOrg) {
		this.handOverOrg = handOverOrg;
	}

	@Override
	public IOrganization getHandOverOrg() {
		return handOverOrg;
	}

	public String getTreatmentAddress() {
		return treatmentAddress;
	}

	public void setTreatmentAddress(String treatmentAddress) {
		this.treatmentAddress = treatmentAddress;
	}

	@Override
	public String getPhone() {
		return this.phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
