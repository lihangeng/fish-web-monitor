package com.yihuacomputer.fish.api.advert;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.version.IVersion;

/**
 * 广告的定义
 *
 * @author xuxigang
 *
 */
public interface IAdvert {
    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public long getId();

    /**
     * 设置广告类型
     *
     * @param type
     *            广告类型
     */
    public void setAdvertType(AdvertType type);

    /**
     * 获得广告类型
     *
     * @return 广告类型
     */
    public AdvertType getAdvertType();

    /**
     * 设置广告下发方式
     *
     * @param advertDownMethod
     */
    public void setAdvertDownMethod(AdvertDownMethod advertDownMethod);

    /**
     * 返回广告下发方式
     *
     * @return
     */
    public AdvertDownMethod getAdvertDownMethod();

    /**
     * 设置广告有效期
     *
     * @param advertValidity
     */
    public void setAdvertValidity(AdvertValidity advertValidity);

    /**
     * 获得广告有效期
     *
     * @return
     */
    public AdvertValidity getAdvertValidity();

    /**
     * 设置广告资源
     *
     * @return
     */
    public List<IAdvertResource> getAdvertResources();

    /**
     * 设置广告资源
     *
     * @param resources
     */
    public void setAdvertResources(List<IAdvertResource> resources);

    /**
     * 增加广告资源
     *
     * @param resource
     */
    public void addAdvertResource(IAdvertResource resource);

    /**
     * 删除广告资源
     *
     * @param resource
     */
    public void removeAdvertResource(IAdvertResource resource);

    /**
     * 生成压缩文件
     */
    public void toZipFile();

    /**
     * 生成版本文件
     *
     * @return
     */
    public IVersion toVersion();

    /**
     * 生成版本文件
     * @param zipGenerator
     * @return
     */
    public IVersion toVersion(IAdvertZipGenerator zipGenerator);

    /**
     * 设置广告的制作时间
     *
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime);

    /**
     * 获得广告的制作时间
     *
     * @return
     */
    public Date getCreatedTime();

    /**
     * 获得广告版本信息
     *
     * @return
     */
    public IVersion getVersion();

    /**
     * 获得广告的配置信息
     *
     * @return
     */
    public String getAdvertConfig();

    /**
     * @param screen
     * @return
     */
    public String getAdvertConfigByScreen(Screen screen);

    /**
     * @param versionId
     */
    public void setVersionId(long versionId);

    /**
     * 版本创建人
     *
     * @param user
     */
    /**
     * 版本创建人
     *
     * @param user
     */
    public void setCreateUser(IUser user);

    /**
     * @param userId
     */
    public void setCreateUserId(long userId);

    /**
     * @return
     */
    public IUser getCreateUser();

    /**
     * 广告归属机构
     */
    public long getCreateOrgId();

    /**
     * @param orgId
     */
    public void setCreateOrgId(long orgId);

    /**
     * 广告复核状态
     */
    public CheckStatus getCheckStatus();

    /**
     * @param checkStatus
     */
    public void setCheckStatus(CheckStatus checkStatus);

    /**
     * 广告视频标识
     */
    public long getVideoFlag();

    /**
     * @param videoFlag
     */
    public void setVideoFlag(long videoFlag);

    /**
     * 广告级别(总行广告,分行广告)
     *
     * @return
     */
    public OrganizationLevel getLevel();

    /**
     * 设备广告级别
     *
     * @param level
     */
    public void setLevel(OrganizationLevel level);
}
