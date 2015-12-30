package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;


public class StatusCam {
	/**
	 * 获取硬件主状态
	 * @return 硬件主状态
	 */
	private DeviceStatus status;

    private String statusName;
	/**
	 * 获取状态代码
	 * @return 状态代码
	 */
	private String code;
	
	/**
	 * 获取硬件代码
	 * @return 状态代码
	 */
	private String hwCode;

	
	/**
	 * 房间摄像头
	 * @return
	 */
	private DeviceStatus roomStatus;
	
	/**
	 * 客户摄像头
	 * @return
	 */
	private DeviceStatus personStatus;
	
	/**
	 * 出钞口摄像头
	 * @return
	 */
	private DeviceStatus exitSlotStatus;

	public DeviceStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DeviceStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(DeviceStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public DeviceStatus getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(DeviceStatus personStatus) {
		this.personStatus = personStatus;
	}

	public DeviceStatus getExitSlotStatus() {
		return exitSlotStatus;
	}

	public void setExitSlotStatus(DeviceStatus exitSlotStatus) {
		this.exitSlotStatus = exitSlotStatus;
	}

	public String getHwCode() {
		return hwCode;
	}

	public void setHwCode(String hwCode) {
		this.hwCode = hwCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
