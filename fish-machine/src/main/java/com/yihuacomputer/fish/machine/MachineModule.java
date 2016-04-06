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
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDeviceRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanService;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetailService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
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
import com.yihuacomputer.fish.machine.service.DeviceGroupRelation;
import com.yihuacomputer.fish.machine.service.DevicePersonRelation;
import com.yihuacomputer.fish.machine.service.DeviceService;
import com.yihuacomputer.fish.machine.service.OpenPlanDeviceRelation;
import com.yihuacomputer.fish.machine.service.OpenPlanService;
import com.yihuacomputer.fish.parameter.service.AppSystemService;
import com.yihuacomputer.fish.parameter.service.ParamClassifyService;
import com.yihuacomputer.fish.parameter.service.ParamDeviceDetailService;
import com.yihuacomputer.fish.parameter.service.ParamElementService;
import com.yihuacomputer.fish.parameter.service.ParamTemplateDetailService;
import com.yihuacomputer.fish.parameter.service.ParamTemplateService;


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

	@Bean
	public IOpenPlanDeviceRelation openPlanDeviceRelation(){
		return new OpenPlanDeviceRelation();
	}

	@Bean
	public IOpenPlanService openPlanService(){
		return new OpenPlanService();

	}
	
	@Bean
	public IAppSystemService AppSystemService(){
		return new AppSystemService();
	}

	@Bean
	public IParamElementService elementService(){
		return new ParamElementService();
	}
	
	
	@Bean
	public IParamTemplateService templateService(){
		return new ParamTemplateService();
	}
	
	@Bean
	public IParamClassifyService classifyService(){
		return new ParamClassifyService();
	}
	
	@Bean
	public IParamTemplateDetailService paramTemplateDetailService(){
		return new ParamTemplateDetailService();
	}
	
	@Bean public IParamDeviceDetailService paramDeviceDetailService(){
		return new ParamDeviceDetailService();
	}

}
