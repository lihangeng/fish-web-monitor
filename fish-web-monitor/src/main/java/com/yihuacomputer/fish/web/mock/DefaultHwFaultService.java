package com.yihuacomputer.fish.web.mock;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

@Service
public class DefaultHwFaultService {

	private Logger logger = LoggerFactory.getLogger(DefaultHwFaultService.class);

	@Autowired
	private IFaultClassifyService faultClassifyService;

	@Autowired
	private ICaseNotifyService caseNotifyService;

	@Autowired
	private ICaseFaultService caseFaultService;
	/**
	 * 处理模块故障
	 * @param xfsStatus
	 */
	public void handleModFualt(IXfsStatus xfsStatus){
		try{
			List<ICaseFault> openCaseList = caseFaultService.listOpenCaseFault(xfsStatus.getTerminalId());

			if(xfsStatus.getRunStatus().equals(RunStatus.Maintain)){
				return;
			}
			handleIdcFault(xfsStatus,openCaseList);
			handleCdmFault(xfsStatus,openCaseList);
			handleCimFault(xfsStatus,openCaseList);
			handleJprFault(xfsStatus,openCaseList);
			handleRprFault(xfsStatus,openCaseList);
			handlePinFault(xfsStatus,openCaseList);

			handleIccFault(xfsStatus,openCaseList);
			handleFgpFault(xfsStatus,openCaseList) ;
			handleIscFault(xfsStatus,openCaseList) ;

		}catch(Exception e){
			e.printStackTrace();
			logger.error(String.format("处理故障异常[%s]", e));
		}
	}

	/**
	 * 读卡器
	 * @param idc
	 * @param openCaseList
	 */
	private void handleIdcFault(IXfsStatus idc,List<ICaseFault> openCaseList){
		if(idc.getStatusIdc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.IDC);
		}else if(idc.getStatusIdc().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.IDC);
			caseFault.setAppStatus(idc.getRunStatus());
			caseFault.setFaultCode(idc.getStatusIdc().getCode());
			caseFault.setTerminalId(idc.getTerminalId());
			caseFault.setVendorHwCode(idc.getStatusIdc().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}

	/**
	 * 取款模块
	 * @param cdm
	 * @param openCaseList
	 */
	private void handleCdmFault(IXfsStatus cdm,List<ICaseFault> openCaseList){
		if(cdm.getStatusCdm().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.CDM);
		}else if(cdm.getStatusCdm().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.CDM);
			caseFault.setAppStatus(cdm.getRunStatus());
			caseFault.setFaultCode(cdm.getStatusCdm().getCode());
			caseFault.setTerminalId(cdm.getTerminalId());
			caseFault.setVendorHwCode(cdm.getStatusCdm().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}

	/**
	 * 存款模块
	 * @param cim
	 * @param openCaseList
	 */
	private void handleCimFault(IXfsStatus cim,List<ICaseFault> openCaseList){
		if(cim.getStatusCim().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.CIM);
		}else if(cim.getStatusCim().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.CIM);
			caseFault.setAppStatus(cim.getRunStatus());
			caseFault.setFaultCode(cim.getStatusCim().getCode());
			caseFault.setTerminalId(cim.getTerminalId());
			caseFault.setVendorHwCode(cim.getStatusCim().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}
	/**
	 * 日志打印
	 * @param jpr
	 * @param openCaseList
	 */
	private void handleJprFault(IXfsStatus jpr,List<ICaseFault> openCaseList){
		if(jpr.getStatusJpr().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.JPR);
		}else if(jpr.getStatusJpr().getStatus().equals(DeviceStatus.Fatal)||
				jpr.getStatusJpr().getStatus().equals(DeviceStatus.Warning)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.JPR);
			caseFault.setAppStatus(jpr.getRunStatus());
			caseFault.setFaultCode(jpr.getStatusJpr().getCode());
			caseFault.setTerminalId(jpr.getTerminalId());
			caseFault.setVendorHwCode(jpr.getStatusJpr().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}
	/**
	 * 凭条打印
	 * @param rpr
	 * @param openCaseList
	 */
	private void handleRprFault(IXfsStatus rpr,List<ICaseFault> openCaseList){
		if(rpr.getStatusRpr().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.RPR);
		}else if(rpr.getStatusRpr().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.RPR);
			caseFault.setAppStatus(rpr.getRunStatus());
			caseFault.setFaultCode(rpr.getStatusRpr().getCode());
			caseFault.setTerminalId(rpr.getTerminalId());
			caseFault.setVendorHwCode(rpr.getStatusRpr().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}
	/**
	 * 密码键盘
	 * @param pin
	 * @param openCaseList
	 */
	private void handlePinFault(IXfsStatus pin,List<ICaseFault> openCaseList){
		if(pin.getStatusPin().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.PIN);
		}else if(pin.getStatusPin().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.PIN);
			caseFault.setAppStatus(pin.getRunStatus());
			caseFault.setFaultCode(pin.getStatusPin().getCode());
			caseFault.setTerminalId(pin.getTerminalId());
			caseFault.setVendorHwCode(pin.getStatusPin().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}
	/**
	 * 创建模块对应的故障
	 *
	 * @param caseFault
	 * @param faultClassifyId
	 * @param openCaseList
	 */
	private void createCaseFault(ICaseFault caseFault,List<ICaseFault> openCaseList){
		IFaultClassify faultClassify = faultClassifyService.getFaultClassifyById("F_MOD");
		if(caseFaultService.hasModCaseFault(openCaseList,caseFault.getDevMod(),faultClassify)){
			return;
		}
		caseFault.setFaultClassify(faultClassify);
		caseFaultService.createCaseFault(caseFault);
		caseNotifyService.createCaseNotify(faultClassify, caseFault);
	}

	/**
	 * 发卡读卡器
	 * @param icc
	 * @param openCaseList
	 */
	private void handleIccFault(IXfsStatus icc,List<ICaseFault> openCaseList){
		if(icc.getStatusIcc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.ICC);
		}else if(icc.getStatusIcc().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.ICC);
			caseFault.setAppStatus(icc.getRunStatus());
			caseFault.setFaultCode(icc.getStatusIcc().getCode());
			caseFault.setTerminalId(icc.getTerminalId());
			caseFault.setVendorHwCode(icc.getStatusIcc().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}

	/**
	 * 指纹仪
	 * @param fgp
	 * @param openCaseList
	 */
	private void handleFgpFault(IXfsStatus fgp,List<ICaseFault> openCaseList){
		if(fgp.getStatusFgp().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.FGP);
		}else if(fgp.getStatusFgp().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.FGP);
			caseFault.setAppStatus(fgp.getRunStatus());
			caseFault.setFaultCode(fgp.getStatusFgp().getCode());
			caseFault.setTerminalId(fgp.getTerminalId());
			caseFault.setVendorHwCode(fgp.getStatusFgp().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}

	/**
	 * 身份证扫描仪
	 * @param isc
	 * @param openCaseList
	 */
	private void handleIscFault(IXfsStatus isc,List<ICaseFault> openCaseList){
		if(isc.getStatusIsc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.ISC);
		}else if(isc.getStatusIsc().getStatus().equals(DeviceStatus.Fatal)){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.ISC);
			caseFault.setAppStatus(isc.getRunStatus());
			caseFault.setFaultCode(isc.getStatusIsc().getCode());
			caseFault.setTerminalId(isc.getTerminalId());
			caseFault.setVendorHwCode(isc.getStatusIsc().getHwCode());
			createCaseFault(caseFault,openCaseList);
		}
	}

}
