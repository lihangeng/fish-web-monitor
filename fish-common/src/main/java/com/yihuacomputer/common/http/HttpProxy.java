package com.yihuacomputer.common.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;

import com.yihuacomputer.common.exception.ConnectionException;
import com.yihuacomputer.common.jackson.JsonUtils;

public class HttpProxy {
	
	private static int CONNECTION_TIMEOUT = 30000;
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
			throw new ConnectionException(String.format("Connect to ATM Failed[%s]",e.getMessage()));
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new ConnectionException(String.format("Close Connection Failed[%s]",e));
				}
			}
		}
	}

	/**
     * 负责向客户端发送信息，并接受客户端的返回
     * 
     * @param url
     * @param classOfT
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
            throw new ConnectionException(String.format("Connect to ATM Failed[%s]",e.getMessage()));
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new ConnectionException(String.format("Close Connection Failed[%s]",e));
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
			entity = new StringEntity(JsonUtils.toJsonWithGson(msg), "UTF-8");
			
			Header header = new BasicHeader("Content-Type", "application/json;charset=UTF-8");
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
					e.printStackTrace();
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
    public static Object httpPost(String url, Object msg, Class<?> classOfT, int connectionTimeout) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = null;
        HttpResponse resp = null;
        InputStream stream = null;
        StringEntity entity = null;
        try {
            
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
            
            post = new HttpPost(url);
            entity = new StringEntity(JsonUtils.toJsonWithGson(msg), "UTF-8");
            
            Header header = new BasicHeader("Content-Type", "application/json;charset=UTF-8");
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
                    e.printStackTrace();
                }
            }
        }
    }
}
