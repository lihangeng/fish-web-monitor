package com.yihuacomputer.fish.machine.service;

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

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.machine.H2TestConfig;

/**
 *
 * @author wangchao
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AtmTypeServiceTest extends BindSessionInTest2{

	@Autowired
	private IAtmTypeService atmTypeService;

    @Ignore
	@Test
	public void test(){
		IAtmType type1 = atmTypeService.make();
		type1.setName("存取款一体机");
		type1.setCashtype(CashType.CASH);
		atmTypeService.add(type1);

		IAtmType type2 = atmTypeService.make();
		type2.setName("自助终端");
		type2.setCashtype(CashType.CASH);
		atmTypeService.add(type2);

		Iterable<IAtmType> lists = atmTypeService.list();
		List<IAtmType> result = new ArrayList<IAtmType>();
		for(IAtmType item : lists){
			result.add(item);
		}
//		assertEquals(2,result.size());

/*		type2.setName("取款机");
		atmTypeService.update(type2);
		IAtmType newType = atmTypeService.get(type2.getId());
		assertEquals("取款机",newType.getName());
		*//**
		 * Iterable<IAtmType> list(IFilter filter)
		 *//*
		IFilter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("no", "q0001"));
		Iterable<IAtmType> resultFilter = atmTypeService.list(filter);
		List<IAtmType> resultType = new ArrayList<IAtmType>();
		for(IAtmType item : resultFilter){
			resultType.add(item);
		}
//		assertEquals(1,resultType.size());
		*//**
		 * 测试page方法(分页)
		 *//*
		IPageResult<IAtmType> page = atmTypeService.page(0, 10, filter);
//		assertEquals(1,page.getTotal());

		*//**
		 * remove(long id)
		 *//*
		atmTypeService.remove(type1.getId());
		Iterable<IAtmType> listRemove = atmTypeService.list();
		List<IAtmType> removeLists = new ArrayList<IAtmType>();
		for(IAtmType item : listRemove){
			removeLists.add(item);
		}
//		assertEquals(1,removeLists.size());
		*//**
		 * remove(String no)
		 *//*
		atmTypeService.remove(type2.getNo());
		Iterable<IAtmType> listRemove1 = atmTypeService.list();
		List<IAtmType> removeLists1 = new ArrayList<IAtmType>();
		for(IAtmType item : listRemove1){
			removeLists1.add(item);
		}*/
//		assertEquals(0,removeLists1.size());


	}
}
