package com.yihuacomputer.fish.web.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.ScreenForm;
import com.yihuacomputer.fish.web.command.format.ScreenSaveForm;
import com.yihuacomputer.fish.web.command.format.ScreenType;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 远程截屏
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent/screenshot")
public class ScreenShotController {
	private Logger logger = LoggerFactory.getLogger(ScreenShotController.class);

	/**
	 * 截屏
	 */
	private final String CAPTURE_PATH = "/ctr/capture";

	/**
	 * 停止录制
	 */
	private final String STOP_CAMERA_PATH = "/ctr/stopcamera";

	/**
	 * 开始录制
	 */
	private final String START_CAMMERA_PATH = "/ctr/startcamera";

	private Map<String, ScreenForm> cameraMap = new HashMap<String, ScreenForm>();

	private List<ScreenForm> historyCamera = new ArrayList<ScreenForm>();



	/**
	 * 截取所有将屏幕的图片
	 * @param ip    ip地址
	 * @param code   设备编号
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/custom")
	public @ResponseBody
	ModelMap screenShot(@RequestParam String ip, @RequestParam String code, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		String url = MonitorCfg.getHttpUrl(ip) + CAPTURE_PATH;
		try {
			ScreenForm screen = (ScreenForm) HttpProxy.httpGet(url, ScreenForm.class);
			String path = FishCfg.getTempDir();
			//String[] fileNames = screen.getNames();
			ScreenSaveForm saveForm = null;
			HttpFileCfg httpFileCfg = new HttpFileCfg();
			httpFileCfg.setCompress(true);
			String screenShotTime = DateUtils.getTimestamp(new Date());
			List<ScreenSaveForm> list = new ArrayList<ScreenSaveForm>();
			for(String fileName:screen.getNames()){
				int index = fileName.lastIndexOf("/");
				String filePathClient = fileName.substring(0, index);
				String fileNameClient = fileName.substring(index + 1);

				httpFileCfg.setRequestPath(filePathClient);
				httpFileCfg.setRequestName(fileNameClient);

//				String name = DateUtils.getTimestamp(date).replace("-", "").replace(":", "").replace(" ", "");
				httpFileCfg.setLocalName(code + "_" + fileNameClient);
				httpFileCfg.setLocalPath(path);
				httpFileCfg.setPort(MonitorCfg.getRemotePort());
				httpFileCfg.setIpAdd(ip);
				HttpFileClient.downloadFile(httpFileCfg);

				saveForm = new ScreenSaveForm();
				saveForm.setScreenShotTime(screenShotTime);
				saveForm.setFileNameClient(httpFileCfg.getLocalName());
				saveForm.setFilePathClient(httpFileCfg.getLocalPath());
				String allPath = httpFileCfg.getLocalPath() + FishCfg.fileSep + httpFileCfg.getLocalName();
				saveForm.setAllPath(allPath);
				list.add(saveForm);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "截屏失败.");
			return result;
		}

	}

	/**
	 * 截屏并直接显示在页面上
	 * @param ip  ip地址
	 * @param code  设备号
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/image")
	public @ResponseBody
	ModelMap image(@RequestParam String ip, @RequestParam String code, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath);
		if(!targetDir.exists()){
			targetDir.mkdir();
		}else{
			this.delAllFile(contextPath);
		}

		String url = MonitorCfg.getHttpUrl(ip) + CAPTURE_PATH;
		try {
			ScreenForm screen = (ScreenForm) HttpProxy.httpGet(url, ScreenForm.class);
			ScreenSaveForm saveForm = null;
			HttpFileCfg httpFileCfg = new HttpFileCfg();
			httpFileCfg.setCompress(true);
			List<ScreenSaveForm> list = new ArrayList<ScreenSaveForm>();
			for(String fileName:screen.getNames()){
				int index = fileName.lastIndexOf("/");
				String filePathClient = fileName.substring(0, index);
				String fileNameClient = fileName.substring(index + 1);

				httpFileCfg.setRequestPath(filePathClient);
				httpFileCfg.setRequestName(fileNameClient);

				httpFileCfg.setLocalName(code + "_" + fileNameClient);
				httpFileCfg.setLocalPath(contextPath);
				httpFileCfg.setPort(MonitorCfg.getRemotePort());
				httpFileCfg.setIpAdd(ip);
				HttpFileClient.downloadFile(httpFileCfg);

				saveForm = new ScreenSaveForm();
				saveForm.setSrc("tmp/screenShot/"+code+"_"+fileNameClient);
				list.add(saveForm);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "远程抓屏失败.");
			return result;
		}

	}

	private String getRealPath(HttpServletRequest request){
	    return FishWebUtils.getRealPathByTmp(request)+File.separator+"screenShot";
	}

	private  boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             flag = true;
	          }
	       }
	       return flag;
	     }


	@RequestMapping(method = RequestMethod.GET, value = "/downloadScreen")
	public @ResponseBody
	void downloadScreen(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = new File(path);

		download(file, response, "gb2312", "image/jpeg");

	}

	/**
	 * 开始录制屏幕
	 *
	 * @param monitorType
	 *            屏幕类型
	 * @param request
	 *            请求对象
	 * @return　
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/startCamera")
	public @ResponseBody
	ModelMap startCamera(@RequestParam ScreenType monitorType, @RequestParam String terminalId, @RequestParam String userId, HttpServletRequest request) {
		logger.info(String.format("startCamera(monitoryType = %s, terminalId = %s)", monitorType, terminalId));
		ModelMap result = new ModelMap();
		ScreenForm screenForm = new ScreenForm();
		String ipaddress = request.getParameter("ip");
		String url = MonitorCfg.getHttpUrl(ipaddress) + START_CAMMERA_PATH;

		String code = String.valueOf(new Date().getTime());
		screenForm.setUserId(userId);
		screenForm.setMonitorType(monitorType);
		ScreenForm screenFormResult = (ScreenForm) HttpProxy.httpPost(url, screenForm, ScreenForm.class);

		// 设备开始录制时间
		screenFormResult.setStartCameraDate(DateUtils.getTimestamp(new Date()));
		screenFormResult.setUserId(userId);
		screenFormResult.setTerminalId(terminalId);
		screenFormResult.setIpAddress(ipaddress);
		if (!("2".equals(screenFormResult.getStatus()) || "5".equals(screenFormResult.getStatus()))) {
			Set<Entry<String, ScreenForm>> set = cameraMap.entrySet();
			for (Entry<String, ScreenForm> form : set) {
				ScreenForm value = form.getValue();
				if (value.getMonitorType().equals(monitorType) && userId.equals(value.getUserId()) && terminalId.equals(value.getTerminalId())) {
					value.setGroupField("history");
					historyCamera.add(value);
					cameraMap.remove(value.getCode());
					break;
				}
			}
			// 保存录制对象
			screenFormResult.setGroupField("current");
			screenFormResult.setCode(code);
			cameraMap.put(code, screenFormResult);
		}
		result.put(FishConstant.SUCCESS, true);
		result.put("data", screenFormResult);
		return result;
	}

	/**
	 * 停止屏幕录制
	 *
	 * @param monitorType
	 *            屏幕类型
	 * @param request
	 *            请求对象
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/stopCamera")
	public @ResponseBody
	ModelMap stopCamera(@RequestParam ScreenType monitorType, @RequestParam String terminalId, @RequestParam String userId, @RequestParam String code, HttpServletRequest request) {
		logger.info(String.format("stopCamera(monitoryType = %s, terminalId = %s)", monitorType, terminalId));
		ScreenForm screenForm = cameraMap.get(code);

		String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + STOP_CAMERA_PATH;

		ScreenForm screenFormResult = (ScreenForm) HttpProxy.httpPost(url, screenForm, ScreenForm.class);

		screenFormResult.setStartCameraDate(screenForm.getStartCameraDate());
		screenFormResult.setStopCameraDate(DateUtils.getTimestamp(new Date()));
		screenFormResult.setTerminalId(terminalId);
		screenFormResult.setUserId(userId);
		screenFormResult.setIpAddress(screenForm.getIpAddress());
		if (!"2".equals(screenFormResult.getStatus())) {
			// 保存录制对象
			screenFormResult.setGroupField("current");
			screenFormResult.setCode(code);
			cameraMap.put(code, screenFormResult);
		}
		ModelMap result = new ModelMap();
		result.put(FishConstant.SUCCESS, true);
		result.put("data", screenFormResult);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/query")
	public @ResponseBody
	ModelMap search(@RequestParam String userId, @RequestParam String terminalId, @RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search(userId = %s, terminalId = %s)", userId, terminalId));
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", listScreenForm(userId, terminalId));
		return result;
	}

	private List<ScreenForm> listScreenForm(String userId, String terminalId) {
		Set<Entry<String, ScreenForm>> set = cameraMap.entrySet();
		List<ScreenForm> resultList = new ArrayList<ScreenForm>();
		for (Entry<String, ScreenForm> entry : set) {
			ScreenForm form = entry.getValue();
			if (userId.equals(form.getUserId()) && terminalId.equals(form.getTerminalId())) {
				resultList.add(form);
			}
		}
		for (ScreenForm form : historyCamera) {
			if (userId.equals(form.getUserId()) && terminalId.equals(form.getTerminalId())) {
				resultList.add(form);
			}
		}
		return resultList;
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/screenQuery")
	// public @ResponseBody
	// ModelMap searchScreen(@RequestParam int start, @RequestParam int limit,
	// WebRequest request) {
	// logger.info(String.format("search()"));
	// ModelMap result = new ModelMap();
	// result.addAttribute(FishConstant.SUCCESS, true);
	// result.addAttribute("data", saveFormList);
	// return result;
	// }

	/**
	 * 下载
	 *
	 * @param monitorType
	 *            屏幕类型
	 * @param request
	 *            请求对象
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/downloadToService")
	public @ResponseBody
	ModelMap downloadToService(@RequestParam String code, HttpServletRequest request) {
		ModelMap result = new ModelMap();

		ScreenForm screenForm = cameraMap.get(code);

		if (screenForm == null) {
			for (ScreenForm form : historyCamera) {
				if (code.equals(form.getCode())) {
					screenForm = form;
					break;
				}
			}
		}
		int index = screenForm.getFileName().lastIndexOf("\\");
		String filePathService = screenForm.getFileName().substring(0, index);
		String fileNameService = screenForm.getFileName().substring(index + 1);

		screenForm.setFileNameClient(screenForm.getMonitorType().name() + "_" + fileNameService);
		screenForm.setFilePathClient(FishCfg.getTempDir());

		HttpFileCfg httpFileCfg = new HttpFileCfg();
		httpFileCfg.setCompress(true);
		httpFileCfg.setRequestPath(filePathService);
		httpFileCfg.setRequestName(fileNameService);
		httpFileCfg.setLocalName(screenForm.getFileNameClient());
		httpFileCfg.setLocalPath(screenForm.getFilePathClient());

		httpFileCfg.setIpAdd(screenForm.getIpAddress());
		httpFileCfg.setPort(MonitorCfg.getRemotePort());

		HttpFileClient.downloadFile(httpFileCfg);

		screenForm.setStatus("4");
		result.put(FishConstant.SUCCESS, true);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/removeAvi")
	public @ResponseBody
	ModelMap removeAvi(@RequestParam String userId, @RequestParam String terminalId) {
		ModelMap model = new ModelMap();
		// historyCamera
		for (int i = 0; i < historyCamera.size(); i++) {
			ScreenForm form = historyCamera.get(i);
			if (userId.equals(form.getUserId()) && terminalId.equals(form.getTerminalId())) {
				historyCamera.remove(i);
				i--;
			}
		}
		model.put(FishConstant.SUCCESS, true);
		return model;
	}

	/**
	 * 下载
	 *
	 * @param monitorType
	 *            屏幕类型
	 * @param request
	 *            请求对象
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/download")
	public @ResponseBody
	void downloadAvi(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) {
		// ModelMap result = new ModelMap();

		ScreenForm screenForm = cameraMap.get(code);

		if (screenForm == null) {
			for (ScreenForm form : historyCamera) {
				if (code.equals(form.getCode())) {
					screenForm = form;
					break;
				}
			}
		}

		File file = new File(screenForm.getFilePathClient() + System.getProperty("file.separator") + screenForm.getFileNameClient());

		download(file, response, "gb2312", "video/avi");

		// result.put(FishConstant.SUCCESS, true);
		// return result;
	}

	/**
	 * 下载文件
	 *
	 * @param file
	 *            文件
	 * @param response
	 *            请求响应
	 * @param encoding
	 *            编码
	 * @param contentType
	 *            头信息
	 */
	private void download(File file, HttpServletResponse response, String encoding, String contentType) {
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment;filename=" + file.getName());

		// response.setHeader("charset", "UTF-8");

		// OutputStream os = null;
		InputStream is = null;
		// InputStreamReader isr = null;
		// OutputStreamWriter osw = null;
		ServletOutputStream out = null;
		try {
			is = new FileInputStream(file);
			// osw = new OutputStreamWriter(os, encoding);

			out = response.getOutputStream();
			// os = response.getOutputStream();

			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			// osa.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// if (osw != null) {
			// try {
			// osw.close();
			// }
			// catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
		}
	}
}
