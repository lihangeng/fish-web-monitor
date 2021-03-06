package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.yihuacomputer.fish.web.interceptor.UserLogAopAspect;
import com.yihuacomputer.fish.web.mvc.BrowseMvcConfig;

/**
 * fish-web-monitor的MVC的配置
 * @author xuxiang
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
		{
			"com.yihuacomputer.fish.web.index.controller",
			"com.yihuacomputer.fish.web.system.controller",
			"com.yihuacomputer.fish.web.machine.controller",
			"com.yihuacomputer.fish.web.advert.controller",
			"com.yihuacomputer.fish.web.bsadvert.controller",
			"com.yihuacomputer.fish.web.command.controller",
			"com.yihuacomputer.fish.web.daily.controller",
			"com.yihuacomputer.fish.web.fault.controller",
			"com.yihuacomputer.fish.web.mock.service",//默认的事故发送服务
			"com.yihuacomputer.fish.web.monitor.controller",
			"com.yihuacomputer.fish.web.report.controller",
			"com.yihuacomputer.fish.web.version.controller",
			"com.yihuacomputer.fish.web.parameter.controller",
			"com.yihuacomputer.fish.web.cashbox.controller"
		},
basePackageClasses = {})
@EnableAspectJAutoProxy
public class FishWebMonitorMvcConfig extends BrowseMvcConfig {
	/**
	 * @return
	 */
	@Bean
    public UserLogAopAspect controllerAspect(){
        return new UserLogAopAspect();
    }
}