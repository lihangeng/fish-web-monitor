package com.yihuacomputer.fish.api.atmlog;

import java.io.File;
import java.util.List;

/**
 * 日志文件处理服务
 * @author YiHua
 *
 */
public interface IJournalFileService {
	
	/**
	 * 将多个日志文件合并为一个日志文件进行处理
	 * @param files
	 * @return
	 */
	public File combineJournal(List<File> files);
	
	/**
	 * 读取文件内容
	 * @param journalFile
	 * @return
	 */
	public List<IAtmCycle> readJournalFile(File journalFile);

}
