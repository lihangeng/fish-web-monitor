/**
 * 
 */
package com.yihuacomputer.domain.mem;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.AbstractSessionFactoryBean;

/**
 * @author xuxigang
 *
 */
public class MockSessionFactory extends AbstractSessionFactoryBean {

	@SuppressWarnings("deprecation")
	@Override
	protected SessionFactory buildSessionFactory() throws Exception {
		Configuration config = new Configuration()
								.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return config.buildSessionFactory();
	}
	


}
