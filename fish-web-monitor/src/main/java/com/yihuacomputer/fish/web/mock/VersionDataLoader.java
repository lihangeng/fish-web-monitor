package com.yihuacomputer.fish.web.mock;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.advert.AdvertDownMethod;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.AdvertValidity;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertService;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCatalog;

/**
 * @author YiHua
 *
 */
public class VersionDataLoader {

	@Autowired
	private IVersionTypeService versionTypeService;
	@Autowired
	private IVersionService versionService;
	@Autowired
	private IAdvertService advertService;
	@Autowired
	private IDeviceSoftVersionService dvService;
	@Autowired
	private IDeviceService deviceService;

	/**
	 * 初始化
	 */
	public void init() {
		//初始化版本
		initVersion();
		//初始化广告
		initAdvert();
		//初始化设备软件分类版本
		initDeviceSoftVersion();
		//初始化内置的自动更新作业
	}

	/**
	 * 初始化设备软件版本
	 */
	private void initDeviceSoftVersion() {
		IDeviceSoftVersion dv = dvService.make();
		dv.setTypeName("YH-ATMC");
		dv.setVersionNo("0.1");
		dvService.add(dv);

		dv = dvService.make();
		dv.setTypeName("YH-ATMC");
		dv.setVersionNo("0.2");
		dvService.add(dv);

	}

	private void initVersion() {
		// init versionType
		IVersionType advert = versionTypeService.make("advert");
		advert.setDefaultInstallPath("C:/yihua/gump/installation");
		advert.setDisplay(false);
		advert.setSystem(true);
		advert.setCustomVersion(false);
		advert.setVersionCatalog(VersionCatalog.ADVERT);
        versionTypeService.add(advert);

		IVersionType profession = versionTypeService.make("gump-professional");
		profession.setDefaultInstallPath("C:/yihua/gump/installation");
		profession.setDisplay(true);
		profession.setSystem(true);
		profession.setCustomVersion(true);
		profession.setVersionCatalog(VersionCatalog.APP);
		versionTypeService.add(profession);

		// init version
		IVersion version = versionService.make();
		version.setVersionNo("0.1");
		version.setVersionPath("c:/test2");
		version.setVersionType(versionTypeService.getByName("gump-professional"));
		version.setCreatedTime(new Date());
		version.setServerPath("gump-0.11.zip");
		version.setDesc("初始化版本");
		versionService.add(version);

		IVersion version2 = versionService.make();
		version2.setVersionNo("0.2");
		version2.setVersionPath("c:/test2");
		version2.setVersionType(versionTypeService.getByName("gump-professional"));
		version2.setCreatedTime(new Date());
		version2.setServerPath("gump-0.11.zip");
		version2.setDependVersion(version);
		versionService.add(version2);

	}

	private void initAdvert() {
		IAdvert advert = advertService.make(AdvertType.TEXT);
		advert.setAdvertDownMethod(AdvertDownMethod.COVER);
		advert.setAdvertValidity(AdvertValidity.TEMP);
		advertService.add(advert);

		advert = advertService.make(AdvertType.WAIT_INSERT_CARD);
		advert.setAdvertDownMethod(AdvertDownMethod.COVER);
		advert.setAdvertValidity(AdvertValidity.TEMP);
		advertService.add(advert);

		advert = advertService.make(AdvertType.TRANS);
		advert.setAdvertDownMethod(AdvertDownMethod.COVER);
		advert.setAdvertValidity(AdvertValidity.ALWAYS);
		advertService.add(advert);
	}

}
