package com.yihuacomputer.fish.web.util;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;

@Controller
@RequestMapping("/dateImport")
public class OrgAndDeviceImportController {
	/**
	 * 设备接口
	 */
	private Logger logger = LoggerFactory.getLogger(OrgAndDeviceImportController.class);
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IPersonService personService;
	@Autowired
	private IAtmTypeService atmTypeService;
	@Autowired
	private ICollectService collectService;
	@Autowired
	private IDevicePersonRelation devicePersonRelation;
	
	/**
	 * 机构接口
	 */
	@Autowired
	private IOrganizationService orgService;

	@PostConstruct
	public void init() {
	}

	@RequestMapping(value = "temp", method = RequestMethod.GET)
	public void export(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		File file = null;
		if ("org".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("resources/file/orgTemp.xls"));
		} else if ("device".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("resources/file/deviceTemp.xls"));
		} else if ("1".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("/orgResult.xls"));
		} else if ("2".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("/deviceResult.xls"));
		}
		OutputStream out = null;
		out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/x-msdownload;charset=UTF-8");
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		try {
			int len = 0;
			long contentLength = 0;
			contentLength = contentLength + randomFile.length();
			randomFile.seek(0);
			byte[] cache = new byte[1024];
			while ((len = randomFile.read(cache)) != -1) {
				out.write(cache, 0, len);
				contentLength += len;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
			if (randomFile != null) {
				randomFile.close();
			}
		}
	}


	@RequestMapping(value = "delFile", method = RequestMethod.POST)
	public @ResponseBody
	void upload(WebRequest webRequest, String type, HttpServletResponse response, HttpServletRequest request) throws Exception {
		File file = null;
		if ("1".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("/orgResult.xls"));
		} else if ("2".equals(type)) {
			file = new File(request.getSession().getServletContext().getRealPath("/deviceResult.xls"));
		}
		file.delete();
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void download(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("resources/file/deviceCode.xls");
		File file = new File(path);
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/x-msdownload;charset=UTF-8");
		OutputStream out = null;
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		try {
			int len = 0;
			out = response.getOutputStream();
			long contentLength = 0;
			contentLength = contentLength + randomFile.length();
			randomFile.seek(0);
			byte[] cache = new byte[1024];
			while ((len = randomFile.read(cache)) != -1) {
				out.write(cache, 0, len);
				contentLength += len;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (randomFile != null) {
				randomFile.close();
			}
		}
	}
}
