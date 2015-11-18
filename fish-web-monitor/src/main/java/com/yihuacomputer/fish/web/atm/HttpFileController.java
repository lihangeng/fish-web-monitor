package com.yihuacomputer.fish.web.atm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileRet;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;

/**
 * 文件下载要求： <br>
 * 1．采用HTTP文件下载方式，同时支持断点续传。 <br>
 * 2．HTTP Client端可以请求服务器端任意路径下的文件。 <br>
 * 3．支持压缩方式下载。
 *
 * @author pengwenchao
 *
 */
@Controller
@RequestMapping("/httpfileservice")
public class HttpFileController {
	@Autowired
	private IParamService paramService;

	private Logger logger = LoggerFactory.getLogger(HttpFileController.class);
	
    private String paramKey = "download_limit_count";
	
	private int currentConnect = 0;

	@RequestMapping(method = RequestMethod.POST)
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp) {
		int maxConnect = 80;
		IParam param = paramService.getParam(paramKey);
		if(null!=param){
			String masCon = param.getParamValue();
			if(masCon!=null){
				maxConnect = Integer.valueOf(masCon);
			}
		}
			
		logger.info(String.format("now connecter number is:[%s]",currentConnect));

		if(currentConnect>=maxConnect){
			return;
		}
		currentConnect++;
		
		// 实例支持对随机访问文件的读取
		RandomAccessFile randomFile = null;

		// 写出流信息
		ServletOutputStream out = null;

		// 将request讲求的输入流,转换为DownloadServiceFileDO对象.
		HttpFileCfg fileCfg = null;

		try {
			// 将request讲求的输入流,转换为DownloadServiceFileDO对象.
			fileCfg = JsonUtils.inputStreamToObject(req.getInputStream(), HttpFileCfg.class);

			// 拼装文件路径
			String fileName = JsonUtils.stringToStringPath(fileCfg.getRequestPath(), fileCfg.getRequestName());

			// 设置下载头
			resp.setContentType("application/octet-stream;charset=UTF-8");

			if (fileCfg.isCompress()) {
				resp.setHeader("Content-Encoding", "gzip");
			}

			// ISO-8859-1 GBK UTF-8
			String localFileName = new String(fileCfg.getRequestName().getBytes("UTF-8"), "UTF-8");

			// 设置响应头和保存文件名
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + localFileName + "\"");

			File localFile = new File(fileName);

			if (!localFile.isFile()) {
				resp.addHeader("Content-Ret", HttpFileRet.REQ_FILE_NOTEXIT.name());
				currentConnect--;
				return;
			}

			// 实例支持对随机访问文件的读取
			randomFile = new RandomAccessFile(localFile, "r");

			resp.addHeader("Content-Ret", HttpFileRet.SUCCESS.name());
			resp.setHeader("Content-Filelength", String.valueOf(randomFile.length()));

			int len = 0;

			// 从指定位置读取文件流
			if (fileCfg.isRetry()) {
				randomFile.seek(fileCfg.getBreakPoints());
			}
			out = resp.getOutputStream();
			byte[] by = new byte[4096];
			while ((len = randomFile.read(by)) != -1) {
				out.write(by, 0, len);
			}
		} catch (Exception e) {
//			logger.error(String.format("处理文件请求异常![%s],请求信息[%s]", e, JsonUtils.toJson(fileCfg)));
			logger.error(String.format("request download file exception![%s],request body is [%s]", e, JsonUtils.toJson(fileCfg)));
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
//					logger.error(String.format("关闭输出流出错,出错信息[%s]", e));
					logger.error(String.format("close outputstream exception,the exception info is [%s]", e));
				}
			}
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					logger.error(String.format("close outputstream exception,the exception info is [%s]", e));
				}
			}
		}
		currentConnect--;
	}
}
