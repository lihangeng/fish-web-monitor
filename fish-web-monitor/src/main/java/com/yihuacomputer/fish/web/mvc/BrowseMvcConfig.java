package com.yihuacomputer.fish.web.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.yihuacomputer.fish.web.interceptor.TimeoutInterceptor;
import com.yihuacomputer.fish.web.interceptor.UserLogInterceptor;

/**
 * 通过浏览器访问的MVC的配置
 * @author xuxiang
 *
 */
@Configuration
@EnableWebMvc
public class BrowseMvcConfig extends CommonSpringMvcConfig {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(timeoutInterceptor());
		registry.addInterceptor(userLogInterceptor());
	}

	/**
	 * @return
	 */
	@Bean
	public TimeoutInterceptor timeoutInterceptor(){
		TimeoutInterceptor timeoutInterceptor = new TimeoutInterceptor();
		timeoutInterceptor.init();
		return timeoutInterceptor;
	}

	/**
	 * @return
	 */
	@Bean
	public UserLogInterceptor userLogInterceptor(){
		return new UserLogInterceptor();
	}

	/**
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}