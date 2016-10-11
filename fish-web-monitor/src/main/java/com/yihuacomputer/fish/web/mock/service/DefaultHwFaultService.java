package com.yihuacomputer.fish.web.mock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultFlowService;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.fault.entity.FaultClassify;

@Service
public class DefaultHwFaultService {

	private Logger logger = LoggerFactory.getLogger(DefaultHwFaultService.class);

	@Autowired
	private IFaultClassifyService faultClassifyService;

	@Autowired
	private ICaseFaultFlowService caseFaultFlowService;

	@Autowired
	private ICaseFaultService caseFaultService;

	@Autowired
	private IDeviceService deviceService;

	public static final String F_MOD = "F_MOD" ;

	public static final String N_AP_SETUP = "N_AP_SETUP" ;

	public static final String N_AP_STAT = "N_AP_STAT" ;

	public static final String R_CASSET = "R_CASSET" ;

	public static final String R_PTR = "R_PTR" ;

	public static final String R_CDM = "R_CDM" ;

	public static final String R_CIM = "R_CIM" ;
	/**
	 * 处理模块故障
	 * @param xfsStatus
	 */
	public void handleModFualt(IXfsStatus xfsStatus,IXfsStatus hisXfsStatus){
		try{
			List<ICaseFault> openCaseList = caseFaultService.listOpenCaseFault(xfsStatus.getTerminalId());

			if(xfsStatus.getRunStatus().equals(RunStatus.Maintain)){
				return;
			}
			handleIdcFault(xfsStatus,openCaseList,hisXfsStatus);
			handleCdmFault(xfsStatus,openCaseList,hisXfsStatus);
			handleCimFault(xfsStatus,openCaseList,hisXfsStatus);
			handleJprFault(xfsStatus,openCaseList,hisXfsStatus);
			handleRprFault(xfsStatus,openCaseList,hisXfsStatus);
			handlePinFault(xfsStatus,openCaseList,hisXfsStatus);

			handleIccFault(xfsStatus,openCaseList,hisXfsStatus);
			handleFgpFault(xfsStatus,openCaseList,hisXfsStatus);
			handleIscFault(xfsStatus,openCaseList,hisXfsStatus);
			handleCamFault(xfsStatus,openCaseList,hisXfsStatus);
			handleBcrFault(xfsStatus,openCaseList,hisXfsStatus);
			handleUkrFault(xfsStatus,openCaseList,hisXfsStatus);
			handleUkdFault(xfsStatus,openCaseList,hisXfsStatus);

			handleBoxFault(xfsStatus, openCaseList);

		}catch(Exception e){
			logger.error(String.format("collection caseFault exception[%s]", e));
		}
	}

