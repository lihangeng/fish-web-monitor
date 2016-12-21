package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropTtu;
@Embeddable
public class PropTtu implements IPropTtu {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TTU_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp ttu;
	
	public PropTtu(){
		this.ttu = DeviceProp.F;
	}
	
	@Override
	public DeviceProp getPropTtu() {
		return this.ttu;
	}
	@Override
	public void setPropTtu(DeviceProp ttu){
		this.ttu = ttu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ttu == null) ? 0 : ttu.hashCode());
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
		PropTtu other = (PropTtu) obj;
		if (ttu != other.ttu){
			return false;
		}
		return true;
	}
	
}
