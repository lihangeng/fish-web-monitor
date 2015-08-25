package com.yihuacomputer.fish.monitor.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.hardware.IBios;
import com.yihuacomputer.fish.api.monitor.hardware.ICpu;
import com.yihuacomputer.fish.api.monitor.hardware.IDisk;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.hardware.IMemory;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class HardwareServiceTest extends BindSessionInTest2{
//	@Autowired
	private IHardwareService hardwareService;
//	@Autowired
	private ICollectService collectService;

//	@Test
	public void testHardware() {
//		hardwareService.init("A#0001");
		IHardware hardware = hardwareService.load("A#0001");
		if(hardware == null){
			hardware = hardwareService.make();
		}
		hardware.setTerminalId("A#0001");
		hardware.setDiskMem(102400);

		IBios bois = hardware.getBios();
		assertNotNull(bois);
		bois.setBiosReleaseDate(DateUtils.getDate(new Date()));
		bois.setBiosVendor("xx");
		bois.setBiosVersion("9.0");

		IMemory memory = hardware.getMemory();
		assertNotNull(bois);
		memory.setFree(100);
		memory.setMemorySize(600);
		memory.setUsed(500);
		memory.setUsedPercent(0.90);

		List<ICpu> cpus = new ArrayList<ICpu>();
		ICpu cpu = hardware.makeCpu();
		cpu.setCacheSize(1024);
		cpu.setCombined("xxxbbxx");
		cpu.setFrequency(1);
		cpu.setModel("ddcccs");
		cpu.setSys("sccc");
		cpu.setTotalCores(2);
		cpu.setUser("intcccel");
		cpu.setVendor("iccbm");
		cpus.add(cpu);
		hardware.setCpu(cpus);

		List<IDisk> disks = new ArrayList<IDisk>();
		IDisk disk = hardware.makeDisk();
		disk.setFileSys("D:");
		disk.setFreeSize(50);
		disk.setLabel("C");
		disk.setLabelAndname("bb");
		disk.setMemo("meccmo");
		disk.setName("bbbcc");
		disk.setTotalSize(200);
		disk.setHardware(hardware);
		disks.add(disk);
		hardware.setHardDisk(disks);

		collectService.collectDeviceHardware("A#0001", hardware);

//		hardwareService.delete(hardware);
//		hardwareService.delete("A#0001");
	}

}
