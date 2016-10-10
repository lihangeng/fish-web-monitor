package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkd;

@Embeddable
public class StatusUkd implements IStatusUkd, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public StatusUkd(){
    	this.ukd = DeviceStatus.Unknown;
    }
	@Enumerated(EnumType.STRING)
	@Column(name = "UKD_STATUS")
	private DeviceStatus ukd;

	@Column(name = "UKD_CODE")
	private String ukdCode;

	@Transient
	private String ukdHwCode;

	/**
	 * 获取硬件主状态
	 *
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus() {
		return this.ukd;
	}

	/**
	 * 获取状态代码
	 *
	 * @return 状态代码
	 */
	public String getCode() {
		return this.ukdCode;
	}

	/**
	 * 设置硬件主状态
	 *
	 * @param cdm
	 */
	public void setStatus(DeviceStatus bcr) {
		this.ukd = bcr;
	}

	/**
	 * 设置状态码
	 *
	 * @param code
	 */
	public void setCode(String code) {
		this.ukdCode = code;
	}

	@Override
	public String getHwCode() {
		return this.ukdHwCode;
	}

	@Override
	public void setHwCode(String hwCode) {
		this.ukdHwCode = hwCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ukd == null) ? 0 : ukd.hashCode());
		result = prime * result + ((ukdCode == null) ? 0 : ukdCode.hashCode());
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
		StatusUkd other = (StatusUkd) obj;
		if (ukd != other.ukd) {
			return false;
		}
		if (ukdCode == null) {
			if (other.ukdCode != null) {
				return false;
			}
		} else if (!ukdCode.equals(other.ukdCode)) {
			return false;
		}
		return true;
	}
}
