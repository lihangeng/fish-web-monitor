package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.api.person.IPersonJobService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.system.DB2TestConfig;
import com.yihuacomputer.fish.system.H2TestConfig;

/**
 * 人员信息单元测试类：
 * 
 * @author huxiaobao
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DB2TestConfig.class)
public class PersonServiceTest extends BindSessionInTest2 {
	@Autowired
	private IPersonService personService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IPersonJobService personJobService;

	@Test
	@Ignore
	public void test() {
		IOrganization organization1 = orgService.make();
		organization1.setCode("12341222");
		organization1.setName("Test2222");
		orgService.add(organization1);

//		IOrganization organ = orgService.get("2");
//		organ.setCode("12341");
//		organ.setName("Test211");
//		System.out.println(organ.getName());
//		orgService.update(organ);
//		IPersonJob personJob = personJobService.make();
//		personJob.setCode("11");
//		personJob.setName("董事长");
//		personJobService.add(personJob);
//
//		// 测试根据名称增加人员信息
//		IPerson person1 = personService.add("person1");
//		assertTrue(Long.valueOf(person1.getGuid()) > 0);
//		assertTrue(person1.getName() == "person1");
//
//		// 测试make后add方法
//		IPerson person2 = personService.make();
//		person2.setBirthday(new Date());
//		person2.setCode("0002");
//		person2.setEmail("123@qq.com");
//		person2.setGender(Gender.MALE);
//		person2.setMobile("12345678910");
//		person2.setName("person2");
//		person2.setOrganization(organization1);
//		person2.setPhone("025-00123456");
//		personService.add(person2);
//		person2.setPersonJob(personJob);
//		assertTrue(Long.valueOf(person2.getGuid()) > 0);
//		// assertTrue(person2.getBirthday()==new Date());
//		assertTrue(person2.getCode() == "0002");
//		assertTrue(person2.getEmail() == "123@qq.com");
//		assertTrue(person2.getGender() == Gender.MALE);
//		assertTrue(person2.getMobile() == "12345678910");
//		assertTrue(person2.getName() == "person2");
//		assertTrue(person2.getOrganization() == organization1);
//		assertTrue(person2.getPhone() == "025-00123456");
//		assertNotNull(person2.getPersonJob());
//		assertEquals("11", person2.getPersonJob().getCode());
//		System.out.println(person2.getGuid());

//		IPerson person = null;
//		// 测试根据外部ID获得人员信息方法
//		person = personService.get(person2.getGuid());
//		assertNotNull(person);
//		System.out.println(person.getEmail());
//		assertTrue(person.getEmail().equals("123@qq.com"));
//
//		IOrganization organization2 = orgService.make();
//		organization2.setCode("abcd");
//		organization2.setName("AB");
//		orgService.add(organization2);
//
//		// 测试更新人员信息方法
//		// IPerson person3 = personService.make();
//		person2.setBirthday(new Date());
//		person2.setCode("0003");
//		person2.setEmail("abc@qq.com");
//		person2.setGender(Gender.FEMALE);
//		person2.setMobile("12345678910");
//		person2.setName("person3");
//		person2.setOrganization(organization2);
//		person2.setPhone("025-00987654");
//		personService.update(person2);
//		person2 = personService.get(person2.getGuid());
//		assertTrue(Long.valueOf(person2.getGuid()) > 0);
//		// assertTrue(person2.getBirthday()==new Date());
//		assertTrue(person2.getCode() == "0003");
//		assertTrue(person2.getEmail() == "abc@qq.com");
//		assertTrue(person2.getGender() == Gender.FEMALE);
//		assertTrue(person2.getMobile() == "12345678910");
//		assertTrue(person2.getName() == "person3");
//		assertTrue(person2.getOrganization() == organization2);
//		assertTrue(person2.getPhone() == "025-00987654");
//
//		// 获得所有人员信息列表方法
//		Iterable<IPerson> personIterable = personService.list();
//
//		for (IPerson p : personIterable) {
//			System.out.println(p.getGuid());
//		}


	}

//	@Test
//	public void testCache() {
//		IOrganization organization1 = orgService.make();
//		organization1.setCode("srcb");
//		organization1.setName("上海");
//		orgService.add(organization1);
//
//		IPersonJob personJob = personJobService.make();
//		personJob.setCode("ceo");
//		personJob.setName("首席执行官");
//		personJobService.add(personJob);
//
//		// add
//		IPerson leader = personService.add("leader");
//		assertTrue(leader.getName() == "leader");
//		System.out.println("1111111111111111111");
//		IPerson cache = personService.get(leader.getGuid());
//		assertTrue(cache.getName() == "leader");
//		System.out.println("2222222222222222222");
//		
//		IPerson leader2 = personService.add("leader2");
//		IPerson cache2 = personService.get(leader2.getGuid());
//		assertTrue(cache2.getName() == "leader2");
//		System.out.println("333333333333333");
//		
//		//update
//		cache2.setEmail("xuxigang@yihua");
//		personService.update(cache2);
//		System.out.println("update===============");
//		IPerson cache3 = personService.get(leader2.getGuid());
//		assertTrue(cache3.getEmail() == "xuxigang@yihua");
//		assertEquals(cache2,cache3);
//		System.out.println(cache2);
//		System.out.println(cache3);
//		System.out.println("444444444444444444");
//		
//		//remove
//		personService.remove(leader2.getGuid());
//		IPerson cache4 = personService.get(leader2.getGuid());
//		assertNull(cache4);
//		System.out.println("555555555555555555");
//	}

}
