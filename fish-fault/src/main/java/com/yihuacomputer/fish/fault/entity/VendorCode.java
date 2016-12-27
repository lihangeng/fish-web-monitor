package com.yihuacomputer.fish.fault.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.fault.IVendorCode;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "CASE_VENDORCODE")
public class VendorCode implements IVendorCode{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_VENDORCODE")
	@SequenceGenerator(name = "SEQ_CASE_VENDORCODE", sequenceName = "SEQ_CASE_VENDORCODE")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "VENDOR_ID")
	private long vendor;
	
	@Column(name = "VENDORCODE_CODE",length=20)
	private String code;
	
	@Column(name = "VENDORCODE_DESCRIPTION")
	private String description;
	
	@Column(name = "VENDORCODE_SOLUTION")
	private String solution;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id){
		this.id = id;
	}

	@Override
	public long getVendor() {
		return vendor;
	}
	
	@Override
	public void setVendor(long vendor){
		this.vendor = vendor;
	}

	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public void setCode(String code){
		this.code = code;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String description){
		this.description = description;
	}

	@Override
	public String getSolution() {
		return solution;
	}
	
	@Override
	public void setSolution(String solution){
		this.solution = solution;
	}

}
