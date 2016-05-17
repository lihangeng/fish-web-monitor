package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmType;

public class AtmTypeForm {
	private long id;
	/**
	 * 型号名称
	 */
	private String name;

	private long devCatalogId;

	private String devCatalogName;

	private long devVendorId;

	private String devVendorName;

	private String cashtype;

	private List<Long> atmModules = new ArrayList<Long>();

	public AtmTypeForm() {
	}

	public AtmTypeForm(IAtmType type) {
		this.id = type.getId();
		setCashtype(type.getCashtype() == null ? null : String.valueOf(type.getCashtype().getId()));
		setDevVendorId(type.getDevVendor().getId());
		setDevVendorName(type.getDevVendor().getName());
		setDevCatalogId(type.getDevCatalog().getId());
		setDevCatalogName(type.getDevCatalog().getName());
		setName(type.getName());
	}

	public static List<AtmTypeForm> convert(List<IAtmType> list) {
		List<AtmTypeForm> result = new ArrayList<AtmTypeForm>();
		for (IAtmType item : list) {
			result.add(new AtmTypeForm(item));
		}
		return result;
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

	public String getDevCatalogName() {
		return devCatalogName;
	}

	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}

	public long getDevCatalogId() {
		return devCatalogId;
	}

	public void setDevCatalogId(long devCatalogId) {
		this.devCatalogId = devCatalogId;
	}

	public long getDevVendorId() {
		return devVendorId;
	}

	public void setDevVendorId(long devVendorId) {
		this.devVendorId = devVendorId;
	}

	public String getDevVendorName() {
		return devVendorName;
	}

	public void setDevVendorName(String devVendorName) {
		this.devVendorName = devVendorName;
	}

	public String getCashtype() {
		return cashtype;
	}

	public void setCashtype(String cashtype) {
		this.cashtype = cashtype;
	}

	public List<Long> getAtmModules() {
		return atmModules;
	}

	public void setAtmModule(List<Long> atmModules) {
		this.atmModules = atmModules;
	}

}
