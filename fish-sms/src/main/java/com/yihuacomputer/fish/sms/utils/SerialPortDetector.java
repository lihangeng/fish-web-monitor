package com.yihuacomputer.fish.sms.utils;


import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 串口检测器
 * @author guoqiang
 *
 */
public class SerialPortDetector {
	
	private static Logger logger = LoggerFactory.getLogger(SerialPortDetector.class);

	/**
	 * 端口
	 */
	public static SerialPortDetector sms_serial;
	/**
	 * 串口
	 */
	public static LevelFinalSerial final_Level;

	//需要设置的参数
	int portId; 					// 串口号：如：com1，则portId为1
	int baudrate;					// 波特率

	//	不一定要设定的参数(有默认值)
	int timeOut; 					// 延迟时间(毫秒数)
	int dataBits; 					// 数据位
	int stopBits; 					// 停止位
	int parity; 					// 奇偶检验
	int funCode; 					// 功能码
	int dataLen;					// 数据长度
	int appendMillsec; 				// 计算发送间隔用---附加毫秒数
	int bytes;						// 计算发送间隔用---发送字节数

	// 自动计算--发送间隔
	int frameInterval; 				// 根据波特率，数据倍率和数据量，自动设置发送间隔

	/**
	 * 构造方法
	 */
	public SerialPortDetector() {
		final_Level = new LevelFinalSerial();
		timeOut = 60;						// 延迟时间(毫秒数)
		dataBits = SerialPort.DATABITS_8;	// 数据位
		stopBits = SerialPort.STOPBITS_1;	// 停止位
		parity = SerialPort.PARITY_NONE;	// 奇偶检验
		funCode = 3;						//	读取当前寄存器内一个或多个二进制值
		dataLen = 4;						//	假设 需要获取4个数据
		appendMillsec = 38;					//	附加毫秒数(需要自己测试调整)
		bytes = 20;							//  发送是字节数
	}

	/**
	 * @describe:  获取程序单例
	 */
	public static SerialPortDetector getInstance() {
		if (sms_serial == null) {
			sms_serial = new SerialPortDetector();
		}
		return sms_serial;
	}

	/**
	 * @describe: 打开串口
	 * @param portStr 串口号. 如: COM3
	 * @param baudrate 波特率
	 * @param appName 串口占用程序的命名
	 * @return: true:打开串口正常 false:打开串口异常
	 */
	public boolean openPort(String portStr, int baudrate, String appName) {
		boolean rsBool = false;

		// 初始化串口
		final_Level.initialize(timeOut, baudrate, dataBits, stopBits, parity);
		final_Level.setAppname(appName.toUpperCase());
		// 打开串口
		if (final_Level.openPort( portStr)) {
			rsBool = true;
			// 设置帧之间的发送间隔
			this.frameInterval = getFrameInterval(appendMillsec, bytes, baudrate);
		}
		return rsBool;
	}


	/**
	 * @describe: 写串口命令 - 发送AT这个指令
	 * @param rs 发送的数据
	 */
	public void writeByte(char[] rs) throws Exception{
		final_Level.writePort(rs);
		// 打印发送的串口数据-16进制显示
		// System.out.println(bytesToHexString(rs));

		//等待一段时间, 以保证数据,有足够的时间发送和接收
		//Thread.sleep(frameInterval);
		Thread.sleep(frameInterval);
	}

	/**
	 * @describe: .读串口命令 - 对发送AT这个指令,返回OK就是成功
	 * @param portStr
	 * @return: true:成功 false:失败
	 */
	public boolean readByte(String portStr) throws Exception{
		boolean rsbool = false;
		String rsStr = "";

		// 读取数据
		char[] rsByte = final_Level.readPackData();
		if (rsByte != null){
			// 打印收到的串口数据-16进制显示
			for (char c : rsByte) {
				rsStr += c;
			}
			if (rsStr.indexOf("OK")>0){
				System.out.println("可用的短信模块串口:" + portStr);
				rsbool = true;
			}
		}
		// 处理收到的数据

		return rsbool;
	}

	/**
	 * @describe: 关闭串口，释放资源
	 */
	public void closePort() {
		final_Level.closePort();
	}

	//---------------工具方法---------------//
	/**
	 * @describe: 获取需要帧之间需要间隔的时间(毫秒) 功能公式(1*12(位)*数据长度*1000/波特率 + 附加毫秒数)--根据自己的程序动态调整
	 * @param appendMillsec	附加毫秒数
	 * @param dataLen	数据区数据长度
	 * @param baudrate	波特率
	 * @return 得到合适的帧发送,间隔毫秒数
	 */
	public static int getFrameInterval(int appendMillsec, int dataLen, int baudrate) {
		int rsInt = (int) Math.ceil(1 * 12 * (dataLen + 4) * 1000 / (float) baudrate) + appendMillsec;
		return rsInt;
	}

	/**
	 * 获取可用的Com接口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getComPorts(){
		Enumeration<CommPortIdentifier> en = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portIdRs = null;

		while (en.hasMoreElements()) {
			portIdRs = en.nextElement();
			if (portIdRs.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				SerialPortDetector serialPortDetector = SerialPortDetector.getInstance();
				boolean rsBool = false;
				try {
					// ①  打开端口
					rsBool = serialPortDetector.openPort(portIdRs.getName(), 9600, "网关名称");
					// ②  串口写
					String atCommand = "AT\r";		// 发送AT指令(加换行符号\r)
					char[] atOrder = atCommand.toCharArray();
					if (rsBool) serialPortDetector.writeByte(atOrder);
					if (rsBool) {
						// ③  串口读(根据得到的数据,判断返回数据的连通性{返回字符包含OK表示成功})
						rsBool = serialPortDetector.readByte(portIdRs.getName());
						return portIdRs.getName();
					}
				} catch (Exception e) {
					logger.error(String.format("Exception is [%s]", e));
				}finally{
					// ④ 关闭串口
					serialPortDetector.closePort();
				}
			}
		}
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String args[]){
		SerialPortDetector.getComPorts();
	}

}
