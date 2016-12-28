package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropRpr;
/**
 * @author YiHua
 *
 */
@Embeddable
public class PropRpr implements IPropRpr {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RPR_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp rpr;
	
	/**
	 * 初始化
	 */
	public PropRpr(){
		this.rpr = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropRpr() {
		return this.rpr;
	}
	@Override
	public void setPropRpr(DeviceProp rpr){
		this.rpr = rpr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rpr == null) ? 0 : rpr.hashCode());
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
		PropRpr other = (PropRpr) obj;
		if (rpr != other.rpr){
			return false;
		}
		return true;
	}
	
}
