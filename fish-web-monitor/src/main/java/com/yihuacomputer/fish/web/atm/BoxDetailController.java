package com.yihuacomputer.fish.web.atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.web.atm.format.BoxDetailReportMsg;
import com.yihuacomputer.fish.web.atm.format.DeviceBoxReportMsg;

@Controller
@RequestMapping("/msg/reportboxdetail")
public class BoxDetailController {
	private Logger logger = LoggerFactory.getLogger(BoxDetailController.class);

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;

	@Autowired
	private IDeviceBoxDetailInfoService deviceBoxDetailInfoService;

	/**
	 * 接收设备定时状态报告
	 *
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap acceptStatus(@RequestBody DeviceBoxReportMsg msg) {
		logger.info(String.format("collection transaction :[%s]", JsonUtils.toJson(msg)));

		ModelMap result = new ModelMap();
		result.addAttribute("Ret", "00");
		
		//TODO 清机时候才判断钞箱是否变化?
		//获取钞箱最大存取款金额信息
		Map<BoxType, Long> boxTypeAmtMap = getBoxCashInfo(msg.getBoxdetailList());
		//获取当前存取款金额
		Map<BoxType, Long> boxTypeAmtValueMap = getBoxCashValueInfo(msg.getBoxdetailList());
		try {
			IDevice device = deviceService.get(msg.getTermianlId());
			if (device == null) {
				logger.error(String.format("collection boxdetail exception!boxdetail is [%s],device don't exist", JsonUtils.toJson(msg)));
				return result;
			}
			IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.findByDeviceId(device.getId());
			// 钞箱信息不存在全部新建
			if (deviceBoxInfo == null) {
				deviceBoxInfo = deviceBoxInfoService.make();
				deviceBoxInfo.setDeviceId(device);
				deviceBoxInfo.setBoxChange(true);
				List<BoxDetailReportMsg> detailList = msg.getBoxdetailList();
				for (BoxDetailReportMsg bdrm : detailList) {
					IDeviceBoxDetailInfo dbdi = deviceBoxDetailInfoService.make();
					dbdi.setBoxType(BoxType.getBoxType(bdrm.getBoxType()));
					dbdi.setCashId(bdrm.getId());
					dbdi.setCurrency(bdrm.getCurrency());
					dbdi.setEffect(true);
					dbdi.setInitialCount(bdrm.getInitialCount());
					dbdi.setCashInCount(bdrm.getCashInCount());
					dbdi.setDispenseCount(bdrm.getDispenseNumber());
					dbdi.setMaxiNum(bdrm.getMaximum());
					dbdi.setValue(bdrm.getValue());
					dbdi.setNumber(bdrm.getNumber());
					dbdi.setDeviceBoxInfo(deviceBoxInfo);
					deviceBoxInfo.add(dbdi);
				}
				deviceBoxInfo.setDefaultBill(boxTypeAmtMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.BILLCASSETTE));
				deviceBoxInfo.setDefaultCashIn(boxTypeAmtMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.CASHINCASSETTE));
				deviceBoxInfo.setBillValue(boxTypeAmtValueMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtValueMap.get(BoxType.BILLCASSETTE));
				deviceBoxInfo.setCashInValue(boxTypeAmtValueMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtValueMap.get(BoxType.CASHINCASSETTE));
				deviceBoxInfoService.save(deviceBoxInfo);
			}
			// 钞箱信息存在，更改钞箱明细的
			else {
				IFilter filter = new Filter();
				filter.eq("deviceBoxInfo.id", deviceBoxInfo.getId());
				filter.eq("effect", true);
				Map<String, IDeviceBoxDetailInfo> dbdiMap = new HashMap<String, IDeviceBoxDetailInfo>();
				for (IDeviceBoxDetailInfo idbdi : deviceBoxInfo.getDeviceBoxDetails()) {
					dbdiMap.put(idbdi.getCashId(), idbdi);
				}
				List<BoxDetailReportMsg> detailList = msg.getBoxdetailList();
				for (BoxDetailReportMsg bdrm : detailList) {
					IDeviceBoxDetailInfo dbdi = dbdiMap.get(bdrm.getId());
					IDeviceBoxDetailInfo dbdiHist = dbdi;
					// 如果钞箱不存在，则新建
					if (dbdi == null) {
						dbdi = deviceBoxDetailInfoService.make();
						dbdi.setBoxType(BoxType.getBoxType(bdrm.getBoxType()));
						dbdi.setCashId(bdrm.getId());
						dbdi.setCurrency(bdrm.getCurrency());
						dbdi.setEffect(true);
						dbdi.setInitialCount(bdrm.getInitialCount());
						dbdi.setCashInCount(bdrm.getCashInCount());
						dbdi.setDispenseCount(bdrm.getDispenseNumber());
						dbdi.setMaxiNum(bdrm.getMaximum());
						dbdi.setValue(bdrm.getValue());
						dbdi.setNumber(bdrm.getNumber());
						dbdi.setDeviceBoxInfo(deviceBoxInfo);
						deviceBoxInfo.add(dbdi);
					}
					// 如果钞箱存在，修改钞箱信息
					else {
						dbdi.setBoxType(BoxType.getBoxType(bdrm.getBoxType()));
						dbdi.setCashId(bdrm.getId());
						dbdi.setCurrency(bdrm.getCurrency());
						dbdi.setEffect(true);
						dbdi.setInitialCount(bdrm.getInitialCount());
						dbdi.setCashInCount(bdrm.getCashInCount());
						dbdi.setDispenseCount(bdrm.getDispenseNumber());
						dbdi.setMaxiNum(bdrm.getMaximum());
						dbdi.setValue(bdrm.getValue());
						dbdi.setNumber(bdrm.getNumber());
						dbdi.setDeviceBoxInfo(deviceBoxInfo);
						if (!dbdiHist.equals(dbdi)) {
							deviceBoxDetailInfoService.update(dbdi);
						}
					}
					if(deviceBoxInfo.getDefaultBill()!=boxTypeAmtMap.get(BoxType.BILLCASSETTE)){
						deviceBoxInfo.setDefaultBill(boxTypeAmtMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.BILLCASSETTE));
						deviceBoxInfo.setBoxChange(true);
					}
					if(deviceBoxInfo.getDefaultCashIn()!=boxTypeAmtMap.get(BoxType.CASHINCASSETTE)){
						deviceBoxInfo.setDefaultCashIn(boxTypeAmtMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.CASHINCASSETTE));
						deviceBoxInfo.setBoxChange(true);
					}
					deviceBoxInfo.setBillValue(boxTypeAmtValueMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtValueMap.get(BoxType.BILLCASSETTE));
					deviceBoxInfo.setCashInValue(boxTypeAmtValueMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtValueMap.get(BoxType.CASHINCASSETTE));
					deviceBoxInfoService.update(deviceBoxInfo);
				}
			}
		} catch (Exception e) {
			logger.error(String.format("collection transaction exception![%s],transaction is [%s]", e, JsonUtils.toJson(msg)));
		}

		return result;
	}

	/**
	 * 获取钞箱最大存取款金额信息
	 * @param detailList
	 * @return
	 */
	private Map<BoxType, Long> getBoxCashInfo(List<BoxDetailReportMsg> detailList) {

		Map<BoxType, Long> boxTypeAmtMap = new HashMap<BoxType, Long>();
		for (BoxDetailReportMsg bdrm : detailList) {
			BoxType bt = BoxType.getBoxType(bdrm.getBoxType());
			long boxAmt = boxTypeAmtMap.get(bt)==null?0l:boxTypeAmtMap.get(bt);
			if (bt.equals(BoxType.BILLCASSETTE)) {
				boxAmt += bdrm.getValue() * bdrm.getMaximum();
				boxTypeAmtMap.put(bt, boxAmt);
			} else if (bt.equals(BoxType.CASHINCASSETTE)) {
				boxAmt += bdrm.getValue() * bdrm.getMaximum();
				boxTypeAmtMap.put(bt, boxAmt);
			} else if (bt.equals(BoxType.RECYCLECASSETTE)) {
				long boxAmtB = boxTypeAmtMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.BILLCASSETTE);
				boxAmtB += bdrm.getValue() * bdrm.getMaximum();
				boxTypeAmtMap.put(BoxType.BILLCASSETTE, boxAmtB);
				long boxAmtC = boxTypeAmtMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.CASHINCASSETTE);
				boxAmtC += bdrm.getValue() * bdrm.getMaximum();
				boxTypeAmtMap.put(BoxType.CASHINCASSETTE, boxAmtC);
			}
		}
		return boxTypeAmtMap;
	}
	
	/**
	 * 获取当前存取款金额
	 * @param detailList
	 * @return
	 */
	private Map<BoxType, Long> getBoxCashValueInfo(List<BoxDetailReportMsg> detailList) {

		Map<BoxType, Long> boxTypeAmtMap = new HashMap<BoxType, Long>();
		for (BoxDetailReportMsg bdrm : detailList) {
			BoxType bt = BoxType.getBoxType(bdrm.getBoxType());
			long boxAmt = boxTypeAmtMap.get(bt)==null?0l:boxTypeAmtMap.get(bt);
			if (bt.equals(BoxType.BILLCASSETTE)) {
				boxAmt += bdrm.getValue() * bdrm.getNumber();
				boxTypeAmtMap.put(bt, boxAmt);
			} else if (bt.equals(BoxType.CASHINCASSETTE)) {
				boxAmt += bdrm.getValue() * bdrm.getNumber();
				boxTypeAmtMap.put(bt, boxAmt);
			} else if (bt.equals(BoxType.RECYCLECASSETTE)) {
				long boxAmtB = boxTypeAmtMap.get(BoxType.BILLCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.BILLCASSETTE);
				boxAmtB += bdrm.getValue() * bdrm.getNumber();
				boxTypeAmtMap.put(BoxType.BILLCASSETTE, boxAmtB);
				long boxAmtC = boxTypeAmtMap.get(BoxType.CASHINCASSETTE)==null?0:boxTypeAmtMap.get(BoxType.CASHINCASSETTE);
				boxAmtC += bdrm.getValue() * bdrm.getNumber();
				boxTypeAmtMap.put(BoxType.CASHINCASSETTE, boxAmtC);
			}
		}
		return boxTypeAmtMap;
	}
}
