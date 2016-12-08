package com.yihuacomputer.common.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.jackson.JsonUtils;

/**
 * 文件下载HTTP方式实现 1．采用HTTP文件下载方式，同时支持断点续传。 <br>
 * 2．HTTP Client端可以请求服务器端任意路径下的文件。 <br>
 * 3．支持压缩方式下载。
 *
 * @author pengwenchao
 * */
public class HttpFileClient {

	private static Logger logger = LoggerFactory.getLogger(HttpFileClient.class);
	private static final int CONNECTION_TIMEOUT = 30 * 1000;

	private static final int SO_TIMEOUT = 30 * 1000;

	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	private static final String ACCEPT_ENCODING = "gzip, deflate";

	private static final String ENCODING_DEFAULT = "UTF-8";

	private HttpFileClient(){
		throw new IllegalAccessError("Utils Class");
	}
	/**
	 * 通过HTTP方式下载文件
	 * @param fileCfg
	 * @return
	 */
	public static HttpFileRet downloadFile(HttpFileCfg fileCfg) {

		if (fileCfg.getLocalPath() == null || fileCfg.getPort() == null
				|| fileCfg.getRequestPath() == null
				|| fileCfg.getRequestName() == null) {
			return HttpFileRet.CFG_ERROR;
		}

		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("http://").append(fileCfg.getIpAdd()).append(":").append(fileCfg.getPort())
		.append("/ctr/download");
		URI uri = null;
		try {
			uri = new URI(stringBuffer.toString());
		} catch (URISyntaxException e) {
			logger.error(String.format("url %s URISyntaxException[%s]", stringBuffer.toString(),e));
			return HttpFileRet.URL_ERROR;
		}

		httpPost.setURI(uri);

		HttpConnectionParams.setConnectionTimeout(httpPost.getParams(), HttpFileClient.CONNECTION_TIMEOUT);

		HttpConnectionParams.setSoTimeout(httpPost.getParams(), HttpFileClient.SO_TIMEOUT);

		httpPost.setHeader("Content-Type", HttpFileClient.CONTENT_TYPE);

		// 本地文件名未设置，将请求的文件名作为本地文件名
		if (fileCfg.getLocalName() == null) {
			fileCfg.setLocalName(fileCfg.getRequestName());
		}

		// 是否压缩
		if (fileCfg.isCompress()) {
			httpPost.setHeader("Accept-Encoding", HttpFileClient.ACCEPT_ENCODING);
		}

		// 客户端的文件夹是否存在
		File fileDirs = new File(fileCfg.getLocalPath());
		if (!fileDirs.exists()) {
			fileDirs.mkdirs();
		}

		File loaclFile = new File(fileCfg.getLocalPath() + System.getProperty("file.separator") + fileCfg.getLocalName());

		// 设置断点
		if (fileCfg.isRetry()) {
			fileCfg.setBreakPoints(HttpFileClient.available(loaclFile));
		} else {
			fileCfg.setBreakPoints(0);
		}

		StringEntity se = null;
		try {

			// 设置HTTP POST请求参数, 及编码方式
			se = new StringEntity(JsonUtils.toJson(fileCfg), HttpFileClient.ENCODING_DEFAULT);
			httpPost.setEntity(se);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException"+e.getMessage());
			return HttpFileRet.ERROR;
		}

		InputStream is = null;
		RandomAccessFile randomFile = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);

			HttpFileRet ret = HttpFileRet.get(httpResponse.getFirstHeader("Content-Ret").getValue());

