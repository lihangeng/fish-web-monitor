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

import com.yihuacomputer.fish.api.monitor.business.IBoxSettleDetail;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;

/**
 * 设备清机结账钞箱信息信息
 * 
 * @author YiHua
 * 
 */
@Entity
@Table(name = "ATMC_SETTLE_BOX")
public class BoxSettleDetail implements IBoxSettleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_SETTLE_BOX")
	@SequenceGenerator(name = "SEQ_ATMC_SETTLE_BOX", sequenceName = "SEQ_ATMC_SETTLE_BOX")
	@Column(name = "ID")
	private long id;

	@Column(name = "BOX_ID", nullable = false, length = 15)
	private String boxId;

	@Column(name = "BOX_CURRENCY", length = 10)
	private String boxCurrency;

	@Column(name = "BOX_LEFT_AMT")
	private long boxAmt;

	@ManyToOne(targetEntity = Settlement.class)
	@JoinColumn(name = "SETTLE_ID", insertable = false, updatable = false)
	private ISettlement settlement;

	public BoxSettleDetail() {

	}

	public BoxSettleDetail(IBoxSettleDetail iDetail) {
		setBoxId(iDetail.getBoxId());
		setBoxCurrency(iDetail.getBoxCurrency());
		setBoxLeftAmt(iDetail.getBoxLeftAmt());
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

	@Override
	public String getBoxCurrency() {
		return this.boxCurrency;
	}

	@Override
	public long getBoxLeftAmt() {
		return this.boxAmt;
	}

	public void setSettlement(ISettlement settlement) {
		this.settlement = settlement;
	}

	public ISettlement getSettlement() {
		return this.settlement;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public void setBoxCurrency(String boxCurrency) {
		this.boxCurrency = boxCurrency;
	}

	public void setBoxLeftAmt(long boxAmt) {
		this.boxAmt = boxAmt;
	}

	public void setBoxAmt(long boxAmt) {
		this.boxAmt = boxAmt;
	}
}
