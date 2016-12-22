package com.yihuacomputer.common.util;

import com.yihuacomputer.common.ITypeIP;

/**
 * IP类实现
 * @author xuxiang
 *
 */
public class IP implements ITypeIP {
	private Long longValue;
	
	/**
	 * 默认的构造函数
	 */
	public IP(){
		this(0);
	}
	
	/**
	 * 给定一个标准化的IP格式的字符串构建一个IP对象
	 * @param stringValue
	 */
	public IP(String stringValue){
		String ipValue = stringValue;
		if(ipValue == null || "".equals(ipValue)) {
			ipValue = "0.0.0.0";
		}
		this.longValue = changeIpFromStringToLong(ipValue);
	}
	/**
	 * 给定一个数字构建一个IP对象
	 * @param longValue
	 */
	public IP(Long longValue){
		this.longValue = longValue;
	}
	
	/**
	 * 给定一个数字构建一个IP对象
	 * @param value
	 */
	public IP(long value) {
		this((Long)value);
	}
	
	private String changeIpFromLongToString(Long ipValue)
	 {
	     long ip = ipValue.longValue();
	     if(ip < 0L)
	         ip += 0x100000000L;
	     long n1 = ip / 256L / 256L / 256L;
	     long s2 = n1 * 256L * 256L * 256L;
	     long n2 = (ip - s2) / 256L / 256L;
	     long s3 = n2 * 256L * 256L + s2;
	     long n3 = (ip - s3) / 256L;
	     long n4 = ip - n3 * 256L - s3;
	     StringBuilder ipStr = new StringBuilder();
	     ipStr.append(String.valueOf(n1 >= 0L ? n1 : 255L + n1)).append(".");
	     ipStr.append(n2 >= 0L ? n2 : 255L + n2).append(".");
	     ipStr.append(n3 >= 0L ? n3 : 255L + n3).append(".");
	     ipStr.append(n4 >= 0L ? n4 : 255L + n4);
	     return ipStr.toString();
	 }

	 /**
	  * 
	  * @param ip
	  * @return Long
	  * 目标: 将IP格式从String型转化为Long 型,如把255.255.255.0转化成4294967040L格式
	  */
	 private Long changeIpFromStringToLong(String ip)
	 {
	     String nodes[] = ip.split("\\.");
	     long node1 = (new Long(nodes[0])).longValue();
	     long node2 = (new Long(nodes[1])).longValue();
	     long node3 = (new Long(nodes[2])).longValue();
	     long node4 = (new Long(nodes[3])).longValue();
	     node1 *= 0x1000000L;
	     node2 *= 0x10000L;
	     node3 *= 256L;
	     Long rst = Long.valueOf(node1 + node2 + node3 + node4);
	     return rst;
	 }

	@Override
	public String toString() {
		return changeIpFromLongToString(this.longValue);
	}
	
	public Long toLong() {
		return this.longValue;
	}

	public long tolong() {
		return toLong().longValue();
	}
	
	@Override
	public boolean equals(Object x) {
		if(x instanceof IP) {
			IP v = (IP)x;
			return v.tolong() == this.tolong();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return toLong().hashCode();
	}

	@Override
	public Integer toInteger() {
		return this.longValue.intValue();
	}

}
