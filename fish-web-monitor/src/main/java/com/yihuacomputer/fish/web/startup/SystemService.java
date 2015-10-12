package com.yihuacomputer.fish.web.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.fault.IFaultFilter;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.machine.service.db.AtmModuleService;

/**
 * WEB系统服务
 * @author xuxiang
 *
 */
public class SystemService {

	@Autowired
	private IParamService paramService;

	@Autowired
	private AtmModuleService atmModuleService;

	@Autowired(required=false)
	private IFaultFilter faultFilter;

	@Autowired
	protected FixedLocaleResolver resolver;
	
	/**
	 * 系统启动时执行的操作
	 */
	public void init() {
		paramService.init();
		ViewCfg.initDirs();
		atmModuleService.init();
		if(faultFilter!=null){
			faultFilter.initFilterCode();
		}
		FishCfg.locale=resolver.resolveLocale(null);
	}

}
