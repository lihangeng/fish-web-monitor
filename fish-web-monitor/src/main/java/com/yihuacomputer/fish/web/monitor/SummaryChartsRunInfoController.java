package com.yihuacomputer.fish.web.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.ChartFormInfo;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.monitor.form.StatusMonitorForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/monitor/summaryInfo")
public class SummaryChartsRunInfoController {
	
	@Autowired
	private IXfsChartService xfsChartService;

	@Autowired
	private MessageSource messageSourceEnum;
	@Autowired
	private MessageSource messageSource;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    
    //获得状态总览概况
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap detailInfo(@RequestParam int start,@RequestParam int limit,HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<StatusMonitorForm> list = new ArrayList<StatusMonitorForm>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        filter = getFilterByRequest(request,filter);
        IPageResult<Object> result = xfsChartService.getXfsChartsDetailInfo(start,limit,filter);
        if(result.list().size()==0){
            model.put(FishConstant.ERROR_MSG, messageSource.getMessage("monitor.summary.noDate=", null, FishCfg.locale));
            model.put(FishConstant.SUCCESS,false);
            return model;
        }
        for(Object objects:result.list()){
        	Object[] object = (Object[])objects;
        	IDevice device = (IDevice)object[0];
        	IXfsStatus xfsStatus = (IXfsStatus)object[1];
        	StatusMonitorForm smf = new StatusMonitorForm();
        	smf.setAddress(device.getAddress());
        	smf.setCode(device.getTerminalId());
        	smf.setOrg(device.getOrganization().getName());
        	smf.setType(device.getDevType().getName());
        	smf.setSeviceMode(getEnumI18n(device.getWorkType().getText()));
        	smf.setInsideOutside(getEnumI18n(device.getAwayFlag().getText()));
        	smf.setIp(device.getIp().toString());
        	smf.setRunStatus(getEnumI18n(xfsStatus.getRunStatus().getText()));
        	smf.setBoxInitCount(String.valueOf(xfsStatus.getBoxInitCount()));
        	smf.setBoxCurrentCount(String.valueOf(xfsStatus.getBoxCurrentCount()));
        	smf.setModStatus(getEnumI18n(xfsStatus.getModStatus().getText()));
        	smf.setBoxStatus(getEnumI18n(xfsStatus.getBoxStatus().getText()));
        	smf.setNetStatus(getEnumI18n(xfsStatus.getNetStatus().getText()));
        	smf.setCashboxLimit(String.valueOf(device.getCashboxLimit()));
        	smf.setRetainCardCount(String.valueOf(xfsStatus.getStatusIdc().getCards()));
        	smf.setId(device.getId());
        	list.add(smf);
        }
        model.put(FishConstant.TOTAL, result.getTotal());
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
	
	 //获得状态总览概况
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap summaryInfo(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<ChartFormInfo> list = new ArrayList<ChartFormInfo>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        List<Object> allList = xfsChartService.getAllDeviceList(filter);
        List<Object> healthyList = xfsChartService.getDeviceSummaryRunInfo(filter);
        ChartFormInfo healthyCfi = new ChartFormInfo();
        healthyCfi.setDisplayName(getEnumI18n(DeviceStatus.Healthy.getText())+":"+healthyList.size());
        healthyCfi.setFilterStr(DeviceStatus.Healthy.name());
        healthyCfi.setColor(DeviceStatus.Healthy.getColor());
        healthyCfi.setNumberInfo(healthyList.size());
        list.add(healthyCfi);
        ChartFormInfo otherCfi = new ChartFormInfo();
        otherCfi.setFilterStr(DeviceStatus.Fatal.name());
        otherCfi.setColor(DeviceStatus.Fatal.getColor());
        otherCfi.setNumberInfo(allList.size()-healthyList.size());
        otherCfi.setDisplayName(getEnumI18n(DeviceStatus.Fatal.getText())+":"+otherCfi.getNumberInfo());
        list.add(otherCfi);
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
    //运行状态概览
    @RequestMapping(value = "/runSummary", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap runSummaryInfo(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<ChartFormInfo> list = new ArrayList<ChartFormInfo>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        
        List<Object> runList = xfsChartService.getDeviceAppRunInfo(filter);
        for(Object obj:runList){
        	Object result[]=(Object[]) obj;
            ChartFormInfo cfi = new ChartFormInfo();
            int number = Integer.parseInt(String.valueOf(result[0]));
            RunStatus status = (RunStatus)result[1];
            cfi.setDisplayName(getEnumI18n(status.getText())+":"+number);
            cfi.setFilterStr(RunStatus.class.getSimpleName()+"."+status.name());
            cfi.setColor(status.getColor());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    //网络状态概览
    @RequestMapping(value = "/netSummary", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap netSummaryInfo(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<ChartFormInfo> list = new ArrayList<ChartFormInfo>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        
        List<Object> runList = xfsChartService.getDeviceNetRunInfo(filter);
        for(Object obj:runList){
        	Object result[]=(Object[]) obj;
            ChartFormInfo cfi = new ChartFormInfo();
            int number = Integer.parseInt(String.valueOf(result[0]));
            NetStatus status = (NetStatus)result[1];
            cfi.setDisplayName(getEnumI18n(status.getText())+":"+number);
            cfi.setFilterStr(NetStatus.class.getSimpleName()+"."+status.name());
            cfi.setColor(status.getColor());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    //模块状态概览
    @RequestMapping(value = "/modSummary", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap modSummaryInfo(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<ChartFormInfo> list = new ArrayList<ChartFormInfo>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        
        List<Object> runList = xfsChartService.getDeviceModRunInfo(filter);
        for(Object obj:runList){
        	Object result[]=(Object[]) obj;
            ChartFormInfo cfi = new ChartFormInfo();
            int number = Integer.parseInt(String.valueOf(result[0]));
            DeviceStatus status = (DeviceStatus)result[1];
            cfi.setDisplayName(getEnumI18n(status.getText())+":"+number);
            cfi.setColor(status.getColor());
            cfi.setFilterStr(DeviceStatus.class.getSimpleName()+"."+status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    //钞箱状态概览
    @RequestMapping(value = "/boxSummary", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap boxSummaryInfo(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        UserSession session = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
        List<ChartFormInfo> list = new ArrayList<ChartFormInfo>();
        IFilter filter = new Filter();
        filter.eq("orgFlag", session.getOrgFlag());
        
        List<Object> runList = xfsChartService.getDeviceBoxRunInfo(filter);
        for(Object obj:runList){
        	Object result[]=(Object[]) obj;
            ChartFormInfo cfi = new ChartFormInfo();
            int number = Integer.parseInt(String.valueOf(result[0]));
            BoxStatus status = (BoxStatus)result[1];
            cfi.setDisplayName(getEnumI18n(status.getText())+":"+number);
            cfi.setColor(status.getColor());
            cfi.setFilterStr(BoxStatus.class.getSimpleName()+"."+status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put(FishConstant.DATA, list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    /**
     * 展示设备详情的条件
     * @param request
     * @param filter
     * @return
     */
    private IFilter getFilterByRequest(HttpServletRequest request, IFilter filter){
    	String args = request.getParameter("args");
    	String []argslipt =  args.split("\\.");
    	if(args.startsWith(getEnumI18n(DeviceStatus.Healthy.name()))){
    		filter.eq("netRunInfo", NetStatus.Healthy);
    		filter.eq("modRunInfo", DeviceStatus.Healthy);
    		filter.eq("boxRunInfo", BoxStatus.Healthy);
    		filter.eq("appRunInfo", RunStatus.Healthy);
    	}
    	else if(args.startsWith(RunStatus.class.getSimpleName())){
    		filter.eq("appRunInfo", RunStatus.valueOf(RunStatus.class, argslipt[1]));
    	}
    	else if(args.startsWith(NetStatus.class.getSimpleName())){
    		filter.eq("netRunInfo", NetStatus.valueOf(NetStatus.class, argslipt[1]));
    	}
    	else if(args.startsWith(DeviceStatus.class.getSimpleName())){
    		filter.eq("modRunInfo", DeviceStatus.valueOf(DeviceStatus.class, argslipt[1]));
    	}
    	else if(args.startsWith(BoxStatus.class.getSimpleName())){
    		filter.eq("boxRunInfo", BoxStatus.valueOf(BoxStatus.class, argslipt[1]));
    	}
    	return filter;
    }
}
