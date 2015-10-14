package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;
import java.util.List;

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
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.TypeStatus;

/**e
 * @author wangchao
 *
 */
@Entity
@Table(name = "DEV_TYPE")
public class AtmType implements IAtmType,Serializable {

	/**
	 * 
	 */
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
	 * 设备规格
	 */
	@Column(name = "SPEC", length = 20)
	private String spec;

	/**
	 * 设备重量
	 */
	@Column(name = "WEIGHT", length = 20)
	private String weight;

	/**
	 * 平均功率
	 */
	@Column(name = "WATT", length = 20)
	private String watt;

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
	@Column(name = "TYPE_STATUS",length = 1)
	private TypeStatus typeStatus;

	public AtmType() {
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

	public IAtmVendor getDevVendor() {
		return devVendor;
	}

	public void setDevVendor(IAtmVendor devVendor) {
		this.devVendor = devVendor;
	}

	public IAtmCatalog getDevCatalog() {
		return devCatalog;
	}

	public void setDevCatalog(IAtmCatalog devCatalog) {
		this.devCatalog = devCatalog;
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

	public CashType getCashtype() {
		return cashtype;
	}

	public void setCashtype(CashType cashtype) {
		this.cashtype = cashtype;
	}

	public void update(IAtmType type) {
		setCashtype(type.getCashtype());
		setDevCatalog(type.getDevCatalog());
		setDevVendor(type.getDevVendor());
		setName(type.getName());
		setSpec(type.getSpec());
		setWatt(type.getWatt());
		setWeight(type.getWeight());
	}

	@Override
	public List<IAtmModule> getAtmModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAtmModules(List<IAtmModule> atmModules) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAtmModule(IAtmModule atmModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAtmModule(IAtmModule atmModule) {
		// TODO Auto-generated method stub

	}


	public String getRemark() {
		return remark;
	}


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
