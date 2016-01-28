package com.yihuacomputer.fish.web.bsadvert.form;

import com.yihuacomputer.fish.api.advert.Screen;

public class BsAdvertResourceForm {
	
	private long id;

	private long advertId;

	private int playTime;

	private String beginDate;

	private String endDate;

	private String beginTime;

	private String endTime;

	private String fileSize;

	private String content;

	private Screen screen;

	public BsAdvertResourceForm() {
		
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAdvertId() {
		return advertId;
	}

	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}
