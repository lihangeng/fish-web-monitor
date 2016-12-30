package com.yihuacomputer.fish.web.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.file.DeleteFileDirectory;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;

/**
 * 清除web上下文中的tmp目录
 *
 * @author xuxigang
 *
 */
public class ClearFileService {

    private Logger log = LoggerFactory.getLogger(ClearFileService.class);

    private final String jobId = "deletetask";

    @Autowired
    private FishServletContext fishServletContext;

    @Autowired
    private DeleteFileDirectory deleteFileDirectory;

    @Autowired
    private IJobSynchService jobSynchService;

    /**
     * 清除临时文件
     */
    public void clearFile(){
    	log.info("clear temp file ....");
    	if(!jobSynchService.getJobRunPriviledge(jobId)){
    		log.info("deletetask job is run");
    		return;
    	}
        //删除tmp
        String path = getTmpPath();
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
        deleteFileDirectory.deleteTempDoc();
    	log.info("clear temp file finish....");

    }

    private String getTmpPath() {
        return fishServletContext.getServletContext().getRealPath("/tmp");
    }


}
