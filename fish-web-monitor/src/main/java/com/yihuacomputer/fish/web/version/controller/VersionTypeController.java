/**
 *
 */
package com.yihuacomputer.fish.web.version.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
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
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.web.util.FishWebUtils;
import com.yihuacomputer.fish.web.version.form.VersionTypeForm;
import com.yihuacomputer.fish.web.version.form.VersionTypeTreeForm;

/**
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/version/versionType")
@ClassNameDescrible(describle="userlog.VersionTypeController")
public class VersionTypeController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(VersionTypeController.class);

	@Autowired
	private IVersionTypeService versionTypeService;
	
	@Autowired
	private IVersionTypeAtmTypeRelationService relationService;
	
	@Autowired
	private IAtmTypeService atmTypeService;
	

    @Autowired
    private MessageSource messageSourceVersion;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search versionType : start = %s ,limt = %s ", start, limit));
		IFilter filter = getFilter(request);
		IPageResult<IVersionType> pageResult = versionTypeService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toForm(pageResult.list()));
		return result;
	}

	/**
	 * 软件分类树的显示
	 * @param node
	 * @return
	 */
	@RequestMapping(value = "/tree",method = RequestMethod.GET)
	public @ResponseBody List<VersionTypeTreeForm> tree(@RequestParam String node) {
		List<VersionTypeTreeForm> forms = new ArrayList<VersionTypeTreeForm>();
		List<IVersionType> lists = versionTypeService.list(new Filter());
		for(IVersionType type : lists){
			forms.add(new VersionTypeTreeForm(type));
		}
		return forms;
	}

	/**
	 * 增加版本分类信息
	 * @param form
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionTypeController.add",hasReqBodyParam=true,reqBodyClass=VersionTypeForm.class,bodyProperties="typeName")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap add(@RequestBody VersionTypeForm form) {
		logger.info(" add versionType...");
		ModelMap result = new ModelMap();
		String typeName = form.getTypeName();
	    IVersionType db= versionTypeService.getByName(typeName);
		if(db != null){
		    result.addAttribute(FishConstant.SUCCESS, false);

    		String tips = messageSourceVersion.getMessage("versionType.addSameVersionType", null, FishCfg.locale);
		    result.addAttribute(FishConstant.ERROR_MSG,String.format(tips,typeName));
//		    result.addAttribute(FishConstant.ERROR_MSG,String.format("增加失败,软件分类[%s]已存在",typeName));
		    return result;
		}

		IVersionType type = versionTypeService.make();
		type.setTypeName(form.getTypeName());
		type.setDefaultInstallPath(form.getDefaultInstallPath());
		type.setAutoDeploy(form.isAutoDeploy());
		type.setVersionCatalog(VersionCatalog.OTHER);
        type.setDesc(form.getDesc());
		versionTypeService.add(type);
		relationService.link(type.getId(), form.getAtmTypes());
		
		/*回填值到form中*/
		form.setId(type.getId());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", form);
		return result;
	}

	/**
	 * 得到某条软件分类的信息
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryOne(@PathVariable long id) {
        logger.info(" query one versionType : versionType.id = " + id);
        ModelMap result = new ModelMap();
        IVersionType type = versionTypeService.getById(id);
        if(type == null)
		{
			result.addAttribute(FishConstant.SUCCESS,false);
    		String tips = messageSourceVersion.getMessage("versionType.versionTypeNotExist", null, FishCfg.locale);
//			result.addAttribute("errorMsg","更改的记录不存在，请刷新后操作");
			result.addAttribute(FishConstant.ERROR_MSG,tips);
			return result;
		}
        result.addAttribute("data", new VersionTypeForm(type));
        return result;
    }

	@MethodNameDescrible(describle="userlog.VersionTypeController.update",hasReqBodyParam=true,reqBodyClass=VersionTypeForm.class,bodyProperties="typeName")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ModelMap update(@PathVariable long id,@RequestBody VersionTypeForm form) {
		logger.info(" update versionType : versionType.id = " + form.getId());
		form.setId(id);
		ModelMap result = new ModelMap();
		try{
			IVersionType type = versionTypeService.getById(id);
			if(type == null)
			{
				result.addAttribute(FishConstant.SUCCESS,false);
				String tips = messageSourceVersion.getMessage("versionType.versionTypeNotExist", null, FishCfg.locale);
				result.addAttribute(FishConstant.ERROR_MSG,tips);
				return result;
			}
			type.setTypeName(form.getTypeName());
			type.setDesc(form.getDesc());
			type.setAutoDeploy(form.isAutoDeploy());
			type.setDefaultInstallPath(form.getDefaultInstallPath());
			
//			if(!cashType.isEmpty()){
//	            type.addVersionTypeRestriction(RestrictionColumn.CASH_TYPE, cashType);
//	        }else{
//	            type.removeVersionTypeRestriction(RestrictionColumn.CASH_TYPE, type.getRestrictionValue(RestrictionColumn.CASH_TYPE));
//	        }
	      /*  if(!awayFlag.equals("")){
	            type.addVersionTypeRestriction(RestrictionColumn.AWAY_FLAG, awayFlag);
	        }else{

	        }*/
			versionTypeService.update(type);
			List<Long> atmTypes = form.getAtmTypes();
			List<Long> relationedAtmTypeIdsInDB = relationService.findAtmTypeIds(id);
			List<Long> atmTypesNew = new ArrayList<Long>(atmTypes);
			List<Long> relationedAtmTypeIdsInDBRemoved = new ArrayList<Long>(relationedAtmTypeIdsInDB);
			//剩余的元素
			atmTypesNew.removeAll(relationedAtmTypeIdsInDB);
			relationService.link(id, atmTypesNew);
			relationedAtmTypeIdsInDBRemoved.removeAll(atmTypes);
			for(Long removedId : relationedAtmTypeIdsInDBRemoved){
				relationService.unlink(id, removedId);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
		}catch(Exception ex){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG,ex.getMessage());
		}
		return result;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionTypeController.delete",hasLogKey=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete versionType: versionType.id = " + id);
		ModelMap result = new ModelMap();
		IVersionType versionType = versionTypeService.getById(id);
		if(versionType == null){
		    result.addAttribute(FishConstant.SUCCESS, true);
            return result;
		}
		result.addAttribute(FishConstant.LOG_KEY, versionType.getTypeName());
		try {
			versionTypeService.delete(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			String tips = messageSourceVersion.getMessage("versionType.deleteFail", new Object[]{ex.getMessage()}, FishCfg.locale);
//			result.addAttribute(FishConstant.ERROR_MSG,"删除失败：" + ex.getMessage());
			result.addAttribute(FishConstant.ERROR_MSG,tips);
		}
		return result;
	}

	/**
	 * 转换数据格式
	 * @param types
	 * @return
	 */
	private List<VersionTypeForm> toForm(List<IVersionType> types){
		List<VersionTypeForm> forms = new ArrayList<VersionTypeForm>();
		for(IVersionType type : types){
			forms.add(new VersionTypeForm(type));
		}
		return forms;
	}

	/**
	 * 获得查询条件
	 * @param request
	 * @return
	 */
	private IFilter getFilter(WebRequest request){
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else{
				filter.like(name, request.getParameter(name));
			}
		}
		return filter;
	}

	/**
	 * 获取编辑页面版本类型的combobox的数据
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/combo")
	public @ResponseBody ModelMap combo() {
		logger.info(" combo versionType ...");
		List<IVersionType> types = versionTypeService.list(new Filter());
		ModelMap result = new ModelMap();
		result.addAttribute("data", types);
		return result;
	}

	/**
	 * 获取查询页面版本类型的combobox的数据
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value="/searchCombo")
	public @ResponseBody ModelMap searchCombo() {
		logger.info(" searchCombo versionType ...");
		List<IVersionType> types = versionTypeService.listContainsAdvert(new Filter());
		ModelMap result = new ModelMap();
		result.addAttribute("data", types);
		return result;
	}
	
	/**
	 * 获取版本分类关联的设备型号信息
	 * @param versionTypeId
	 * @return
	 * @since 2.0.0.0
	 */
	@RequestMapping(method = RequestMethod.POST,value="/atmType")
	public @ResponseBody ModelMap findAtmTypes(@RequestParam int versionTypeId) {
		logger.info(String.format("findAtmTypes : versionTypeId = %s", versionTypeId));
		ModelMap result = new ModelMap();
		String data = "";
		List<IAtmType> atmTypes = atmTypeService.list();
		if(versionTypeId == 0){
			data = toAtmTypeForm(atmTypes,null);
		}else{
			data = toAtmTypeForm(atmTypes,relationService.findAtmTypes(versionTypeId));
		}
		
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", data);
		return result;
	}

	private String toAtmTypeForm(List<IAtmType> atmTypes,List<IAtmType> checkeds) {
		StringBuffer result = new StringBuffer("");
		int length = atmTypes.size();
		Map<Long,IAtmType> atmTypeMaps = new HashMap<Long,IAtmType>();
		if(checkeds != null){
			for(IAtmType atmType : checkeds){
				atmTypeMaps.put(atmType.getId(), atmType);
			}
		}
		for(int i = 0 ; i < length;i++ ){
			IAtmType atmType = atmTypes.get(i);
			result.append(",");
			result.append("{boxLabel:'").append(atmType.getName()).append("',");
			result.append("name:'atmTypes',");
			if(checkeds != null){
				IAtmType checked = atmTypeMaps.get(atmType.getId());
				if(checked != null){
					result.append("checked:true,");
				}
			}
			result.append("inputValue:").append(atmType.getId()).append("}");
		}
		
		if (result.length() != 0) {
			result.deleteCharAt(0);
			result.insert(0, "[");
			result.append("]");
		}else{
			result.append("[]");
		}
		return result.toString();
	}


}
