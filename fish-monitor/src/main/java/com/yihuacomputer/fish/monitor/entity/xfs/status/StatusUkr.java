package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkr;

/**
 * UKEY Reader 状态信息
 * @author GQ
 *
 */
@Embeddable
public class StatusUkr implements IStatusUkr, Serializable {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "UKR_STATUS")
	private DeviceStatus ukr;

	@Column(name = "UKR_CODE")
	private String ukrCode;

	@Transient
	private String ukrHwCode;

	/**
	 * 获取硬件主状态
	 *
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus() {
		return this.ukr;
	}

    public StatusUkr(){
    	this.ukr = DeviceStatus.Unknown;
    }
	/**
	 * 获取状态代码
	 *
	 * @return 状态代码
	 */
	public String getCode() {
		return this.ukrCode;
	}

	/**
	 * 设置硬件主状态
	 *
	 * @param cdm
	 */
	public void setStatus(DeviceStatus bcr) {
		this.ukr = bcr;
	}

	/**
	 * 设置状态码
	 *
	 * @param code
	 */
	public void setCode(String code) {
		this.ukrCode = code;
	}

	@Override
	public String getHwCode() {
		// TODO Auto-generated method stub
		return this.ukrHwCode;
	}

	@Override
	public void setHwCode(String hwCode) {
		this.ukrHwCode = hwCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ukr == null) ? 0 : ukr.hashCode());
		result = prime * result + ((ukrCode == null) ? 0 : ukrCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StatusUkr other = (StatusUkr) obj;
		if (ukr != other.ukr) {
			return false;
		}
		if (ukrCode == null) {
			if (other.ukrCode != null) {
				return false;
			}
		} else if (!ukrCode.equals(other.ukrCode)) {
			return false;
		}
		return true;
	}

}
