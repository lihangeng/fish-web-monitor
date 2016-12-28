package com.yihuacomputer.fish.monitor.entity.xfs.prop;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.propertise.DeviceProp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCam;

import javax.persistence.Embeddable;

/**
 * @author YiHua
 *
 */
@Embeddable
public class PropCam implements IPropCam {

	@Enumerated(EnumType.STRING)
	@Column(name = "CAM_EXIST",columnDefinition="CHAR",length=1)
	private DeviceProp cam;
	
	/**
	 * 初始化
	 */
	public PropCam(){
		cam = DeviceProp.F;
	}
	@Override
	public DeviceProp getPropCam() {
		return this.cam;
	}

	@Override
	public void setPropCam(DeviceProp cam) {
		this.cam = cam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cam == null) ? 0 : cam.hashCode());
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
		PropCam other = (PropCam) obj;
		if (cam != other.cam){
			return false;
		}
		return true;
	}	
}
