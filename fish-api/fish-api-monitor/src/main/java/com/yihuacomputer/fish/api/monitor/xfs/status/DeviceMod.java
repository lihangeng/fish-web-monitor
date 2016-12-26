package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * @author YiHua
 *
 */
public enum DeviceMod {
	/**
	 * 读卡器
	 */
	IDC("DeviceMod.IDC"),
	/**
	 * 射频读卡器
	 */
	NFC("DeviceMod.NFC"),
	/**
	 * 存款
	 */
	CIM("DeviceMod.CIM"),
	/**
	 * 取款
	 */
	CDM("DeviceMod.CDM"),
	/**
	 * 凭条打印机
	 */
	RPR("DeviceMod.RPR"),
	/**
	 * 日志打印机
	 */
	JPR("DeviceMod.JPR"),
	/**
	 * 存折打印机
	 */
	PBK("DeviceMod.PBK"),
	/**
	 * 密码键盘
	 */
	PIN("DeviceMod.PIN"),
	/**
	 * 文本终端
	 */
	TTU("DeviceMod.TTU"),
	/**
	 * 传感器
	 */
	SIU("DeviceMod.SIU"),
	/**
	 * 发卡读卡器
	 */
	ICC("DeviceMod.ICC") ,
	/**
	 * 指纹仪
	 */
	FGP("DeviceMod.FGP") ,
	/**
	 * 二维码
	 */
	BCR("DeviceMod.BCR") ,
	/**
	 * 摄像头
	 */
	CAM("DeviceMod.CAM") ,
	/**
	 * 身份证扫描仪
	 */
	ISC("DeviceMod.ISC") ,
	/**
	 * 发UKEY模块
	 */
	UKD("DeviceMod.UKD") ,
	/**
	 * 读UKEY模块
	 */
	UKR("DeviceMod.UKR") ;


	private String text;

	private DeviceMod(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
