package com.yihuacomputer.fish.web.atm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropBcr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCam;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCdm;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropCim;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropFgp;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIcc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIdc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropIsc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropJpr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropNfc;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPbk;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropPin;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropRpr;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropSiu;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IPropTtu;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;
import com.yihuacomputer.fish.web.atm.format.PropertiseMsg;

/**
 * 提供设备属性状态报文的处理接口
 *
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/propertise")

public class PropertiseController {

	private Logger logger = LoggerFactory.getLogger(PropertiseController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IXfsService xfsService;

	/**
	 * 接受设备模块属性信息
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody PropertiseMsg msg) {

		ModelMap result = new ModelMap();
		result.addAttribute("ret", "RET_00");

		IXfsPropertise xfsProp = xfsService.makeXfsPropertise();
		xfsProp.setTerminalId(msg.getTermId());
		IPropIdc idc = xfsProp.makePropIdc();
		idc.setPropIdc(msg.isIdc());
		xfsProp.setIdc(idc);

		IPropJpr jpr = xfsProp.makePropJpr();
		jpr.setPropJpr(msg.isJpr());
		xfsProp.setJpr(jpr);

		IPropCdm cdm = xfsProp.makePropCdm();
		cdm.setPropCdm(msg.isCdm());
		xfsProp.setCdm(cdm);

		IPropCim cim = xfsProp.makePropCim();
		cim.setPropCim(msg.isCdm());
		xfsProp.setCim(cim);

		IPropRpr rpr =  xfsProp.makePropRpr();
		rpr.setPropRpr(msg.isRpr());
		xfsProp.setRpr(rpr);

		IPropTtu ttu =  xfsProp.makePropTtu();
		ttu.setPropTtu(msg.isTtu());
		xfsProp.setTtu(ttu);

		IPropSiu siu = xfsProp.makePropSiu();
		siu.setPropSiu(msg.isSiu());
		xfsProp.setSiu(siu);

		IPropPin pin =  xfsProp.makePropPin();
		pin.setPropPin(msg.isPin());
		xfsProp.setPin(pin);

		IPropPbk pbk =  xfsProp.makePropPbk();
		pbk.setPropPbk(msg.isPbk());
		xfsProp.setPbk(pbk);

		IPropNfc nfc =  xfsProp.makePropNfc();
		nfc.setPropNfc(msg.isNfc());
		xfsProp.setNfc(nfc);

		IPropIcc icc =  xfsProp.makePropIcc();
		icc.setPropIcc(msg.isIcc());
		xfsProp.setIcc(icc) ;

		IPropFgp fgp =  xfsProp.makePropFgp();
		fgp.setPropFgp(msg.isFgp());
		xfsProp.setFgp(fgp) ;

		IPropIsc isc =  xfsProp.makePropIsc();
		isc.setPropIsc(msg.isIsc()) ;
		xfsProp.setIsc(isc) ;
		


		IPropBcr bcr =  xfsProp.makePropBcr();
		bcr.setPropBcr(msg.getBcr()) ;
		xfsProp.setBcr(bcr) ;
		
		IPropCam cam =  xfsProp.makePropCam();
		cam.setPropCam(msg.getCam()) ;
		xfsProp.setCam(cam) ;
		try{
			collectService.collectModulePropertise(msg.getTermId(), xfsProp);
		}catch(Exception e){
//			logger.error(String.format("处理设备模块属性请求异常![%s],请求信息[%s]",e,JsonUtils.toJson(msg)));  
			logger.error(String.format("collection device model properties exception![%s],the model properties info is [%s]",e,JsonUtils.toJson(msg)));
		}

		return result;
	}

}
