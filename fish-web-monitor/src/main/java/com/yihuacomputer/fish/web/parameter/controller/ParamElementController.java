package com.yihuacomputer.fish.web.parameter.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.file.INIFileReader;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.parameter.entity.ParamElement;
import com.yihuacomputer.fish.web.parameter.form.AppSystemForm;
import com.yihuacomputer.fish.web.parameter.form.ParamClassifyForm;
import com.yihuacomputer.fish.web.parameter.form.ParamElementForm;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/parameter/element")
@ClassNameDescrible(describle="userlog.ParamElementController")
public class ParamElementController {

	private Logger logger = LoggerFactory.getLogger(ParamElementController.class);

	@Autowired
	private IParamElementService elementService;

	@Autowired
	private IParamClassifyService classifyService;

	@Autowired
	private IAppSystemService appSystemService;

    @Autowired
    protected MessageSource messageSource;


	/**
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request){
		logger.info(String.format("search element : start = %s ,limt = %s ", start, limit));

		IFilter filter = request2filter(request);
		if(request.getParameter("appSystem") ==null || "".equals(request.getParameter("appSystem"))){
			IAppSystem appSystem = appSystemService.get(1);
			filter.eq("paramBelongs",appSystem);
		}
		ModelMap result = new ModelMap();
		IPageResult<IParamElement> pageResult = elementService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("element size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, ParamElementForm.convert(pageResult.list()));
		return result;

	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.ParamElementController.add",hasReqBodyParam=true,reqBodyClass=ParamElementForm.class,bodyProperties="paramName")
	public @ResponseBody
	ModelMap add(@RequestBody ParamElementForm request){
		logger.info("add elementelement");
		ModelMap result=new ModelMap();
		boolean isExist=this.isExistParamName(request.getId(), request.getParamName(), request.getClassifyId(), request.getParamBelongsId());
		if(isExist){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.element.addFailure", null, FishCfg.locale));
		}else{
		IParamElement element =elementService.make();
		IParamClassify classify=classifyService.get(request.getClassifyId());
		IAppSystem appSystem=appSystemService.get(request.getParamBelongsId());
		Date date=new Date();

		element.setParamName(request.getParamName());
		element.setParamValue(request.getParamValue());
		element.setParamType(request.getParamType());
		element.setParamClassify(classify);
		element.setParamRights(request.getParamRights());
		element.setParamBelongs(appSystem);
		element.setRemark(request.getRemark());
		request.setCreateTime(DateUtils.getTimestamp(date));
		element.setCreateTime(DateUtils.getTimestamp(request.getCreateTime()));
		elementService.add(element);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new ParamElementForm(element));
		}
		return result;
	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.ParamElementController.update",hasReqBodyParam=true,reqBodyClass=ParamElementForm.class,bodyProperties="paramName")
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody ParamElementForm request) {
		logger.info("update elemet: elemet.id = " + id);
		request.setId(id);
		ModelMap result = new ModelMap();
		boolean isExist=this.isExistParamName(id, request.getParamName(), request.getClassifyId(), request.getParamBelongsId());
		if(isExist){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.element.addFailure", null, FishCfg.locale));
		}else{
		IParamElement element = elementService.get(id);
	    Date date=new Date();

		IParamClassify classify=classifyService.get(request.getClassifyId());
		element.setParamName(request.getParamName());
		element.setParamValue(request.getParamValue());
		element.setParamType(request.getParamType());
		element.setParamClassify(classify);
		element.setParamRights(request.getParamRights());
		IAppSystem appSystem=appSystemService.get(request.getParamBelongsId());
		element.setParamBelongs(appSystem);
		element.setRemark(request.getRemark());
		request.setLastModifyTime(DateUtils.getTimestamp(date));
		element.setLastModifyTime(DateUtils.getTimestamp(request.getLastModifyTime()));
		elementService.update(element);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new ParamElementForm(element));
		}
		return result;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/elementClassify", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryElementClassify() {
        logger.info("search element : queryClassify");
        ModelMap model = new ModelMap();
        List<IParamClassify> cassifyList = EntityUtils.convert(classifyService.list());
        model.put(FishConstant.DATA, convert1(cassifyList));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

   /**
	 * @return
	 */
   @RequestMapping(value= "/queryAppsystemRadioGroup",method=RequestMethod.GET)
   public @ResponseBody ModelMap queryAppSystem1(){
	   logger.info("search appSystem : queryAppSystem");
	   ModelMap model=new ModelMap();
	   List<IAppSystem> appSystemList = appSystemService.listContainsApp(new Filter());
	   model.put(FishConstant.DATA, appSystemList);
	return model;
   }

   /**
	 * @return
	 */
   @RequestMapping(value= "/queryAppsystem",method=RequestMethod.GET)
   public @ResponseBody ModelMap queryAppSystem2(){
	   logger.info("search appSystem : queryAppSystem");
	   ModelMap model=new ModelMap();
	   List<IAppSystem> appSystemList =appSystemService.list();
	   model.put(FishConstant.DATA, convert2(appSystemList));
	   model.put(FishConstant.SUCCESS,true);
	return model;
   }


