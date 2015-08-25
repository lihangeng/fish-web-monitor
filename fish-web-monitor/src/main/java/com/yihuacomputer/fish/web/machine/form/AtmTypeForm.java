package com.yihuacomputer.fish.web.machine.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmType;

public class AtmTypeForm {
	private long id;
	/**
	 * 编号
	 */
//	private String no;
	/**
	 * 型号
	 */
	private String name;

	private long devCatalogId;

	private String devCatalogName;

	private long devVendorId;

	private String devVendorName;

	private String spec;

	private String weight;

	private String watt;

	private String cashtype;

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
//		setNo(type.getNo());
		setSpec(type.getSpec());
		setWatt(type.getWatt());
		setWeight(type.getWeight());
	}

	public static List<AtmTypeForm> convert(List<IAtmType> list) {
		List<AtmTypeForm> result = new ArrayList<AtmTypeForm>();
		for (IAtmType item : list) {
			result.add(new AtmTypeForm(item));
		}
		return result;
	}

/*	public void translate(IAtmType type) {
		type.setCashtype("1".equals(getCashtype()) ? CashType.CASH
				: CashType.NOT_CASH);
		type.getDevVendor().setName(getDevVendorName());
		type.getDevCatalog().setId(getDevCatalogId());
		type.getDevCatalog().setName(getDevCatalogName());
		
		type.setName(getName());
		type.setNo(getNo());
		type.setSpec(getSpec());
		type.setWatt(getWatt());
		type.setWeight(getWeight());
	}*/

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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWatt() {
		return watt;
	}

	public void setWatt(String watt) {
		this.watt = watt;
	}

	public String getCashtype() {
		return cashtype;
	}

	public void setCashtype(String cashtype) {
		this.cashtype = cashtype;
	}

}
