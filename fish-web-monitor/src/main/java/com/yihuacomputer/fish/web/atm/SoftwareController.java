package com.yihuacomputer.fish.web.atm;

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
import com.yihuacomputer.fish.api.monitor.software.IAnti;
import com.yihuacomputer.fish.api.monitor.software.IOS;
import com.yihuacomputer.fish.api.monitor.software.ISP;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.monitor.entity.software.Software;
import com.yihuacomputer.fish.web.atm.format.SoftwareForm;
import com.yihuacomputer.fish.web.atm.format.SoftwareMsg;

/**
 * 提供操作系统软件信息处理服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/software")

public class SoftwareController {
	
	private Logger logger = LoggerFactory.getLogger(SoftwareController.class);
	
	@Autowired
	private ICollectService collectService;
	
	@Autowired
	private ISoftwareService softwareService;

    /**
     * 接收系统软件信息
     * @param softwareMsg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody SoftwareMsg softwareMsg) {
    	

    	ModelMap result = new ModelMap();
    	try{
			ISoftware software = softwareService.load(softwareMsg.getTermId());
			if(software==null){
				result.addAttribute("ret", "RET_02");			
			}else{
				result.addAttribute("ret", "RET_00");
	
				software.setAnti(softwareMsg.getAnti());
				software.setAtmcVer(softwareMsg.getAtmcVer());
				software.setChkCashData(softwareMsg.getChkCashData());
				software.setOS(softwareMsg.getOS());
				software.setSP(softwareMsg.getSP());
				collectService.collectDeviceSoftware(softwareMsg.getTermId(), software);
			}
    	}catch(Exception e){
    		logger.error(String.format("collection software exception![%s],software is [%s]",e,JsonUtils.toJson(softwareMsg)));
    	}
		return result;
	}
    
	/**
	 * 提供系统软件信息显示
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getSoftware(@PathVariable long id) {
		ModelMap model = new ModelMap();
		String terminalId = String.valueOf(id);

		Software software = (Software) softwareService.load(terminalId);

		SoftwareForm softwareForm = new SoftwareForm();
		softwareForm.setAtmcVer(software.getAtmcVer());
		softwareForm.setChkCashData(software.getChkCashData());

		IAnti anti = software.getAnti();
		softwareForm.setAntiName(anti.getAntiName());
		softwareForm.setAntiVer(anti.getAntiVer());

		IOS os = software.getOS();
		softwareForm.setType(os.getType());
		softwareForm.setDescription(os.getDescription());
		softwareForm.setArch(os.getArch());
		softwareForm.setVendor(os.getVendor());
		softwareForm.setVendorName(os.getVendorName());
		softwareForm.setVersion(os.getVersion());
		softwareForm.setPatchLevel(os.getPatchLevel());

		ISP sp = software.getSP();
		softwareForm.setSpDate(sp.getSpDate());
		softwareForm.setSpPatch(sp.getSpPatch());
		softwareForm.setSpVersion(sp.getSpVersion());

		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute("data", softwareForm);
		return model;
	}

}
