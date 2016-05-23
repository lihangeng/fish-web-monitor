package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.base.ITransactionDays;

@Entity
@Table(name = "ATMC_TRANSACTION_DAYS ")
public class TransactionDays implements ITransactionDays{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_TRANSACTION_DAYS")
    @SequenceGenerator(name = "SEQ_ATMC_TRANSACTION_DAYS", sequenceName = "SEQ_ATMC_TRANSACTION_DAYS")
    @Column(name = "ID")
	private long id;

	@Column(name = "VENDOR_NAME", length = 50)
	private String vendorName;

	@Column(name = "DEV_TYPE", length = 30)
	private String devType;
	
	@Column(name = "TRANS_CODE", length = 20)
	private String transCode;

	@Column(name = "CARD_TYPE", length = 30)
	private String cardType;
	
	@Column(name = "TRANS_DATE", length = 11)
	private long transDate;

	@Column(name = "TRANS_COUNT", length = 11)
	private long transCount;

	@Column(name = "TRANS_AMT", length = 11)
	private long transAmt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTransDate() {
		return transDate;
	}

	public void setTransDate(long transDate) {
		this.transDate = transDate;
	}

	public long getTransCount() {
		return transCount;
	}

	public void setTransCount(long transCount) {
		this.transCount = transCount;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public long getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(long transAmt) {
		this.transAmt = transAmt;
	}

}
