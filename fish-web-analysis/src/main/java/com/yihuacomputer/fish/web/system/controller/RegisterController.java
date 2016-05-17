package com.yihuacomputer.fish.web.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.domain.util.DBType;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
    private Logger logger = org.slf4j.LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private LocalSessionFactoryBean sf;
    
	@Autowired
	private MessageSource messageSource;
    
    @RequestMapping(value = "/isRegister",method = RequestMethod.POST)
    public @ResponseBody
    ModelMap isRegister(HttpSession session, HttpServletRequest request, WebRequest webrequest) {
        logger.info("is isRegister start.....");
        ModelMap result = new ModelMap();
        if (!new DBType(sf.getHibernateProperties()).isMemDB() && FishCfg.isFishExpiry()) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute("message", messageSource.getMessage("register.regist", null, FishCfg.locale));
        }else{
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        return result;
    }

}
