package com.yihuacomputer.fish.web.system.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.util.RegisterInfo;
import com.yihuacomputer.common.util.RegisterResult;
import com.yihuacomputer.common.util.SystemRegisterUtil;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/system/register")
@ClassNameDescrible(describle="userlog.SystemRegisterController")
public class SystemRegisterController {

	@Autowired
	private IParamService paramService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Logger logger = LoggerFactory.getLogger(SystemRegisterController.class);
	
	/**
	 * @param request
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.SystemRegisterController.register",hasArgs=false)
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ModelMap register(WebRequest request){
		ModelMap map = new ModelMap();
		if("".equals(request.getParameter("key")) || null == request.getParameter("key")){
			map.addAttribute("isRegister", false);
			map.addAttribute("message", messageSource.getMessage("system.register.noSerialNo", null, FishCfg.locale));
			return map;
		}
		if("".equals(request.getParameter("serial")) || null == request.getParameter("serial")){
			map.addAttribute("isRegister", false);
			map.addAttribute("message", messageSource.getMessage("system.register.noRegisterNo", null, FishCfg.locale));
			return map;
		}
		String key = request.getParameter("key");
		String serial = request.getParameter("serial");
		RegisterResult result = SystemRegisterUtil.isOk(key, serial);
		boolean success = result.isSuccess();
		if(success){
			IParam param = paramService.getParam("register_serial");
			if(param != null){
				param.setParamValue(serial);
				paramService.update(param);
			}
			else {
				paramService.add("register_serial",serial,messageSource.getMessage("system.register.registerNo", null, FishCfg.locale));
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			RegisterInfo bean = SystemRegisterUtil.analys(serial);
			String startDate = format.format(bean.getStartDate().getTime());
			String endDate	= format.format(bean.getEndDate().getTime());
			int days = bean.getDisDay();
			map.addAttribute("serial",serial);
			map.addAttribute("startDate",startDate);
			map.addAttribute("endDate",endDate);
			if(days == 9999){
				map.addAttribute("type", "1");
			}
			else {
				map.addAttribute("type", "0");
			}

		}else{
			logger.error(result.getMessage());
			map.addAttribute("message", messageSource.getMessage("system.register.registerFail", null, FishCfg.locale));
		}
		//注册成功就直接跳转到index（成功登陆系统）页面。
		map.addAttribute(FishConstant.SUCCESS, success);
		return map;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ModelMap getHardWareInfo(HttpServletRequest request){
		ModelMap map = new ModelMap();
		map.addAttribute("key",SystemRegisterUtil.convertKey(getRegSn()));
		map.addAttribute(FishConstant.SUCCESS, true);
		return map;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	public @ResponseBody ModelMap getSystemRegisterInfo(HttpServletRequest request){
		ModelMap map = new ModelMap();
		IParam serialParam = paramService.getParam("register_serial");
		if(serialParam == null){
			map.addAttribute(FishConstant.SUCCESS, false);
			return map;
		}
		map.addAttribute(FishConstant.SUCCESS, true);
		RegisterInfo regInfo = SystemRegisterUtil.analys(serialParam.getParamValue());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = format.format(regInfo.getStartDate().getTime());
		String endDate	= format.format(regInfo.getEndDate().getTime());
		int days = regInfo.getDisDay();
		map.addAttribute("serial", serialParam.getParamValue());
		map.addAttribute("startDate",startDate);
		map.addAttribute("endDate",endDate);
		if(days == 9999){
			map.addAttribute("type", "1");
		}
		else {
			map.addAttribute("type", "0");
		}
		return map;
	}

	/**
	 * Get System Reg SN.
	 * @return
	 */
	private String getRegSn(){

		String sn=System.getProperty("os.version")+System.getProperty("user.name")+System.getProperty("java.version");

		StringBuffer key = new StringBuffer();
		byte[] src =sn.getBytes();
		for(int idx=0;idx<src.length/2;idx++){
			key.append(src[idx]);
			key.append(src[src.length-1-idx]);
		}
		return key.toString();

	}
}


