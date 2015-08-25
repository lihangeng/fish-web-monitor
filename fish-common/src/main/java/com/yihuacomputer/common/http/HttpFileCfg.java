package com.yihuacomputer.common.http;



/**
 * HTTP方式文件传输配置
 * */
public class HttpFileCfg{
	
	/*设备IP地址*/
	private String ipAdd;
	
	/*文件监听端口号*/
	private String port;
	
	/*传输的文件名*/
	private String requestName;
	
	/*请求服务端文件存放路径*/
	private String requestPath;
	
	/*客户端文件名*/
	private String localName;
	
	/*客户端文件本地存放路径*/	
	private String localPath;
	
	/*是否压缩*/
	private boolean compress;	
	
	/*是否断点续传*/
	private boolean retry;
	
	/*断点*/	
	private long breakPoints;
	
	
	public String getPort() {
		return port;
	}

	/**
	 * 文件传输监听端口
	 * 
	 * @param port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	public String getIpAdd() {
		return ipAdd;
	}

	/**
	 * 通信IP地址
	 * @param ipAdd
	 */
	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public String getLocalPath() {
		return localPath;
	}

	/**
	 * 本地文件存放路径
	 * @param localPath
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public boolean isCompress() {
		return compress;
	}

	/**
	 * 压缩标志
	 * @param compress
	 */
	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	public long getBreakPoints() {
		return breakPoints;
	}

	/**
	 * 断点位置
	 * @param breakPoints
	 */
	public void setBreakPoints(long breakPoints) {
		this.breakPoints = breakPoints;
	}

	public String getRequestPath() {
		return requestPath;
	}

	/**
	 * 请求文件路径
	 * @param requestPath
	 */
	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public String getRequestName() {
		return requestName;
	}

	/**
	 * 请求文件名
	 * @param requestName
	 */
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getLocalName() {
		return localName;
	}

	/**
	 * 本地存放文件名
	 * @param localName
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public boolean isRetry() {
		return retry;
	}

	/**
	 * 是否重试
	 * @param retry
	 */
	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	@Override
	public String toString() {
		return "HttpFileCfg [ipAdd=" + ipAdd + ", port=" + port
				+ ", requestName=" + requestName + ", requestPath="
				+ requestPath + ", localName=" + localName + ", localPath="
				+ localPath + ", compress=" + compress + ", retry=" + retry
				+ ", breakPoints=" + breakPoints + "]";
	}

}
