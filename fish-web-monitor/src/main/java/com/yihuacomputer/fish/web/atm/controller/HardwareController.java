package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.hardware.IBios;
import com.yihuacomputer.fish.api.monitor.hardware.ICpu;
import com.yihuacomputer.fish.api.monitor.hardware.IDisk;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.hardware.IMemory;
import com.yihuacomputer.fish.web.atm.format.CpuMsg;
import com.yihuacomputer.fish.web.atm.format.DiskMsg;
import com.yihuacomputer.fish.web.atm.format.HardwareForm;
import com.yihuacomputer.fish.web.atm.format.HardwareMsg;

/**
 * 提供操作系统硬件信息处理服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * 
 * */

@Controller
@RequestMapping("/msg/hardware")
public class HardwareController {

	private Logger logger = LoggerFactory.getLogger(HardwareController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IHardwareService hardwareService;

	/**
	 * 接收系统硬件信息
	 * 
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody HardwareMsg msg) {

		ModelMap result = new ModelMap();
		try{
			IHardware hardware = hardwareService.load(msg.getTermId());
			if (hardware == null) {
				 result.addAttribute("ret", "RET_02");
			}else{
				result.addAttribute("ret", "RET_00");
	
				hardware.setTerminalId(msg.getTermId());
				hardware.setDiskMem(msg.getDiskMem());
				
				IBios bios = hardware.getBios();
				if(msg.getBios() != null && bios!=null){
					msg.getBios().toBios(bios);
				}
				
				IMemory memory = hardware.getMemory();
				if(msg.getMemory() != null && memory!=null){
					msg.getMemory().toMemory(memory);
				}
				
				
				List<IDisk> disks = new ArrayList<IDisk>();
				for(DiskMsg diskMsg : msg.getHardDisk()){
					IDisk disk = hardware.makeDisk();
					diskMsg.toDisk(disk);
					disk.setHardware(hardware);
					disks.add(disk);
				}
				hardware.setHardDisk(disks);
				
				List<ICpu> cpus = new ArrayList<ICpu>();
				for(CpuMsg cpuMsg : msg.getCpu()){
					ICpu cpu = hardware.makeCpu();
					cpuMsg.toCpu(cpu);
					cpus.add(cpu);
				}
				hardware.setCpu(cpus);
				
				collectService.collectDeviceHardware(msg.getTermId(), hardware);	
			}
		}catch(Exception e){
//			logger.error(String.format("处理操作系统硬件配置信息异常![%s],系统硬件内容:[%s]",e,JsonUtils.toJson(msg)));
			logger.error(String.format("collection os hardware config info exception![%s],hardware config is:[%s]",e,JsonUtils.toJson(msg)));
		}
		return result;
	}

	/**
	 * 提供显示系统硬件信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getHardware(@PathVariable long id) {
		ModelMap model = new ModelMap();
		String deviceId = String.valueOf(id);

		IHardware hardware =  hardwareService.load(deviceId);

		HardwareForm hardwareForm = new HardwareForm();
		hardwareForm.setBiosReleaseDate(hardware.getBios().getBiosReleaseDate());
		hardwareForm.setBiosVendor(hardware.getBios().getBiosVendor());
		hardwareForm.setBiosVersion(hardware.getBios().getBiosVersion());

		ICpu cpu = hardware.getCpu().get(0);
		hardwareForm.setCacheSize(cpu.getCacheSize());
		hardwareForm.setCombined(cpu.getCombined());
		hardwareForm.setFrequency(cpu.getFrequency());
		hardwareForm.setIdle(cpu.getIdle());
		hardwareForm.setModel(cpu.getModel());
		hardwareForm.setTotalCores(cpu.getTotalCores());
		hardwareForm.setSys(cpu.getSys());
		hardwareForm.setUser(cpu.getUser());
		hardwareForm.setVendor(cpu.getVendor());

		IDisk disk = hardware.getHardDisk().get(0);
		hardwareForm.setFileSys(disk.getFileSys());
		hardwareForm.setTotalSize(disk.getTotalSize());
		hardwareForm.setFreeSize(disk.getFreeSize());
		hardwareForm.setMemo(disk.getMemo());
		hardwareForm.setLabelAndname(disk.getLabelAndname());

		hardwareForm.setMemorySize(hardware.getMemory().getMemorySize());
		hardwareForm.setUsed(hardware.getMemory().getUsed());
		hardwareForm.setFree(hardware.getMemory().getFree());
		hardwareForm.setUsedPercent(hardware.getMemory().getUsedPercent());

		hardwareForm.setDiskMem(hardware.getDiskMem());

		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute("data", hardwareForm);
		return model;
	}

}
