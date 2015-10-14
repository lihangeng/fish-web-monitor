package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;

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
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.system.H2TestConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class ParamServiceTest extends BindSessionInTest2 {

	@Autowired
	private IParamService paramService;

	@Test
	public void test(){
		IParam fileParam = paramService.make();
		fileParam.setParamKey("fish_home");
		fileParam.setParamValue("/home/fish/server_file");
		fileParam.setDescription("系统文件根路径");
		paramService.add(fileParam);

		IParam serviceParam = paramService.make();
		serviceParam.setParamKey("atm_port");
		serviceParam.setParamValue("8080");
		serviceParam.setDescription("服务端口");
		paramService.add(serviceParam);

		IParam param = paramService.make();
		param.setParamKey("fish_web");
		param.setParamValue("F:\\workspaces\\fish\\fish_web");
		param.setDescription("fish_web路径");
		paramService.add(param);

		IParam getParam = paramService.getParam("fish_home");
		assertEquals("/home/fish/server_file",getParam.getParamValue());

		IParam getParamById = paramService.get(fileParam.getId());
		assertEquals("fish_home",getParamById.getParamKey());

		Iterable<IParam> lists = paramService.list();
		List<IParam> entities = new ArrayList<IParam>();
		for(IParam item : lists){
			entities.add(item);
		}
		assertEquals(3,entities.size());

		IFilter filter = new Filter();
		filter.like("paramValue", "fish_web");

		Iterable<IParam> paramList = paramService.list(filter);
		List<IParam> params = EntityUtils.convert(paramList);
		assertEquals(1,params.size());

		IPageResult<IParam> page = paramService.page(0, 0, filter);
		assertEquals(1,page.getTotal());

		param.setParamKey("fish-web-person");
		paramService.update(param);
		assertEquals("fish_web路径",paramService.getParam("fish-web-person").getDescription());

		paramService.remove(fileParam.getId());
		Iterable<IParam> items =paramService.list();
		List<IParam> newList = new ArrayList<IParam>();
		for(IParam item : items){
			newList.add(item);
		}
		assertEquals(2,newList.size());

		paramService.remove(serviceParam.getParamKey());
		Iterable<IParam> result =paramService.list();
		List<IParam> newParam = new ArrayList<IParam>();
		for(IParam item : result){
			newParam.add(item);
		}
		assertEquals(1,newParam.size());
	}
}
