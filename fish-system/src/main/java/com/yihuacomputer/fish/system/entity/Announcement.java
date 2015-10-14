package com.yihuacomputer.fish.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.system.im.AnnounceStatus;
import com.yihuacomputer.fish.api.system.im.IAnnouncement;
import com.yihuacomputer.fish.api.system.im.IAnnouncementService;

/**
 * 公告信息：（信息实体对应数据库表SM_ANNOUNCEMENT）
 *
 * @author shixiaolong
 * @version
 * @since
 * @date 2012-3-14
 */
@Entity
@Table(name = "SM_ANNOUNCEMENT")
public class Announcement implements IAnnouncement {

    @Transient
    private IAnnouncementService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_ANNOUNCEMENT")
    @SequenceGenerator(name = "SEQ_SM_ANNOUNCEMENT", sequenceName = "SEQ_SM_ANNOUNCEMENT")
    @Column(name = "ID")
    private long id;

    /**
     * 主题
     */
    @Column(name = "THEME", nullable = false, length = 64)
    private String theme;

    /**
     * 创建人
     */
    @Column(name = "PUBLISHER", nullable = false, length = 20)
    private String publisher;

    /**
     * 创建日期
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "PUBLISHDATE", nullable = false)
    private Date publishDate;

    /**
     * 截止日期
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATE")
    private Date endDate;

    /**
     * 内容
     */
    @Column(name = "CONTENT", nullable = false, length = 3000)
    private String content;

    /**
     * 附件1
     */
    @Column(name = "ATTACHMENT", nullable = true, length = 64)
    private String attachment;

    /**
     * 发布日期
     */
    @Column(name = "RELEASEDATE")
    private Date releaseDate;

    /**
     * 发布人
     */
    @Column(name = "RELEASEPERSON")
    private String releasePerson;

    /**
     * 公告状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ANNOUNCESTATUS",length =1)
    private AnnounceStatus announceStatus;

    /**
     * 创建人所属机构
     */
    @Column(name = "PUBLISHERORG",nullable = false)
    private long publisherOrg;

    public Announcement() {
    }

    public Announcement(IAnnouncementService service) {
        this.service = service;
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
    public String getTheme() {
        return theme;
    }

    @Override
    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public Date getPublishDate() {
        return publishDate;
    }

    @Override
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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
    public String getPublisher() {
        return publisher;
    }

    @Override
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getAttachment() {
        return attachment;
    }

    @Override
    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
    public void setGuid(String guid) {
        this.id = Long.valueOf(guid);
    }

    @Override
    public String getGuid() {
        return String.valueOf(id);
    }

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleasePerson() {
		return releasePerson;
	}

	public void setReleasePerson(String releasePerson) {
		this.releasePerson = releasePerson;
	}

	public AnnounceStatus getAnnounceStatus() {
		return announceStatus;
	}

	public void setAnnounceStatus(AnnounceStatus announceStatus) {
		this.announceStatus = announceStatus;
	}

	public long getPublisherOrg() {
		return publisherOrg;
	}

	public void setPublisherOrg(long publisherOrg) {
		this.publisherOrg = publisherOrg;
	}

}