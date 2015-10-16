package com.yihuacomputer.fish.api.version;

public enum VersionStatus {
    NEW("VersionStatus.NEW"),//刚创建
	WAITING("VersionStatus.WAITING"),//经配置后进入下发队列
	DOWNLOADED("VersionStatus.DOWNLOADED");//已经下发到一台设备，不管成功与否

	private String text;

	private VersionStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
