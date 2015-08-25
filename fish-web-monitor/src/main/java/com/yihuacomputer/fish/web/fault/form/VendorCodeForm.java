package com.yihuacomputer.fish.web.fault.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.fault.IVendorCode;
import com.yihuacomputer.fish.api.person.IOrganizationService;

public class VendorCodeForm {

	private long id;
	/**
	 *  厂商编号
	 */
	private long vendor;
	/**
	 * 厂商名称
	 */
	private String vendorName;
	/**
	 * 厂商故障码
	 */
	private String code;
	/**
	 * 厂商故障描述
	 */
	private String description;
	/**
	 * 解决方案
	 */
	private String solution;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVendor() {
		return vendor;
	}

	public void setVendor(long vendor) {
		this.vendor = vendor;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public VendorCodeForm(){

	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public VendorCodeForm(IVendorCode vendorCode, IOrganizationService organizationService){
		setId(vendorCode.getId());
		setVendor(vendorCode.getVendor());
		setVendorName(organizationService.get(String.valueOf(vendor)).getName());
		setCode(vendorCode.getCode());
		setDescription(vendorCode.getDescription());
		setSolution(vendorCode.getSolution());
	}

	public static List<VendorCodeForm> convert(List<IVendorCode> list, IOrganizationService organizationService){
		List<VendorCodeForm> result = new ArrayList<VendorCodeForm>();
		for(IVendorCode item : list){
			result.add(new VendorCodeForm(item, organizationService));
		}
		return result;
	}


}
