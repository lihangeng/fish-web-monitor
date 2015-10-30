package com.yihuacomputer.fish.web.monitor;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.monitor.form.HostRetForm;
import com.yihuacomputer.fish.web.monitor.form.TransTypeForm;
import com.yihuacomputer.fish.web.monitor.form.TransactionForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/msg/transaction")
public class TransactionHisController {

    @Autowired
    private ITransactionService transService;

    @Autowired
    private ITransTypeService transTypeService;

    @Autowired
    private IDeviceService deviceService;
    
	@Autowired
	private MessageSource messageSource;

    //获得交易类型
    @RequestMapping(value = "/queryTransType", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap queryAtmType() {
        ModelMap model = new ModelMap();
        model.put("data", TransTypeForm.convert(transTypeService.list(new Filter())));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    //获得交易主机返回码
    @RequestMapping(value = "/queryHostRet", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap queryHostRet() {
        ModelMap model = new ModelMap();
        model.put("data", HostRetForm.convert(transService.listHostRet()));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    /**
     * 查询历史交易信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap queryHistoryTrans(@RequestParam int start,
            @RequestParam int limit, WebRequest webRequest,HttpServletRequest request)
    {
    	ModelMap result = new ModelMap();
        String terminalId = request.getParameter("terminalId");
        UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
        String orgFlag = userSession.getOrgFlag();
        IDevice device = deviceService.get(terminalId);
        if(device == null){
        	return makeErrorPage(result,String.format(messageSource.getMessage("transactionHis.termNotExist", null, FishCfg.locale),terminalId));
        }

        if(device.getOrganization().getOrgFlag().contains(orgFlag)){
            IPageResult<ITransaction> pageResultTransList = null;
            IFilter filter = request2filter(webRequest,"transaction.");
            if(request.getParameter("blacklist")==null || request.getParameter("blacklist").equals("0")){
            	filter.descOrder("dateTime");
                pageResultTransList = transService.page(start,limit,filter);
            }else if(request.getParameter("blacklist").equals("1")){
            	filter.descOrder("transaction.dateTime");
                pageResultTransList = transService.pageBlackList(start,limit, request2filter(webRequest,"transaction."),Long.valueOf(request.getParameter("organizationId")));
            }else{
            	filter.descOrder("transaction.dateTime");
                pageResultTransList = transService.pageNoBlackList(start,limit, request2filter(webRequest,"transaction."),Long.valueOf(request.getParameter("organizationId")));
            }
            result.put(FishConstant.SUCCESS, true);
            result.put("total", pageResultTransList.getTotal());
            result.put("data", TransactionForm.convert(pageResultTransList.list()));
            return result;
        }else{
        	return makeErrorPage(result,String.format(messageSource.getMessage("transactionHis.termRight", null, FishCfg.locale),terminalId));
        }


    }

    private ModelMap makeErrorPage(ModelMap result,String reasson){
    	result.put(FishConstant.SUCCESS, true);
        result.put("total", 1);
        TransactionForm msg = new TransactionForm();
        msg.setTermId(reasson);
        List<TransactionForm> forms = new ArrayList<TransactionForm>();
        forms.add(msg);
        result.put("data", forms);
    	return result;
    }

    private IFilter request2filter(WebRequest request,String prefix)
    {
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (isNotFilterName(name))
            {
                continue;
            }
            String value = request.getParameter(name);
            if (value.isEmpty())
            {
                continue;
            }
            if ("startAmt".equals(name))
            {
                filter.ge(prefix + "amt", Double.parseDouble(value));
                continue;
            }
            if ("endAmt".equals(name))
            {
                filter.le(prefix + "amt", Double.parseDouble(value));
                continue;
            }
            if ("startDateTime".equals(name))
            {
                int startDate = Integer.parseInt(value.replaceAll("-", ""));
            	filter.ge(prefix + "transDate",startDate);
                continue;
            }
            if ("endDateTime".equals(name))
            {
            	int endDate = Integer.parseInt(value.replaceAll("-", ""));
            	filter.le(prefix + "transDate",endDate);
                continue;
            }
            if ("transCode".equals(name))
            {
                filter.eq(prefix + name,value);
                continue;
            }
            if ("terminalId".equals(name))
            {
                filter.eq(prefix + name,value);
                continue;
            }

            filter.eq(prefix + name, value);
        }
        return filter;
    }

    private boolean isNotFilterName(String name)
    {
        return "page".equals(name) || "start".equals(name)
                || "limit".equals(name) || "_dc".equals(name)|| "sort".equals(name)
                || "blacklist".equals(name)|| "organizationId".equals(name);
    }
}
