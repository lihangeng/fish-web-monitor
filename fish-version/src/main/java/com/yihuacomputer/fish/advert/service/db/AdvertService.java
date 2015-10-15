package com.yihuacomputer.fish.advert.service.db;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.entity.Advert;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertResourceService;
import com.yihuacomputer.fish.advert.service.api.IDomainAdvertService;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertZipGenerator;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.VersionStatus;

/**
 * 广告服务的数据库版实现
 *
 * @author xuxigang
 *
 */
@Service
@Transactional
public class AdvertService implements IDomainAdvertService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IDomainAdvertResourceService advertResourceService;

    @Autowired(required = false)
    private IVersionService versionService;

    @Autowired(required = false)
    private IVersionTypeService versionTypeService;

    @Autowired(required = false)
    private IUserService userService;

    @Autowired
    private MessageSource messageSourceVersion;
    
    @Override
    @Transactional(readOnly = true)
    public IAdvert make(AdvertType advertType) {
        Advert advert = new Advert(advertType);
        advert.setAdvertService(this);
        return advert;
    }

    @Override
    @Transactional(readOnly = true)
    public IAdvert getById(long id) {
        return dao.get(id, Advert.class);
    }

    @Override
    public IAdvert add(IAdvert advert) {
        Advert entity = interface2Entity(advert, false);
        dao.save(entity);
        return entity;
    }

    @Override
    public void update(IAdvert advert) {
        dao.update(this.interface2Entity(advert, true));
    }

    private Advert interface2Entity(IAdvert advert, boolean load) {
        if (advert instanceof Advert) {
            return (Advert) advert;
        }
        return null;
    }

    @Override
    public void delete(IAdvert advert) {
        if (advert == null) {
            throw new NotFoundException(messageSourceVersion.getMessage("advert.deleteNullRecord", null, FishCfg.locale));
        }
        // delete version
        IVersion version = advert.getVersion();
        if (version != null && versionService != null) {
        	if(VersionStatus.WAITING.equals(version.getVersionStatus())
        			||VersionStatus.DOWNLOADED.equals(version.getVersionStatus())){
        		throw new AppException(messageSourceVersion.getMessage("advert.cantDeleteDownOrWaitAdvert", null, FishCfg.locale));
        	}
            versionService.delete(version);
        }
        long id = advert.getId();
        // delete advert
        dao.delete(id, Advert.class);
        // delete advert temp dir
        String dir = VersionCfg.getAdvertDir() + File.separator + id;
        IOUtils.deleteDir(dir);
    }

    @Override
    public void delete(long id) {
        this.delete(this.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IAdvert> list(IFilter filter) {
        return dao.findByFilter(filter, IAdvert.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IAdvert> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Advert.class);
    }

    /**
     * 根据广告配置生成版本信息 only generate once time?
     */
    public IVersion generateVersion(Advert advert,IAdvertZipGenerator zipGenerator) {
        IVersion version = findVersion(advert);
        // 生成压缩文件
        zipGenerator.generateZipFile(advert);
        if (version == null) {
            // 生成版本
            IVersionType type = versionTypeService.getByName("advert");
            version = versionService.make();
            version.setVersionType(type);

            version.setVersionPath(type.getDefaultInstallPath());
            version.setVersionNo(versionService.getNextVersionNo(type.getId()));
            version.setServerPath("advert_" + advert.getId() + ".zip");
            version.setCreateUser(advert.getCreateUser());
            versionService.add(version);

            //update to 3.1.2 ,这句话不生效，why？先放到控制层中调用
           /* advert.setVersionId(version.getId());
            dao.update(advert);*/
            this.updateAdvert(advert, version.getId());
        }
        return version;
    }

    private void updateAdvert(Advert advert,long versionId){
        advert.setVersionId(versionId);
        dao.update(advert);
    }

    @Transactional(readOnly = true)
    public IVersion findVersion(Advert advert) {
        if (versionService != null && advert.getVersionId() != 0) {
            return versionService.getById(advert.getVersionId());
        }
        else {
            return null;
        }
    }

    @Override
    public IDomainAdvertResourceService getAdvertResourceService() {
        return this.advertResourceService;
    }

    public IUserService getUserService() {
        return this.userService;
    }

}