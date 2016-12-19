package com.yihuacomputer.fish.sms.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;



public class LevelFinalSerial {
	
	private static Logger logger = LoggerFactory.getLogger(LevelFinalSerial.class);
			
	/**
	 * 数据包长度
	 */
	public static final int PACKET_LENGTH = 500;

	public static LevelFinalSerial final_Level;

	SerialPort serialPort;
	CommPortIdentifier identifier;
	String PortName;				// 串口名.如:COM3
	OutputStream out;				// 串口输出流
	InputStream in;					// 串口输入流
	String appname = "SerialBean"; 	// 程序名
	int timeOut; 					// 延迟时间(毫秒数)
	int baudrate; 					// 波特率
	int dataBits; 					// 数据位
	int stopBits; 					// 停止位
	int parity; 					// 奇偶检验

	/**
	 * @describe: 获取SerialBeanl类单例
	public static Level_Final_Serial getInstance() {
		if (final_Level == null) {
			final_Level = new Level_Final_Serial();
		}
		return final_Level;
	}

	/**
	 * 构造函数
	 */
	public LevelFinalSerial() {
	}

	/**
	 * @describe: 设置 串口程序名
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * @describe: 初始化类
	 * @param timeOut  等待时间
	 * @param baudrate	波特率
	 * @param dataBits	数据位
	 * @param stopBits	停止位
	 * @param parity	奇偶检验
	 */
	public void initialize(int timeOut, int baudrate, int dataBits, int stopBits, int parity) {
		this.timeOut = timeOut;
		this.baudrate = baudrate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
	}

	/**
	 * @describe: 初始化串口详细信息
	 * @return true : 初始化串口成功 false: 初始化串口失败
	 */
	public boolean openPort(String portName) {
		boolean rsBool = false;
		this.PortName = portName;

		try {
			//获取串口
			identifier = getCommPort();

			if (identifier == null) {
				// null
			} else {
				if (identifier.isCurrentlyOwned()){

				}else{
					// open方法打开通讯端口
					serialPort = (SerialPort) identifier.open(appname, timeOut);

					// 获取端口的输入,输出流对象
					in = serialPort.getInputStream();
					out = serialPort.getOutputStream();

					// 设置串口初始化参数，依次是波特率，数据位，停止位和校验
					serialPort.setSerialPortParams(baudrate, dataBits, stopBits, parity);
					serialPort.setDTR(true);
					serialPort.setRTS(true);

					rsBool = true;
				}
			}
		} catch (PortInUseException e) {
			logger.error(String.format("PortInUseException is [%s]", e));
		} catch (Exception e) {
			logger.error(String.format("Exception is [%s]", e));
		}

		return rsBool;
	}

	/**
	 * @describe: 列举并得到需要用串口
	 */
	public CommPortIdentifier getCommPort() throws Exception {
		CommPortIdentifier portIdRs = null;
		portIdRs = CommPortIdentifier.getPortIdentifier(PortName);
		return portIdRs;
	}

	/**
	 * @describe: 读取串口数据
	 */
	public char[] readPackData() throws Exception {
		byte[] readBuffer = new byte[PACKET_LENGTH];
		char[] msgPack = null;
		int numBytes = 0;

		while (in.available() > 0) {
			numBytes = in.read(readBuffer);
			msgPack = null;
			msgPack = new char[numBytes];
			for (int i = 0; i < numBytes; i++) {
				msgPack[i] = (char) (readBuffer[i] & 0xFF);
			}
		}
		return msgPack;
	}

	/**
	 * @throws IOException
	 * @describe: 向串口写数据 char[] bytes
	 */
	public void writePort(char[] bytes) throws IOException {
		for (char b : bytes) {
			writePort(b);
		}
	}

	/**
	 * @describe: 向串口写数据 char bytes
	 */
	public void writePort(char b) throws IOException {
		out.write(b);
		out.flush();
	}

	/**
	 * @describe: 关闭串口,释放资源
	 */
	public void closePort() {
		if (out != null) {
			try {
				out.close();
				in.close();
				out = null;
				in = null;
			} catch (IOException e) {
				logger.error(String.format("IOException is [%s]", e));
			}
		}
		if (serialPort != null) {
			serialPort.close();
			serialPort = null;
		}
	}

	/**
	 * @describe: 列举全部串口名称
	 */
	public static List<String> getAllComPorts(){
		List<String> comList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portIdRs = null;

		while (en.hasMoreElements()) {
			portIdRs = (CommPortIdentifier) en.nextElement();
			if (portIdRs.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				comList.add(portIdRs.getName());
			}
		}
		return comList;
	}
}
