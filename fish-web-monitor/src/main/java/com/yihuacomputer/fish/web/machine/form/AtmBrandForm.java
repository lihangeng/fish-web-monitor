package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.atm.VendorStatus;

public class AtmBrandForm {

	private long id;

//	private String no;

	private String name;

	private String country;

	private String address;

	private String hotline1;

	private String hotline2;

	private int status;

	public AtmBrandForm() {

	}

	public AtmBrandForm(IAtmVendor brand) {
		setId(brand.getId());
		setAddress(brand.getAddress());
		setCountry(brand.getCountry());
		setHotline1(brand.getHotline1());
		setHotline2(brand.getHotline2());
		setName(brand.getName());
//		setNo(brand.getNo());
		// setStatus(brand.getStatus() == null ? null : brand.getStatus()
		// .getText());
		setStatus(brand.getStatus().getId());
	}

	public static List<AtmBrandForm> convert(List<IAtmVendor> list) {
		List<AtmBrandForm> result = new ArrayList<AtmBrandForm>();
		for (IAtmVendor item : list) {
			result.add(new AtmBrandForm(item));
		}
		return result;
	}

	public void translate(IAtmVendor brand) {
		brand.setAddress(getAddress());
		brand.setCountry(getCountry());
		brand.setHotline1(getHotline1());
		brand.setHotline2(getHotline2());
		brand.setName(getName().trim());
//		brand.setNo(getNo());
		// brand.setStatus("1".equals(getStatus()) ? VendorStatus.SUPPLY
		// : VendorStatus.SERVE);
		brand.setStatus(VendorStatus.getById(getStatus()));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public String getNo() {
//		return no;
//	}
//
//	public void setNo(String no) {
//		this.no = no;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotline1() {
		return hotline1;
	}

	public void setHotline1(String hotline1) {
		this.hotline1 = hotline1;
	}

	public String getHotline2() {
		return hotline2;
	}

	public void setHotline2(String hotline2) {
		this.hotline2 = hotline2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
