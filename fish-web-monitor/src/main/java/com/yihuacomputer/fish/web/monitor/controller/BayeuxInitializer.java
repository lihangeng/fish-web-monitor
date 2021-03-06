package com.yihuacomputer.fish.web.monitor.controller;

/**
 * Comet config Bayeux Initializer
 */

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.cometd.annotation.ServerAnnotationProcessor;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.server.BayeuxServerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * @author YiHua
 *
 */
@Component
public class BayeuxInitializer implements DestructionAwareBeanPostProcessor,
		ServletContextAware {
	private BayeuxServer bayeuxServer;
	private ServerAnnotationProcessor processor;

	@Inject
	private void setBayeuxServer(BayeuxServer bayeuxServer) {
		this.bayeuxServer = bayeuxServer;
	}

	@PostConstruct
	private void init() {
		this.processor = new ServerAnnotationProcessor(bayeuxServer);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name)
			throws BeansException {
		processor.processDependencies(bean);
		processor.processConfigurations(bean);
		processor.processCallbacks(bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String name)
			throws BeansException {
		return bean;
	}

	@Override
	public void postProcessBeforeDestruction(Object bean, String name)
			throws BeansException {
		processor.deprocessCallbacks(bean);
	}

	/**
	 * @return
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public BayeuxServer bayeuxServer() {
		BayeuxServerImpl bean = new BayeuxServerImpl();
		bean.setOption(BayeuxServerImpl.LOG_LEVEL, "2");
		bean.setOption("timeout", "25000");

		return bean;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setAttribute(BayeuxServer.ATTRIBUTE, bayeuxServer);
	}
}
