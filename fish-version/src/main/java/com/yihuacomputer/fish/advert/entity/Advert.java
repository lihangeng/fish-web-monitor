package com.yihuacomputer.fish.advert.entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertService;
import com.yihuacomputer.fish.api.advert.AbstractAdvertZipGenerator;
import com.yihuacomputer.fish.api.advert.AdvertDownMethod;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.AdvertValidity;
import com.yihuacomputer.fish.api.advert.CheckStatus;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertResource;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertZipGenerator;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionCfg;

/**
 * 关于广告和广告资源的关系，这里有广告来维护，即由“一”端来维护，正常的配置应该是由“多”端维护。 采用“一”端维护，会增加一条update语句
 *
 * @author xuxigang
 *
 */
@Entity
@Table(name = "ADV_ADVERT")
public class Advert implements IAdvert, Serializable {
	
	@Transient
	private static Logger logger = LoggerFactory.getLogger(Advert.class);

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_ADVERT")
    @SequenceGenerator(name = "SEQ_ADV_ADVERT", sequenceName = "SEQ_ADV_ADVERT")
    @Column(name = "ID")
    private long id;

    /**
     * 广告类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ADVERT_TYPE", nullable = false, length = 32)
    private AdvertType advertType;

    /**
     * 广告下发方式
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ADVERT_DOWN_METHOD", nullable = false, length = 32)
    private AdvertDownMethod advertDownMethod;

    /**
     * 广告有效期
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ADVERT_VALIDITY", nullable = false, length = 32)
    private AdvertValidity advertValidity;

    /**
     * 广告制作时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME", nullable = true)
    private Date createdTime;

    // @OneToMany(mappedBy = "advert",targetEntity = AdvertResource.class)
    @OneToMany(targetEntity = AdvertResource.class, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADVERT_ID")
    private List<IAdvertResource> advertResources = new ArrayList<IAdvertResource>();

    @Column(name = "VERSION_ID", nullable = true)
    private long versionId;

    @Column(name = "CREATE_USER_ID", nullable = true, length = 20)
    private long createUserId;

    @Transient
    private IUser createUser;

    @Transient
    private IDomainAdvertService advertService;

    @Column(name = "CREATE_ORG_ID")
    private long createOrgId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CHECK_STATUS")
    private CheckStatus checkStatus;

    @Column(name = "VIDEO_FLAG")
    private long videoFlag;

    @Transient
    private OrganizationLevel level;
    
    @Transient
    private MessageSource messageSourceVersion;

    
    public MessageSource getMessageSourceVersion() {
		return messageSourceVersion;
	}

	public void setMessageSourceVersion(MessageSource messageSourceVersion) {
		this.messageSourceVersion = messageSourceVersion;
	}

	public Advert() {
        this.createdTime = new Date();
    }

    public Advert(AdvertType advertType) {
        this();
        this.advertType = advertType;
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
    public AdvertType getAdvertType() {
        return advertType;
    }

    @Override
    public void setAdvertType(AdvertType advertType) {
        this.advertType = advertType;
    }

    @Override
    public List<IAdvertResource> getAdvertResources() {
        return advertResources;
    }

    @Override
    public void setAdvertResources(List<IAdvertResource> advertResources) {
        this.advertResources = advertResources;
    }

    @Override
    public void addAdvertResource(IAdvertResource resource) {
        this.advertResources.add(resource);
        if (this.getId() > 0) {
            resource.setAdvert(this);
            getResourceService().add(resource);
        }
    }

    private IAdvertResourceService getResourceService() {
        return this.advertService.getAdvertResourceService();
    }

    @Override
    public void removeAdvertResource(IAdvertResource resource) {
        this.advertResources.remove(resource);
        if (this.getId() > 0 && resource.getId() > 0) {
            getResourceService().delete(resource);
        }
    }

    @Override
    public AdvertDownMethod getAdvertDownMethod() {
        return advertDownMethod;
    }

    @Override
    public void setAdvertDownMethod(AdvertDownMethod advertDownMethod) {
        this.advertDownMethod = advertDownMethod;
    }

    @Override
    public AdvertValidity getAdvertValidity() {
        return advertValidity;
    }

    @Override
    public void setAdvertValidity(AdvertValidity advertValidity) {
        this.advertValidity = advertValidity;
    }
    
    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 广告上传的默认目录是：FishCfg.getAdvertDir() 每个广告以ID为名称，生成一个文件夹${id}
     * 在${id}下放入config.ini文件和所有的广告资源
     * 把${id}文件打包放到FishCfg.getVersionDir()目录下，压缩包以advert_ID.zip命名
     */
    @Override
    public void toZipFile() {
        // 在指定目录生成压缩文件
        String config = this.getAdvertConfig();
        IOUtils.writeStringToFile(
                this.getBasePath() + File.separator + AdvertTypeConversionService.convert(this.advertType)
                        + File.separator + "config.ini", config);
        genenateMetaFile();
        ZipUtils.zip(this.getBasePath(), getZipFileName(), "GBK");
    }

