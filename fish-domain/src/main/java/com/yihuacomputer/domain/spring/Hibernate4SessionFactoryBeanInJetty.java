package com.yihuacomputer.domain.spring;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.internal.StandardServiceRegistryImpl;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

public class Hibernate4SessionFactoryBeanInJetty extends LocalSessionFactoryBean {

	@Override
	@SuppressWarnings("serial")
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		Configuration cfg = this.getConfiguration();
		cfg.setInterceptor(sfb.getInterceptor());
		Properties properties = sfb.getProperties();
		Environment.verifyProperties(properties);
		ConfigurationHelper.resolvePlaceHolders(properties);
		final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
		cfg.setSessionFactoryObserver(new SessionFactoryObserver() {
			@Override
			public void sessionFactoryCreated(SessionFactory factory) {
			}

			@Override
			public void sessionFactoryClosed(SessionFactory factory) {
				((StandardServiceRegistryImpl) serviceRegistry).destroy();
			}
		});
		return cfg.buildSessionFactory(serviceRegistry);
	}

}
