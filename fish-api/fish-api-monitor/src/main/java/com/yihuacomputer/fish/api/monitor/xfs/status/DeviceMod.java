package com.yihuacomputer.fish.api.monitor.xfs.status;

public enum DeviceMod {
	IDC("读卡器"),
	NFC("射频读卡器"),
	CIM("存款"),
	CDM("取款"),
	RPR("凭条打印机"),
	JPR("日志打印机"),
	PBK("存折打印机"),
	PIN("密码键盘"),
	TTU("文本终端"),
	SIU("传感器"),
	ICC("发卡读卡器") ,
	FGP("指纹仪") ,
	ISC("身份证扫描仪") ;


	private String text;

	private DeviceMod(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
