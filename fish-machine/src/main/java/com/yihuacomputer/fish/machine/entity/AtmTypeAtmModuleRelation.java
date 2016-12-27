package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atm.IAtmTypeAtmModuleRelation;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_ATMTYPE_MODULE")
public class AtmTypeAtmModuleRelation implements IAtmTypeAtmModuleRelation,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_ATMTYPE_MODULE")
	@SequenceGenerator(name = "SEQ_DEV_ATMTYPE_MODULE", sequenceName = "SEQ_DEV_ATMTYPE_MODULE")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "ATM_TYPE_ID", nullable = false)
	private long atmTypeId;
	
	@Column(name = "MODULE_ID", nullable = false)
	private long atmModuleId;

	@Override
	public long getId() {
	    
		return id;
	}
	public void setId(long id){
		
		this.id=id;
	}
	
	@Override
	public long getAtmModuleId() {
		
		return atmModuleId;
	}

	@Override
	public void setAtmModuleId(long atmModuleId) {
		this.atmModuleId=atmModuleId;
		
	}

	@Override
	public long getAtmTypeId() {
	
		return atmTypeId;
	}
	
	@Override
	public void setAtmTypeId(long atmTypeId) {
		this.atmTypeId=atmTypeId;
		
	}
	
	
	
}
