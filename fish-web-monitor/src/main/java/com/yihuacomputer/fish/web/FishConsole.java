//package com.yihuacomputer.fish.web;
//
//import java.io.File;
//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.webapp.WebAppContext;
//import org.eclipse.jetty.xml.XmlConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.yihuacomputer.fish.web.util.OsUtil;
//
//
///**
// * ATMV控制台主程序
// * 
// * 独立式web应用服务器 系统支持独立部署web应用程序 系统支持windows、linux、Aix
// * 
// * @author yihua
// * @date 2015-07-08
// * 
// */
//public class FishConsole {
//	/** 日志输出 **/
//	private static Logger logger = LoggerFactory.getLogger(FishConsole.class);
//	/** WEB APP上下文 **/
//	private WebAppContext context = null;
//	/** 服务器 **/
//	private Server server = null;
//	
//	private String CONTEXT = "/atmv";
//	
//	private static String FILESEP = System.getProperty("file.separator");
//
//	private static String WEBHOME = System.getProperty("user.dir");
//	
//	static{
//		if(!new File(WEBHOME+FILESEP+"conf").exists()){
//			WEBHOME = WEBHOME+FILESEP+"src"+FILESEP+"main"+FILESEP+"webapp";
//		}
//		System.out.println(WEBHOME);
//	}
//	public static void main(String[] args) throws Exception {
//		logger.info("Starting ATMVS Console……");
//		FishConsole runtime = new FishConsole();
//		if(OsUtil.isWindows()){
//			runtime.initWebAppContextLocal();
//		}else{
//			runtime.initWebAppContext();
//		}
//		runtime.startConsoleServer();
//	}
//
//	/**
//	 * 启动服务器
//	 * 
//	 * @param port 端口号 默认8080
//	 * 
//	 */
//	public void startConsoleServer() {
//		try {
//			server = new Server();
//			String[] configFiles = {WEBHOME+FILESEP+"conf"+FILESEP+"jetty.xml"};
//			for(String configFile : configFiles) {
//				  XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
//				  configuration.configure(server);
//			}
//			server.setHandler(context);
//			server.start();
//			server.join();
//		} catch (Exception e) {
//			stopWebServer();
//			logger.error("Starting Atmp Console error:["+e+"]");
//		}
//	}
//	
//	/** 添加WEB应用 **/
//	public void initWebAppContext() throws Exception{
//		context = new WebAppContext();
//		context.setResourceBase(".");
//		context.setContextPath(CONTEXT);
//		context.setDescriptor("web.xml");
//		context.setDisplayName("Yihua ATMVS console web application");
//		context.setClassLoader(Thread.currentThread().getContextClassLoader());
//		context.setConfigurationDiscovered(true);
//		context.setParentLoaderPriority(true);
//		String[] configFiles = {WEBHOME+FILESEP+"conf"+FILESEP+"jetty-mysql.xml"};
//		for(String configFile : configFiles) {
//			  XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
//			  configuration.configure(context);
//		}
//	}
//	
//	/** 添加WEB应用 **/
//	public void initWebAppContextLocal() throws Exception{
//		context = new WebAppContext();
//		context.setContextPath(CONTEXT);
//		context.setDescriptor("/WEB-INF/web.xml");  
//		context.setDisplayName("Yihua ATMVS console web application");  
//		context.setResourceBase(WEBHOME);  
//		context.setClassLoader(Thread.currentThread().getContextClassLoader());  
//		context.setConfigurationDiscovered(true);  
//		context.setParentLoaderPriority(true);
//		String[] configFiles = {WEBHOME+FILESEP+"conf"+FILESEP+"jetty-mysql.xml"};
//		for(String configFile : configFiles) {
//			XmlConfiguration configuration = new XmlConfiguration(new File(configFile).toURI().toURL());
//			configuration.configure(context);
//		}
//	}
//	
//
//	/**
//	 * 停止服务器
//	 */
//	public void stopWebServer() {
//		try {
//			if (server != null){
//				server.stop();
//			}
//		} catch (Exception e) {
//			logger.error("Closing Atmp Console error:["+e+"]");
//		}
//	}
//}
