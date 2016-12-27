package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atm.IAtmModule;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_MODULE")
public class AtmModule implements IAtmModule ,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7139332064779989246L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_MODULE")
	@SequenceGenerator(name = "SEQ_DEV_MODULE", sequenceName = "SEQ_DEV_MODULE")
	@Column(name = "ID")
	private long id;
	/**
	 * 模块编号
	 */
	@Column(name = "MOD_NO", length = 5)
	private String no;
	/**
	 * 模块名称
	 */
	@Column(name = "NAME", length = 5)
	private String name;
	/**
	 * 备注
	 */
	@Column(name = "NOTE", length = 100)
	private String note;
	
	/**
	 * 是否创建模块故障
	 */
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "IS_CASE", nullable = true, columnDefinition="CHAR",length=1)
	private boolean isCase;
	
	public AtmModule(){
		
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String getNo() {
		return no;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String getNote() {
		return this.note;
	}
	
	/**
	 * @param module
	 */
	public void update(IAtmModule module){
		setName(module.getName());
		setNo(module.getNo());
		setNote(module.getNote());
		setCase(module.isCase());
	}

	@Override
	public boolean isCase() {
		return isCase;
	}

	@Override
	public void setCase(boolean isCase) {
		this.isCase = isCase;
	}	
}
