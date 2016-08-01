package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropUkd;
@Embeddable
public class PropUkd implements IPropUkd {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "UKD_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp ukd;
	
	public PropUkd(){
		this.ukd = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropUkd() {
		return this.ukd;
	}
	public void setPropUkd(DeviceProp ukd){
		this.ukd = ukd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ukd == null) ? 0 : ukd.hashCode());
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
		PropUkd other = (PropUkd) obj;
		if (ukd != other.ukd){
			return false;
		}
		return true;
	}
	
}
