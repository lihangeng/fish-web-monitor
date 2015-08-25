package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.yihuacomputer.fish.web.mvc.CommonSpringMvcConfig;

/**
 * fish-web-monitor的MVC的配置
 * @author xuxiang
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
		{
			"com.yihuacomputer.fish.web.atm"
		})
public class AtmMvcConfig extends CommonSpringMvcConfig {

}