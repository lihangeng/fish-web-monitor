package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCim;
@Embeddable
public class PropCim implements IPropCim {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CIM_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp cim;
	
	public PropCim(){
		this.cim = DeviceProp.F;
	}
	
	@Override
	public DeviceProp getPropCim() {
		return this.cim;
	}
	public void setPropCim(DeviceProp cim){
		this.cim = cim;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cim == null) ? 0 : cim.hashCode());
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
		PropCim other = (PropCim) obj;
		if (cim != other.cim){
			return false;
		}
		return true;
	}
	
}
