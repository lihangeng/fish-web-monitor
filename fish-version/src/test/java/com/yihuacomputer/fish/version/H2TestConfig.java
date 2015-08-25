package com.yihuacomputer.fish.version;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.person.PersonCoreModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import({DomainModule.class,PersonCoreModule.class,MachineModule.class,SystemModule.class,VersionModule.class})
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
