package com.yihuacomputer.fish.machine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.H2TestConfig;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午03:25:01
 * @version 类说明
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class DeviceServiceTest extends BindSessionInTest2
{
    /**
     * 设备接口
     */
    @Autowired
    private IDeviceService deviceService;

    /**
     * 机构接口
     */
    @Autowired
    private IOrganizationService orgService;

    /**
     * 品牌接口
     */
   @Autowired
    private IAtmBrandService brandService;

    /**
     * 类型接口
     */
    @Autowired
    private IAtmCatalogService catalogService;

    /**
     * 型号接口
     */
    @Autowired
    private IAtmTypeService typeService;
    @Ignore
    @Test
    public void testDevice()
    {
        IOrganization organization1 = orgService.make();
        organization1.setCode("12345");
        organization1.setName("Test5");
        orgService.add(organization1);

        IOrganization organization2 = orgService.make();
        organization2.setCode("1234522");
        organization2.setName("Test522");
        orgService.add(organization2);

        IAtmVendor vendor = brandService.make();
        vendor.setName("yihua");
        brandService.add(vendor);

        IAtmCatalog catalog = catalogService.make();
        catalog.setName("1000");
        catalogService.add(catalog);

        IAtmType type = typeService.make();
        type.setName("yihua-1000");
        type.setDevCatalog(catalog);
        type.setDevVendor(vendor);
        typeService.add(type);

        // ===========增加=============
        IDevice deviceAdd1 = deviceService.make();
        deviceAdd1.setTerminalId("20120222");
        deviceAdd1.setIp(new IP("192.168.0.0"));
        deviceAdd1.setOrganization(organization1);
        deviceAdd1.setDevType(type);
        deviceService.add(deviceAdd1);
        assertEquals(DevStatus.OPEN,deviceAdd1.getStatus());

        IDevice resultAdd1 = deviceService.get("20120222");
        assertEquals("Test5", resultAdd1.getOrganization().getName());
        assertEquals("yihua-1000", resultAdd1.getDevType().getName());
        assertEquals("yihua", resultAdd1.getDevType().getDevVendor().getName());
        assertEquals("1000", resultAdd1.getDevType().getDevCatalog().getName());

        IDevice deviceAdd2 = deviceService.make();
        deviceAdd2.setTerminalId("20120223");
        IDevice resultAdd2 = deviceService.add(deviceAdd2);
        assertEquals(resultAdd2, deviceAdd2);

        // ==============查询===============
        IDevice deviceGet1 = deviceService.get(resultAdd1.getTerminalId());
        assertEquals("20120222", deviceGet1.getTerminalId());

        // ==============删除==================
        deviceAdd2.setStatus(DevStatus.DISABLED);
        deviceService.update(deviceAdd2);
        deviceService.remove(deviceAdd2.getId());
        List<IDevice> deviceList1 = deviceService.list();
        assertEquals(1, deviceList1.size());

        // ===============修改================
        IDevice deviceUGet1 = deviceService.get("20120222");
        deviceUGet1.setOrganization(organization2);
        deviceUGet1.setTerminalId("20120223_U");
        deviceService.update(deviceUGet1);
        IDevice deviceUGet2 = deviceService.get("20120223_U");
        assertEquals("20120223_U", deviceUGet2.getTerminalId());

        // ==============查询全部=============
        List<IDevice> deviceListAll = deviceService.list();
        assertEquals(1, deviceListAll.size());

        //======================
        long total = deviceService.getOpeningDeviceTotal();
        assertEquals(1,total);

        // ==============条件查询===============

        List<IDevice> deviceListFilter = deviceService.list(new Filter());
        assertEquals(1, deviceListFilter.size());

        System.out.println("___________________________________________");
        deviceService.page(0, 10, new Filter());
    }
    

    @Ignore
    @Test
    public void testCache(){
    	 IOrganization organization1 = orgService.make();
         organization1.setCode("shenzhen");
         organization1.setName("shenzhen");
         orgService.add(organization1);

         IOrganization organization2 = orgService.make();
         organization2.setCode("shenzhen");
         organization2.setName("shenzhen");
         orgService.add(organization2);

         IAtmVendor vendor = brandService.make();
         vendor.setName("shenzhen");
         brandService.add(vendor);

         IAtmCatalog catalog = catalogService.make();
         catalog.setName("shenzhen");
         catalogService.add(catalog);

         IAtmType type = typeService.make();
         type.setName("shenzhen");
         type.setDevCatalog(catalog);
         type.setDevVendor(vendor);
         typeService.add(type);

         // ===========ADD=============
         IDevice deviceAdd1 = deviceService.make();
         deviceAdd1.setTerminalId("9999");
         deviceAdd1.setIp(new IP("192.168.9.1"));
         deviceAdd1.setOrganization(organization1);
         deviceAdd1.setDevType(type);
         deviceService.add(deviceAdd1);
         System.out.println("11111111111111");
         
         IDevice device = deviceService.get(deviceAdd1.getId());
         System.out.println("22222222222222");
         
         IOrganization org = device.getOrganization();
         assertEquals("shenzhen",org.getCode());
         System.out.println("@@@@@@@@@@@@@@@@@@@@");
         //update
         device.setAddress("xxxx");
         device.setStatus(DevStatus.DISABLED);
         IDevice cache = deviceService.get(deviceAdd1.getId());
         assertEquals("xxxx",cache.getAddress());
         System.out.println("33333333333333");
         
         //remove
         deviceService.remove(cache.getId());
         System.out.println("444444444444444");
         IDevice cache2 = deviceService.get(cache.getId());
         assertNull(cache2);
         System.out.println("55555555555555");
         
         
    }
}
