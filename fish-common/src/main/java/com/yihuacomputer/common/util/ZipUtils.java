package com.yihuacomputer.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 压缩工具
 * @author GQ
 *
 */
public class ZipUtils {

	private static Logger logger = LoggerFactory.getLogger(ZipUtils.class);
	private ZipUtils(){
		throw new IllegalAccessError("Utils Class");
	}
	
	// 缓存大小
	public static final int BUFFER = 1024;

	/**
	 * 压缩文件
	 * @param sourceFile  压缩的源文件
	 * @param targetZip 生成的目标文件
	 * @param encoding 压缩编码方式．默认GBK
	 * 
	 */
	public static void zip(String sourceFile, String targetZip, String encoding){
		List<File> fileList = getSubFiles(new File(sourceFile));
		ZipOutputStream zos =null;
		ZipEntry ze = null;	
		InputStream is=null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(targetZip));
			
			byte[] buf = new byte[BUFFER];
			int readLen = 0;
			for (int i = 0; i < fileList.size(); i++) {
				File f = fileList.get(i);
				ze = new ZipEntry(getAbsFileName(sourceFile, f));
				ze.setSize(f.length());
				ze.setTime(f.lastModified());
				zos.putNextEntry(ze);
				is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
					zos.write(buf, 0, readLen);
				}
				is.close();
			}
			zos.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			try {
				if (is!=null) {
					is.close();
				}
				if (zos!=null) {
					zos.close();
				}
			} catch (Exception e2) {
				logger.error(e2.getMessage());
			}
		}
	}
	
	

	/**
	 * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
	 * 
	 * @param baseDir 根目录
	 * @param realFileName 实际的文件名
	 * @return 相对文件名
	 */
	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		StringBuffer ret = new StringBuffer(real.getName());
		while (true) {
			real = real.getParentFile();
			if (real == null){
				break;
			}
			if (real.equals(base)){
				break;
			}else{
				ret .append(real.getName()).append(File.separator).append(ret);
			}
		}
		return ret.toString();
	}

	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir 指定的目录
	 * @return 包含java.io.File的List
	 */
	private static List<File> getSubFiles(File baseDir) {
		List<File> ret = new ArrayList<File>();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()){
				ret.add(tmp[i]);
			}
			if (tmp[i].isDirectory()){
				ret.addAll(getSubFiles(tmp[i]));
			}
		}
		return ret;
	}

	/**
	 * 解压缩功能. 将ZIP_FILENAME文件解压到ZIP_DIR目录下.
	 * 
	 * @throws Exception
	 */
	public static void unZip(String sourceZip, String destDir, String encoding){
		File file=new File(sourceZip);
		OutputStream os=null;
		InputStream is=null;
		if (!file.isFile() || !file.getName().endsWith(".zip")) {
			logger.error("The application only decompression zipFile");
		}else{
			destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
			byte b[] = new byte[1024];  
	        int length;  
	        ZipFile zipFile = null;
	        try {
				zipFile=new ZipFile(file);
				Enumeration<? extends ZipEntry> enumeration =zipFile.entries();
				ZipEntry zipEntry = null; 
				while (enumeration.hasMoreElements()) {
					zipEntry = enumeration.nextElement();
					File loadFile = new File(destDir + zipEntry.getName());
					//判断压缩文件中的某个条目是文件夹还是文件;如果是目录，那么判断该文件是否已存在并且不是一个文件夹,解决空文件夹解压后不存在的问题
					if (zipEntry.isDirectory()&&!loadFile.exists()) {
						loadFile.mkdirs();
					}else{
						if (!loadFile.getParentFile().exists()){  
	                        loadFile.getParentFile().mkdirs();  
	                    }  
						os=new FileOutputStream(loadFile);
						is = zipFile.getInputStream(zipEntry);
						while ((length = is.read(b)) > 0){
	                        os.write(b, 0, length);  
	                        os.flush();
	                     }
						os.close();
						is.close();
					}
				
				}
				zipFile.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}finally{
				try {
					if (os!=null) {
						os.close();
					}
					if (is!=null) {
						is.close();
					}
					if(zipFile!=null){
						zipFile.close();
					}
				} catch (Exception e2) {
					logger.error(e2.getMessage());
				}
			}
		}
	}

	/**
	 * 给定根目录，返回一个相对路径所对应的实际文件名.
	 * 
	 * @param baseDir 指定根目录
	 * @param absFileName 相对路径名，来自于ZipEntry中的name
	 * @return File 实际的文件
	 */
	public static File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split(File.separator);
		File ret = new File(baseDir);
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);
			}
			if (!ret.exists()){
				ret.mkdirs();
			}
			ret = new File(ret, dirs[dirs.length - 1]);
		}
		return ret;
	}
	

	/**
	 * 删除文件
	 *
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	private static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	/**
	 *
	 * @param sourcePath
	 *            源文件路径
	 * @param tempPath
	 *            临时文件路劲
	 * @param encoding
	 *            解码编码方式．默认GBK
	 * @param name
	 *            文件名称
	 * @param destDir
	 *            提取出文件路径
	 */
	public static void unZipByName(String sourcePath, String tempPath, String encoding, String name, String destDir) {

		unZip(sourcePath, tempPath, encoding);
		File file = new File(tempPath);
		find(file, name, destDir);
		delFolder(tempPath);
	}
	/**
	 * 找出路径下面的指定文件，并写到目标文件中
	 *
	 * @param file
	 * @param name
	 * @param desDir
	 */
	private static void find(File file, String name, String desDir) {
		String line = null;
		File[] files = file.listFiles();
		for(File item : files){
			if(item.isDirectory()){
				find(item, name, desDir);
			}else{
				if(item.getName().equals(name)){
					File newfile = new File(desDir);
					FileReader reader = null;
					FileWriter writer = null;
					BufferedReader br = null;
					BufferedWriter bw = null;
					try {
						reader = new FileReader(item);
						writer = new FileWriter(newfile.getPath() + File.separator + name);
						br = new BufferedReader(reader);
						bw = new BufferedWriter(writer);
						while ((line = br.readLine()) != null) {
							bw.write(line);
							bw.newLine();
							bw.flush();
						}
						bw.close();
						br.close();
						writer.close();
						reader.close();
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}finally{
						try {
							if(bw!=null){
								bw.close();
							}
							if(br!=null){
								br.close();
							}
							if(writer!=null){
								writer.close();
							}
							if(reader!=null){
								reader.close();
							}
						} catch (IOException e) {
							logger.error(e.getMessage());
						}
					}
				}
			}
		}
	}
	/**
	 *
	 * @param sourcePath
	 *            源文件路径
	 * @param tempPath
	 *            临时文件路劲
	 * @param encoding
	 *            解码编码方式．默认GBK
	 * @param name
	 *            文件名称
	 * @param destDir
	 *            提取出文件路径
	 */
	public static void unZipByNameUnDel(String sourcePath, String tempPath, String encoding, String name, String destDir) {

		unZip(sourcePath, tempPath, encoding);
		File file = new File(tempPath);
		find(file, name, destDir);
	}
	/**
	 * 是否是压缩文件
	 *
	 * @param zipFile
	 * @return
	 */
	public static boolean isZipFile(File zipFile) {
		ZipFile zip = null;
		try {
			zip = new ZipFile(zipFile);
			zip.close();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally{
			if(zip!=null){
				try {
					zip.close();
				} catch (IOException e1) {
					
					logger.error(e1.getMessage());
				}
			}
		}
		return false;
	}
}
