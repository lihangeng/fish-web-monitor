package com.yihuacomputer.fish.advert.bs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "ADV_BSADVERT_RESOURCE")
public class BsAdvertResource implements IBsAdvertResource, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_BSADVERT_RESOURCE")
	@SequenceGenerator(name = "SEQ_ADV_BSADVERT_RESOURCE", sequenceName = "SEQ_ADV_BSADVERT_RESOURCE")
	@Column(name = "ID")
	private long id;

	@Column(name = "PLAY_TIME", length = 10)
	private int playTime = 5;

	@Column(name = "CONTENT", length = 140)
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE")
	private Date beginDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "BEGIN_TIME", length = 32)
	private String beginTime;

	@Column(name = "END_TIME", length = 32)
	private String endTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "SCREEN", nullable = false, length = 20)
	private Screen screen;

	/**
	 * 此处的关系由广告来维护，所以需要设置为不可增加和不可修改
	 */
	@ManyToOne(targetEntity = BsAdvert.class)
	@JoinColumn(name = "ADVERT_ID", insertable = false, updatable = false)
	private IBsAdvert bsAdvert;

	/**
	 * 初始化
	 */
	public BsAdvertResource() {
		this.beginDate = new Date();
		this.beginTime = "00:00:00";
		this.endTime = "23:59:59";
		this.screen = Screen.SCREEN_resource;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int getPlayTime() {
		return playTime;
	}

	@Override
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Date getBeginDate() {
		return beginDate;
	}

	@Override
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String getBeginTime() {
		return beginTime;
	}

	@Override
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Override
	public String getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public IBsAdvert getBsAdvert() {
		return bsAdvert;
	}

	@Override
	public void setBsAdvert(IBsAdvert bsAdvert) {
		this.bsAdvert = bsAdvert;
	}

	@Override
	public String getConfig() {
		StringBuffer cfg = new StringBuffer();
		cfg.append("{");
		cfg.append("\"resourceId\":\"").append(this.getId()).append("\",");
		cfg.append("\"playTime\":\"").append(this.getPlayTime()).append("\",");
		cfg.append("\"content\":\"").append(this.getContent()).append("\",");
		cfg.append("\"beginDate\":\"")
				.append(DateUtils.getDate(this.getBeginDate())).append("\",");
		cfg.append("\"endDate\":\"")
				.append(this.getEndDate() == null ? "" : DateUtils
						.getDate(this.getEndDate())).append("\",");
		cfg.append("\"beginTime\":\"").append(this.getBeginTime())
				.append("\",");
		cfg.append("\"endTime\":\"").append(this.getEndTime()).append("\"");
		cfg.append("}");
		return cfg.toString();
	}

	@Override
	public Screen getScreen() {
		return screen;
	}

	@Override
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
}
