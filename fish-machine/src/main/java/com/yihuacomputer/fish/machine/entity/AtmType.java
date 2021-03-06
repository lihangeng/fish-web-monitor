package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.TypeStatus;

/**
 * e
 * 
 * @author wangchao
 *
 */
@Entity
@Table(name = "DEV_TYPE")
public class AtmType implements IAtmType, Serializable {

	private static final long serialVersionUID = -5649648438244601048L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_TYPE")
	@SequenceGenerator(name = "SEQ_DEV_TYPE", sequenceName = "SEQ_DEV_TYPE")
	@Column(name = "ID")
	private long id;

	/**
	 * 设备型号
	 */
	@Column(name = "NAME", length = 30)
	private String name;

	/**
	 * 设备品牌
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AtmVendor.class)
	@JoinColumn(name = "DEV_VENDOR_ID")
	private IAtmVendor devVendor;

	/**
	 * 设备类型
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AtmCatalog.class)
	@JoinColumn(name = "DEV_CATALOG_ID")
	private IAtmCatalog devCatalog;

	/**
	 * 非现金标志
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CASHTYPE", length = 1)
	private CashType cashtype;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 50)
	private String remark;

	/**
	 * 状态（标识此类型号的推荐度）
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "TYPE_STATUS", length = 1)
	private TypeStatus typeStatus;

	/**
	 * 初始现金标志
	 */
	public AtmType() {
		this.cashtype = CashType.CASH;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public IAtmVendor getDevVendor() {
		return devVendor;
	}

	@Override
	public void setDevVendor(IAtmVendor devVendor) {
		this.devVendor = devVendor;
	}

	@Override
	public IAtmCatalog getDevCatalog() {
		return devCatalog;
	}

	@Override
	public void setDevCatalog(IAtmCatalog devCatalog) {
		this.devCatalog = devCatalog;
	}

	@Override
	public CashType getCashtype() {
		return cashtype;
	}

	@Override
	public void setCashtype(CashType cashtype) {
		this.cashtype = cashtype;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TypeStatus getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(TypeStatus typeStatus) {
		this.typeStatus = typeStatus;
	}

}
