package com.yihuacomputer.fish.web.monitor;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.yihuacomputer.fish.api.monitor.business.ITransactionColor;
import com.yihuacomputer.fish.api.monitor.business.ITransactionColorService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.monitor.form.TransactionColorForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/monitor/transaction/color")
public class TransactionColorController {

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    private ITransactionColorService transactionColorService;

    private Logger logger = LoggerFactory.getLogger(TransactionColorController.class);

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ModelMap add(@RequestBody TransactionColorForm form, HttpServletRequest request) {
        logger.info("add transaction/color");
        ModelMap model = new ModelMap();

        String userCode = getUserName(request);

        if (!isUnique(form, userCode)) {
            model.addAttribute(FishConstant.SUCCESS, false);
            model.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("monitor.transaction.color.hostRet", null, FishCfg.locale));

            return model;
        }

        ITransactionColor transactionColor = transactionColorService.make();
        form.translate(transactionColor);
        transactionColor.setUserName(userCode);

        transactionColorService.add(transactionColor);

        model.addAttribute(FishConstant.SUCCESS, true);

        return model;
    }

    /**
     * 判断用户下的主机返回码是否唯一
     *
     * @param form
     *            返回码信息
     * @param userCode
     *            用户名
     * @return true唯一,false不唯一
     */
    private boolean isUnique(TransactionColorForm form, String userCode) {

        IFilter filter = new Filter();
        filter.eq("userName", userCode);
        filter.eq("hostRet", form.getHostRet());

        List<ITransactionColor> listColor = transactionColorService.list(filter);

        if (listColor == null || listColor.isEmpty()) {

            return true;
        }

        for (ITransactionColor color : listColor) {
            if (color.getId() == form.getId()) {
                return true;
            }
        }

        return false;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ModelMap delete(@PathVariable long id) {
        logger.info(" delete transaction/color: transaction/color.id = " + id);
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
        if( transactionColorService.get(id) == null){
        	
        	 result.addAttribute(FishConstant.SUCCESS, true);
             result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.delSucess", null, FishCfg.locale));
             return result;
        	
        }
        try {
            transactionColorService.remove(id);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
            logger.error(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody TransactionColorForm request) {
        logger.info("update transaction/color: transaction/color.id = " + id);
        ModelMap model = new ModelMap();
        request.setId(id);
        model.addAttribute(FishConstant.SUCCESS, true);

        ITransactionColor transactionColor = transactionColorService.get(id);

        if (transactionColor == null) {
            model.addAttribute(FishConstant.SUCCESS, false);
            model.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("error.update.noData", null, FishCfg.locale));

            // 验证失败时，需要把正确(数据库)的数据返回
            model.addAttribute(FishConstant.DATA, request);
            return model;
        }

        request.setId(id);
        if (!isUnique(request, transactionColor.getUserName())) {
            model.put(FishConstant.SUCCESS, false);
            model.put(FishConstant.ERROR_MSG, messageSource.getMessage("monitor.transaction.color.hostRet", null, FishCfg.locale));

            // 验证失败时，需要把正确(数据库)的数据返回
            model.addAttribute(FishConstant.DATA, request);
            return model;
        }

        request.translate(transactionColor);

        try {
            transactionColorService.update(transactionColor);
        }
        catch (Exception e) {
            model.put("success", false);
            model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
            
            logger.error(e.getMessage());
        }

        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public @ResponseBody ModelMap list(WebRequest webRequest, HttpServletRequest request) {

        IFilter filter = new Filter();
        filter.eq("userName", getUserName(request));

        ModelMap result = new ModelMap();
        List<ITransactionColor> listResult = transactionColorService.list(filter);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.TOTAL, listResult.size());
        result.addAttribute(FishConstant.DATA, TransactionColorForm.convert(listResult));

        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {
        logger.info(String.format("search transaction/color : start = %s ,limt = %s ", start, limit));
        IFilter filter = request2filter(webRequest);

        filter.eq("userName", getUserName(request));
        ModelMap result = new ModelMap();
        IPageResult<ITransactionColor> pageResult = transactionColorService.page(start, limit, filter);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
        result.addAttribute(FishConstant.DATA, TransactionColorForm.convert(pageResult.list()));
        return result;
    }

    @RequestMapping(value = "/describe", method = RequestMethod.POST)
    public @ResponseBody ModelMap Describe(WebRequest webRequest, HttpServletRequest request,
            @RequestParam String localRet, @RequestParam String hostRet) {

        IFilter filter = new Filter();
        filter.eq("userName", getUserName(request));
        if (!"".equals(hostRet)) {
            filter.eq("hostRet", hostRet);
        }
        if (!"".equals(localRet)) {
            filter.eq("localRet", localRet);
        }

        ModelMap result = new ModelMap();
        List<ITransactionColor> listResult = transactionColorService.list(filter);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.TOTAL, listResult.size());
        result.addAttribute(FishConstant.DATA, TransactionColorForm.convert(listResult));

        return result;
    }

    /**
     * 当前登录用户帐号
     *
     * @param request
     * @return
     */
    private String getUserName(HttpServletRequest request) {
        UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);

        if (userSession == null) {
            return "";
        }

        return userSession.getUserCode();
    }

    private IFilter request2filter(WebRequest request) {
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (isNotFilterName(name)) {
                continue;
            }
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) {
                continue;
            }

            if ("hostRet".equals(name)) {
                filter.like("hostRet", value);
            }
        }

        return filter;
    }

    private boolean isNotFilterName(String name) {
        return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
                || "sort".equals(name);
    }
}
