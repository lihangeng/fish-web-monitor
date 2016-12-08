package com.yihuacomputer.common.file;

import java.io.File;

import com.yihuacomputer.common.FishCfg;

/**
 * 删除文件目录工具
 * @author GQ
 *
 */
public class DeleteFileDirectory {

	public static void deleteDir(String path){
	    File f=new File(path);
	    if(f.isDirectory()){//如果是目录，先递归删除
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	        	DeleteFileDirectory.deleteDir(path+"//"+list[i]);//先删除目录下的文件
	        }
	    }       
	    f.delete();
	}
	
	public void deleteTempDoc(){
		DeleteFileDirectory.deleteDir(FishCfg.getTempDir());
		
		File tempDir = new File(FishCfg.getTempDir());
		if (!tempDir.isDirectory()) {
			tempDir.mkdir();
		}

	}
}
