package com.yihuacmputer.domain.spring.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigMainTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		 ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		 IMyService myService = ctx.getBean(IMyService.class);
		 System.out.print(myService.getName());
		 IHisService hisService  = ctx.getBean(IHisService.class);
		 System.out.println(hisService.getHisName());
	}

}
