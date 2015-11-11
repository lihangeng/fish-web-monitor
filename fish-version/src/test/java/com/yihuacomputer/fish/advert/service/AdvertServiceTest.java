package com.yihuacomputer.fish.advert.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.advert.H2TestConfig;
import com.yihuacomputer.fish.api.advert.AdvertDownMethod;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.AdvertValidity;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertResource;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;

/**
 * 广告服务单元测试类
 * @author xuxigang
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AdvertServiceTest extends BindSessionInTest2{

	@Autowired
	private IAdvertService advertService;
	@Autowired
	private IAdvertResourceService advertResourceService;
	@Autowired
	private IVersionTypeService versionTypeService;

	@Test
	@Ignore
	public void testToVersion(){
	    versionTypeService.add(versionTypeService.make("advert"));

	    IAdvert advert = advertService.make(AdvertType.TEXT);
        advert.setAdvertValidity(AdvertValidity.ALWAYS);
        advert.setAdvertDownMethod(AdvertDownMethod.APPEND);

        IAdvertResource adRes1 = advertResourceService.make();
        adRes1.setContent("滚动广告测试");
        adRes1.setPlayTime(10);
        adRes1.setAdvert(advert);
        advert.addAdvertResource(adRes1);

        advertService.add(advert);

        advert.toVersion();
       /* IVersion ver = advert.getVersion();
        assertNotNull(ver);*/
	}

	@Test
	public void testAdd(){
		IAdvert advert = advertService.make(AdvertType.TEXT);
		advert.setAdvertValidity(AdvertValidity.ALWAYS);
		advert.setAdvertDownMethod(AdvertDownMethod.APPEND);

		IAdvertResource adRes1 = advertResourceService.make();
		adRes1.setContent("滚动广告测试");
		adRes1.setPlayTime(10);
		adRes1.setAdvert(advert);
		advert.addAdvertResource(adRes1);

		advertService.add(advert);

//		advertResourceService.add(adRes1); //因为由广告来维护关系，所以不需要写。

		assertNotNull(advert.getCreatedTime());
		assertEquals(10,adRes1.getPlayTime());
		assertEquals("滚动广告测试",adRes1.getContent());
		assertTrue(advert.getId() > 0);

		IAdvertResource adRes2 = advertResourceService.make();
		adRes2.setContent("滚动广告测试2");
		adRes2.setPlayTime(20);
		advert.addAdvertResource(adRes2);//此时保存到数据库

		System.out.println("---输出配置---");
		System.out.println(advert.getAdvertConfig());

//		advertService.update(advert);//需要强制更新一下

		List<IAdvert> adverts = advertService.list(new Filter());
		assertEquals(1,adverts.size());

		IAdvert adv = adverts.get(0);

		List<IAdvertResource> adRess = adv.getAdvertResources();
		assertEquals(2,adRess.size());

		List<IAdvertResource> lists = advertResourceService.list(new Filter());
		assertEquals(2,lists.size());

		//更新广告信息
		advert.setAdvertValidity(AdvertValidity.TEMP);
		//如果发现已经存在广告资源，且要修改的广告类型和广告资源不匹配时，需要提示（这部分功能由页面提供）
//		advert.setAdvertType(AdvertType.TRANS);//业务上不容许
		advertService.update(advert);

		assertEquals(AdvertValidity.TEMP,advert.getAdvertValidity());

		advert.removeAdvertResource(adRes2);//选择性的立即保存
//		advertService.update(advert);//

		List<IAdvertResource> lists2 = advertResourceService.list(new Filter());
		assertEquals(1,lists2.size());



		//删除广告,此时应该级联删除广告资源
		advertService.delete(advert);

		List<IAdvertResource> lists3 = advertResourceService.list(new Filter());
		assertEquals(0,lists3.size());

	}

	@Test
	public void testUpdateAdvertResource(){
		IAdvertResource adRes1 = advertResourceService.make();
		adRes1.setContent("滚动广告测试");
		adRes1.setPlayTime(10);
		advertResourceService.add(adRes1);
		assertTrue(adRes1.getId() > 0);

		adRes1.setBeginTime("09:00:00");
		advertResourceService.update(adRes1);

		assertEquals("09:00:00",adRes1.getBeginTime());


		advertResourceService.delete(adRes1);
	}

	@Test
	@Ignore
	public void testToZip(){
		IAdvert advert = advertService.make(AdvertType.TRANS);
		advert.setAdvertValidity(AdvertValidity.ALWAYS);
		advert.setAdvertDownMethod(AdvertDownMethod.APPEND);

		IAdvertResource adRes1 = advertResourceService.make();
		adRes1.setContent("Koala.jpg");
		adRes1.setPlayTime(10);
		adRes1.setAdvert(advert);
		advert.addAdvertResource(adRes1);

		advertService.add(advert);

		advert.toZipFile();
	}

}
