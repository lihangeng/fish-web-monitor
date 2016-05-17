package com.yihuacomputer.fish.web.command.format;

public class FileSysOperaterForm {
	private String filePath;
	private String fileName;
	private FileSysOperatorType fileSysOperatorType;
	private boolean success;
	private boolean existFile;
	private String failReason;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FileSysOperatorType getFileSysOperatorType() {
		return fileSysOperatorType;
	}

	public void setFileSysOperatorType(FileSysOperatorType fileSysOperatorType) {
		this.fileSysOperatorType = fileSysOperatorType;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public boolean isExistFile() {
		return existFile;
	}

	public void setExistFile(boolean existFile) {
		this.existFile = existFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
