package com.yihuacomputer.fish.web.fault.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.fault.ICaseNotify;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.machine.service.DevicePersonRelation;
import com.yihuacomputer.fish.web.fault.form.CaseNotifyForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/case/caseNotify")
public class CaseNotifyController
{

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CaseNotifyController.class);

    @Autowired
    private ICaseNotifyService service;

    @Autowired
    private DevicePersonRelation devicePersonService;

    /**
     * 根据条件得到短息列表
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req)
    {
        logger.info(String.format("search caseNotify : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        long orgId = userSession.getOrgId();
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name))
            {
                continue;
            }
            else
            {
                if (request.getParameter(name).isEmpty())
                {
                    continue;
                }
                else
                {
                    if ("sort".equals(name))
                    { // 去掉前端页面传来的sort排序字段
                        continue;
                    }
                    else
                    {
                        filter.eq(name, request.getParameter(name));
                    }
                }
            }
        }
        IPageResult<ICaseNotify> pageResult = service.page(start, limit, filter, orgId);
        List<ICaseNotify> list = pageResult.list();
        for (ICaseNotify icn : list)
        {
            List<IPerson> serPersons = devicePersonService.listAdminMaintainPersonByDevice(icn.getTerminalId(), PersonType.FIXMAN);
            icn.setSerPerson(serPersons);
            List<IPerson> bankPersons = devicePersonService.listAdminMaintainPersonByDevice(icn.getTerminalId(), PersonType.MANAGE);
            icn.setBankPerson(bankPersons);
        }
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", CaseNotifyForm.convert(list));
        return result;
    }
}
