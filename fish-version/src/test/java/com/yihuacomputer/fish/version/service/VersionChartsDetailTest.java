package com.yihuacomputer.fish.version.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelation;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.VersionChartsDetailForm;
import com.yihuacomputer.fish.version.MySqlTestConfig;
import com.yihuacomputer.fish.version.service.db.VersionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySqlTestConfig.class})
public class VersionChartsDetailTest {
	
	@Autowired
	private IVersionStaticsStautsService versionStaticsStautsService;
	@Autowired
	private IVersionTypeAtmTypeRelationService versionTypeAtmTypeRelationService;
	@Autowired
	private IVersionService versionService;
	
//	@Test
	public void test(){
		IFilter filter = new Filter();
		filter.eq("orgFlag", "-1");
		List<Long> list = versionTypeAtmTypeRelationService.findAtmTypeIds(2l);
		list.add(1l);
		list.add(2l);
		filter.in("devType", list);
		IPageResult<VersionChartsDetailForm> page = versionStaticsStautsService.getVersionChartsDetailForm(0,10,filter);
		System.out.println(JsonUtils.toJson(page));
	}
	@Test
	public void getTotalDeviceNumber(){
		versionStaticsStautsService.getOpeningDeviceTotal("%-1", 1l);
	}
	
//	@Test
	public void getDevTypeByVersionId(){
		List<IVersion> list = versionService.list(new Filter());
		for(IVersion version :list){
			List<Long> atmTypeList = versionStaticsStautsService.getAtmTypeIdsByVersionId(version.getId());
			if(null==atmTypeList||atmTypeList.size()==0){
				System.out.println(version.getId()+"<==>"+null);
			}
			else{
				System.out.println(version.getId()+"<==>"+JsonUtils.toJson(atmTypeList));
			}
		}
		
	}
}