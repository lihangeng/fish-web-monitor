package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropJpr;
@Embeddable
public class PropJpr implements IPropJpr {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "JPR_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp jpr;
	
	public PropJpr(){
		this.jpr = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropJpr() {		
		return this.jpr;
	}
	public void setPropJpr(DeviceProp jpr){
		this.jpr = jpr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jpr == null) ? 0 : jpr.hashCode());
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
		PropJpr other = (PropJpr) obj;
		if (jpr != other.jpr){
			return false;
		}
		return true;
	}

}
