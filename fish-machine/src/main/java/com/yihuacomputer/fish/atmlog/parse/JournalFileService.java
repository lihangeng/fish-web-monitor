package com.yihuacomputer.fish.atmlog.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.atmlog.BizJournal;
import com.yihuacomputer.fish.api.atmlog.IAtmCycle;
import com.yihuacomputer.fish.api.atmlog.ICustomerCycle;
import com.yihuacomputer.fish.api.atmlog.IJournalFileService;
import com.yihuacomputer.fish.atmlog.entity.AtmCycle;
import com.yihuacomputer.fish.atmlog.entity.CustomerCycle;
import com.yihuacomputer.fish.atmlog.entity.TransCycle;

/**
 * 日志文件处理
 * */
@Service
public class JournalFileService implements IJournalFileService{

	@Autowired
	private JournalParser journalParser = new JournalParser();
	
	private Logger logger = LoggerFactory.getLogger(JournalFileService.class);
	/**
	 * 将多个日志文件合并为一个日志文件进行处理
	 * @param files
	 * @return
	 */
	@Override
	public File combineJournal(List<File> files) {
		File journalFile = new File(FishCfg.getTempDir()+FishCfg.FILESEP+UUID.randomUUID().toString());
		FileChannel mFileChannel = null;
		FileChannel inFileChannel = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(journalFile);
			mFileChannel = fos.getChannel();

			for (File dayFile : files) {
				fis = new FileInputStream(dayFile);
				inFileChannel = fis.getChannel();
				inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);
				fis.close();
				inFileChannel.close();
			}
			fos.close();
			mFileChannel.close();
		} catch (Exception e) {
			logger.error(String.format("Exception is %s", e));
			return null;
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
			}
			if (mFileChannel != null) {
				try {
					mFileChannel.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
			}
			if (inFileChannel != null) {
				try {
					inFileChannel.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
			}
		}
		return journalFile;
	}
	
	/**
	 * 读取文件内容
	 * @param journalFile
	 * @return
	 */
	@Override
	public List<IAtmCycle> readJournalFile(File journalFile){

		List<IAtmCycle> atmCycles = new ArrayList<IAtmCycle>();
		
		List<ICustomerCycle> customers = new ArrayList<ICustomerCycle>();;
		
		AtmCycle atmCycle = new AtmCycle();
		atmCycle.setCashInId("0");
		atmCycle.setCustomers(customers);
		
		CustomerCycle customerCycle =null;
		TransCycle transCycle =null;
		
        BufferedReader bufferedReader = null;
        String content = null;
        StringBuffer contents = new StringBuffer();
        BizJournal lastJournal = null;
        InputStreamReader isr = null;
        FileInputStream fis = null;
        
        try {
            /* 读文件内容 */
        	fis = new FileInputStream(journalFile);
        	isr = new InputStreamReader(fis,"GBK");
            bufferedReader = new BufferedReader(isr);

            while ((content = bufferedReader.readLine()) != null) {
            	BizJournal journal = journalParser.readLineJournalLog(content);
            	if(journal!=null){            		
            		if(lastJournal!=null){	            		
	                	switch(lastJournal){
	                	case TRANS:{
	                		transCycle = journalParser.readTransBiz(contents.toString());
	                		if(customerCycle!=null&&transCycle!=null){
	                			customerCycle.getTrans().add(transCycle);
	                		}
	            		}
	                	break;
	            		case CUSTOMER:{
	            			customerCycle = journalParser.readCustomer(contents.toString());
	            			if(atmCycle!=null&&customerCycle!=null){
	            				atmCycle.getCustomers().add(customerCycle);
	            			}
	            		}
	            		break;
	            		case CASHIN:{
	            			atmCycle = journalParser.readCashIn(contents.toString());
	            			atmCycles.add(atmCycle);
	            		}}
                	}
            		lastJournal=journal;
                	contents.setLength(0);
            	}          
            	if(lastJournal!=null){
            		contents.append(content);    
                	contents.append("\n");
            	}            	
            }
            if(lastJournal!=null&&contents.length()>0){                       
                switch(lastJournal){
                case TRANS:{
                    transCycle = journalParser.readTransBiz(contents.toString());
                    if(customerCycle!=null&&transCycle!=null){
                        customerCycle.getTrans().add(transCycle);
                    }
                }
                break;
                case CUSTOMER:{
                    customerCycle = journalParser.readCustomer(contents.toString());
                    if(atmCycle!=null&&customerCycle!=null){
                        atmCycle.getCustomers().add(customerCycle);
                    }
                }
                break;
                case CASHIN:{
                    atmCycle = journalParser.readCashIn(contents.toString());
                    atmCycles.add(atmCycle);
                }}
            }
            fis.close();
            isr.close();
            bufferedReader.close();
        }
        catch (Exception ex) {
        	logger.error(String.format("Exception is [%s]", ex));
        }finally{
        	if(fis!=null){
        		try {
        			fis.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
        	}
        	if(isr!=null){
        		try {
        			isr.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
        	}
        	if(bufferedReader!=null){
        		try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(String.format("IOException is [%s]", e));
				}
        	}
        }

		return atmCycles;		
	}
}
