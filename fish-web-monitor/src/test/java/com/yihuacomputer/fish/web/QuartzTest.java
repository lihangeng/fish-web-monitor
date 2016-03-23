/**
 * 
 */
package com.yihuacomputer.fish.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yihuacomputer.fish.web.mock.YihuaQuartz;

/**
 * @author xuxiang
 *
 */
public class QuartzTest {
	public static void main(String... args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		YihuaQuartz yihuaQuartz = context.getBean(YihuaQuartz.class);
		yihuaQuartz.init();
	}
}
