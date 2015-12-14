package com.yihuacomputer.fish.machine.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.machine.H2TestConfig;

 /**
  *
  * @author wangchao
  *
  */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AtmModuleServiceTest extends BindSessionInTest2{
	@Autowired
	private IAtmModuleService atmModuleService;

    @Ignore
	@Test
	public void test(){
		IAtmModule module1 = atmModuleService.make();
		module1.setName("读卡器");
		module1.setNo("0001");
		module1.setNote("这是读卡器模块");
		module1.setCase(false);
		atmModuleService.add(module1);

		IAtmModule module2 = atmModuleService.make();
		module2.setName("打印机");
		module2.setNo("0002");
		module2.setNote("这是打印机模块");
		module1.setCase(false);
		atmModuleService.add(module2);

		module2.setNo("0003");
		atmModuleService.update(module2);
		module1.setCase(false);
		IAtmModule test = atmModuleService.get(module2.getId());
		assertEquals("0003",test.getNo());

		Iterable<IAtmModule> lists = atmModuleService.list();
		List<IAtmModule> result = new ArrayList<IAtmModule>();
		for(IAtmModule item : lists){
			result.add(item);
		}
		assertEquals(2,result.size());

		IFilter filter = new Filter();
		filter.eq("no", "0001");
		Iterable<IAtmModule> resultFilter = atmModuleService.list(filter);
		List<IAtmModule> resultType = new ArrayList<IAtmModule>();
		for(IAtmModule item : resultFilter){
			resultType.add(item);
		}
		assertEquals(1,resultType.size());

		IPageResult<IAtmModule> page = atmModuleService.page(0, 10, filter);
		assertEquals(1,page.getTotal());

		atmModuleService.remove(module1.getId());
		Iterable<IAtmModule> listRemove = atmModuleService.list();
		List<IAtmModule> removeLists = new ArrayList<IAtmModule>();
		for(IAtmModule item : listRemove){
			removeLists.add(item);
		}
		assertEquals(1,removeLists.size());

	}
}
