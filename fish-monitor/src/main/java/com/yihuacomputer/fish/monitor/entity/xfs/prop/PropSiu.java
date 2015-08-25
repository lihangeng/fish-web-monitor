package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropSiu;
@Embeddable
public class PropSiu implements IPropSiu {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SIU_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp siu;
	
	public PropSiu(){
		this.siu = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropSiu() {
		return this.siu;
	}
	public void setPropSiu(DeviceProp siu){
		this.siu = siu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((siu == null) ? 0 : siu.hashCode());
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
		PropSiu other = (PropSiu) obj;
		if (siu != other.siu){
			return false;
		}
		return true;
	}

}
