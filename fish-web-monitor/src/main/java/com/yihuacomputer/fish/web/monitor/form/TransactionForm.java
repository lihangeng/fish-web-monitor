package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionView;

public class TransactionForm {

	private String termId;

	private String transId;

	private String debitAccount;

	private String creditAccount;

	private String transCode;

	private double amt;

	private String currency;

	private String dateTime;

	private String hostRet;

	private String localRet;

	private double tipFee;

	public TransactionForm() {
	}

	public TransactionForm(ITransaction itrans) {
		setTermId(itrans.getTerminalId());
		setTransId(itrans.getTransId());
		setDebitAccount(itrans.getDebitAccount());
		setCreditAccount(itrans.getCreditAccount());
		setTransCode(itrans.getTransCode());
		setAmt(itrans.getAmt());
		setCurrency(itrans.getCurrency());
		setDateTime(DateUtils.getTimestamp(itrans.getDateTime()));
		setHostRet(itrans.getHostRet());
		setLocalRet(itrans.getLocalRet());
		setTipFee(itrans.getTipFee());
	}

	public TransactionForm(ITransactionView itrans) {
		setTermId(itrans.getTerminalId());
		setTransId(itrans.getTransId());
		setDebitAccount(itrans.getDebitAccount());
		setCreditAccount(itrans.getCreditAccount());
		setTransCode(itrans.getTransCode());
		setAmt(itrans.getAmt());
		setCurrency(itrans.getCurrency());
		setDateTime(DateUtils.getTimestamp(itrans.getDateTime()));
		setHostRet(itrans.getHostRet());
		setLocalRet(itrans.getLocalRet());
		setTipFee(itrans.getTipFee());
	}

	public static List<TransactionForm> convert(List<ITransaction> list) {
		List<TransactionForm> result = new ArrayList<TransactionForm>();
		for (ITransaction item : list) {
			result.add(new TransactionForm(item));
		}
		return result;
	}
	public static List<TransactionForm> convertView(List<ITransactionView> list) {
		List<TransactionForm> result = new ArrayList<TransactionForm>();
		for (ITransactionView item : list) {
			result.add(new TransactionForm(item));
		}
		return result;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getHostRet() {
		return hostRet;
	}

	public void setHostRet(String hostRet) {
		this.hostRet = hostRet;
	}

	public String getLocalRet() {
		return localRet;
	}

	public void setLocalRet(String localRet) {
		this.localRet = localRet;
	}

	public double getTipFee() {
		return tipFee;
	}

	public void setTipFee(double tipFee) {
		this.tipFee = tipFee;
	}

}
