
package com.yihuacomputer.common.util;

import com.yihuacomputer.common.ITypeMAC;

/**
 * MAC地址
 *
 */
public class MAC implements ITypeMAC {
	private Long longValue;
	
	/**
	 * 无参构造方法
	 */
	public MAC(){
		this(0);
	}
	
	/**
	 * @param stringValue
	 */
	public MAC(String stringValue){
		String macValue = stringValue;
		if(macValue == null || "".equals(macValue)) {
			macValue = "0.0.0.0.0.0";
		}
		this.longValue = changeIpFromStringToLong(macValue);
	}
	
	/**
	 * @param longValue
	 */
	public MAC(Long longValue){
		this.longValue = longValue;
	}
	
	/**
	 * @param value
	 */
	public MAC(long value) {
		this((Long)value);
	}
	
	private String changeIpFromLongToString(Long macValue)
	 {
		 long mac = macValue.longValue();
	     if(mac < 0L)
	         mac += 0x1000000000000L;
	     long n1 = mac / 256L / 256L / 256L / 256L / 256L;
	     long s2 = n1 * 256L * 256L * 256L * 256L * 256L;
	     long n2 = (mac - s2) / 256L / 256L / 256L / 256L;
	     long s3 = n2 * 256L * 256L * 256L * 256L + s2; 
	     long n3 = (mac - s3) / 256L / 256L / 256L;
	     long s4 = n3 * 256L * 256L * 256L + s3;
	     long n4 = (mac - s4) / 256L / 256L;
	     long s5 = n4 * 256L * 256L +s4;
	     long n5 = (mac - s5) / 256L;
	     long n6 = mac - n5 * 256L - s5; 
	     return (new StringBuilder(String.valueOf(n1 >= 0L ? Long.toHexString(n1) : 255L + Long.toHexString(n1))))
	     .append("-").append(n2 >= 0L ? Long.toHexString(n2) : 255L + Long.toHexString(n2))
	     .append("-").append(n3 >= 0L ? Long.toHexString(n3) : 255L + Long.toHexString(n3))
	     .append("-").append(n4 >= 0L ? Long.toHexString(n4) : 255L + Long.toHexString(n4))
	     .append("-").append(n5 >= 0L ? Long.toHexString(n5) : 255L + Long.toHexString(n5))
	     .append("-").append(n6 >= 0L ? Long.toHexString(n6) : 255L + Long.toHexString(n6)).toString();     
	 }
	
	 private Long changeIpFromStringToLong(String mac)
	 {
	     String nodes[] = mac.split("\\-");
	     
	     long node1 = (Long.valueOf(nodes[0], 16).longValue());
	     long node2 = (Long.valueOf(nodes[1], 16).longValue());
	     long node3 = (Long.valueOf(nodes[2], 16).longValue());
	     long node4 = (Long.valueOf(nodes[3], 16).longValue());
	     long node5 = (Long.valueOf(nodes[4], 16).longValue());
	     long node6 = (Long.valueOf(nodes[5], 16).longValue());
     
	     node1 *= 0x10000000000L;
	     node2 *= 0x100000000L;
	     node3 *= 0x1000000L;
	     node4 *= 0x10000L;
	     node5 *= 256L;
	     Long rst = Long.valueOf(node1 + node2 + node3 + node4 + node5 + node6);
	     return rst;
	 }
	
	@Override
	public String toString() {
		String tempString = changeIpFromLongToString(this.longValue);
		StringBuilder result = new StringBuilder();
		for(String each : tempString.split("-")){
			if(each.length() != 2){
				each = "0".concat(each);
			}
			result.append("-").append(each);
		}
		return result.substring(1);
	}
	
	@Override
	public Long toLong() {
		return this.longValue;
	}
	
	@Override
	public Integer toInteger(){
		return toLong().intValue();
	}

	@Override
	public long tolong() {
		return toLong().longValue();
	}
	
	@Override
	public boolean equals(Object x) {
		if(x instanceof MAC) {
			MAC v = (MAC)x;
			return v.tolong() == this.tolong();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return toLong().hashCode();
	}
}
