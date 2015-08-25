package com.yihuacomputer.fish.permission;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;

@Configuration
@Import({DomainModule.class,PersonPermissionModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/permission/spring-permission-h2.xml")
public class H2TestConfig {

	@Bean
	public IOrganizationService organizationService(){
		IOrganizationService service = mock(IOrganizationService.class);
		IOrganization root = mock(IOrganization.class);
		when(service.getByCode("root")).thenReturn(root);
		return service;
	}

}
