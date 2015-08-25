package com.yihuacomputer.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileMD5 {
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8 * 1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 8 * 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);

    }
    
    public static void main(String ...args){
     /*   File file = new File("d:/c3p0-0.9.1.1.jar");
        System.out.println(FileMD5.getFileMD5(file));*/
    }
}
