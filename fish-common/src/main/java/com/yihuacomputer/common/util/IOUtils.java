package com.yihuacomputer.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.yihuacomputer.common.exception.AppException;

public class IOUtils {

    /**
     * 创建一个文件，如果目录不存在会自动创建
     * 
     * @param fileName
     * @return
     */
    public static File createFile(String fileName) {
        try {
            File file = new File(fileName);
            FileUtils.touch(file);
            return file;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        throw new AppException("文件创建时失败:" + fileName);
    }

    /**
     * 得到一个文件的后缀
     * 
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        String file = fileName;
        return file.substring(file.lastIndexOf("."));
    }

    public static String getFilePrefix(String fileName) {
        String file = fileName;
        return file.substring(0, file.lastIndexOf("."));
    }

    public static String addTimeStampInFileName(String fileName) {
        String file = fileName;
        return getFilePrefix(file) + "_" + DateUtils.get(new Date(),"yyyyMMddhhmmss") + getFileSuffix(file);
    }

    public static void writeStringToFile(String fileName, String data) {
        try {
            FileUtils.writeStringToFile(createFile(fileName), data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFileToDirectory(String srcFileName, String desDir) {
        try {
            FileUtils.copyFileToDirectory(new File(srcFileName), new File(desDir));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moveFile(String resFilePath, String distFolder) throws IOException {
        File resFile = new File(resFilePath);
        File distFile = new File(distFolder);
        if (resFile.isDirectory()) {
            FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
        }
        else if (resFile.isFile()) {
            FileUtils.moveFileToDirectory(resFile, distFile, true);
        }
    }

    public static void copyDirToDir(String srcDir, String destDir) {
        try {
            FileUtils.copyDirectory(new File(srcDir), new File(destDir));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDir(String dir) {
        try {
            FileUtils.deleteDirectory(new File(dir));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static File createDir(String path){
    	 try {
             File file = new File(path);
             if(file.exists()&&file.isDirectory()){
            	 return file;
             }
             file.mkdirs();
             return file;
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         throw new AppException("文件夹创建时失败:" + path);
    }
    
    public static void main(String...args){
        String fileName = "adfdfd_dfe-L.jpg";
        System.out.println(IOUtils.createFile("d:\\e\\f\\d\\g.txt"));
        System.out.println(fileName);
    }

}
