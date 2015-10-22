package com.yihuacomputer.fish.version;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import({DomainModule.class,SystemModule.class,MachineModule.class,SystemModule.class,VersionModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/version/spring-version-h2.xml")
public class H2TestConfig {
	
//	@Bean
//	public IDeviceService deviceService(){
//		IDeviceService service = mock(IDeviceService.class);
//		IDevice device = mock(IDevice.class);
//		when(device.getId()).thenReturn(1L);
//		when(service.get(1)).thenReturn(device);
//		return service;
//	}
//	
//	@Bean
//    public IComplexDeviceService complexDeviceService(){
//	    IComplexDeviceService service = mock(IComplexDeviceService.class);
//        return service;
//    }

}
