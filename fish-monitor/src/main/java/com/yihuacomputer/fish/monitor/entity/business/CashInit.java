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

import com.yihuacomputer.fish.api.monitor.business.IBoxInitDetail;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
/**
 * 设备加钞信息
 * @author YiHua
 *
 */
@Entity
@Table(name = "ATMC_CASH_INIT")
public class CashInit implements ICashInit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_CASH_INIT")
	@SequenceGenerator(name = "SEQ_ATMC_CASH_INIT", sequenceName = "SEQ_ATMC_CASH_INIT")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TERMINAL_ID",length = 20,nullable=false)
	private String terminalId;
	
	@Column(name = "CASH_DATE",length=20)
	private String date;
	
	@Column(name = "UUID",length=20)
	private String uuId;
	
	@Column(name = "AMT")
	private long amt;
	
	@OneToMany(targetEntity = BoxInitDetail.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "INIT_ID")
	private List<IBoxInitDetail> boxDetail;	
	
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
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public long getAmt() {
		return amt;
	}
	public void setAmt(long amt) {
		this.amt = amt;
	}
	public List<IBoxInitDetail> getBoxDetail() {
		return this.boxDetail;
	}
	public void setBoxDetail(List<IBoxInitDetail> boxDetail) {
		this.boxDetail = boxDetail;
	}
}
