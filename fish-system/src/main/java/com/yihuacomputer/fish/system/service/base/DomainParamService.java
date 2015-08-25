package com.yihuacomputer.fish.system.service.base;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.system.entity.Param;

public abstract class DomainParamService implements IParamService {

	@Override
	public IParam make() {
		return new Param(this);
	}

	@Override
	public void init() {
		for (IParam item : list()) {
			FishCfg.setFishCfg(item.getParamKey(), item.getParamValue());
		}
	}

	@Override
	public IParam add(String name, String value, String description) {
		IParam param = make();
		param.setParamKey(name);
		param.setParamValue(value);
		param.setDescription(description);
		this.add(param);
        FishCfg.setFishCfg(name, value);
		return param;
	}
	
	@Override
	public void reload() {
		
	}
	
}
