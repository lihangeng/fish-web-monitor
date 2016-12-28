package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropFgp;
/**
 * @author YiHua
 *
 */
@Embeddable
public class PropFgp implements IPropFgp {

	@Enumerated(EnumType.STRING)
	@Column(name = "FGP_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp fgp;

	/**
	 * 初始化
	 */
	public PropFgp(){
		this.fgp = DeviceProp.F;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fgp == null) ? 0 : fgp.hashCode());
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
		PropFgp other = (PropFgp) obj;
		if (fgp != other.fgp){
			return false;
		}
		return true;
	}
	@Override
	public DeviceProp getPropFgp() {
		return  this.fgp ;
	}
	@Override
	public void setPropFgp(DeviceProp fgp) {
		this.fgp = fgp ;
	}
}
