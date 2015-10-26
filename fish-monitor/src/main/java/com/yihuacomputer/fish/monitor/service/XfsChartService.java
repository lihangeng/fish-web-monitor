package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;

@Service
@Transactional
public class XfsChartService implements IXfsChartService {

	private final static String DEVICERUNINFOBASICHQL="select device,xfs from "+XfsStatus.class.getSimpleName()+" xfs,"+
	Device.class.getSimpleName()+" device  where xfs.terminalId=device.terminalId and device.organization.orgFlag like ? and device.status=?";
	
	@Autowired
	private IGenericDao dao;
	
	public IPageResult<Object> getXfsChartsDetailInfo(int start, int limit,IFilter filter){
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		StringBuffer sb = new StringBuffer(DEVICERUNINFOBASICHQL);
		//监控概况 应用状态 明细
		if(null!=filter.getFilterEntry("appRunInfo")){
			sb.append(" and xfs.runStatus=? ");
			argList.add(filter.getValue("appRunInfo"));
		}
		//监控概况 钞箱状态 明细
		if(null!=filter.getFilterEntry("boxRunInfo")){
			sb.append(" and xfs.boxStatus=? ");
			argList.add(filter.getValue("boxRunInfo"));
		}
		//监控概况 模块状态 明细
		if(null!=filter.getFilterEntry("modRunInfo")){
			sb.append(" and xfs.modStatus=? ");
			argList.add(filter.getValue("modRunInfo"));
		}
		//监控概况 网络状态 明细
		if(null!=filter.getFilterEntry("netRunInfo")){
			sb.append(" and xfs.netStatus=? ");
			argList.add(filter.getValue("netRunInfo"));
		}
		//如果以上几种数据都为空，则是状态总览中异常设备信息
		if(null==filter.getFilterEntry("appRunInfo")&&null==filter.getFilterEntry("boxRunInfo")
				&&null==filter.getFilterEntry("modRunInfo")&&null==filter.getFilterEntry("netRunInfo")){
			sb.append(" and (xfs.netStatus <>? or xfs.modStatus<>? or xfs.boxStatus<>? or xfs.runStatus<>? )");
			argList.add(NetStatus.Healthy);
			argList.add(DeviceStatus.Healthy);
			argList.add(BoxStatus.Healthy);
			argList.add(RunStatus.Healthy);
		}
		@SuppressWarnings("unchecked")
		IPageResult<Object> deviceDetailResult = (IPageResult<Object>) dao.page(start,limit,sb.toString(), argList.toArray());
		return deviceDetailResult;
	}
	
	
	public List<Object> getAllDeviceList(IFilter filter){
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		List<Object> allDeviceList = dao.findByHQL(DEVICERUNINFOBASICHQL, argList.toArray());
		return allDeviceList;
	}
	
	@Override
	public List<Object> getDeviceSummaryRunInfo(IFilter filter) {
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		StringBuffer summaryHealthyBuf = new StringBuffer(DEVICERUNINFOBASICHQL);
		summaryHealthyBuf.append(" and (xfs.runStatus=? or xfs.runStatus=?) and xfs.netStatus=?  ").
		append(" and xfs.boxStatus=? and xfs.modStatus=?  ");
		argList.add(RunStatus.Customer);
		argList.add(RunStatus.Healthy);
		argList.add(NetStatus.Healthy);
		argList.add(BoxStatus.Healthy);
		argList.add(DeviceStatus.Healthy);
		List<Object> healthyDeviceList = dao.findByHQL(summaryHealthyBuf.toString(), argList.toArray());
		return healthyDeviceList;
	}

	@Override
	public List<Object> getDeviceAppRunInfo(IFilter filter) {
		StringBuffer appBuf = new StringBuffer();
		appBuf.append("select count(device),xfs.runStatus from ").append(XfsStatus.class.getSimpleName()).append(" xfs,").
		append(Device.class.getSimpleName()).append(" device ").
		append("where xfs.terminalId=device.terminalId and device.organization.orgFlag like ? and device.status=? group by xfs.runStatus");
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		List<Object> appRunInfoList = dao.findByHQL(appBuf.toString(), argList.toArray());
		return appRunInfoList;
	}

	@Override
	public List<Object> getDeviceBoxRunInfo(IFilter filter) {
		StringBuffer boxBuf = new StringBuffer();
		boxBuf.append("select count(device),xfs.boxStatus from ").append(XfsStatus.class.getSimpleName()).append(" xfs,").
		append(Device.class.getSimpleName()).append(" device ").
		append("where xfs.terminalId=device.terminalId and device.organization.orgFlag like ? and device.status=? group by xfs.boxStatus");
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		List<Object> boxRunInfoList = dao.findByHQL(boxBuf.toString(), argList.toArray());
		return boxRunInfoList;
	}

	@Override
	public List<Object> getDeviceModRunInfo(IFilter filter) {
		StringBuffer modBuf = new StringBuffer();
		modBuf.append("select count(device),xfs.modStatus from ").append(XfsStatus.class.getSimpleName()).append(" xfs,").
		append(Device.class.getSimpleName()).append(" device ").
		append("where xfs.terminalId=device.terminalId and device.organization.orgFlag like ? and device.status=? group by xfs.modStatus");
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		List<Object> modRunInfoList = dao.findByHQL(modBuf.toString(), argList.toArray());
		return modRunInfoList;
	}

	@Override
	public List<Object> getDeviceNetRunInfo(IFilter filter) {
		StringBuffer netBuf = new StringBuffer();
		netBuf.append("select count(device),xfs.netStatus from ").append(XfsStatus.class.getSimpleName()).append(" xfs,").
		append(Device.class.getSimpleName()).append(" device ").
		append("where xfs.terminalId=device.terminalId and device.organization.orgFlag like ? and device.status=? group by xfs.netStatus");
		List<Object> argList = new ArrayList<Object>();
		Object orgFlagObject = filter.getValue("orgFlag");
		argList.add("%"+orgFlagObject);
		argList.add(Status.OPENING);
		List<Object> netRunInfoList = dao.findByHQL(netBuf.toString(), argList.toArray());
		return netRunInfoList;
	}

}