	/**
	 * 发UKEY模块
	 * @param idc
	 * @param openCaseList
	 */
	private void handleUkdFault(IXfsStatus ukd,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(ukd.getStatusUkd().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.UKD,F_MOD);
		}else if(ukd.getStatusUkd().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusUkd().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.UKD);
			caseFault.setAppStatus(ukd.getRunStatus());
			caseFault.setFaultCode(ukd.getStatusUkd().getCode());
			caseFault.setTerminalId(ukd.getTerminalId());
			caseFault.setVendorHwCode(ukd.getStatusUkd().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 读UKEY模块
	 * @param idc
	 * @param openCaseList
	 */
	private void handleUkrFault(IXfsStatus ukr,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(ukr.getStatusUkr().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.UKR,F_MOD);
		}else if(ukr.getStatusUkr().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusUkr().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.UKR);
			caseFault.setAppStatus(ukr.getRunStatus());
			caseFault.setFaultCode(ukr.getStatusUkr().getCode());
			caseFault.setTerminalId(ukr.getTerminalId());
			caseFault.setVendorHwCode(ukr.getStatusUkr().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 读卡器
	 * @param idc
	 * @param openCaseList
	 */
	private void handleIdcFault(IXfsStatus idc,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(idc.getStatusIdc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.IDC,F_MOD);
		}else if(idc.getStatusIdc().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusIdc().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.IDC);
			caseFault.setAppStatus(idc.getRunStatus());
			caseFault.setFaultCode(idc.getStatusIdc().getCode());
			caseFault.setTerminalId(idc.getTerminalId());
			caseFault.setVendorHwCode(idc.getStatusIdc().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}

	/**
	 * 取款模块
	 * @param cdm
	 * @param openCaseList
	 */
	private void handleCdmFault(IXfsStatus cdm,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(cdm.getStatusCdm().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.CDM,F_MOD);
		}else if(cdm.getStatusCdm().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusCdm().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.CDM);
			caseFault.setAppStatus(cdm.getRunStatus());
			caseFault.setFaultCode(cdm.getStatusCdm().getCode());
			caseFault.setTerminalId(cdm.getTerminalId());
			caseFault.setVendorHwCode(cdm.getStatusCdm().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}

	/**
	 * 存款模块
	 * @param cim
	 * @param openCaseList
	 */
	private void handleCimFault(IXfsStatus cim,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(cim.getStatusCim().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.CIM,F_MOD);
		}else if(cim.getStatusCim().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusCim().getStatus())){
			IDevice device = deviceService.get(cim.getTerminalId()) ;
			IFaultClassify faultClassify = faultClassifyService.getFaultClassifyById(F_MOD);
			if(device.getDevType().getDevCatalog().getNo().equals("02") && caseFaultService.hasModCaseFault(openCaseList,DeviceMod.CDM,faultClassify)){
				logger.info("设备为存取款机，且取款模块已有故障，存款模块不创建故障");
				return ;
			}
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.CIM);
			caseFault.setAppStatus(cim.getRunStatus());
			caseFault.setFaultCode(cim.getStatusCim().getCode());
			caseFault.setTerminalId(cim.getTerminalId());
			caseFault.setVendorHwCode(cim.getStatusCim().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 日志打印
	 * @param jpr
	 * @param openCaseList
	 */
	private void handleJprFault(IXfsStatus jpr,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(jpr.getStatusJpr().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.JPR);
		}else if(jpr.getStatusJpr().getStatus().equals(DeviceStatus.Warning)
				&& DeviceStatus.Warning.equals(hisXfsStatus.getStatusJpr().getStatus())){
		 if (jpr.getStatusJpr().getCode().charAt(2) == '1'
                    || jpr.getStatusJpr().getCode().charAt(2) == '2') {
			closeCase(jpr.getTerminalId(),DeviceMod.JPR,F_MOD) ;
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.JPR);
			caseFault.setAppStatus(jpr.getRunStatus());
			caseFault.setFaultCode(jpr.getStatusJpr().getCode());
			caseFault.setTerminalId(jpr.getTerminalId());
			caseFault.setVendorHwCode(jpr.getStatusJpr().getHwCode());
			createCaseFault(caseFault,openCaseList,R_PTR);
		 }
		}else if(jpr.getStatusJpr().getStatus().equals(DeviceStatus.Fatal)
                && DeviceStatus.Fatal.equals(hisXfsStatus.getStatusJpr().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.JPR);
			caseFault.setAppStatus(jpr.getRunStatus());
			caseFault.setFaultCode(jpr.getStatusJpr().getCode());
			caseFault.setTerminalId(jpr.getTerminalId());
			caseFault.setVendorHwCode(jpr.getStatusJpr().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 凭条打印
	 * @param rpr
	 * @param openCaseList
	 */
	private void handleRprFault(IXfsStatus rpr,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(rpr.getStatusRpr().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.RPR);
		}else if(rpr.getStatusRpr().getStatus().equals(DeviceStatus.Warning)
				 && DeviceStatus.Warning.equals(hisXfsStatus.getStatusRpr().getStatus())){
		 if (rpr.getStatusRpr().getCode().charAt(2) == '1'
                    || rpr.getStatusRpr().getCode().charAt(2) == '2') {
			closeCase(rpr.getTerminalId(), DeviceMod.RPR, F_MOD);
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.RPR);
			caseFault.setAppStatus(rpr.getRunStatus());
			caseFault.setFaultCode(rpr.getStatusRpr().getCode());
			caseFault.setTerminalId(rpr.getTerminalId());
			caseFault.setVendorHwCode(rpr.getStatusRpr().getHwCode());
			createCaseFault(caseFault,openCaseList,R_PTR);
		 }
		}else if(rpr.getStatusRpr().getStatus().equals(DeviceStatus.Fatal)
				 && DeviceStatus.Fatal.equals(hisXfsStatus.getStatusRpr().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.RPR);
			caseFault.setAppStatus(rpr.getRunStatus());
			caseFault.setFaultCode(rpr.getStatusRpr().getCode());
			caseFault.setTerminalId(rpr.getTerminalId());
			caseFault.setVendorHwCode(rpr.getStatusRpr().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 密码键盘
	 * @param pin
	 * @param openCaseList
	 */
	private void handlePinFault(IXfsStatus pin,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(pin.getStatusPin().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.PIN,F_MOD);
		}else if(pin.getStatusPin().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusPin().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.PIN);
			caseFault.setAppStatus(pin.getRunStatus());
			caseFault.setFaultCode(pin.getStatusPin().getCode());
			caseFault.setTerminalId(pin.getTerminalId());
			caseFault.setVendorHwCode(pin.getStatusPin().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 创建模块对应的故障
	 *
	 * @param caseFault
	 * @param faultClassifyId
	 * @param openCaseList
	 */
	private void createCaseFault(ICaseFault caseFault,List<ICaseFault> openCaseList,String modType){
		IFaultClassify faultClassify = faultClassifyService.getFaultClassifyById(modType);
		if(caseFaultService.hasModCaseFault(openCaseList,caseFault.getDevMod(),faultClassify)){
			return;
		}
		caseFault.setFaultClassify(faultClassify);
		caseFaultService.createCaseFault(caseFault);
		caseFaultFlowService.execute(faultClassify, caseFault);
	}

    /**
     * 关闭故障；当设备状态为警告时,关闭设备之前创建的类型为故障的故障工单
     *
     * @param terminalId
     * @param mod
     * @param modType
     */
    private void closeCase(String terminalId, DeviceMod mod, String modType) {
        IFilter filter = new Filter();
        filter.eq("terminalId", terminalId);
        filter.eq("devMod", mod);

        FaultClassify faultClassify = new FaultClassify();
        faultClassify.setId(modType);

        filter.eq("faultClassify", faultClassify);

        filter.eq("faultStatus", FaultStatus.OPEN);

        List<ICaseFault> listCase = caseFaultService.list(filter);

        for (ICaseFault caseFault : listCase) {
            caseFaultService.closeCaseFault(caseFault);
        }
    }

	/**
	 * 发卡读卡器
	 * @param icc
	 * @param openCaseList
	 */
	private void handleIccFault(IXfsStatus icc,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(icc.getStatusIcc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.ICC,F_MOD);
		}else if(icc.getStatusIcc().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusIcc().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.ICC);
			caseFault.setAppStatus(icc.getRunStatus());
			caseFault.setFaultCode(icc.getStatusIcc().getCode());
			caseFault.setTerminalId(icc.getTerminalId());
			caseFault.setVendorHwCode(icc.getStatusIcc().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}

	/**
	 * 指纹仪
	 * @param fgp
	 * @param openCaseList
	 */
	private void handleFgpFault(IXfsStatus fgp,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(fgp.getStatusFgp().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.FGP,F_MOD);
		}else if(fgp.getStatusFgp().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusFgp().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.FGP);
			caseFault.setAppStatus(fgp.getRunStatus());
			caseFault.setFaultCode(fgp.getStatusFgp().getCode());
			caseFault.setTerminalId(fgp.getTerminalId());
			caseFault.setVendorHwCode(fgp.getStatusFgp().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}

	/**
	 * 身份证扫描仪
	 * @param isc
	 * @param openCaseList
	 */
	private void handleIscFault(IXfsStatus isc,List<ICaseFault> openCaseList,IXfsStatus hisXfsStatus){
		if(isc.getStatusIsc().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList,DeviceMod.ISC,F_MOD);
		}else if(isc.getStatusIsc().getStatus().equals(DeviceStatus.Fatal)
				&& DeviceStatus.Fatal.equals(hisXfsStatus.getStatusIsc().getStatus())){
			ICaseFault caseFault = caseFaultService.make();
			caseFault.setDevMod(DeviceMod.ISC);
			caseFault.setAppStatus(isc.getRunStatus());
			caseFault.setFaultCode(isc.getStatusIsc().getCode());
			caseFault.setTerminalId(isc.getTerminalId());
			caseFault.setVendorHwCode(isc.getStatusIsc().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 摄像头
	 * @param cam
	 * @param openCaseList
	 * @param hisXFsStatus
	 */
	private void handleCamFault(IXfsStatus cam,List<ICaseFault> openCaseList,IXfsStatus hisXFsStatus){
		if(cam.getStatusCam().getStatus().equals(DeviceStatus.Healthy)){
			caseFaultService.closeHealthyModCase(openCaseList, DeviceMod.CAM,F_MOD);
		}else if(cam.getStatusCam().getStatus().equals(DeviceStatus.Fatal)
			&&DeviceStatus.Fatal.equals(hisXFsStatus.getStatusCam().getStatus())){
			ICaseFault caseFault=caseFaultService.make();
			caseFault.setDevMod(DeviceMod.CAM);
			caseFault.setAppStatus(cam.getRunStatus());
			caseFault.setFaultCode(cam.getStatusCam().getCode());
			caseFault.setTerminalId(cam.getTerminalId());
			caseFault.setVendorHwCode(cam.getStatusCam().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);
		}
	}
	/**
	 * 二维码扫描
	 * @param bcr
	 * @param openCaseList
	 * @param hisXFsStatus
	 */
	private void handleBcrFault(IXfsStatus bcr,List<ICaseFault> openCaseList,IXfsStatus hisXFsStatus){
		if(bcr.getStatusBcr().getStatus().equals(DeviceStatus.Healthy)){
		caseFaultService.closeHealthyModCase(openCaseList, DeviceMod.BCR,F_MOD);
		}else if(bcr.getStatusBcr().getStatus().equals(DeviceStatus.Fatal)
				&&DeviceStatus.Fatal.equals(hisXFsStatus.getStatusBcr().getStatus())){
			ICaseFault caseFault=caseFaultService.make();
			caseFault.setDevMod(DeviceMod.BCR);
			caseFault.setAppStatus(bcr.getRunStatus());
			caseFault.setFaultCode(bcr.getStatusBcr().getCode());
			caseFault.setTerminalId(bcr.getTerminalId());
			caseFault.setVendorHwCode(bcr.getStatusBcr().getHwCode());
			createCaseFault(caseFault,openCaseList,F_MOD);

		}
	}

    /**
     * 处理钞空，钞满
     *
     * @param xfsStatus
     * @param openCaseList
     */
    private void handleBoxFault(IXfsStatus xfsStatus, List<ICaseFault> openCaseList) {
        if (BoxStatus.Fatal.equals(xfsStatus.getBoxStatus())) {
            return;
        }

        if (!xfsStatus.getStatusCdm().getStatus().equals(DeviceStatus.NoDevice)) {
            if (BoxStatus.Low.equals(xfsStatus.getBoxStatus())) {
            	ICaseFault caseFault = caseFaultService.make();
    			caseFault.setDevMod(DeviceMod.CDM);
    			caseFault.setAppStatus(xfsStatus.getRunStatus());
    			caseFault.setFaultCode(xfsStatus.getStatusCdm().getCode());
    			caseFault.setTerminalId(xfsStatus.getTerminalId());
    			caseFault.setVendorHwCode(xfsStatus.getStatusCdm().getHwCode());
    			createCaseFault(caseFault, openCaseList,R_CDM);

                // 当钞少时，需要关闭钞空报警
                for (ICaseFault cdmCaseFault : openCaseList) {
                    String id = cdmCaseFault.getFaultClassify().getId();
                    if (cdmCaseFault.getDevMod().equals(DeviceMod.CDM) && "R_CASSET".equals(id)) {
                        caseFaultService.closeCaseFault(cdmCaseFault);
                    }
                }

            } else if (BoxStatus.Empty.equals(xfsStatus.getBoxStatus())) {
            	ICaseFault caseFault = caseFaultService.make();
    			caseFault.setDevMod(DeviceMod.CDM);
    			caseFault.setAppStatus(xfsStatus.getRunStatus());
    			caseFault.setFaultCode(xfsStatus.getStatusCdm().getCode());
    			caseFault.setTerminalId(xfsStatus.getTerminalId());
    			caseFault.setVendorHwCode(xfsStatus.getStatusCdm().getHwCode());
                createCaseFault(caseFault,openCaseList, R_CASSET);
            } else {
                for (ICaseFault caseFault : openCaseList) {
                    String id = caseFault.getFaultClassify().getId();
                    if (caseFault.getDevMod().equals(DeviceMod.CDM) && (R_CASSET.equals(id) || R_CDM.equals(id))) {
                        caseFaultService.closeCaseFault(caseFault);
                    }
                }
            }
        }

        if (!xfsStatus.getStatusCim().getStatus().equals(DeviceStatus.NoDevice)) {
            if (BoxStatus.Full.equals(xfsStatus.getBoxStatus())) {
            	ICaseFault caseFault = caseFaultService.make();
    			caseFault.setDevMod(DeviceMod.CIM);
    			caseFault.setAppStatus(xfsStatus.getRunStatus());
    			caseFault.setFaultCode(xfsStatus.getStatusCim().getCode());
    			caseFault.setTerminalId(xfsStatus.getTerminalId());
    			caseFault.setVendorHwCode(xfsStatus.getStatusCim().getHwCode());
                createCaseFault(caseFault,openCaseList, R_CIM);
            } else {
                for (ICaseFault caseFault : openCaseList) {
                    if (caseFault.getDevMod().equals(DeviceMod.CIM)
                            && R_CIM.equals(caseFault.getFaultClassify().getId())) {
                        caseFaultService.closeCaseFault(caseFault);
                    }
                }
            }
        }

    }

}
