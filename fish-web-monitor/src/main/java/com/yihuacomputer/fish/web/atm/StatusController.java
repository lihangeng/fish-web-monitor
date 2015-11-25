package com.yihuacomputer.fish.web.atm;

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
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCdm;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCim;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusFgp;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIcc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIdc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIsc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusJpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusNfc;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPbk;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPin;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusRpr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusSiu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusTtu;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.web.atm.format.StatusMsg;

/**
 * 提供设备状态报文处理的服务接口
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/status")
public class StatusController {

	private Logger logger = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IXfsService xfsService;


	/**
	 * 接收设备定时状态报告
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap acceptStatus(@RequestBody StatusMsg msg){
        ModelMap result = new ModelMap();
        result.addAttribute("TermId", msg.getTermId());
        result.addAttribute("ret", "RET_00");

        IXfsStatus xfsStatus = xfsService.makeXfsStatus();
        xfsStatus.setTerminalId(msg.getTermId());
        xfsStatus.setRunStatus(msg.getRunStatus());
        xfsStatus.setNetStatus(NetStatus.Healthy);
        IStatusIdc idc = xfsStatus.makeStatusIdc();
        if(msg.getIdc() != null){
	        idc.setStatus(msg.getIdc());
	        idc.setCode(msg.getIdcCode());
	        idc.setCards(msg.getIdcReatianCard());
	        idc.setHwCode(msg.getIdcHwCode());
        }else{
        	idc.setStatus(DeviceStatus.NoDevice);
        }

        IStatusJpr jpr = xfsStatus.makeStatusJpr();
        if(msg.getJpr() != null){
	        jpr.setStatus(msg.getJpr());
	        jpr.setCode(msg.getJprCode());
	        jpr.setHwCode(msg.getJprHwCode());
        }else{
        	jpr.setStatus(DeviceStatus.NoDevice);
        }
        IStatusRpr rpr = xfsStatus.makeStatusRpr();
        if(msg.getRpr() != null){
	        rpr.setStatus(msg.getRpr());
	        rpr.setCode(msg.getRprCode());
	        rpr.setHwCode(msg.getRprHwCode());
        }else{
        	rpr.setStatus(DeviceStatus.NoDevice);
        }

        IStatusCdm cdm = xfsStatus.makeStatusCdm();
        if(msg.getCdm() != null){
	        cdm.setStatus(msg.getCdm());
	        cdm.setCode(msg.getCdmCode());
	        cdm.setHwCode(msg.getCdmHwCode());
        }else{
        	cdm.setStatus(DeviceStatus.NoDevice);
        }

        IStatusCim cim = xfsStatus.makeStatusCim();
        if(msg.getCim() != null){
	        cim.setStatus(msg.getCim());
	        cim.setCode(msg.getCimCode());
	        cim.setHwCode(msg.getCimHwCode());
        }else{
        	cim.setStatus(DeviceStatus.NoDevice);
        }

        IStatusSiu siu = xfsStatus.makeStatusSiu();
        if(msg.getSiu() != null){
	        siu.setStatus(msg.getSiu());
	        siu.setCode(msg.getSiuCode());
	        siu.setHwCode(msg.getSiuHwCode());
        }else{
        	siu.setStatus(DeviceStatus.NoDevice);
        }

        IStatusPin pin = xfsStatus.makeStatusPin();
        if(msg.getPin() != null){
	        pin.setStatus(msg.getPin());
	        pin.setCode(msg.getPinCode());
	        pin.setHwCode(msg.getPinHwCode());
        }else{
        	pin.setStatus(DeviceStatus.NoDevice);
        }

        IStatusTtu ttu = xfsStatus.makeStatusTtu();
        if(msg.getTtu() != null){
	        ttu.setStatus(msg.getTtu());
	        ttu.setCode(msg.getTtuCode());
	        ttu.setHwCode(msg.getTtuHwCode());
        }else{
        	ttu.setStatus(DeviceStatus.NoDevice);
        }
        IStatusNfc nfc = xfsStatus.makeStatusNfc();
        if(msg.getNfc() != null){
	        nfc.setStatus(msg.getNfc());
	        nfc.setCode(msg.getNfcCode());
	        nfc.setHwCode(msg.getNfcHwCode());
        }else{
        	nfc.setStatus(DeviceStatus.NoDevice);
        }
        IStatusPbk pbk = xfsStatus.makeStatusPbk();
        if(msg.getPbk() != null){
        	pbk.setStatus(msg.getPbk());
        	pbk.setCode(msg.getPbkCode());
        	pbk.setHwCode(msg.getPbkHwCode());
        }else{
        	pbk.setStatus(DeviceStatus.NoDevice);
        }
        xfsStatus.setModStatus(msg.getModStatus());
        
        xfsStatus.setBoxStatus(msg.getBoxStatus());

        xfsStatus.setBoxInitCount(msg.getBoxInitCount());
        xfsStatus.setBoxCurrentCount(msg.getBoxCurrentCount());

        IStatusIcc icc = xfsStatus.makeStatusIcc();
        if(msg.getIcc() != null){
	        icc.setStatus(msg.getIcc());
	        icc.setCode(msg.getIccCode());
	        icc.setCards(msg.getIccReatianCard());
	        icc.setHwCode(msg.getIccHwCode());
	        icc.setIccCurrCards(msg.getIccCurrentCount()) ;
        }else{
        	icc.setStatus(DeviceStatus.NoDevice);
        }
        IStatusFgp fgp = xfsStatus.makeStatusFgp() ;
        if(msg.getFgp() != null){
	        fgp.setStatus(msg.getFgp()) ;
	        fgp.setCode(msg.getFgpCode()) ;
	        fgp.setHwCode(msg.getFgpHwCode()) ;
        }else{
        	fgp.setStatus(DeviceStatus.NoDevice);
        }
        IStatusIsc isc = xfsStatus.makeStatusIsc() ;
        if(msg.getIsc() != null){
	        isc.setStatus(msg.getIsc()) ;
	        isc.setCode(msg.getIscCode()) ;
	        isc.setHwCode(msg.getIscHwCode()) ;
        }else{
        	isc.setStatus(DeviceStatus.NoDevice);
        }

        xfsStatus.setStatusIdc(idc);
        xfsStatus.setStatusJpr(jpr);
        xfsStatus.setStatusRpr(rpr);
        xfsStatus.setStatusCdm(cdm);
        xfsStatus.setStatusCim(cim);
        xfsStatus.setStatusSiu(siu);
        xfsStatus.setStatusPin(pin);
        xfsStatus.setStatusTtu(ttu);
        xfsStatus.setStatusNfc(nfc);
        xfsStatus.setStatusPbk(pbk);

        xfsStatus.setStatusIcc(icc);
        xfsStatus.setStatusFgp(fgp);
        xfsStatus.setStatusIsc(isc);
        try{
        	collectService.collectModuleStatus(msg.getTermId(), xfsStatus);
        }catch(Exception e){
        	logger.error(String.format("collection xfsStatus exception![%s],xfsStatus is [%s]",e,JsonUtils.toJson(msg)));
        }
        return result;
	}
}
