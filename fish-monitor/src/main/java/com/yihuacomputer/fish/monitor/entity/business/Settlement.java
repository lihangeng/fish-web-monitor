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
	@Column(name = "DATES",length = 20)
	private int dates;
	
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
	
	@Override
	public String getTerminalId() {
		return terminalId;
	}
	
	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	@Override
	public String getDate() {
		return date;
	}
	
	@Override
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String getUuId() {
		return uuId;
	}
	
	@Override
	public long getLeftAmt() {
		return leftAmt;
	}
	
	@Override
	public List<IBoxSettleDetail> getBoxDetail() {
		return this.boxDetail;
	}
	
	@Override
	public long getDeposit() {
		return deposit;
	}
	
	@Override
	public long getDepositAmt() {
		return depositAmt;
	}
	
	@Override
	public long getWithdrawal() {
		return withdrawal;
	}
	
	@Override
	public long getWithdrawalAmt() {
		return withdrawalAmt;
	}
	
	@Override
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	
	@Override
	public void setLeftAmt(long leftAmt) {
		this.leftAmt = leftAmt;
	}
	
	@Override
	public void setBoxDetail(List<IBoxSettleDetail> boxDetail) {			
		this.boxDetail = boxDetail;
	}
	
	@Override
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	
	@Override
	public void setDepositAmt(long depositAmt) {
		this.depositAmt = depositAmt;
	}
	
	@Override
	public void setWithdrawal(long withdrawal) {
		this.withdrawal = withdrawal;
	}
	
	@Override
	public void setWithdrawalAmt(long withdrawalAmt) {
		this.withdrawalAmt = withdrawalAmt;
	}
	
	@Override
	public long getTransaction() {
		return transaction;
	}
	
	@Override
	public void setTransaction(long transaction) {
		this.transaction = transaction;
	}
	
	@Override
	public long getTransactionAmt() {
		return transactionAmt;
	}
	
	@Override
	public void setTransactionAmt(long transactionAmt) {
		this.transactionAmt = transactionAmt;
	}
	
	@Override
	public int getDates() {
		return dates;
	}
	
	@Override
	public void setDates(int dates) {
		this.dates = dates;
	}
}
