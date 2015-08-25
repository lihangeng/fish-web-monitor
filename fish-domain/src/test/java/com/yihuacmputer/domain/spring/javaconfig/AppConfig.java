package com.yihuacmputer.domain.spring.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  /**
   * 注意此处的bean定义,没有使用IMyChildService的父接口IMyService
   * 如果使用，在例子中无法跑通。
   * ISubA-->IA
   * SubAImp-->ISubA
   * FinalAImp exntends SubAImpl
   * SubAImp或者FinalAImp中依赖了IB
   *
   *
   * BImpl-->IB
   *
   * 出现相互依赖的情况，
   * 如果BImpl实现中依赖IA(配置父或子接口bean都行)
   * 如果BImpl实现中依赖ISubA(只能配置子接口bean)
   * @return
   */
  @Bean
  public IMyChildService myService() {
      return new MyServiceImpl();
  }

  @Bean
  public IHisService hisSerivce(){
	  return new HisServiceImpl();
  }
}