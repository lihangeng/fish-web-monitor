package com.yihuacomputer.fish.api.advert.bs;

import java.util.Date;

import com.yihuacomputer.fish.api.advert.Screen;

/**
 * @author YiHua
 *
 */
public interface IBsAdvertResource {
	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * 设置播放时长，单位是秒
	 * @param playTime 播放时长
	 */
	public void setPlayTime(int playTime);
	/**
	 * 获得播放时长
	 * @return 播放时长
	 */
	public int getPlayTime();
	
	/**
	 * 设置广告内容
	 * @param content
	 */
	public void setContent(String content);

	/**
	 * 获得广告内容
	 * 
	 * @return
	 */
	public String getContent();

	/**
	 * 设置播放的开始日期
	 * @param beginDate 开始日期
	 */
	public void setBeginDate(Date beginDate);
	/**
	 * 获得播放的开始日期
	 * @return 开始日期
	 */
	public Date getBeginDate();
	/**
	 * 设置播放的结束日期
	 * @param endDate 结束日期
	 */
	public void setEndDate(Date endDate);
	/**
	 * 获得播放的结束日期
	 * @return 结束日期
	 */
	public Date getEndDate();
	/**
	 * 设置播放的开始时间
	 * 时间格式是：HH:mm:ss
	 * @param beginTime 开始时间
	 */
	public void setBeginTime(String beginTime);
	/**
	 * 获得播放的开始时间
	 * @return
	 */
	public String getBeginTime();
	/**
	 * 设置播放的结束时间
	 * 时间格式是：HH:mm:ss
	 * @param endTime
	 */
	public void setEndTime(String endTime);
	/**
	 * 获得播放的结束时间
	 * @return
	 */
	public String getEndTime();
	/**
	 * 获得归属的广告
	 * @return
	 */
	public IBsAdvert getBsAdvert();
	/**
	 * 设置归属的广告
	 * @param advert
	 */
	public void setBsAdvert(IBsAdvert advert);
	
	/**
	 * 获得资源的配置文件，组织成JSON的格式
	 * @return
	 */
	public String getConfig();
	
	/**
	 * 获得资源可以显示的分辨率，农商行特有
	 * @return
	 */
	public Screen getScreen();
	
	/**
	 * @param screen
	 */
	public void setScreen(Screen screen);
}
