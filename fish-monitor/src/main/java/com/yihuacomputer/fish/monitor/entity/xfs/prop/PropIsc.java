package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIsc;
@Embeddable
public class PropIsc implements IPropIsc {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ISC_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp isc;
	
	public PropIsc(){
		this.isc = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropIsc() {		
		return this.isc;
	}
	@Override
	public void setPropIsc(DeviceProp isc){
		this.isc = isc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isc == null) ? 0 : isc.hashCode());
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
		PropIsc other = (PropIsc) obj;
		if (isc != other.isc){
			return false;
		}
		return true;
	}	
}
