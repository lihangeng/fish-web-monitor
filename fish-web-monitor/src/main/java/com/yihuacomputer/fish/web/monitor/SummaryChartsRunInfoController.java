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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.ChartFormInfo;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/monitor/summaryInfo")
public class SummaryChartsRunInfoController {
	
	@Autowired
	private IXfsChartService xfsChartService;
	
	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	
	 //获得交易类型
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
        healthyCfi.setDisplayName(getEnumI18n(DeviceStatus.Healthy.getText()));
        healthyCfi.setFilterStr(DeviceStatus.Healthy.name());
        healthyCfi.setNumberInfo(healthyList.size());
        list.add(healthyCfi);
        ChartFormInfo otherCfi = new ChartFormInfo();
        otherCfi.setDisplayName(getEnumI18n(DeviceStatus.Fatal.getText()));
        otherCfi.setFilterStr(DeviceStatus.Fatal.name());
        otherCfi.setNumberInfo(allList.size()-healthyList.size());
        list.add(otherCfi);
        model.put("data", list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
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
            cfi.setDisplayName(getEnumI18n(status.getText()));
            cfi.setFilterStr(status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put("data", list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
    
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
            cfi.setDisplayName(getEnumI18n(status.getText()));
            cfi.setFilterStr(status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put("data", list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
    
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
            cfi.setDisplayName(getEnumI18n(status.getText()));
            cfi.setFilterStr(status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put("data", list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
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
            cfi.setDisplayName(getEnumI18n(status.getText()));
            cfi.setFilterStr(status.name());
            cfi.setNumberInfo(number);
            list.add(cfi);
        }
        model.put("data", list);
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
}
