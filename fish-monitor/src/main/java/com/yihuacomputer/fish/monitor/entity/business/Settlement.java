package com.yihuacomputer.fish.monitor.entity.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.IBoxSettleDetail;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
@Entity
@Table(name = "ATMC_SETTLEMENT")
public class Settlement implements ISettlement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_SETTLEMENT")
	@SequenceGenerator(name = "SEQ_ATMC_SETTLEMENT", sequenceName = "SEQ_ATMC_SETTLEMENT")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "UUID",length = 20)
	private String uuId;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Column(name = "SETTLE_DATE",length = 20)
	private String date;
	
	@Column(name = "LEFT_AMT")
	private long leftAmt;
	
	@OneToMany(targetEntity = BoxSettleDetail.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "SETTLE_ID")
	private List<IBoxSettleDetail> boxDetail;
	
	@Column(name = "DEPOSIT")
	private long deposit;
	
	@Column(name = "DEPOSIT_AMT")
	private long depositAmt;
	
	@Column(name = "WITHDRAWAL")
	private long withdrawal;
	
	@Column(name = "WITHDRAWAL_AMT")
	private long withdrawalAmt;
	
	@Column(name = "TRANS_COUNT")
	private long transaction;
	
	@Column(name = "TRANS_AMT")
	private long transactionAmt;
	
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUuId() {
		return uuId;
	}
	public long getLeftAmt() {
		return leftAmt;
	}
	public List<IBoxSettleDetail> getBoxDetail() {
//		List<IBoxSettleDetail> boxDetail = new ArrayList<IBoxSettleDetail>();
//		boxDetail.addAll(this.boxDetail);
		return this.boxDetail;
	}
	public long getDeposit() {
		return deposit;
	}
	public long getDepositAmt() {
		return depositAmt;
	}
	public long getWithdrawal() {
		return withdrawal;
	}
	public long getWithdrawalAmt() {
		return withdrawalAmt;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public void setLeftAmt(long leftAmt) {
		this.leftAmt = leftAmt;
	}
	public void setBoxDetail(List<IBoxSettleDetail> boxDetail) {			
		this.boxDetail = boxDetail;
	}
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	public void setDepositAmt(long depositAmt) {
		this.depositAmt = depositAmt;
	}
	public void setWithdrawal(long withdrawal) {
		this.withdrawal = withdrawal;
	}
	public void setWithdrawalAmt(long withdrawalAmt) {
		this.withdrawalAmt = withdrawalAmt;
	}
	public long getTransaction() {
		return transaction;
	}
	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}
	public long getTransactionAmt() {
		return transactionAmt;
	}
	public void setTransactionAmt(long transactionAmt) {
		this.transactionAmt = transactionAmt;
	}
}
