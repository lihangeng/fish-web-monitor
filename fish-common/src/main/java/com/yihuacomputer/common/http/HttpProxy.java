package com.yihuacomputer.common.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.exception.ConnectionException;
import com.yihuacomputer.common.jackson.JsonUtils;

/**
 * Http请求代理工具类
 * @author GQ
 *
 */
public class HttpProxy {
	
	private static final int CONNECTION_TIMEOUT = 30000;
	
	private static Logger logger = LoggerFactory.getLogger(HttpProxy.class);

	private static final String CONTENT_TYPE="Content-Type";

	private static final String CONTENT_TYPE_VALUE="application/json;charset=UTF-8";
	
	private HttpProxy(){
		throw new IllegalAccessError("Utils Class");
	}
	/**
	 * 负责向客户端发送信息，并接受客户端的返回
	 * 
	 * @param url
	 * @param classOfT
	 * @return
	 */
	public static Object httpGet(String url, Class<?> classOfT) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = null;
		HttpResponse resp = null;
		InputStream stream = null;
		try {
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HttpProxy.CONNECTION_TIMEOUT);
			get = new HttpGet(url);
			resp = client.execute(get);
			stream = resp.getEntity().getContent();
			if (classOfT != null) {
				return JsonUtils.inputStreamToObject(stream, classOfT);
			} else {
				return stream;
			}
		} catch (Exception e) {
			throw new ConnectionException(String.format("Connect to ATM Failed[%s]",e.getMessage()),e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					logger.error(String.format("Close Connection Failed[%s]",e));
				}
			}
		}
	}

    /**
     * 负责向客户端发送信息，并接受客户端的返回
     * @param url
     * @param classOfT
     * @param connectionTimeout
     * @return
     */
    public static Object httpGet(String url, Class<?> classOfT, int connectionTimeout) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = null;
        HttpResponse resp = null;
        InputStream stream = null;
        try {
            
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
            
            get = new HttpGet(url);
            
            resp = client.execute(get);
            stream = resp.getEntity().getContent();
            if (classOfT != null) {
                return JsonUtils.inputStreamToObject(stream, classOfT);
            } else {
                return stream;
            }
        } catch (Exception e) {
            throw new ConnectionException(String.format("Connect to ATM Failed[%s]",e.getMessage()),e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error(String.format("Close Connection Failed[%s]",e));
                }
            }
        }
    }
	
	
	/**
	 * 负责向客户端发送信息，并接受客户端的返回
	 * 
	 * @param url
	 * @param msg
	 * @param classOfT
	 * @return
	 */
	public static Object httpPost(String url, Object msg, Class<?> classOfT) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = null;
		HttpResponse resp = null;
		InputStream stream = null;
		StringEntity entity = null;
		try {
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HttpProxy.CONNECTION_TIMEOUT);

			post = new HttpPost(url);
			entity = new StringEntity(JsonUtils.toJsonWithGson(msg), Charsets.UTF_8.name());
			
			Header header = new BasicHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
			entity.setContentType(header);
			
			post.setEntity(entity);
			resp = client.execute(post);
			stream = resp.getEntity().getContent();
			if (classOfT != null) {
				return JsonUtils.inputStreamToObject(stream, classOfT);
			} else {
				return stream;
			}
		} catch (Exception e) {
			throw new ConnectionException(String.format("connectting ATM Fail[%s]",e));
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					logger.error(String.format("[%s]", e));
				}
			}
		}
	}
	/**
     * 负责向客户端发送信息，并接受客户端的返回
     * 
     * @param url
     * @param msg
     * @param classOfT
     * @param connectionTimeout
     * @return
     */
    public static Object httpPost(String url, Object msg, Class<?> classOfT, int connectionTimeout) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = null;
        HttpResponse resp = null;
        InputStream stream = null;
        StringEntity entity = null;
        try {
            
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
            
            post = new HttpPost(url);
            entity = new StringEntity(JsonUtils.toJsonWithGson(msg), Charsets.UTF_8.name());
            
            Header header = new BasicHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
            entity.setContentType(header);
            
            post.setEntity(entity);
            resp = client.execute(post);
            stream = resp.getEntity().getContent();
            if (classOfT != null) {
                return JsonUtils.inputStreamToObject(stream, classOfT);
            } else {
                return stream;
            }
        } catch (Exception e) {
            throw new ConnectionException(String.format("connectting ATM Fail[%s]",e));
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                	logger.error(String.format("[%s]", e));
                }
            }
        }
    }
    /**
     * 负责向客户端发送信息，并接受客户端的返回
     * 
     * @param url
     * @param msg
     * @param classOfT
     * @param connectTimeOut
     *            连接超时
     * @param soTimeOut
     *            响应超时
     * @return
     */
    public static Object httpPost(String url, Object msg, Class<?> classOfT, int connectTimeOut, int soTimeOut) {
        DefaultHttpClient client = new DefaultHttpClient();

        // 请求超时
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeOut);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOut);

        HttpPost post = null;
        HttpResponse resp = null;
        InputStream stream = null;
        StringEntity entity = null;
        try {
            post = new HttpPost(url);
            entity = new StringEntity(JsonUtils.toJsonWithGson(msg), Charsets.UTF_8.name());

            Header header = new BasicHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE);
            entity.setContentType(header);

            post.setEntity(entity);
            resp = client.execute(post);
            stream = resp.getEntity().getContent();
            if (classOfT != null) {
                return JsonUtils.inputStreamToObject(stream, classOfT);
            } else {
                return stream;
            }
        } catch (Exception e) {
            throw new ConnectionException(String.format("connectting ATM Fail[%s]", e),e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                	logger.error(String.format("[%s]", e));
                }
            }
        }
    }
}
