package com.yihuacomputer.common.http;

public enum HttpFileRet {
    SUCCESS("成功"), // 成功
    CFG_ERROR("参数配置出错"), // 参数配置出错
    REQ_FILE_NOTEXIT("请求文件不存在"), // 请求文件不存在
    CONN_ERROR("通信出错"), // 通信出错
    URL_ERROR("URL出错"), // URL出错
    ERROR("文件下载失败");// 文件下载失败

    private String text;

    private HttpFileRet(String text) {
        this.text = text;
    }

    public static HttpFileRet get(String ret) {
        for (HttpFileRet httpRet : HttpFileRet.values()) {
            if (httpRet.name().equals(ret)) {
                return httpRet;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", ret));
    }

    public String getText() {
        return text;
    }

}
