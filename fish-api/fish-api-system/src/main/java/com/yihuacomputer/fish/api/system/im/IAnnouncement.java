package com.yihuacomputer.fish.api.system.im;

import java.util.Date;

/**
 * 公告信息：
 *
 * @author shixiaolong
 *
 */
public interface IAnnouncement {

    /**
     * @return
     */
    public long getId();

    /**
     * @return
     */
    public String getGuid();

    /**
     * @param guid
     */
    public void setGuid(String guid);

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public Date getPublishDate();

    /**
     * @param publishDate
     */
    public void setPublishDate(Date publishDate);

    /**
     * @return
     */
    public Date getEndDate();

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate);

    /**
     * @return
     */
    public String getPublisher();

    /**
     * @param publisher
     */
    public void setPublisher(String publisher);

    /**
     * @return
     */
    public String getTheme();

    /**
     * @param theme
     */
    public void setTheme(String theme);

    /**
     * @return
     */
    public String getAttachment();

    /**
     * @param attachment
     */
    public void setAttachment(String attachment);

    /**
     * @return
     */
    public String getContent();

    /**
     * @param content
     */
    public void setContent(String content);

    /**
     * @return
     */
    public Date getReleaseDate();

    /**
     * @param releaseDate
     */
    public void setReleaseDate(Date releaseDate);

    /**
     * @return
     */
    public String getReleasePerson();

    /**
     * @param releasePerson
     */
    public void setReleasePerson(String releasePerson);

    /**
     * @return
     */
    public AnnounceStatus getAnnounceStatus();

    /**
     * @param announceStatus
     */
    public void setAnnounceStatus(AnnounceStatus announceStatus);

    /**
     * @return
     */
    public long getPublisherOrg();

    /**
     * @param publisherOrg
     */
    public void setPublisherOrg(long publisherOrg);
}
