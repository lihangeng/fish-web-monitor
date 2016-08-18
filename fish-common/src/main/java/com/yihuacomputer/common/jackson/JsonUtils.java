package com.yihuacomputer.common.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
/**
 * JSON格式转换工具类
 * @author xuxiang
 *
 */
public class JsonUtils {
	public static final ObjectMapper om = new ObjectMapper();

	public static final Gson gson = new Gson();
	
	private static Logger logger = LoggerFactory.getLogger(HttpProxy.class);

	static{
		om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		om.setDateFormat(new SimpleDateFormat(DateUtils.STANDARD_TIMESTAMP));
	}
	public JsonUtils(){
	}
	/**
	 * 将post提交的数据流转换为对象
	 *
	 * @param <T>
	 * @param in
	 *            数据流
	 * @param classOfT
	 *            the class of T
	 * @return 从字符串类型T的对象
	 * @throws IOException
	 */
	public static <T> T inputStreamToObject(InputStream in, Class<T> classOfT) throws IOException {
		return gson.fromJson(JsonUtils.inputStreamToString(in), classOfT);
	}

	public static String toJsonWithGson(Object object){
		String jsonStr = "";
		try {
			jsonStr = gson.toJson(object);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return jsonStr;
		}
		return jsonStr;
	}

	/**
	 * 将post提交的数据流转换为字符串
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream in) throws IOException {
		byte[] buffer = new byte[4096];

		List<byte[]> lists = new ArrayList<byte[]>();
		int len;
		int count = 0;

		while ((len = in.read(buffer)) != -1) {
			byte[] temp = new byte[len];
			System.arraycopy(buffer, 0, temp, 0, len);
			lists.add(temp);
			count += len;
		}
		//读取太大的文件夹时内存会溢出，控制10M
		if(count>1024*1024){
			return null;
		}
		byte[] result = new byte[count];
		int destPos = 0;
		for (byte[] bytes : lists) {
			System.arraycopy(bytes, 0, result, destPos, bytes.length);
			destPos = destPos + bytes.length;
		}
		return new String(result, "UTF-8");
	}

	/**
	 * 将json格式的字符串,转换成对象
	 *
	 * @param <T>
	 *            所需的对象类型
	 * @param json
	 *            从该对象的反序列化的字符串
	 * @param classOfT
	 *            the class of T
	 * @return 从字符串类型T的对象
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		try {
			om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return om.readValue(json, classOfT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 将对象转换成json格式的字符串
	 *
	 * @param src
	 *            JSON表示要创建的对象的设置Gson
	 * @return src的JSON表示。
	 */
	public static String toJson(Object src) {
		String jsonStr = "";
		try {
			jsonStr = om.writeValueAsString(src);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return jsonStr;
		}
		return jsonStr;
	}

	/**
	 * 将路径和文件名拼装成完整路径
	 *
	 * @param path
	 *            路径
	 * @param fileName
	 *            文件名称
	 * @return 完整路径
	 */
	public static String stringToStringPath(String path, String fileName) {
		// 拼装文件路径
		StringBuilder result = new StringBuilder();
		result.append(path);
		if (!(path.endsWith("\\") || path.endsWith("/"))) {
			result.append("/");
		}
		result.append(fileName);
		return result.toString();
	}

	/**
	 * 从json格式中取出指定字段的value值
	 *
	 * @param json
	 *            json字符串
	 * @param name
	 *            字段名称
	 * @return 指定字段value
	 */
	public static String jsonValue(String json, String name) {
		String result = "";
		try {
			JsonNode jsonNode = om.readTree(json);
			result = jsonNode.get(name).toString();
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
