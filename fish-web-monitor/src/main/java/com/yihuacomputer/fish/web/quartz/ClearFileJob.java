package com.yihuacomputer.fish.web.quartz;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.file.DeleteFileDirectory;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;
import com.yihuacomputer.fish.web.util.ClearFileService;
import com.yihuacomputer.fish.web.util.FishServletContext;

/**
 * 定期清理web运行过程中的临时文件等
 * @author xuxiang
 * @since 2.1.1.1
 *
 */
public class ClearFileJob extends AbstractYihuaJob{
	
    private Logger log = LoggerFactory.getLogger(ClearFileService.class);

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		FishServletContext fishServletContext = super.getApplicationContext().getBean(FishServletContext.class);
		String path = getTmpPath(fishServletContext);
        File tmpFile = new File(path);
        File []files  = tmpFile.listFiles();
        if(files != null){
            for(File file : files){
                try {
                    FileUtils.deleteDirectory(file);
                }
                catch (IOException e) {
                   log.error(String.format("delete dir [%s] Failed [%s]",file.getName(),e));
                }
            }
        }

        //删除工作区下的temp
        this.deleteTempDoc();
    	log.info("clear temp file finish....");
		
	}
	
	private String getTmpPath(FishServletContext fishServletContext) {
        return fishServletContext.getServletContext().getRealPath("/tmp");
    }
	
	private void deleteDir(String path){
	    File f=new File(path);
	    if(f.isDirectory()){//如果是目录，先递归删除
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	        	DeleteFileDirectory.deleteDir(path+"//"+list[i]);//先删除目录下的文件
	        }
	    }       
	    f.delete();
	}
	
	/**
	 * 删除临时目录
	 */
	private void deleteTempDoc(){
		this.deleteDir(FishCfg.getTempDir());
		
		File tempDir = new File(FishCfg.getTempDir());
		if (!tempDir.isDirectory()) {
			tempDir.mkdir();
		}

	}

}
