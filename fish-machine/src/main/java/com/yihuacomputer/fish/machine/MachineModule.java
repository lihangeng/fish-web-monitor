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


/**
 * @author YiHua
 *
 */
@Configuration
public class MachineModule {

	/**
	 * @return
	 */
	@Bean
	public IAtmBrandService atmBrandService() {
		return new AtmBrandService();
	}

	/**
	 * @return
	 */
	@Bean
	public IAtmCatalogService atmCatalogService() {
		return new AtmCatalogService();
	}

	/**
	 * @return
	 */
	@Bean
	public IAtmGroupService atmGroupService() {
		return new AtmGroupService();
	}

	/**
	 * @return
	 */
	@Bean
	public IAtmModuleService atmModuleService() {
		return new AtmModuleService();
	}

	/**
	 * @return
	 */
	@Bean
	public IAtmMoveService atmMoveService() {
		return new AtmMoveService();
	}

	/**
	 * @return
	 */
	@Bean
	public IAtmTypeService atmTypeService() {
		return new AtmTypeService();
	}

	/**
	 * @return
	 */
	@Bean
	public IQuittingNoticeService atmQuittingNoticeService() {
		return new AtmQuittingNoticeService();
	}

	/**
	 * @return
	 */
	@Bean
	public IComplexDeviceService complexDeviceService() {
		return new ComplexDeviceService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceGroupRelation deviceGroupRelation() {
		return new DeviceGroupRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IDevicePersonRelation devicePersonRelation() {
		return new DevicePersonRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceService deviceService() {
		return new DeviceService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAtmTypeAtmModuleRelationService atmTypeAtmModuleRelationService(){
		return new AtmTypeAtmModuleRelationService();
	}

	/**
	 * @return
	 */
	@Bean
	public IOpenPlanDeviceRelation openPlanDeviceRelation(){
		return new OpenPlanDeviceRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IOpenPlanService openPlanService(){
		return new OpenPlanService();

	}
	
}
