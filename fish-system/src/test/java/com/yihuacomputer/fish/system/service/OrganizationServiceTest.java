package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.OrganizationState;
import com.yihuacomputer.fish.system.H2TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class OrganizationServiceTest extends BindSessionInTest2{

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IPersonService personService;

	@Test
	public void testC(){

		//测试增加一条机构信息并初始化其编号和名称方法
		IOrganization root = organizationService.add("test","测试组");//增加方式1
		assertTrue(Long.valueOf(root.getGuid()) > 0);
		assertEquals("test",root.getCode());
		assertEquals("测试组",root.getName());

		//测试机构信息实体的addChild方法
		IOrganization child = root.addChild("test2", "测试子组2");//增加方式3
		assertTrue(Long.valueOf(child.getGuid()) > 0);
		assertEquals("test2",child.getCode());
		assertEquals("测试子组2",child.getName());
		assertEquals("test",child.getParent().getCode());
		assertEquals("测试组",child.getParent().getName());

		//增加测试人员信息记录
		IPerson person1 = personService.add("person4");
        assertTrue(Long.valueOf(person1.getGuid())>0);
        assertTrue(person1.getName()=="person4");

		//测试机构信息实体的make（）方法
		IOrganization test1 = organizationService.make();//增加方式2
		test1.setCode("test1");
		test1.setName("测试子组1");
		test1.setAddress("郁金香大厦");
		test1.setZip("123456");
		test1.setDescription("测试作用");
		test1.setManager(person1);
		test1.setOrganizationState(OrganizationState.START);
//		test1.setOrganizationType(OrganizationType.BANK);
		test1.setParent(root);
		organizationService.add(test1);
		assertTrue(Long.valueOf(test1.getGuid()) > 0);
		assertEquals("test1",test1.getCode());
		assertEquals("测试子组1",test1.getName());
		assertEquals("郁金香大厦",test1.getAddress());
		assertEquals("123456",test1.getZip());
		assertEquals("测试作用",test1.getDescription());
		assertEquals(person1,test1.getManager());
		assertEquals(OrganizationState.START,test1.getOrganizationState());
	//	assertEquals("商业机构",test1.getOrganizationType());
		assertEquals("test",test1.getParent().getCode());
        assertEquals("测试组",test1.getParent().getName());

		//测试根据外部ID获得机构信息方法
		IOrganization test = organizationService.get("3");
		assertEquals("test1",test.getCode());
        assertEquals("测试子组1",test.getName());
        assertEquals("郁金香大厦",test.getAddress());
        assertEquals("123456",test.getZip());
        assertEquals("测试作用",test.getDescription());
        assertEquals(person1,test.getManager());
        assertNull(test.getOrganizationType());
        assertEquals(OrganizationState.START,test.getOrganizationState());
      //  assertEquals("商业机构",test1.getOrganizationType());

        //获得所有机构信息方法
        Iterable<IOrganization> organizationIterable = organizationService.list();
        for(IOrganization or : organizationIterable){
            System.out.println(or.getGuid());
        }

		//测试显示所有的子机构
        Iterable<IOrganization> childs = root.listChildren();
        int i = 0;
        for(IOrganization each : childs){
            i ++;
            System.out.println(each.getCode());
        }
         assertEquals(2,i);

		//测试更新机构信息方法
		child.setAddress("郁金香大厦");
		child.setZip("123456");
		organizationService.update(child);
		assertEquals("郁金香大厦",child.getAddress());
		assertEquals("123456",child.getZip());

		//测试有条件的分页显示机构信息方法
//		IFilter filter = new Filter();
//		filter.like("code", "test");
//		filter.eq("parent", root);
//		IPageResult<IOrganization> page = organizationService.page(0, 10,filter);
//		assertEquals(1,page.getTotal());

	}
	
	@Test
	public void testOrgCache(){
		IOrganization root = organizationService.add("cache","cacheTest");
		assertTrue(Long.valueOf(root.getGuid()) > 0);
		assertEquals("cache",root.getCode());
		assertEquals("cacheTest",root.getName());
		System.out.println("1111111111111111111");
		IOrganization cache = organizationService.get(root.getGuid());
		assertEquals("cache",cache.getCode());
		assertEquals("cacheTest",cache.getName());
		System.out.println("2222222222222222222");
		
		//update
		cache.setAddress("Shanghai");
		organizationService.update(cache);
		IOrganization org2 =  organizationService.get(cache.getGuid());
		assertEquals("Shanghai",org2.getAddress());
		System.out.println("44444444444444444444");
		
		//remove
		organizationService.remove(cache.getGuid());
		IOrganization org4 =  organizationService.get(cache.getGuid());
		assertNull(org4);
		
	}

}
