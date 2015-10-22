package com.yihuacomputer.fish.version.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.version.H2TestConfig;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class VersionTypeServiceTest extends BindSessionInTest2{
	
//	@Autowired
	private IVersionTypeService versionTypeService;
	
//	@Autowired
	private IAtmTypeService atmTypeService;
	
	//	@Autowired
	private IAtmCatalogService atmCatalogService;
	
	//	@Autowired
	private IAtmBrandService atmVendorService;
	
	//	@Autowired
	private IVersionTypeAtmTypeRelationService relationService;
	
	//	@Test
	public void test(){
		
		IAtmCatalog atmCatalog = atmCatalogService.make();
		atmCatalog.setName("yihuacomputer");
		atmCatalogService.add(atmCatalog);
		
		IAtmVendor atmBrand = atmVendorService.make();
		atmBrand.setName("YIHUA");
		atmVendorService.add(atmBrand);
		
		IAtmType atmType = atmTypeService.make();
		atmType.setName("YH6040W");
		atmType.setCashtype(CashType.CASH);
		atmType.setDevCatalog(atmCatalog);
		atmType.setDevVendor(atmBrand);
		atmTypeService.add(atmType);
		
		IVersionType versionType = versionTypeService.make("test");
		versionTypeService.add(versionType);
		assertTrue(versionType.getId() > 0);
		assertEquals(versionType.getTypeName(),"test");
		
		relationService.link(versionType.getId(), atmType.getId());
		List<IAtmType> atmTypes = relationService.findAtmTypes(versionType.getId());
		assertEquals(1,atmTypes.size());
		
		
	}

}
