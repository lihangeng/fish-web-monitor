package com.yihuacomputer.fish.advert;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;

@Configuration
@Import(value = { DomainModule.class, AdvertModule.class })
@ImportResource("classpath:/com/yihuacomputer/fish/advert/spring-advert-h2.xml")
public class H2TestConfig {

	@Bean
	public IVersionTypeService versionTypeService() {
		IVersionTypeService service = mock(IVersionTypeService.class);
		IVersionType type = mock(IVersionType.class);
		when(type.getId()).thenReturn(1L);
		when(type.getTypeName()).thenReturn("advert");
		when(service.getByName("advert")).thenReturn(type);
		when(service.make("advert")).thenReturn(type);
		return service;
	}

	@Bean
	public IVersionService versionService() {
		IVersionService service = mock(IVersionService.class);
		IVersion ver = mock(IVersion.class);
		when(ver.getId()).thenReturn(1L);
		when(service.make()).thenReturn(ver);
		return service;
	}
}
