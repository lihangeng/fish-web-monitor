package com.yihuacomputer.fish.web.command.controller;

import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.StringUtils;
import com.yihuacomputer.fish.api.monitor.business.CommandResult;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;

public class RemoteCommandRunnable implements Runnable {

    private String url;

    private Object param;

    private String method;

    private long id;

    private IRemoteCommHistService remoteCommHistService;

    public RemoteCommandRunnable() {
    }

    public RemoteCommandRunnable(String url, Object param, String method, long id,
            IRemoteCommHistService remoteCommHistService) {
        this.url = url;
        this.param = param;
        this.method = method;
        this.id = id;
        this.remoteCommHistService = remoteCommHistService;
    }

    @Override
    public void run() {
        try {

            if (StringUtils.isEmpty(method) || "GET".equals(method)) {
                HttpProxy.httpGet(url, null, 5000);
            } else {
                HttpProxy.httpPost(url, param, null, 5000);
            }
        }
        catch (Exception e) {

            IRemoteCommHist hist = remoteCommHistService.get(id);
            hist.setCommandResult(CommandResult.CONNECT_FAIL);
            remoteCommHistService.update(hist);
        }

    }
}
