package com.yihuacomputer.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算文件MD5值
 * @author GQ
 *
 */
public class FileMD5 {

	private static Logger logger = LoggerFactory.getLogger(FileMD5.class);
    /**
     * 计算文件MD5值
     * @param file
     * @return
     */
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
        catch (NoSuchAlgorithmException e) {
        	logger.error(e.getMessage());
            return "0";
        }
        catch( FileNotFoundException e){
        	logger.error(e.getMessage());
        	return "0";
        }
        catch( IOException e){
        	logger.error(e.getMessage());
        	return "0";
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);

    }
    
  
}
