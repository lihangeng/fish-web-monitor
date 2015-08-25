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

import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.INoteItem;

/**
 * 每张钱币信息
 * 
 * @author YiHua
 * 
 */
@Entity
@Table(name = "ATMC_TRANSACTION_CROWN")
public class NoteItem implements INoteItem{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_TRANSACTION_CROWN")
	@SequenceGenerator(name = "SEQ_ATMC_TRANSACTION_CROWN", sequenceName = "SEQ_ATMC_TRANSACTION_CROWN")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "SERIAL", nullable = false, length = 20)
	private int serial;
	
	@Column(name = "CROWN_ID", nullable = false, length = 15)
	private String crownId;
	
	@ManyToOne(targetEntity = CounterFeitMoney.class)
	@JoinColumn(name = "NOTE_ID", insertable = false, updatable = false)
	private ICounterFeitMoney counterFeitMoney;
	
	public NoteItem() {
	}
	

	public NoteItem(INoteItem noteItem) {
		setSerial(noteItem.getSerial());
		setNoteCode(noteItem.getNoteCode());
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int getSerial() {
		return serial;
	}
	
	public void setSerial(int serial) {
		this.serial = serial;
	}


//	@Override
//	public String getVersion() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public String getNoteCode() {
		return crownId;
	}
	
	public void setNoteCode(String noteCode) {
		this.crownId = noteCode;
	}
//
//	@Override
//	public String getImage() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public void setCounterFeitMoney(ICounterFeitMoney counterFeitMoney) {
		this.counterFeitMoney = counterFeitMoney;
	}

	public ICounterFeitMoney getCounterFeitMoney() {
		return this.counterFeitMoney;
	}

}

