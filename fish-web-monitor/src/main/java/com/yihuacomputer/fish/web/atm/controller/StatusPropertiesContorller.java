package com.yihuacomputer.fish.web.atm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusBcr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCam;
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
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkd;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusUkr;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.web.atm.format.StatusPropertiesMsg;

/**
 * @author GQ 状态属性信息包
 */
@Controller
@RequestMapping("/msg/statusProperties")
public class StatusPropertiesContorller {

	private Logger logger = LoggerFactory.getLogger(StatusPropertiesContorller.class);

	@Autowired
	private IXfsService xfsService;

	/**
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap acceptStatus(@RequestBody StatusPropertiesMsg msg) {

		ModelMap result = new ModelMap();
		result.addAttribute("TermId", msg.getTermId());
		result.addAttribute("ret", "RET_00");

		IXfsStatus xfsStatus = xfsService.loadXfsStatus(msg.getTermId());
		IStatusIdc idc = xfsStatus.getStatusIdc() == null ? xfsStatus.makeStatusIdc() : xfsStatus.getStatusIdc();
		if (msg.getIdcCode() != null) {
			idc.setCode(msg.getIdcCode());
			idc.setCards(msg.getIdcReatianCard());
			idc.setHwCode(msg.getIdcHwCode());
		}
		IStatusJpr jpr = xfsStatus.getStatusJpr() == null ? xfsStatus.makeStatusJpr() : xfsStatus.getStatusJpr();
		jpr.setCode(msg.getJprCode());
		jpr.setHwCode(msg.getJprHwCode());
		IStatusRpr rpr = xfsStatus.getStatusRpr() == null ? xfsStatus.makeStatusRpr() : xfsStatus.getStatusRpr();
		rpr.setCode(msg.getRprCode());
		rpr.setHwCode(msg.getRprHwCode());
		IStatusCdm cdm = xfsStatus.getStatusCdm() == null ? xfsStatus.makeStatusCdm() : xfsStatus.getStatusCdm();
		cdm.setCode(msg.getCdmCode());
		cdm.setHwCode(msg.getCdmHwCode());
		IStatusCim cim = xfsStatus.getStatusCim() == null ? xfsStatus.makeStatusCim() : xfsStatus.getStatusCim();
		cim.setCode(msg.getCimCode());
		cim.setHwCode(msg.getCimHwCode());
		IStatusSiu siu = xfsStatus.getStatusSiu() == null ? xfsStatus.makeStatusSiu() : xfsStatus.getStatusSiu();
		siu.setCode(msg.getSiuCode());
		siu.setHwCode(msg.getSiuHwCode());
		IStatusPin pin = xfsStatus.getStatusPin() == null ? xfsStatus.makeStatusPin() : xfsStatus.getStatusPin();
		pin.setCode(msg.getPinCode());
		pin.setHwCode(msg.getPinHwCode());
		IStatusTtu ttu = xfsStatus.getStatusTtu() == null ? xfsStatus.makeStatusTtu() : xfsStatus.getStatusTtu();
		ttu.setCode(msg.getTtuCode());
		ttu.setHwCode(msg.getTtuHwCode());
		IStatusNfc nfc = xfsStatus.getStatusNfc() == null ? xfsStatus.makeStatusNfc() : xfsStatus.getStatusNfc();
		nfc.setCode(msg.getNfcCode());
		nfc.setHwCode(msg.getNfcHwCode());
		IStatusPbk pbk = xfsStatus.getStatusPbk() == null ? xfsStatus.makeStatusPbk() : xfsStatus.getStatusPbk();
		pbk.setCode(msg.getPbkCode());
		pbk.setHwCode(msg.getPbkHwCode());
		IStatusIcc icc = xfsStatus.getStatusIcc() == null ? xfsStatus.makeStatusIcc() : xfsStatus.getStatusIcc();
		icc.setCode(msg.getIccCode());
		icc.setCards(msg.getIccReatianCard());
		icc.setHwCode(msg.getIccHwCode());
		icc.setIccCurrCards((int) msg.getIccCurrentCount());
		IStatusFgp fgp = xfsStatus.getStatusFgp() == null ? xfsStatus.makeStatusFgp() : xfsStatus.getStatusFgp();
		fgp.setCode(msg.getFgpCode());
		fgp.setHwCode(msg.getFgpHwCode());
		IStatusIsc isc = xfsStatus.getStatusIsc() == null ? xfsStatus.makeStatusIsc() : xfsStatus.getStatusIsc();
		isc.setCode(msg.getIscCode());
		isc.setHwCode(msg.getIscHwCode());
		IStatusBcr bcr = xfsStatus.getStatusBcr() == null ? xfsStatus.makeStatusBcr() : xfsStatus.getStatusBcr();
		bcr.setCode(msg.getBcrCode());
		bcr.setHwCode(msg.getBcrHwCode());
		IStatusCam cam = xfsStatus.getStatusCam() == null ? xfsStatus.makeStatusCam() : xfsStatus.getStatusCam();
		cam.setCode(msg.getCamCode());
		cam.setHwCode(msg.getCamHwCode());
		IStatusUkr ukr = xfsStatus.getStatusUkr() == null ? xfsStatus.makeStatusUkr() : xfsStatus.getStatusUkr();
		ukr.setCode(msg.getUkeyReaderCode());
		ukr.setHwCode(msg.getUkeyReaderHwCode());

		IStatusUkd ukd = xfsStatus.getStatusUkd() == null ? xfsStatus.makeStatusUkd() : xfsStatus.getStatusUkd();
		ukd.setCode(msg.getUkeyDispenserCode());
		ukd.setHwCode(msg.getUkeyDispenserHwCode());

		xfsStatus.setTerminalId(msg.getTermId());
		xfsStatus.setNetStatus(NetStatus.Healthy);
		xfsStatus.setBoxInitCount(msg.getBoxInitCount());
		xfsStatus.setBoxCurrentCount(msg.getBoxCurrentCount());

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
		xfsStatus.setStatusBcr(bcr);
		xfsStatus.setStatusCam(cam);
		xfsStatus.setStatusUkr(ukr);
		xfsStatus.setStatusUkd(ukd);
		xfsStatus.setDateTime(DateUtils.getTimestamp(new Date()));
		xfsService.updateXfsStatus(xfsStatus);

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("statusProperties for terminalId is %s ", msg.getTermId()));
		}
		return result;
	}
}
