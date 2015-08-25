package com.yihuacomputer.fish.person.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.person.H2TestConfig;

/**
 * 账户信息服务单元测试
 * @author huxiaobao
 *
 */
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
    public void test(){

        //增加人员信息
        IPerson person1 = personService.add("person5");
        assertTrue(Long.valueOf(person1.getGuid())>0);
        assertTrue(person1.getName()=="person5");

        //增加一条机构信息并初始化其编号和名称方法
        IOrganization root = organizationService.add("test5","测试组5");
        assertTrue(Long.valueOf(root.getGuid()) > 0);
        assertEquals("test5",root.getCode());
        assertEquals("测试组5",root.getName());

        //测试增加一条账户信息并初始化账号方法
        IUser user1 = userService.add("abc", person1);
        assertTrue(user1.getId()>0);
        assertEquals("abc",user1.getCode());
        assertEquals(person1,user1.getPerson());

//        userService.login("abc", "1");


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

        //测试根据条件分页显示人员信息方法
        IFilter filter = new Filter();
        filter.eq("code", "abc");
//        filter.like("person.name", "p");
        IPageResult<IUser> page = userService.page(0, 10, filter);
        assertEquals(1,page.getTotal());

        //测试删除账户信息方法
        userService.remove("456");
        userIterable = userService.list();
        for(IUser u : userIterable){
            System.out.println(u.getCode());
            assertNotNull(u.getPerson());
        }



        //测试账户登录验证
     //   assertTrue(userService.isValiadUser("abc", "yihuasoftware"));

    }

}
