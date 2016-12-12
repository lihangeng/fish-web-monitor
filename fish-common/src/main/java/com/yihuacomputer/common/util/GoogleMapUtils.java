package com.yihuacomputer.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.FishCfg;

public class GoogleMapUtils {
	
	private static Logger logger = LoggerFactory.getLogger(GoogleMapUtils.class);
	/**
	 * 从Google地图服务器下载地图文件
	 * 
	 * @param axisX
	 * @param axisY
	 * @param zoomZ
	 * @param mapPath
	 */
	public static void downloadTile(int axisX,int axisY,int zoomZ,String mapPath){
		
        HttpClient httpClient = new DefaultHttpClient();
        InputStream is = null;
        OutputStream out = null;
        Random random = new Random();
        
        String url = "http://mt"+random.nextInt(3)+".googleapis.com/vt?lyrs=m@174000000&src=apiv3&hl=zh-CN&x=" + axisX + "&y="
                + axisY + "&z=" + zoomZ + "&s=G&style=api%7Csmartmaps";
        try{
	
	        String dirPath = mapPath + FishCfg.FILESEP + zoomZ + FishCfg.FILESEP + axisX;
	        File dirFile = new File(dirPath);
	
	        if (!dirFile.exists()) {
	            dirFile.mkdirs();
	        }
	        File file = new File(dirPath + FishCfg.FILESEP + axisY + ".png");
	        if(file.exists()){
	        	return;
	        }
	        HttpGet get = new HttpGet(url);
	    	
	        HttpResponse response = httpClient.execute(get);

	
	        is = response.getEntity().getContent();
	        out = new FileOutputStream(file);
	        byte[] by = new byte[4096];
	        int len;
	
	        while ((len = is.read(by)) != -1) {
	            out.write(by, 0, len);
	        }
	        get.abort();
        }catch(Exception e){
        	logger.error(e.getMessage());
        }finally{
        	if(is!=null){
        		try {
					is.close();
				} catch (IOException e) {
		        	logger.error(e.getMessage());
				}
        	}
        	if(out!=null){
        		try {
					out.close();
				} catch (IOException e) {
		        	logger.error(e.getMessage());
				}
        	}
        }
	 
	}
}
