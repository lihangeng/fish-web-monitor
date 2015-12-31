package com.yihuacomputer.fish.web.monitor.form;

public class PropertyCam {
	/**
	 * 最大拍照张数
	 * @return
	 */
	private int maxPictures;
	
	/**
	 * 照片文字叠加能力
	 * @return
	 */
	private String canCamData;
	
	/**
	 * 最大照片叠加文字数，0表示不可叠加
	 * @return
	 */
	private int maxDataLength;
	
	/**
	 * 房间摄像头
	 * @return
	 */
	private String existRoom;
	
	/**
	 * 客户摄像头
	 * @return
	 */
	private String existPerson;
	
	/** 
	 * 是否具出钞口摄像头
	 */
	private String existExitSlot;

	public int getMaxPictures() {
		return maxPictures;
	}

	public void setMaxPictures(int maxPictures) {
		this.maxPictures = maxPictures;
	}

	public String getCanCamData() {
		return canCamData;
	}

	public void setCanCamData(String canCamData) {
		this.canCamData = canCamData;
	}

	public int getMaxDataLength() {
		return maxDataLength;
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public String getExistRoom() {
		return existRoom;
	}

	public void setExistRoom(String existRoom) {
		this.existRoom = existRoom;
	}

	public String getExistPerson() {
		return existPerson;
	}

	public void setExistPerson(String existPerson) {
		this.existPerson = existPerson;
	}

	public String getExistExitSlot() {
		return existExitSlot;
	}

	public void setExistExitSlot(String existExitSlot) {
		this.existExitSlot = existExitSlot;
	}
}
