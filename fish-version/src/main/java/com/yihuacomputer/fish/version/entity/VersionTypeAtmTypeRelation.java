package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelation;

@Entity
@Table(name = "VER_VERSIONTYPE_ATMTYPE")
public class VersionTypeAtmTypeRelation implements IVersionTypeAtmTypeRelation, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_VERSIONTYPE_ATMTYPE")
	@SequenceGenerator(name = "SEQ_VER_VERSIONTYPE_ATMTYPE", sequenceName = "SEQ_VER_VERSIONTYPE_ATMTYPE")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "ATM_TYPE_ID", nullable = false)
	private long atmTypeId;
	
	@Column(name = "VERSION_TYPE_ID", nullable = false)
	private long versionTypeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAtmTypeId() {
		return atmTypeId;
	}

	public void setAtmTypeId(long atmTypeId) {
		this.atmTypeId = atmTypeId;
	}

	public long getVersionTypeId() {
		return versionTypeId;
	}

	public void setVersionTypeId(long versionTypeId) {
		this.versionTypeId = versionTypeId;
	}

}
