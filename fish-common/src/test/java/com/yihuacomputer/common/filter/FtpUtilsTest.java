package com.yihuacomputer.common.filter;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.yihuacomputer.common.ftp.FtpUtils;

public class FtpUtilsTest {

    @Test
    @Ignore
    public void testUploadFile() {
        try {
            FtpUtils.uploadFile("192.168.0.224", 21, "yihua", "yihua123", "/home/yihua/", "sms.txt", "D:/a.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
