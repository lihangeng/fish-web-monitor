package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIdc;
@Embeddable
public class PropIdc implements IPropIdc {

	@Enumerated(EnumType.STRING)
	@Column(name = "IDC_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp idc;

	public PropIdc(){
		this.idc = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropIdc() {
		return this.idc;
	}

	public void setPropIdc(DeviceProp idc){
		this.idc = idc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idc == null) ? 0 : idc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		PropIdc other = (PropIdc) obj;
		if (idc != other.idc){
			return false;
		}
		return true;
	}
}
