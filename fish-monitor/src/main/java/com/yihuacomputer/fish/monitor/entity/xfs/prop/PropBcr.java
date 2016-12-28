package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropBcr;

import javax.persistence.Embeddable;

/**
 * @author YiHua
 *
 */
@Embeddable
public class PropBcr implements IPropBcr {
	@Enumerated(EnumType.STRING)
	@Column(name = "BCR_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp bcr;
	
	/**
	 * 初始化
	 */
	public PropBcr(){
		bcr = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropBcr() {
		return this.bcr;
	}

	@Override
	public void setPropBcr(DeviceProp bcr) {
		this.bcr = bcr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcr == null) ? 0 : bcr.hashCode());
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
		PropBcr other = (PropBcr) obj;
		if (bcr != other.bcr){
			return false;
		}
		return true;
	}	
}
