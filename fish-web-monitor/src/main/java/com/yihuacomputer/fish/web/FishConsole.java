package com.yihuacomputer.fish.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.web.util.OsUtil;


/**
 * ATMV控制台主程序
 * 
 * 独立式web应用服务器 系统支持独立部署web应用程序 系统支持windows、linux、Aix
 * 
 * @author yihua
 * @date 2015-07-08
 * 
 */
public class FishConsole {
	/** 日志输出 **/
	private static Logger logger = LoggerFactory.getLogger(FishConsole.class);
	/** WEB APP上下文 **/
	private WebAppContext context = null;
	/** 服务器 **/
	private Server server = null;
	
	/**
	 * 配置jetty容器启动上下文的路径
	 */
	private static final String CONTEXT = "/atmv";
	
	private static String webHome = System.getProperty("user.dir");
	private static String dbConfig = "";
	
	static{
		if(!new File(webHome+FishCfg.FILESEP+"conf").exists()){
			File file = new File(webHome+FishCfg.FILESEP+"target");
			File[] files = file.listFiles();
			for(File homeFile: files){
				if(homeFile.getName().startsWith("atmvs-abc-")&&homeFile.isDirectory()){
					webHome=homeFile.getPath();
					break;
				}
			}
		}
		StringBuilder fileConfig = new StringBuilder();
		fileConfig.append(webHome).append(FishCfg.FILESEP).append("conf").append(FishCfg.FILESEP).append("jetty-mysql.xml");
		dbConfig = fileConfig.toString();
		logger.info(String.format("WebHome is %s.",webHome));
	}
	/**
	 * jetty容器启动
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		logger.info("Starting ATMVS Console……");
		recordPid();
		FishConsole runtime = new FishConsole();
		if(OsUtil.isWindows()){
			runtime.initWebAppContextLocal();
		}else{
			runtime.initWebAppContext();
		}
		runtime.startConsoleServer();
	}

	/**
	 * 启动服务器
	 * 
	 * @param port 端口号 默认8080
	 * 
	 */
	public void startConsoleServer() {
		try {
			server = new Server();
			String[] configFiles = {webHome+FishCfg.FILESEP+"conf"+FishCfg.FILESEP+"jetty.xml"};
			for(String configFile : configFiles) {
				  XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
				  configuration.configure(server);
			}
			server.setHandler(context);
			server.start();
			server.join();
		} catch (Exception e) {
			stopWebServer();
			logger.error("Starting Atmp Console error:["+e+"]");
		}
	}
	
	/** 添加WEB应用 **/
	public void initWebAppContext() throws Exception{
		context = new WebAppContext();
		context.setResourceBase(".");
		context.setContextPath(CONTEXT);
		context.setDescriptor("web.xml");
		context.setDisplayName("Yihua ATMVS console web application");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setConfigurationDiscovered(true);
		context.setParentLoaderPriority(true);
		String[] configFiles = {dbConfig};
		for(String configFile : configFiles) {
			  XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
			  configuration.configure(context);
		}
	}
	
	/** 添加WEB应用 **/
	public void initWebAppContextLocal() throws Exception{
		context = new WebAppContext();
		context.setContextPath(CONTEXT);
		context.setDescriptor("/WEB-INF/web.xml");  
		context.setDisplayName("Yihua ATMVS console web application");  
		context.setResourceBase(webHome);  
		context.setClassLoader(Thread.currentThread().getContextClassLoader());  
		context.setConfigurationDiscovered(true);  
		context.setParentLoaderPriority(true);
		String[] configFiles = {dbConfig};
		for(String configFile : configFiles) {
			XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
			configuration.configure(context);
		}
	}
	

	/**
	 * 停止服务器
	 */
	public void stopWebServer() {
		try {
			if (server != null){
				server.stop();
			}
		} catch (Exception e) {
			logger.error("Closing Atmv Console error:["+e+"]");
		}
	}
	
	/**
	 * 记录当前进程PID信息
	 */
	private static void recordPid(){
		try {
			String name = ManagementFactory.getRuntimeMXBean().getName();
			String pid = name.split("@")[0];
			File cfgFile = new File(System.getProperty("user.dir")+File.separator+"ATMVSPID.ini");
			if(!cfgFile.exists()){
				cfgFile.getParentFile().mkdirs();
				cfgFile.createNewFile();
			}
			changeProValue(cfgFile,"WEB_PID", pid);
			logger.info("WEB PID=["+pid+"]");
		} catch (Exception e) {
			logger.error("record java web pid info failer:["+e+"]");
		}
	}
	
	/**
	 * 向指定文件中记录PID
	 * @param file
	 * @param key
	 * @param value
	 */
	public static  void changeProValue(File file,String key, Object value){
		Properties pro = new Properties();
		FileInputStream  fis=null;
		BufferedInputStream bis=null;
        try{
        	fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			pro.load(bis);
            FileOutputStream fos = new FileOutputStream(file);
            pro.setProperty(key, String.valueOf(value));
            pro.store(fos, null);
            fos.close();
        }catch(Exception e){
            logger.error(String.format("changeProValue exception %s",e));
        }
    }
}