    private void genenateMetaFile() {
        File file = new File(getBasePath() + File.separator + "META-INF");
        if (!file.exists()) {
            file.mkdir();
        }

        File meta = new File(file.getAbsolutePath() + File.separator + "MANIFEST.MF");
        if (!meta.exists()) {
            try {
                meta.createNewFile();
            } catch (IOException e) {
            	logger.error(String.format("[%s]", e));
                throw new AppException(messageSourceVersion.getMessage("advert.createMetaFail", null, FishCfg.locale));
            }
        }

        FileWriter fw = null;
        BufferedWriter bw =null;
        try {
            fw = new FileWriter(meta);
            bw = new BufferedWriter(fw);
            bw.write("Manifest-Version: 1.0");
            bw.newLine();
            bw.write("Gump-Scheduler: IMMEDIATE");
            bw.newLine();
            bw.write("Gump-Type: ADVERTISE");
            bw.newLine();
            bw.write("Gump-AppName: YHATMC-AD");
            bw.newLine();
            bw.write("Built-By: yihua");
            bw.newLine();
            bw.write("Build-Jdk: 1.6.0_25");
            bw.newLine();
            bw.write("Gump-Version: 0001");
            bw.newLine();
            bw.write("Created-By: monitor");
            bw.newLine();
            bw.write("Archiver-Version: Plexus Archiver");
            bw.newLine();
            bw.write("Gump-InstallDate: 2012-02-01 00:00:00");
            bw.newLine();
            bw.write("Gump-InstallEndDate: 2012-02-01 00:00:00");
            bw.flush();
        } catch (IOException e) {
        	logger.error(String.format("[%s]", e));
        } finally {
        	if(bw!=null){
           	 	try {
           	 		bw.close();
	            } catch (IOException e) {
	            	logger.error(String.format("[%s]", e));
	            }
        	}
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                	logger.error(String.format("[%s]", e));
                }
            }
            
        }

    }

    private String getZipFileName() {
        return VersionCfg.getVersionDir() + File.separator + "advert" + File.separator + "advert_" + this.id + ".zip";
    }

    private String getBasePath() {
        return VersionCfg.getAdvertDir() + File.separator + this.id;
    }

    @Override
    public String getAdvertConfig() {
        StringBuffer cfg = new StringBuffer();
        cfg.append("{");
        cfg.append("\"id\":\"").append(this.getId()).append("\",");
        cfg.append("\"type\":\"").append(AdvertTypeConversionService.convert(this.getAdvertType())).append("\",");
        cfg.append("\"downMethod\":\"").append(this.getAdvertDownMethod().name()).append("\",");
        cfg.append("\"validity\":\"").append(this.getAdvertValidity().name()).append("\",");
        cfg.append("\"resources\":[");
        StringBuffer resources = new StringBuffer();
        for (IAdvertResource resource : this.getAdvertResources()) {
            resources.append(resource.getConfig()).append(",");
        }
        String r = resources.toString();
        if (StringUtils.isNotEmpty(r)) {
            cfg.append(r.substring(0, r.length() - 1));
        }
        cfg.append("]}");
        return cfg.toString();
    }

    @Override
    public String getAdvertConfigByScreen(Screen screen) {
        StringBuffer cfg = new StringBuffer();
        cfg.append("{");
        cfg.append("\"id\":\"").append(this.getId()).append("\",");
        cfg.append("\"type\":\"").append(AdvertTypeConversionService.convert(this.getAdvertType())).append("\",");
        cfg.append("\"downMethod\":\"").append(this.getAdvertDownMethod().name()).append("\",");
        cfg.append("\"validity\":\"").append(this.getAdvertValidity().name()).append("\",");
        cfg.append("\"resources\":[");
        StringBuffer resources = new StringBuffer();
        for (IAdvertResource resource : this.getAdvertResources()) {
            if (screen.equals(resource.getScreen())) {
                resources.append(resource.getConfig()).append(",");
            }
        }
        String r = resources.toString();
        if (StringUtils.isNotEmpty(r)) {
            cfg.append(r.substring(0, r.length() - 1));
        }
        cfg.append("]}");
        return cfg.toString();
    }

    @Override
    public IVersion toVersion(IAdvertZipGenerator zipGenerator) {
        return advertService.generateVersion(this, zipGenerator);
    }

    @Override
    public IVersion toVersion() {
        return advertService.generateVersion(this, new AbstractAdvertZipGenerator() {
        });
    }

    
    public IDomainAdvertService getAdvertService() {
        return advertService;
    }

    public void setAdvertService(IDomainAdvertService advertService) {
        this.advertService = advertService;
    }

    @Override
    public IVersion getVersion() {
        return advertService.findVersion(this);
    }

    public long getVersionId() {
        return versionId;
    }

    @Override
    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    @Override
    public IUser getCreateUser() {
        if (this.createUser == null && this.createUserId > 0) {
            this.createUser = advertService.getUserService().get(this.createUserId);
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

    @Override
    public long getCreateOrgId() {
        return createOrgId;
    }

    @Override
    public void setCreateOrgId(long createOrgId) {
        this.createOrgId = createOrgId;
    }

    @Override
    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    @Override
    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public long getVideoFlag() {
        return videoFlag;
    }

    @Override
    public void setVideoFlag(long videoFlag) {
        this.videoFlag = videoFlag;
    }

    @Override
    public OrganizationLevel getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(OrganizationLevel level) {
        this.level = level;
    }
}
