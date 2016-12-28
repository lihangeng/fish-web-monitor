package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.trans.ITransactionDays;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "ATMC_TRANSACTION_DAYS ")
public class TransactionDays implements ITransactionDays {

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

	@Column(name = "VENDOR_ID")
	private long vendorId;

	@Column(name = "DEV_TYPE_ID")
	private long devTypeId;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getTransDate() {
		return transDate;
	}

	@Override
	public void setTransDate(long transDate) {
		this.transDate = transDate;
	}

	@Override
	public long getTransCount() {
		return transCount;
	}

	@Override
	public void setTransCount(long transCount) {
		this.transCount = transCount;
	}

	@Override
	public String getVendorName() {
		return vendorName;
	}

	@Override
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String getDevType() {
		return devType;
	}

	@Override
	public void setDevType(String devType) {
		this.devType = devType;
	}

	@Override
	public String getTransCode() {
		return transCode;
	}

	@Override
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	@Override
	public String getCardType() {
		return cardType;
	}

	@Override
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public long getTransAmt() {
		return transAmt;
	}

	@Override
	public void setTransAmt(long transAmt) {
		this.transAmt = transAmt;
	}

	@Override
	public long getVendorId() {
		return vendorId;
	}

	@Override
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	@Override
	public long getDevTypeId() {
		return devTypeId;
	}

	@Override
	public void setDevTypeId(long devTypeId) {
		this.devTypeId = devTypeId;
	}

}
