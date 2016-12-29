package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.atm.VendorStatus;

/**
 * @author YiHua
 *
 */
public class AtmBrandForm {

	private long id;

	private String name;

	private String country;

	private String address;

	private String hotline1;

	private String hotline2;

	private int status;

	public AtmBrandForm() {

	}

	/**
	 * @param brand
	 */
	public AtmBrandForm(IAtmVendor brand) {
		setId(brand.getId());
		setAddress(brand.getAddress());
		setCountry(brand.getCountry());
		setHotline1(brand.getHotline1());
		setHotline2(brand.getHotline2());
		setName(brand.getName());
		setStatus(brand.getStatus().getId());
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<AtmBrandForm> convert(List<IAtmVendor> list) {
		List<AtmBrandForm> result = new ArrayList<AtmBrandForm>();
		for (IAtmVendor item : list) {
			result.add(new AtmBrandForm(item));
		}
		return result;
	}

	/**
	 * @param brand
	 */
	public void translate(IAtmVendor brand) {
		brand.setAddress(getAddress());
		brand.setCountry(getCountry());
		brand.setHotline1(getHotline1());
		brand.setHotline2(getHotline2());
		brand.setName(getName().trim());
		brand.setStatus(VendorStatus.getById(getStatus()));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
