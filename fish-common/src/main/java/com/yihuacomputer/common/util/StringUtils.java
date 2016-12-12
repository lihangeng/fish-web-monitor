package com.yihuacomputer.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 *
 * @author YiHua
 *
 */
public class StringUtils {
	/**
	 * 对字符串进行匹配替换
	 *
	 * @param desMatcher
	 *            需要匹配的字符串
	 * @param srcPattern
	 *            需要替换的源变量
	 * @param compile
	 *            替换之后的内容
	 * */
	public static String replaceLogRule(String desMatcher, String srcPattern, String compile) {
		Pattern pattern = Pattern.compile(srcPattern);
		Matcher matcher = pattern.matcher(desMatcher);

		return matcher.replaceAll(compile);
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}


	 /**
     * 前置零补位
     * @return
     */
	public static String preZeroStr(String srcStr,int length){
		String srcValue = srcStr;
		if(null==srcValue){
			srcValue="0";
		}
    	StringBuilder sb = new StringBuilder();
    	for(int index=0;index<length;index++){
    		sb.append("0");
    	}
    	String descStr = sb.append(srcValue).toString();
    	return descStr.substring(descStr.length()-length);
    }

    /**
     * 后置补空格
     * @return
     */
	public static String subBlankStr(String srcStr,int length){
		String srcValue = srcStr;
		if(null == srcValue){
			srcValue = "null";
		}
    	StringBuilder sb = new StringBuilder(srcValue);
    	for(int index=srcValue.length();index<length;index++){
    		sb.append(" ");
    	}
    	String descStr = sb.toString();
    	return descStr.substring(0,length);
    }
}
