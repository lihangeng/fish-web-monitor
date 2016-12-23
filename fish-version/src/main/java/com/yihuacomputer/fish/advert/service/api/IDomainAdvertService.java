/**
 *
 */
package com.yihuacomputer.fish.advert.service.api;

import com.yihuacomputer.fish.advert.entity.Advert;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertService;
import com.yihuacomputer.fish.api.advert.IAdvertZipGenerator;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IVersion;

/**
 * @author xuxigang
 *
 */
public interface IDomainAdvertService extends IAdvertService {

	/**
	 * @return
	 */
	public IAdvertResourceService getAdvertResourceService();

	/**
	 * @param advert
	 * @param zipGenerator
	 * @return
	 */
	public IVersion generateVersion(Advert advert,IAdvertZipGenerator zipGenerator);

	/**
	 * 获得版本信息
	 * @param advert
	 * @return 找不到返回null
	 */
	public IVersion findVersion(Advert advert);

	/**
	 * @return
	 */
	public IUserService getUserService();
}
