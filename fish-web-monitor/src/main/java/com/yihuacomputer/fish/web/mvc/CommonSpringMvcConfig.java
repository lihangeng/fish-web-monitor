package com.yihuacomputer.fish.web.mvc;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewInterceptor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihuacomputer.common.util.DateUtils;

/**
 * WEB-INF/spring.xml文件的编码版本
 * @author xuxiang
 *
 */
@Configuration
@EnableWebMvc
public abstract class CommonSpringMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		converters.add(mappingJackson2HttpMessageConverter());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(openSessionInViewInterceptor());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");// 解决上传的文件名是中文产生乱码的问题
		//cmr.setMaxUploadSize(314572800L);// 300M  上传文件大小在程序中控制，方便给出超过300M的提示信息。
		return cmr;
	}

	/**
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.STANDARD_TIMESTAMP);
		objectMapper.setDateFormat(dateFormat);
		return objectMapper;
	}

	/**
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jackson2 = new MappingJackson2HttpMessageConverter();
		jackson2.setObjectMapper(objectMapper());
		return jackson2;
	}

	/**
	 * @return
	 */
	@Bean
	public OpenSessionInViewInterceptor openSessionInViewInterceptor() {
		OpenSessionInViewInterceptor osii = new OpenSessionInViewInterceptor();
		osii.setSessionFactory(sessionFactory);
		return osii;
	}
}