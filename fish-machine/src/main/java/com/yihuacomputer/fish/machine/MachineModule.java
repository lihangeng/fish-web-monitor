package com.yihuacomputer.fish.machine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.api.atm.IAtmTypeAtmModuleRelationService;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atmMove.IAtmMoveService;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDeviceExtendService;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.api.relation.IDeviceGroupRelation;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.machine.service.AtmBrandService;
import com.yihuacomputer.fish.machine.service.AtmCatalogService;
import com.yihuacomputer.fish.machine.service.AtmGroupService;
import com.yihuacomputer.fish.machine.service.AtmModuleService;
import com.yihuacomputer.fish.machine.service.AtmMoveService;
import com.yihuacomputer.fish.machine.service.AtmQuittingNoticeService;
import com.yihuacomputer.fish.machine.service.AtmTypeAtmModuleRelationService;
import com.yihuacomputer.fish.machine.service.AtmTypeService;
import com.yihuacomputer.fish.machine.service.ComplexDeviceService;
import com.yihuacomputer.fish.machine.service.DeviceExtendService;
import com.yihuacomputer.fish.machine.service.DeviceGroupRelation;
import com.yihuacomputer.fish.machine.service.DevicePersonRelation;
import com.yihuacomputer.fish.machine.service.DeviceService;

@Configuration
public class MachineModule {

	@Bean
	public IAtmBrandService atmBrandService() {
		return new AtmBrandService();
	}

	@Bean
	public IAtmCatalogService atmCatalogService() {
		return new AtmCatalogService();
	}

	@Bean
	public IAtmGroupService atmGroupService() {
		return new AtmGroupService();
	}

	@Bean
	public IAtmModuleService atmModuleService() {
		return new AtmModuleService();
	}

	@Bean
	public IAtmMoveService atmMoveService() {
		return new AtmMoveService();
	}

	@Bean
	public IAtmTypeService atmTypeService() {
		return new AtmTypeService();
	}

	@Bean
	public IQuittingNoticeService atmQuittingNoticeService() {
		return new AtmQuittingNoticeService();
	}

	@Bean
	public IComplexDeviceService complexDeviceService() {
		return new ComplexDeviceService();
	}

	@Bean
	public IDeviceExtendService deviceExtendService() {
		return new DeviceExtendService();
	}

	@Bean
	public IDeviceGroupRelation deviceGroupRelation() {
		return new DeviceGroupRelation();
	}

	@Bean
	public IDevicePersonRelation devicePersonRelation() {
		return new DevicePersonRelation();
	}

	@Bean
	public IDeviceService deviceService() {
		return new DeviceService();
	}
	@Bean
	public IAtmTypeAtmModuleRelationService atmTypeAtmModuleRelationService(){
		return new AtmTypeAtmModuleRelationService();
	}

}
