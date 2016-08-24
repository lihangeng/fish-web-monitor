package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.ITransType;

/**
 * ATMC 交易类型
 * @author YiHua
 *
 */
@Entity
@Table(name = "ATMC_TRANS_TYPE")
public class TransType implements ITransType{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_TRANS_TYPE")
    @SequenceGenerator(name = "SEQ_ATMC_TRANS_TYPE", sequenceName = "SEQ_ATMC_TRANS_TYPE")
    @Column(name = "ID")
	private long id;
	
    @Column(name = "TRANS_CODE", nullable=false,unique = true,length = 20)
	private String transCode;
	
    @Column(name = "CODE_DESC", nullable=false, length = 30)
	private String codeDesc;
    
    @Column(name = "TRANS_SEQ")
    private int transSeq;

    @Column(name = "IN_OUT_FLAG")
    private int inOutFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTransSeq() {
		return transSeq;
	}

	public void setTransSeq(int transSeq) {
		this.transSeq = transSeq;
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public int getInOutFlag() {
		return inOutFlag;
	}

	public void setInOutFlag(int inOutFlag) {
		this.inOutFlag = inOutFlag;
	}
	
	
}