    /**
     * @param appSystem
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/import")
	@MethodNameDescrible(describle="userlog.ParamElementController.importFile",hasFaceParam=true,faceParam="appSystemName")
	public @ResponseBody
	String importFile(@RequestParam long appSystem, HttpServletRequest request, HttpServletResponse response) {

	   response.setContentType("text/html;charset=UTF-8");// 解决IE9 上传文件乱码问题
	   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	   List<MultipartFile> files = multipartRequest.getFiles("file");
	   MultipartFile file = files.get(0);
	   if (!file.getOriginalFilename().isEmpty() && file.getSize() > 10485760) {
			return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileSize", null, FishCfg.locale)+"'}";
		}
	   String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
	   if (!file.getOriginalFilename().isEmpty()) {

			try {
				File readFile = new File(FishCfg.getTempDir() + System.getProperty("file.separator")
						+ UUID.randomUUID());
				file.transferTo(readFile);
				/* 读文件内容 */
				ArrayList<IParamElement> paramElementList = new ArrayList<IParamElement>();
				if (".ini".equals(fileType)) {
					INIFileReader iniReader= new INIFileReader(readFile.getAbsolutePath());
					 Map<String,Properties> sectionLevel = iniReader.getIniInfo();
					 Iterator<String> elementTypeItertor = sectionLevel.keySet().iterator();
					 while(elementTypeItertor.hasNext()){
						 //获取参数分类
						 String elementType =  elementTypeItertor.next();
						 logger.debug(elementType);
						 Properties pro = sectionLevel.get(elementType);

						 Set<Entry<Object, Object>> set = pro.entrySet();
						 Iterator<Map.Entry<Object, Object>> it = set.iterator();
						 String key = null, value = null;
			            ParamElement paramElement = null;
			            // 循环取出key-value
			            while (it.hasNext()) {
			          	  paramElement = new ParamElement();
			                Entry<Object, Object> entry = it.next();

			                key = String.valueOf(entry.getKey());
			                value = String.valueOf(entry.getValue());

			                key = key == null ? key : key.trim();
			                value = value == null ? value : value.trim();
			                // 将key-value放入map中
			                paramElement.setParamName(key);
			                paramElement.setParamValue(value);
			                Date date = new Date();
							paramElement.setCreateTime(DateUtils.getTimestamp(DateUtils.getTimestamp(date)));
			                paramElement.setParamTimestamp(Long.parseLong(DateUtils.getTimestamp5(date)));
			                paramElement.setParamType("2");
			                paramElement.setParamRights("1");
			                IParamClassify classify=classifyService.get(elementType);
			                if(classify!=null){
			                	paramElement.setParamClassify(classifyService.get(elementType));
			                }else{
			                	IParamClassify paramClassify =classifyService.make();

			                	if(elementType != null && elementType.length()!=0  ){
			                	paramClassify.setName(elementType);
			                	classifyService.add(paramClassify);
			                	paramElement.setParamClassify(paramClassify);
			                	}
			                	else{
			                		paramElement.setParamClassify(classifyService.get(1));
			                	}
			                }

			                paramElementList.add(paramElement);
			            }
					 }

				}  else {
					return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileType", null, FishCfg.locale)+"'}";
				}
				if (paramElementList != null && !paramElementList.isEmpty()) {
					if (this.check(paramElementList, appSystem)) {
						return "{'success':false,'content':'"+messageSource.getMessage("paramElement.fileComment", null, FishCfg.locale)+"'}";
					} else {
						for (IParamElement item : paramElementList) {
							item.setParamBelongs(appSystemService.get(appSystem));
							elementService.save(item);
						}
					}
				} else {
					return "{'success':false,'content':'"+messageSource.getMessage("vendorCode.fileEmpty", null, FishCfg.locale)+"'}";
				}
			} catch (Exception ex) {
				logger.error(String.format("[%s]", ex));
				return "{'success':false,'content':'"+messageSource.getMessage("paramElement.fileComment", null, FishCfg.locale)+"'}";
			}

	   }else
		{
			return "{'success':false,'content':'"+messageSource.getMessage("paramElement.fileComment", null, FishCfg.locale)+"'}";
		}
		return "{'success':true,'content':'"+messageSource.getMessage("paramElement.fileSuccess", null, FishCfg.locale)+"'}";
   }


	private boolean check(List<IParamElement> list, long appSystem) {
		boolean flag = false;
		List<IParamElement> paramElementList = elementService.getByAppSystem(appSystemService.get(appSystem));
		if (paramElementList.isEmpty()) {
			flag = false;
		} else {
			for (IParamElement item : list) {
				for (IParamElement paramElement : paramElementList) {
					if (item.getParamName().equals(paramElement.getParamName()) && item.getParamClassify().equals(paramElement.getParamClassify()) && item.getParamBelongs().equals(paramElement.getParamBelongs())) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}


	private IFilter request2filter(WebRequest request) {

		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) {
						continue;
					} else if("appSystem".equals(name)){
						IAppSystem appSystem = appSystemService.get(Long.parseLong(request.getParameter(name)));
						filter.eq("paramBelongs",appSystem);
					}
					else if("classifyId".equals(name)) {
						IParamClassify classify = classifyService.get(Long.parseLong(request.getParameter(name)));
						filter.eq("paramClassify", classify);
					} else {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}

		return filter;

	}
	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<ParamClassifyForm> convert1(List<IParamClassify> list) {
		List<ParamClassifyForm> result = new ArrayList<ParamClassifyForm>();
		for (IParamClassify item : list) {
			result.add(new ParamClassifyForm(item));
		}
		return result;
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static List<AppSystemForm> convert2(List<IAppSystem> list){

		List<AppSystemForm> result=new ArrayList<AppSystemForm>();
		for(IAppSystem item: list){
			result.add(new AppSystemForm(item));
		}
		return result;
	}

	private boolean isExistParamName(long id,String name,long classifyId,long paramBelongsId){
		try{
			logger.info("checkParamName" + name);
			IParamElement paramElement =elementService.get(name.trim(), classifyId, paramBelongsId);
			if(paramElement==null){
				return false;
			}
			else if(paramElement.getId()==id){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			logger.error(String.format("Exception is [%s]", e));
			return false;
		}

	}

}
