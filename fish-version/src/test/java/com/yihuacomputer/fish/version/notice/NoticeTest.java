package com.yihuacomputer.fish.version.notice;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Ignore;
import org.junit.Test;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.version.notice.NoticeForm;

public class NoticeTest {
	@Test
	@Ignore
	public void testNotice(){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:8080/ctr/patchpush");
		NoticeForm notice = new NoticeForm();
		notice.setPatch("test");
		notice.setPatchNo("1.0");
		notice.setPrePatchNo("");
		notice.setServerPath("D:/yihua/web");
		notice.setLocalPath("D:/yihua/atm");
		notice.setFileName("ext-4.0.7-gpl.zip");
		notice.setFileSize("0");
		String json = JsonUtils.toJson(notice);
//		String json = "{'path':'D:/','offset':0,'limit':10}";
		try {
			StringEntity reqEntity = new StringEntity(json,"UTF-8");
			post.setEntity(reqEntity);
			
			HttpResponse resp = client.execute(post);
			InputStream is = resp.getEntity().getContent();
			String content = JsonUtils.inputStreamToString(is);
			System.out.println(content);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
