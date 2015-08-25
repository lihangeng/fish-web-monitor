package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCdm;
@Embeddable
public class PropCdm implements IPropCdm {

	@Enumerated(EnumType.STRING)
	@Column(name = "CDM_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp cdm;
	
	public PropCdm(){
		cdm = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropCdm() {
		return this.cdm;
	}

	public void setPropCdm(DeviceProp cdm){
		this.cdm =  cdm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdm == null) ? 0 : cdm.hashCode());
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
		PropCdm other = (PropCdm) obj;
		if (cdm != other.cdm){
			return false;
		}
		return true;
	}	
}
