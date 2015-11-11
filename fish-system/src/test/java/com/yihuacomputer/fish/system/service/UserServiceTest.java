package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.system.H2TestConfig;

/**
 * 账户信息服务单元测试
 * @author xuxigang
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class UserServiceTest extends BindSessionInTest2
{
    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IUserService userService;

    @Test
    @Ignore
    public void test(){
        //增加人员信息
        IPerson person1 = personService.add("person5");
        assertTrue(Long.valueOf(person1.getGuid())>0);
        assertTrue(person1.getName()=="person5");

        //测试增加一条账户信息并初始化账号方法
        IUser user1 = userService.add("abc", person1);
        assertTrue(user1.getId()>0);
        assertEquals("abc",user1.getCode());
        assertEquals(person1,user1.getPerson());

        //增加人员信息
        IPerson person2 = personService.add("person6");
        assertTrue(Long.valueOf(person2.getGuid())>0);
        assertTrue(person2.getName()=="person6");

        //测试make()后增加账户方法
        IUser user2 = userService.make();
        user2.setCode("123");
        user2.setPerson(person2);
        user2.setPlainText("123");
        user2.setState(UserState.REMOVED);
        user2 = userService.add(user2);
        assertEquals("123",user2.getCode());
        assertEquals(person2,user2.getPerson());
        assertEquals(UserState.REMOVED,user2.getState());

        //测试根据账号得到账户信息方法
        IUser user = userService.get("123");
        assertEquals("123",user.getCode());
        assertEquals(person2,user.getPerson());
        assertEquals(UserState.REMOVED,user.getState());

        //测试获得所有的人员信息方法
        Iterable<IUser> userIterable = userService.list();
        for(IUser u : userIterable){
            System.out.println(u.getCode());
        }

        //增加一条机构信息并初始化其编号和名称方法
        IOrganization test1 = organizationService.add("test6","测试组6");
        assertTrue(Long.valueOf(test1.getGuid()) > 0);
        assertEquals("test6",test1.getCode());
        assertEquals("测试组6",test1.getName());

        //测试更新账户信息方法
        user.setCode("456");
        user.setPerson(person2);
        user.setPlainText("456");
        user.setState(UserState.LOCK);
        userService.update(user);

        //测试删除账户信息方法
        userService.remove("456");
        userIterable = userService.list();
        for(IUser u : userIterable){
            System.out.println(u.getCode());
            assertNotNull(u.getPerson());
        }

    }
    
    @Test
    public void testCache(){
    	 IPerson person = personService.add("cachePerson");
         IUser user = userService.add("cacheUser", person);
         assertEquals("cacheUser",user.getCode());
         
         IUser cache = userService.get(user.getId());
         assertEquals("cacheUser",cache.getCode());
         System.out.println("1111111111111");
         
         cache.setCheckRemark("888");
         userService.update(user);
         System.out.println("222222");
         
         IUser cache2 = userService.get(user.getId());
         assertEquals("888",cache2.getCheckRemark());
         System.out.println("333333");
         
         userService.remove(user.getId());
         IUser cache3 = userService.get(user.getId());
         assertNull(cache3);
         System.out.println("4444444");
    }

}
