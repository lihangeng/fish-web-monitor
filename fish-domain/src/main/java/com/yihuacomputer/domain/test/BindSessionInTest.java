/**
 *
 */
package com.yihuacomputer.domain.test;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 解决利用spring测试框架，测试过程懒加载的问题
 * @author xuxigang
 *
 */
@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../spring-mock.xml")
public class BindSessionInTest {
	@Autowired
	private ApplicationContext applicationContext;
	private SessionFactory sessionFactory;
	private Session session;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		if(applicationContext.containsBean("sessionFactory")){
			this.sessionFactory = applicationContext.getBean(SessionFactory.class);
			session = sessionFactory.openSession();
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		}
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		if(this.sessionFactory != null){
			TransactionSynchronizationManager.unbindResource(sessionFactory);
			SessionFactoryUtils.closeSession(session);
		}
	}
	/**
	 * 测试例子
	 */
	@Test
	public void testExample(){
		assertNotNull(applicationContext);
	}

}
