package com.yihuacomputer.fish.monitor.entity.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.monitor.entity.report.ClassifyReport;



/**
 * @author YiHua
 *
 */
public class ClassifyModStatusFilter implements IClassifyModStatusFilter{
	private String orgId;
	private String terminalId;
	private List<Long> subOrg;
	private List<String> modDeviceList;
	private List<String> netDeviceList;
	private List<String> boxDeviceList;
	private int modLimit;
	private int netLimit;
	private int boxLimit;


	@Override
	public int getNetLimit() {
		return netLimit;
	}

	@Override
	public void setNetLimit(int netLimit) {
		this.netLimit = netLimit;
	}

	@Override
	public int getBoxLimit() {
		return boxLimit;
	}

	@Override
	public void setBoxLimit(int boxLimit) {
		this.boxLimit = boxLimit;
	}

	@Override
	public List<String> getNetDeviceList() {
		return netDeviceList;
	}

	@Override
	public void setNetDeviceList(List<String> netDeviceList) {
		this.netDeviceList = new ArrayList<String>(netDeviceList);
	}

	@Override
	public List<String> getBoxDeviceList() {
		return boxDeviceList;
	}

	@Override
	public void setBoxDeviceList(List<String> boxDeviceList) {
		this.boxDeviceList = new ArrayList<String>(boxDeviceList);
	}

	@Override
	public void modAddDevice(String terminalId) {
		this.modDeviceList.add(terminalId);
	}

	@Override
	public void modRemoveDevice(String terminalId) {
		this.modDeviceList.remove(terminalId);
	}


	@Override
	public void netAddDevice(String terminalId) {
		this.netDeviceList.add(terminalId);
	}

	@Override
	public void netRemoveDevice(String terminalId) {
		this.netDeviceList.remove(terminalId);
	}


	@Override
	public void boxAddDevice(String terminalId) {
		this.boxDeviceList.add(terminalId);
	}

	@Override
	public void boxRemoveDevice(String terminalId) {
		this.boxDeviceList.remove(terminalId);
	}

	@Override
	public String getOrgId() {
		return this.orgId;
	}

	@Override
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String getTerminalId() {
		return this.terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public List<Long> getSubOrg() {
		return this.subOrg;
	}

	@Override
	public void setSubOrg(List<Long> subOrg) {
		this.subOrg = subOrg;
	}


	@Override
	public List<String> getModDeviceList() {
		return modDeviceList;
	}

	@Override
	public void setModDeviceList(List<String> deviceList) {
		this.modDeviceList = new ArrayList<String>(deviceList);
	}

	@Override
	public int getModLimit() {
		return modLimit;
	}

	@Override
	public void setModLimit(int limit) {
		this.modLimit = limit;
	}

	@Override
	public IClassifyReport filterMod(IDeviceReport deviceReport,MessageSource messageSourceRef) {
		IClassifyReport classifyReport = new ClassifyReport();
		classifyReport.setStatusReport(deviceReport,messageSourceRef);
		IDevice device = deviceReport.getDevice();
		// 设备号不存在
		if (null==subOrg||subOrg.isEmpty()||device == null||deviceReport.getXfsStatus()==null||deviceReport.getXfsStatus().getModStatus()==null) {
			classifyReport.setMethod( ReportMedthod.BEFILTERED);
			classifyReport.setNetMethod(ReportMedthod.BEFILTERED);
			classifyReport.setBoxMethod(ReportMedthod.BEFILTERED);
			return classifyReport;
		}
		//如果设备停用,则停止监控
		if(device.getStatus().equals(DevStatus.DISABLED)){
			if(modDeviceList.contains(device.getTerminalId())){
				classifyReport.setMethod( ReportMedthod.DELETE);
			}
			else{
				classifyReport.setMethod( ReportMedthod.BEFILTERED);
			}
			if(netDeviceList.contains(device.getTerminalId())){
				classifyReport.setNetMethod(ReportMedthod.DELETE);
			}
			else{
				classifyReport.setNetMethod(ReportMedthod.BEFILTERED);
			}
			if(boxDeviceList.contains(device.getTerminalId())){
				classifyReport.setBoxMethod(ReportMedthod.DELETE);
			}
			else{
				classifyReport.setBoxMethod(ReportMedthod.BEFILTERED);
			}
			return classifyReport;
		}
		//机构条件符合
		if(this.subOrg.contains(device.getOrganization().getId())){
			//模块
			if(!deviceReport.getXfsStatus().getModStatus().equals(DeviceStatus.Healthy)){
				if(!modDeviceList.contains(device.getTerminalId())){
					classifyReport.setMethod(ReportMedthod.ADD);
					modAddDevice(device.getTerminalId());
				}
				else{
					classifyReport.setMethod(ReportMedthod.UPDATE);
				}
			}
			else{
				if(modDeviceList.contains(device.getTerminalId())){
					modRemoveDevice(device.getTerminalId());
					classifyReport.setMethod(ReportMedthod.DELETE);
				}
				else{
					classifyReport.setMethod(ReportMedthod.BEFILTERED);
				}
			}
			//网络
			if(!deviceReport.getXfsStatus().getNetStatus().equals(NetStatus.Healthy)){
				if(!netDeviceList.contains(device.getTerminalId())){
					netAddDevice(device.getTerminalId());
					classifyReport.setNetMethod(ReportMedthod.ADD);

				}
				else{
					classifyReport.setNetMethod(ReportMedthod.UPDATE);
				}
			}
			else{
				if(netDeviceList.contains(device.getTerminalId())){
					netRemoveDevice(device.getTerminalId());
					classifyReport.setNetMethod(ReportMedthod.DELETE);
				}
				else{
					classifyReport.setNetMethod(ReportMedthod.BEFILTERED);
				}
			}
			//钞箱
			if(!deviceReport.getXfsStatus().getBoxStatus().equals(BoxStatus.Healthy)&&!deviceReport.getXfsStatus().getBoxStatus().equals(BoxStatus.Unknown)){
				if(!boxDeviceList.contains(device.getTerminalId())){
					boxAddDevice(device.getTerminalId());
					classifyReport.setBoxMethod(ReportMedthod.ADD);
				}
				else{
					classifyReport.setBoxMethod(ReportMedthod.UPDATE);
				}
			}
			else{
				if(boxDeviceList.contains(device.getTerminalId())){
					boxRemoveDevice(device.getTerminalId());
					classifyReport.setBoxMethod(ReportMedthod.DELETE);
				}
				else{
					classifyReport.setBoxMethod(ReportMedthod.BEFILTERED);
				}
			}
			return classifyReport;
		}
		//机构条件不符合
		else{
			if(boxDeviceList.contains(device.getTerminalId())){
				boxRemoveDevice(device.getTerminalId());
				classifyReport.setBoxMethod(ReportMedthod.DELETE);
			}
			else{
				classifyReport.setBoxMethod(ReportMedthod.BEFILTERED);
			}
			if(netDeviceList.contains(device.getTerminalId())){
				netRemoveDevice(device.getTerminalId());
				classifyReport.setNetMethod(ReportMedthod.DELETE);
			}
			else{
				classifyReport.setNetMethod(ReportMedthod.BEFILTERED);
			}
			if(modDeviceList.contains(device.getTerminalId())){
				modRemoveDevice(device.getTerminalId());
				classifyReport.setMethod(ReportMedthod.DELETE);
			}
			else{
				classifyReport.setMethod(ReportMedthod.BEFILTERED);
			}
			return classifyReport;
		}

	}
}
