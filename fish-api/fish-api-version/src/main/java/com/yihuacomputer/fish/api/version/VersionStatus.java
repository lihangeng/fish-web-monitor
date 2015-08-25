package com.yihuacomputer.fish.api.version;

public enum VersionStatus {
    NEW("新建"),//刚创建
	WAITING("等待下发"),//经配置后进入下发队列
	DOWNLOADED("已下发");//已经下发到一台设备，不管成功与否

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
