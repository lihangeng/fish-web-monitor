package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropNfc;
@Embeddable
public class PropNfc implements IPropNfc {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NFC_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp nfc;
	
	public PropNfc(){
		this.nfc = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropNfc() {		
		return this.nfc;
	}
	
	public void setPropNfc(DeviceProp nfc){
		this.nfc = nfc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nfc == null) ? 0 : nfc.hashCode());
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
		PropNfc other = (PropNfc) obj;
		if (nfc != other.nfc){
			return false;
		}
		return true;
	}	
}
