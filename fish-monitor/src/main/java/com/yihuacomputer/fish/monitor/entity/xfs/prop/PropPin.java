package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPin;
@Embeddable
public class PropPin implements IPropPin {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PIN_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp pin;
	
	public PropPin(){
		this.pin = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropPin() {
		return this.pin;
	}
	public void setPropPin(DeviceProp pin){
		this.pin = pin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
		PropPin other = (PropPin) obj;
		if (pin != other.pin){
			return false;
		}
		return true;
	}
	
}
