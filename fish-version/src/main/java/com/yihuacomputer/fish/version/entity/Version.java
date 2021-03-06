/**
 *
 */
package com.yihuacomputer.fish.version.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.context.MessageSource;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.VersionNo;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.version.service.api.IDomainVersionService;

/**
 * @author xuxigang
 *
 */
@Entity
@Table(name = "VER_VERSION")
public class Version implements IVersion, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_VERSION")
    @SequenceGenerator(name = "SEQ_VER_VERSION", sequenceName = "SEQ_VER_VERSION")
    @Column(name = "ID")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @ManyToOne(targetEntity = VersionType.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "VERSION_TYPE_ID", nullable = false)
    private IVersionType versionType;

    @ManyToOne(targetEntity = Version.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPEND_VERSION_ID", nullable = true)
    private IVersion dependVersion;

    /**
     * 支持的版本编号格式 如:001 2 1.01 1.0.03 001.000.0004
     */
    @Column(name = "VERSION_NO", nullable = false, length = 40)
    private String versionNo;
    
    /**
     * 版本号字符串拼接
     * 1.2.3.4* 00000001000000020000000300000004
     * 1.1.32.5*00000001000000020000003200000005
     */
    @Column(name = "VERSION_STR", nullable = false, length = 40)
    private String versionStr;

    @Column(name = "VERSION_PATH", nullable = false, length = 50)
    private String versionPath;

    @Column(name = "SERVER_PATH", nullable = true, length = 64)
    private String serverPath;

    @Column(name = "ORIGINAL_FILE_NAME", nullable = true, length = 60)
    private String originalFileName;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "AUTO_DOWN", columnDefinition = "CHAR", length = 1)
    private boolean autoDown;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "UNCOMPRESS", columnDefinition = "CHAR", length = 1)
    private boolean uncompress;

    @org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
    @Column(name = "EAGER_RESTART", columnDefinition = "CHAR", length = 1)
    private boolean eagerRestart;

    @Column(name = "REMARK", nullable = true, length = 100)
    private String desc;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VERSION_STATUS")
    private VersionStatus versionStatus;

    @Column(name = "EXEC_BEFORE", nullable = true, length = 50)
    private String execBefore;

    @Column(name = "EXEC_AFTER", nullable = true, length = 50)
    private String execAfter;

    @Column(name = "CREATE_USER_ID", nullable = true, length = 20)
    private long createUserId;

    @Column(name = "MD5_CHECK_NUM", nullable = true, length = 32)
    private String md5CheckNum;
    

    @Column(name = "DOWNLOAD_COUNTER")
    private int downloadCounter;

    @Transient
    private IUser createUser;

    @Transient
    private IDomainVersionService versionService;
    
    @Transient
    private MessageSource messageSourceVersion;

    /**
     * 初始化
     */
    public Version() {
        this.createdTime = new Date();
        this.autoDown = false;
        this.uncompress = false;
        this.versionStatus = VersionStatus.NEW;
        this.eagerRestart = false;
    }

    /**
     * @param versionService
     */
    public Version(IDomainVersionService versionService) {
        this();
        this.versionService = versionService;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public IVersionType getVersionType() {
        return versionType;
    }

    @Override
    public void setVersionType(IVersionType versionType) {
        this.versionType = versionType;
    }

    @Override
    public IVersion getDependVersion() {
        return dependVersion;
    }

    @Override
    public void setDependVersion(IVersion preVersion) {
        this.dependVersion = preVersion;
    }

    @Override
    public String getVersionNo() {
        return versionNo;
    }

    @Override
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String getVersionPath() {
        return versionPath;
    }

    @Override
    public void setVersionPath(String versionPath) {
        this.versionPath = versionPath;
    }

    @Override
    public boolean isAutoDown() {
        return autoDown;
    }

    @Override
    public void setAutoDown(boolean autoDown) {
        this.autoDown = autoDown;
    }

    @Override
    public void setExecBefore(String execBefore) {
        this.execBefore = execBefore;
    }

    @Override
    public String getOriginalFileName() {
        return originalFileName;
    }

    @Override
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Override
    public void setExecAfter(String execAfter) {
        this.execAfter = execAfter;
    }

    @Override
    public String getExecBefore() {
        return execBefore;
    }

    @Override
    public String getExecAfter() {
        return execAfter;
    }

    @Override
    public boolean isStandAlone() {
        return this.getDependVersion() == null ? true : false;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getFullName() {
        return this.getVersionType().getTypeName() + "_" + this.versionNo;
    }

    @Override
    public String getServerPath() {
        return serverPath;
    }

    @Override
    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    @Override
    public VersionStatus getVersionStatus() {
        return versionStatus;
    }

    @Override
    public void setVersionStatus(VersionStatus versionStatus) {
        this.versionStatus = versionStatus;
    }

    @Override
    public boolean isUncompress() {
        return uncompress;
    }

    @Override
    public void setUncompress(boolean uncompress) {
        this.uncompress = uncompress;
    }

    @Override
    public boolean isEagerRestart() {
        return eagerRestart;
    }

    @Override
    public void setEagerRestart(boolean eagerRestart) {
        this.eagerRestart = eagerRestart;
    }

    public MessageSource getMessageSourceVersion() {
		return messageSourceVersion;
	}

	public void setMessageSourceVersion(MessageSource messageSourceVersion) {
		this.messageSourceVersion = messageSourceVersion;
	}

	/**
     * 目标版本是否在当前版本在之后 1.目标版本大于当前版本 2.目标版本的依赖版本小于等于当前版本
     */
	@Override
    public boolean isAfter(IVersion currentVersion) {
        if (currentVersion == null) {
            return (this.getDependVersion() == null);
        }

        if (!this.getVersionType().getTypeName().equals(currentVersion.getVersionType().getTypeName())) {
//        	"不同的软件分类之间不能进行版本比较"
            throw new AppException(messageSourceVersion.getMessage("exception.version.diffVersionTypeCantCompare", null, FishCfg.locale));
        }

        if (biggerThan(currentVersion)) {// 1.目标版本大于当前版本
            if (this.getDependVersion() == null) {
                return true;
            }
            VersionNo currentVersionNo = new VersionNo(currentVersion.getVersionNo());
            VersionNo depVersionNo = new VersionNo(this.getDependVersion().getVersionNo());
            if (!depVersionNo.isBiggerThan(currentVersionNo)) {
                return true;
            }
        }

        return false;
    }

    private boolean biggerThan(IVersion version) {
        VersionNo me = new VersionNo(this.getVersionNo());
        VersionNo other = new VersionNo(version.getVersionNo());
        return me.isBiggerThan(other);
    }

    @Override
    public IUser getCreateUser() {
        if (this.createUser == null && this.createUserId > 0) {
            this.createUser = versionService.getUserService().get(this.createUserId);
        }
        return this.createUser;
    }

    @Override
    public void setCreateUser(IUser user) {
        this.createUser = user;
        if (user != null) {
            this.createUserId = user.getId();
        }
    }

    public long getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public void setVersionService(IDomainVersionService versionService) {
        this.versionService = versionService;
    }

    @Override
    public String getMd5CheckNum() {
        return md5CheckNum;
    }

    @Override
    public void setMd5CheckNum(String md5CheckNum) {
        this.md5CheckNum = md5CheckNum;
    }

    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
	public String getVersionStr() {
		return versionStr;
	}

    @Override
	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

    @Override
	public int getDownloadCounter() {
		return downloadCounter;
	}

    @Override
	public void setDownloadCounter(int downloadCounter) {
		this.downloadCounter = downloadCounter;
	}

}
