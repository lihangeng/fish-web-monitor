package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPbk;
/**
 * @author YiHua
 *
 */
@Embeddable
public class PropPbk implements IPropPbk {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PBK_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp pbk;
	
	/**
	 * 初始化
	 */
	public PropPbk(){
		this.pbk = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropPbk() {
		return this.pbk;
	}
	@Override
	public void setPropPbk(DeviceProp pbk){
		this.pbk = pbk;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pbk == null) ? 0 : pbk.hashCode());
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
		PropPbk other = (PropPbk) obj;
		if (pbk != other.pbk){
			return false;
		}
		return true;
	}	
}
