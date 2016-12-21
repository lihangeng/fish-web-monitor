package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropUkr;

@Embeddable
public class PropUkr implements IPropUkr {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "UKR_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp ukr;
	
	public PropUkr(){
		this.ukr = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropUkr() {
		return this.ukr;
	}
	@Override
	public void setPropUkr(DeviceProp ukr){
		this.ukr = ukr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ukr == null) ? 0 : ukr.hashCode());
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
		PropUkr other = (PropUkr) obj;
		if (ukr != other.ukr){
			return false;
		}
		return true;
	}
	
}
