package com.yihuacomputer.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.ITypeIP;

public class IPUtils {

	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
    public static List<ITypeIP> listLocalIp(){
        List<ITypeIP> ipList = new ArrayList<ITypeIP>();
        Enumeration<NetworkInterface> netInterfaces = null;  
        try {  
            netInterfaces = NetworkInterface.getNetworkInterfaces();  
            while (netInterfaces.hasMoreElements()) {  
                NetworkInterface ni = netInterfaces.nextElement();  
                Enumeration<InetAddress> ips = ni.getInetAddresses();  
                while (ips.hasMoreElements()) {  
                    String ip = ips.nextElement().getHostAddress();
                    if(isValidIp(ip)){
                        ipList.add(new IP(ip));
                    }
                }  
            }  
        } catch (Exception e) {  
        	logger.error(String.format("[%s]", e));
        }  
        return ipList;
    }
    
    private static boolean isValidIp(String ip){
        if(ip.contains(":")){
            return false;
        }
        if("127.0.0.1".equals(ip)){
            return false;
        }
        return true;
    }
}