			if(ret.equals(HttpFileRet.SUCCESS)){
				// 输出返回值
				is = httpResponse.getEntity().getContent();

				randomFile = new RandomAccessFile(loaclFile, "rw");

				if(fileCfg.isRetry()){
					randomFile.seek(fileCfg.getBreakPoints());
				}

				int len;

				byte[] by = new byte[4096];
				while ((len = is.read(by)) != -1) {
					randomFile.write(by, 0, len);
				}
			}else{
				return ret;
			}
		}catch(HttpHostConnectException e){
			logger.error("HttpHostConnectException "+e.getMessage());
			return HttpFileRet.CONN_ERROR;
		}
		catch (Exception e) {
			logger.error("Exception "+e.getMessage());
			return HttpFileRet.ERROR;
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					logger.error("randomFile close IOException "+e.getMessage());
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("inputStream close IOException "+e.getMessage());
				}
			}
		}
		return HttpFileRet.SUCCESS;
	}

	
	
	public static HttpFileRet mergeDownloadFile(HttpFileCfg fileCfg) {

		if (fileCfg.getLocalPath() == null || fileCfg.getPort() == null
				|| fileCfg.getRequestPath() == null) {
			return HttpFileRet.CFG_ERROR;
		}

		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost();

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("http://").append(fileCfg.getIpAdd()).append(":").append(fileCfg.getPort())
		.append("/ctr/mergeDownload");
		URI uri = null;
		try {
			uri = new URI(stringBuffer.toString());
		} catch (URISyntaxException e) {
			logger.error(String.format("url %s URISyntaxException[%s]", stringBuffer.toString(),e));
			return HttpFileRet.URL_ERROR;
		}

		httpPost.setURI(uri);

		HttpConnectionParams.setConnectionTimeout(httpPost.getParams(), HttpFileClient.CONNECTION_TIMEOUT);

		HttpConnectionParams.setSoTimeout(httpPost.getParams(), HttpFileClient.SO_TIMEOUT);

		httpPost.setHeader("Content-Type", HttpFileClient.CONTENT_TYPE);

		// 本地文件名未设置，将请求的文件名作为本地文件名
		if (fileCfg.getLocalName() == null) {
			fileCfg.setLocalName(fileCfg.getRequestName());
		}

		// 是否压缩
		if (fileCfg.isCompress()) {
			httpPost.setHeader("Accept-Encoding", HttpFileClient.ACCEPT_ENCODING);
		}

		// 客户端的文件夹是否存在
		File fileDirs = new File(fileCfg.getLocalPath());
		if (!fileDirs.exists()) {
			fileDirs.mkdirs();
		}

		File loaclFile = new File(fileCfg.getLocalPath() + System.getProperty("file.separator") + fileCfg.getLocalName());

		// 设置断点
		if (fileCfg.isRetry()) {
			fileCfg.setBreakPoints(HttpFileClient.available(loaclFile));
		} else {
			fileCfg.setBreakPoints(0);
		}

		StringEntity se = null;
		try {

			// 设置HTTP POST请求参数, 及编码方式
			se = new StringEntity(JsonUtils.toJson(fileCfg), HttpFileClient.ENCODING_DEFAULT);
			httpPost.setEntity(se);
		} catch (UnsupportedEncodingException e) {
			logger.error(String.format("UnsupportedEncodingException [%s]",e));
			return HttpFileRet.ERROR;
		}

		InputStream is = null;
		RandomAccessFile randomFile = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);

			HttpFileRet ret = HttpFileRet.get(httpResponse.getFirstHeader("Content-Ret").getValue());

			if(ret.equals(HttpFileRet.SUCCESS)){
				// 输出返回值
				is = httpResponse.getEntity().getContent();

				randomFile = new RandomAccessFile(loaclFile, "rw");

				if(fileCfg.isRetry()){
					randomFile.seek(fileCfg.getBreakPoints());
				}

				int len;

				byte[] by = new byte[4096];
				while ((len = is.read(by)) != -1) {
					randomFile.write(by, 0, len);
				}
			}else{
				return ret;
			}
		}catch(HttpHostConnectException e){
			logger.error(String.format("HttpHostConnectException [%s]",e));
			return HttpFileRet.CONN_ERROR;
		}
		catch (Exception e) {
			return HttpFileRet.ERROR;
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					logger.error(String.format("randomFile close [%s]",e));
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(String.format("inputStream close [%s]",e));
				}
			}
		}
		return HttpFileRet.SUCCESS;
	}
	/**
	 * 获得文件的大小
	 *
	 * @param file
	 *            文件
	 * @return 文件的大小
	 */
	private static long available(File file) {
		if (file.exists()) {
			return file.length();
		}
		return 0L;
	}

}
