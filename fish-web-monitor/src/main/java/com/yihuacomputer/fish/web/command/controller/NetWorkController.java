package com.yihuacomputer.fish.web.command.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.BandWidthForm;
import com.yihuacomputer.fish.web.command.format.NetWorkForm;

/**
 * 网络连接信息.
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent/netWork")
public class NetWorkController {

    private Logger logger = LoggerFactory.getLogger(NetWorkController.class);

    /**
     * 测试网络速度文件
     */
    public static String FILENAME_TEST = "NetWorkTest.T";

    @RequestMapping(value = "/getNetWork", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap searchNetWork(@RequestParam String ip) {

        ModelMap result = new ModelMap();
        try {
            String url = MonitorCfg.getHttpUrl(ip) + "/ctr/network";
            NetWorkForm netWork = (NetWorkForm) HttpProxy.httpGet(url, NetWorkForm.class, 5000);
            result.addAttribute(FishConstant.SUCCESS, true);
            String conenctRate = String.format("%,d", netWork.getConenctRate() / 1000000);
            String receivedByte = String.format("%,d", netWork.getReceivedByte());
            String sendByte = String.format("%,d", netWork.getSendByte());
            // String rate = String.valueOf(Double.parseDouble(conenctRate));
            result.addAttribute("conenctRate", conenctRate);
            result.addAttribute("receivedByte", receivedByte);
            result.addAttribute("sendByte", sendByte);
        }
        catch (Exception e) {
            logger.error(String.format("NetWork request is error! ip is [%s],Exception is :[%s]", ip,e));
            result.addAttribute(FishConstant.SUCCESS, false);
        }

        return result;
    }

    @RequestMapping(value = "/bandwidth", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap netWorkBandwidth(@RequestParam String ip) {
        ModelMap result = new ModelMap();

        String testFileStr = FishCfg.getTempDir() + File.separator + FILENAME_TEST;
        File testFile = new File(testFileStr);
        writeFile(testFile);

        try {
            String url = MonitorCfg.getHttpUrl(ip) + "/ctr/bandwidth";

            logger.info(url);

            BandWidthForm bwf = new BandWidthForm();

            bwf.setFileName(FILENAME_TEST);
            bwf.setDesPath(null); // 设置为null,默认选择ATM的临时文件夹
            bwf.setSrcPath(FishCfg.getTempDir());

            BandWidthForm responseBwf = (BandWidthForm) HttpProxy.httpPost(url, bwf, BandWidthForm.class, 5000);


            double d = testFile.length() * 1.0 / 1024;

            responseBwf.setFilesize(d);

            result.addAttribute("data", responseBwf);
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception e) {
            logger.error(String.format("Error test bandwidth! IP is [%s]", ip));
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }

    private void writeFile(File file) {
        FileWriter fw = null;
        try {
            if (!file.exists()) {

                // 给测试文件写入内容
                fw = new FileWriter(file);
                for (int i = 0; i < 10000; i++) {
                    fw.write("#~!@$%^&*()");
                }
            }
        }
        catch (Exception e) {
            logger.error(String.format("write test file error! file : [%s]", file.getAbsoluteFile()));
        }
        finally {
            if (fw != null) {
                try {
                    fw.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
//        NetWorkController nwc = new NetWorkController();
//        nwc.netWorkBandwidth("192.18.30.38");
        System.out.println(FishCfg.getTempDir());

    }
}
