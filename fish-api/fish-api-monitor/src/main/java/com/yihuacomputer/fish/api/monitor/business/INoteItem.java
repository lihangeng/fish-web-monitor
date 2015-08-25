package com.yihuacomputer.fish.api.monitor.business;

/**
 * 每张钱币的信息
 * @author YiHua
 *
 */
public interface INoteItem{
	/**
	 * 序号.
	 * @return 序号
	 */
	public int getSerial();
	
//
//	/**
//	 * 版别
//	 * 90/99/05
//	 * @return
//	 */
//	public String getVersion() ;


//	/**
//	 * 面值.
//	 */
//	public String getValue();

	/**
	 * 获取冠字号.
	 * @return 冠字号
	 */
	public String getNoteCode();

//	/**
//	 * 图像全路径文件名.
//	 * @return null 表示无图像
//	 */
//	public String getImage() ;
}