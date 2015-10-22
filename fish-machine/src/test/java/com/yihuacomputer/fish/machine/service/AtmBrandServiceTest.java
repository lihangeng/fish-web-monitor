package com.yihuacomputer.fish.machine.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.atm.VendorStatus;
import com.yihuacomputer.fish.machine.H2TestConfig;
/**
 *
 * @author wangchao
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = H2TestConfig.class)
public class AtmBrandServiceTest extends BindSessionInTest2{

//	@Autowired
	private IAtmBrandService atmBrandService;

//	@Test
	public void test(){
		IAtmVendor vendor = atmBrandService.make();
		vendor.setName("怡化");
//		vendor.setNo("0001");
		vendor.setStatus(VendorStatus.SUPPLY);
		vendor.setCountry("中国");
		vendor.setAddress("南京郁金香路2号");
		vendor.setHotline1("025-86985698");
		vendor.setHotline2("025-86236565");
		atmBrandService.add(vendor);

		IAtmVendor oVendor = atmBrandService.make();
		oVendor.setName("怡化1");
//		oVendor.setNo("0002");
		oVendor.setStatus(VendorStatus.SERVE);
		oVendor.setCountry("中国深圳");
		oVendor.setAddress("南京郁金香路2号");
		oVendor.setHotline1("025-8656110");
		oVendor.setHotline2("025-8656120");
		atmBrandService.add(oVendor);

		IAtmVendor vendor1 = atmBrandService.get(vendor.getId());
		assertEquals("怡化",vendor1.getName());

		IAtmVendor vendor2 = atmBrandService.get(oVendor.getId());
		assertEquals("怡化1",vendor2.getName());

		oVendor.setName("怡化make");
		atmBrandService.update(oVendor);
		assertEquals("怡化make",oVendor.getName());

		Iterable<IAtmVendor> list = atmBrandService.list();
		List<IAtmVendor> entities = new ArrayList<IAtmVendor>();
		for(IAtmVendor item : list){
			entities.add(item);
		}
		assertEquals(2,entities.size());

		IFilter filter = new Filter();
		filter.eq("name", "怡化make");

		IPageResult<IAtmVendor> page = atmBrandService.page(0, 10, filter);
		assertEquals(1,page.getTotal());
		for(IAtmVendor item : page.list()){
			assertEquals("中国深圳",item.getCountry());
		}

		Iterable<IAtmVendor> lists = atmBrandService.list(filter);
		for(IAtmVendor item : lists){
			assertEquals("中国深圳",item.getCountry());
		}

		atmBrandService.remove(vendor.getId());
		Iterable<IAtmVendor> listRemove = atmBrandService.list();
		List<IAtmVendor> removeLists = new ArrayList<IAtmVendor>();
		for(IAtmVendor item : listRemove){
			removeLists.add(item);
		}
		assertEquals(1,removeLists.size());


		atmBrandService.remove(oVendor.getId());
		Iterable<IAtmVendor> listRemove1 = atmBrandService.list();
		List<IAtmVendor> removeLists1 = new ArrayList<IAtmVendor>();
		for(IAtmVendor item : listRemove1){
			removeLists1.add(item);
		}
		assertEquals(0,removeLists1.size());
	}
}
