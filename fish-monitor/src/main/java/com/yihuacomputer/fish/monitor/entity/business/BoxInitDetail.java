package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.IBoxInitDetail;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;

/**
 * 设备加钞钞箱详情
 * 
 * @author YiHua
 * 
 */
@Entity
@Table(name = "ATMC_CASH_INIT_BOX")
public class BoxInitDetail implements IBoxInitDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_CASH_INIT_BOX")
	@SequenceGenerator(name = "SEQ_ATMC_CASH_INIT_BOX", sequenceName = "SEQ_ATMC_CASH_INIT_BOX")
	@Column(name = "ID")
	private long id;

	@Column(name = "BOX_ID", length = 15)
	private String boxId;

	@Column(name = "BOX_CURRENCY", length = 10)
	private String boxCurrency;

	@Column(name = "BOX_INIT_AMT")
	private long boxAmt;

	@ManyToOne(targetEntity = CashInit.class)
	@JoinColumn(name = "INIT_ID", insertable = false, updatable = false)
	private ICashInit cashInit;

	public BoxInitDetail() {
	}

	public BoxInitDetail(IBoxInitDetail detail) {
		setBoxId(detail.getBoxId());
		setBoxCurrency(detail.getBoxCurrency());
		setBoxInitAmt(detail.getBoxInitAmt());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getBoxId() {
		return this.boxId;
	}

	public void setCashInit(ICashInit cashInit) {
		this.cashInit = cashInit;
	}

	public ICashInit getCashInit() {
		return this.cashInit;
	}

	@Override
	public String getBoxCurrency() {
		return this.boxCurrency;
	}

	@Override
	public long getBoxInitAmt() {
		return this.boxAmt;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public void setBoxCurrency(String boxCurrency) {
		this.boxCurrency = boxCurrency;
	}

	public void setBoxInitAmt(long boxAmt) {
		this.boxAmt = boxAmt;
	}

	public void setBoxAmt(long boxAmt) {
		this.boxAmt = boxAmt;
	}
}
