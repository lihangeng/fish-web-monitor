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
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.machine.H2TestConfig;
/**
 *
 * @author wangchao
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AtmCatalogServiceTest extends BindSessionInTest2{
	@Autowired
	private IAtmCatalogService atmCatalogService;

	@Test
	public void test(){
		IAtmCatalog catalog1 = atmCatalogService.make();
		catalog1.setName("分类1");
		catalog1.setNo("0001");
		catalog1.setNote("分类1");
		atmCatalogService.add(catalog1);

		IAtmCatalog catalog2 = atmCatalogService.make();
		catalog2.setName("分类2");
		catalog2.setNo("0002");
		catalog2.setNote("分类2");
		atmCatalogService.add(catalog2);

		catalog2.setName("分类信息2");
		atmCatalogService.update(catalog2);
		IAtmCatalog newCatalog = atmCatalogService.get(catalog2.getId());
		assertEquals("0002",newCatalog.getNo());

		Iterable<IAtmCatalog> lists = atmCatalogService.list();
		List<IAtmCatalog> result = new ArrayList<IAtmCatalog>();
		for(IAtmCatalog item : lists){
			result.add(item);
		}
//		assertEquals(2,result.size());

		IFilter filter = new Filter();
		filter.eq("no", "0001");
		Iterable<IAtmCatalog> resultFilter = atmCatalogService.list(filter);
		List<IAtmCatalog> resultType = new ArrayList<IAtmCatalog>();
		for(IAtmCatalog item : resultFilter){
			resultType.add(item);
		}
		assertEquals(1,resultType.size());

		IPageResult<IAtmCatalog> page = atmCatalogService.page(0, 10, filter);
		assertEquals(1,page.getTotal());

		atmCatalogService.remove(catalog1.getId());
		Iterable<IAtmCatalog> listRemove = atmCatalogService.list();
		List<IAtmCatalog> removeLists = new ArrayList<IAtmCatalog>();
		for(IAtmCatalog item : listRemove){
			removeLists.add(item);
		}
		assertEquals(1,removeLists.size());

		atmCatalogService.remove(catalog2.getNo());
		Iterable<IAtmCatalog> listRemove1 = atmCatalogService.list();
		List<IAtmCatalog> removeLists1 = new ArrayList<IAtmCatalog>();
		for(IAtmCatalog item : listRemove1){
			removeLists1.add(item);
		}
		assertEquals(0,removeLists1.size());
	}
}
