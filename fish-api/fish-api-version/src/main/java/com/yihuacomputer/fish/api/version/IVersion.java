package com.yihuacomputer.fish.api.version;

import java.util.Date;

import com.yihuacomputer.fish.api.person.IUser;

/**
 * 版本信息的定义
 * 版本在业务上由版本类型和版本号唯一确定。
 */
public interface IVersion {
	public long getId();

	/**
	 * 设置版本创建时间
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime);

	/**
	 * 获得版本创建时间
	 * @return
	 */
	public Date getCreatedTime();

	/**
	 * 设备版本类型
	 * @param versionType
	 */
	public void setVersionType(IVersionType versionType);

	/**
	 * 获得版本类型
	 * @return
	 */
	public IVersionType getVersionType();

	/**
	 * 设置依赖的版本
	 *
	 * @param version
	 */
	public void setDependVersion(IVersion version);

	/**
	 * 获得依赖的版本
	 * @return
	 */
	public IVersion getDependVersion();

	/**
	 * 目前的版本规则不包括里程碑版本(因为无法比较大小)
	 * 设置版本编号
	 * 版本编号可以确定版本的先后顺序
	 * 版本编号的规则:
	 * 1.版本号包含以下5个部分：主版本.次版本.增量版本.修正版本-里程碑版本
	 * 2.次版本、增量版本、修正版本、里程碑版本不是必须的
	 * 3.主版本、次版本、增量版本、修正版本必须为数字；比较是基于数字的
	 * 4.里程碑版本只能包含英文字母、下划线、数字、横线、小数点、空格；比较是基于字符串
	 *
	 * @param versionNo
	 */
	public void setVersionNo(String versionNo);

	/**
	 * 获得版本编号
	 * @return
	 */
	public String getVersionNo();

	/**
	 * 设置版本执行前执行的脚本名称
	 * @param execBefor
	 */
	public void setExecBefore(String execBefore);

	public String getExecBefore();

	/**
	 * 设置版本执行后执行的脚本名称
	 * @param execAfter
	 */
	public void setExecAfter(String execAfter);
	public String getExecAfter();
	/**
	 * 版本存放到ATM上的位置
	 *
	 * @param versionPath
	 */
	public void setVersionPath(String versionPath);
	/**
	 * 获得版本在ATM上的存放位置
	 * @return
	 */
	public String getVersionPath();

	/**
	 * 版本存放在服务器上的位置
	 *
	 * @param serverPath
	 *            相对于版本的绝对位置为 D:/yihua/version/软件类型/**.zip, 则serverPath为fish-web.zip
	 */
	public void setServerPath(String serverPath);

	/**
	 * 获得版本在服务器上的存放位置
	 * @return
	 */
	public String getServerPath();

	/**
	 * 设置自动更新
	 * @param autoDown
	 */
	public void setAutoDown(boolean autoDown);
	/**
	 * 是否自动更新
	 * @return
	 */
	public boolean isAutoDown();

	/**
	 * 是否是独立的版本
	 *
	 * @return
	 */
	public boolean isStandAlone();

	/**
	 * 设置备注
	 * @param desc
	 */
	public void setDesc(String desc);

	public String getDesc();

	/**
	 * 获得版本的全名
	 * @return
	 */
	public String getFullName();

	/**
	 * 获得版本文件的原始文件名
	 * @return
	 */
	public String getOriginalFileName();
	public void setOriginalFileName(String originalFileName);
	/**
	 * 判断当前版本在某版本之后
	 *
	 * @param oneVersion
	 *            某版本
	 * @return
	 */
	public boolean isAfter(IVersion oneVersion);
	/**
	 * 版本状态
	 * @return
	 */
	public VersionStatus getVersionStatus();

	public void setVersionStatus(VersionStatus versionStatus);
	/**
	 * 是否在ATMC端解压缩
	 *
	 * @return true 需要解压
	 */
	public boolean isUncompress ();
	public void setUncompress(boolean uncompress);
	   /**
     * 是否立即重启ATM,软重启
     * @return
     */
	@Deprecated
    public boolean isEagerRestart();
	@Deprecated
    public void setEagerRestart(boolean eagerRestart);

	/**
	 * 版本创建人
	 * @param user
	 */
	public void setCreateUser(IUser user);
	public void setCreateUserId(long userId);
	public IUser getCreateUser();

	/**
	 * 版本文件的MD5校验码，防止在服务器上的压缩包被认为替换，
	 * 在保存版本的时候计算MD5，在修改的时候同步修改（如果压缩包可以修改的话）
	 * @return
	 */
	public String getMd5CheckNum() ;

	public void setMd5CheckNum(String md5CheckNum);

	/**
	 * 获得版本的发布时间
	 * @param releaseDate
	 */
	public void setReleaseDate(Date releaseDate);

	public Date getReleaseDate();
	/**
	 * 获取可比较的版本号字符串信息
	 * @return
	 */
	public String getVersionStr();
	/**
	 * 设置可比较的版本号字符串信息
	 * @param versionStr
	 */
	public void setVersionStr(String versionStr);

	/**
	 * 当前版本手工下发次数
	 * @return
	 */
	public int getDownloadCounter() ;

	/**
	 * 当前版本手工下发次数
	 * @return
	 */
	public void setDownloadCounter(int downloadCounter);
}
