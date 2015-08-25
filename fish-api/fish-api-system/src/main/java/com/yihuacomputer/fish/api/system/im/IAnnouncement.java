package com.yihuacomputer.fish.api.system.im;

import java.util.Date;

/**
 * 公告信息：
 *
 * @author shixiaolong
 *
 */
public interface IAnnouncement {

    public long getId();

    public String getGuid();

    public void setGuid(String guid);

    public void setId(long id);

    public Date getPublishDate();

    public void setPublishDate(Date publishDate);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public String getPublisher();

    public void setPublisher(String publisher);

    public String getTheme();

    public void setTheme(String theme);

    public String getAttachment();

    public void setAttachment(String attachment);

    public String getContent();

    public void setContent(String content);

    public Date getReleaseDate();

    public void setReleaseDate(Date releaseDate);

    public String getReleasePerson();

    public void setReleasePerson(String releasePerson);

    public AnnounceStatus getAnnounceStatus();

    public void setAnnounceStatus(AnnounceStatus announceStatus);

    public long getPublisherOrg();

    public void setPublisherOrg(long publisherOrg);
}
