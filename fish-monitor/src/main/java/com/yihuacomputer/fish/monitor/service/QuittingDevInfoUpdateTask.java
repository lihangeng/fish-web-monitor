package com.yihuacomputer.fish.monitor.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;

@Service
@Transactional
public class QuittingDevInfoUpdateTask {

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IQuittingNoticeService quittingNoticeService;

	@Autowired
	private IXfsService xfsService;

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IRunInfoService runInfoService;



	public void quittingDevInfoUpdate()
	{
		StringBuffer hql = new StringBuffer();
		hql.append("select qt from QuittingNotice qt where qt.stopTime = ? ");
		Date day = new Date();
		String strDayTime = DateUtils.getDate(day)+" 00:00:00";
		Date dayTime = DateUtils.getTimestamp(strDayTime);
		List<Object> objValues = new ArrayList<Object>();
		objValues.add(dayTime);
		List<IQuittingNotice> quittingNoticeList = dao.findByHQL(hql.toString(), objValues.toArray());

		for(IQuittingNotice quittingNotice : quittingNoticeList)
		{
			quittingNotice.setDevStatus(Status.DISABLED);
			quittingNoticeService.update(quittingNotice);
			IDevice device = deviceService.get(quittingNotice.getDeviceCode());
			if (quittingNotice.getDevStatus() != null) {
				// 当设备报停时,更新设备表状态
				if (!device.getStatus().equals(quittingNotice.getDevStatus())) {
					device.setStatus(quittingNotice.getDevStatus());
					deviceService.update(device);
				}
				/* 停机开始 */
				if (quittingNotice.getDevStatus().equals(Status.DISABLED)) {
					IRunInfo runInfo = runInfoService.make();
					runInfo.setTerminalId(quittingNotice.getDeviceCode());
					runInfo.setRunStatus(RunStatus.StopManmade);
					collectService.collectATMCRunInfo(
							quittingNotice.getDeviceCode(), runInfo);
				}
			}
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select qt from QuittingNotice qt where qt.openTime = ? ");
		List<IQuittingNotice> nextQuittingNoticeList = dao.findByHQL(sql.toString(), objValues.toArray());
		for(IQuittingNotice nextQuittingNotice : nextQuittingNoticeList)
		{
			nextQuittingNotice.setDevStatus(Status.OPENING);
			quittingNoticeService.update(nextQuittingNotice);

			IDevice device = deviceService.get(nextQuittingNotice
					.getDeviceCode());
			device.setStatus(Status.OPENING);
			deviceService.update(device);		

			IXfsStatus status = xfsService.loadXfsStatus(nextQuittingNotice
					.getDeviceCode());
			status.setRunStatus(RunStatus.Unknown);
			xfsService.updateXfsStatus(status);

		}
	}
}
