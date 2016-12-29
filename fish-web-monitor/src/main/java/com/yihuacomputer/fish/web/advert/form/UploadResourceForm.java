package com.yihuacomputer.fish.web.advert.form;

import java.util.Date;

import com.yihuacomputer.common.util.DateUtils;

/**
 * @author YiHua
 *
 */
public class UploadResourceForm {

	private String id;

	private boolean success;

	private String originalFileName;
	// 加上时间戳的文件名，保证唯一性
	private String fileName;
	// 特别长的名字，中间用...省略
	private String displayName;

	private String path;

	private int playTime;

	private String beginDate;

	private String endDate;

	private String beginHour;

	private String beginMinute;

	private String beginSecond;

	private String endHour;

	private String endMinute;

	private String endSecond;

	private String screen;

	/**
	 * @param originalFileName
	 * @param fileName
	 * @param path
	 * @param screen
	 */
	public UploadResourceForm(String originalFileName, String fileName, String path, String screen) {
		this.success = true;
		this.originalFileName = originalFileName;
		this.fileName = fileName;
		this.id = fileName;
		this.path = path;
		this.displayName = originalFileName;
		this.screen = screen;

		this.playTime = 10;
		this.beginDate = DateUtils.getDate(new Date());
		this.beginHour = "00";
		this.beginMinute = "00";
		this.beginSecond = "00";
		this.endHour = "23";
		this.endMinute = "59";
		this.endSecond = "59";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}

	public String getBeginMinute() {
		return beginMinute;
	}

	public void setBeginMinute(String beginMinute) {
		this.beginMinute = beginMinute;
	}

	public String getBeginSecond() {
		return beginSecond;
	}

	public void setBeginSecond(String beginSecond) {
		this.beginSecond = beginSecond;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}

	public String getEndSecond() {
		return endSecond;
	}

	public void setEndSecond(String endSecond) {
		this.endSecond = endSecond;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
