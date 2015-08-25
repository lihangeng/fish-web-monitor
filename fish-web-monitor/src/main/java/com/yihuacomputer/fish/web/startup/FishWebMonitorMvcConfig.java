package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
			"com.yihuacomputer.fish.web.command",
			"com.yihuacomputer.fish.web.daily.controller",
			"com.yihuacomputer.fish.web.fault.controller",
			"com.yihuacomputer.fish.web.mock",//默认的事故发送服务
			"com.yihuacomputer.fish.web.monitor",
			"com.yihuacomputer.fish.web.report.controller",
			"com.yihuacomputer.fish.web.version.controller"
		},
basePackageClasses = {})
public class FishWebMonitorMvcConfig extends BrowseMvcConfig {

}