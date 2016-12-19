package com.yihuacomputer.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ftp操作工具类
 * 
 * @author xuxigang
 * 
 */
public class FtpUtils {
    
	private FtpUtils(){
		throw new IllegalAccessError("Utils Class");
	}

	private static final String ISOCHARSET="iso-8859-1";

	private static final String UTF8CHARSET="UTF-8";
	
	
    public static final Logger logger = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * 指定编码向FTP服务器上传文件
     * 
     * @param ip
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param localFilePath
     *            需要上传的本地文件路径
     * @param encode
     * 			  上传的编码格式
     * @return 成功返回true，否则返回false
     * @throws IOException
     */
    public static boolean uploadFile(String ip, int port, String username, String password, String path, String filename, String localFilePath,String encode) throws IOException {
        InputStream input;

        input = new FileInputStream(localFilePath);

        return uploadFile(ip, port, username, password, path, filename, input,encode);
    }
    
    /**
     * 未指定编码向FTP服务器上传文件
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param path
     * @param filename
     * @param localFilePath
     * @return
     * @throws IOException
     */
    public static boolean uploadFile(String ip, int port, String username, String password, String path, String filename, String localFilePath) throws IOException {
        return uploadFile(ip, port, username, password, path, filename, localFilePath,UTF8CHARSET);
    }


    /**
     * 向FTP服务器上传文件
     * 
     * @param ip
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param input
     *            输入流
     * @return 成功返回true，否则返回false
     * @throws IOException
     */
    public static boolean uploadFile(String ip, int port, String username, String password, String path, String filename, InputStream input,String encode) throws IOException {
        FTPClient ftp = login(ip, port, username, password);
        if (ftp == null)
            return false;
        // 转到指定上传目录
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        String str[] = path.split("/");
        String resultPath = "";
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            if (!"".equals(s)) {
            	sb.append(s).append("/");
                resultPath = sb.toString();
                if ((!"".equals(resultPath)) && (!isDirExist(resultPath, ftp))) {
                    // 创建文件夹
                    ftp.makeDirectory(resultPath);
                }
            }
        }
        ftp.enterLocalPassiveMode();
        ftp.changeWorkingDirectory(resultPath);
        // 将上传文件存储到指定目录
        ftp.storeFile(utfToiso8859(filename,encode), input);
        // 关闭输入流
        input.close();
        // 退出ftp
        ftp.logout();
        // 表示上传成功
        if (ftp.isConnected()) {
            ftp.disconnect();
        }
        return true;
    }

    /**
     * 判断文件夹是否存在
     * 
     * @param path
     * @param ftpClient
     * @return
     */
    private static boolean isDirExist(String path, FTPClient ftpClient) {
        try {
            return ftpClient.changeWorkingDirectory(path);
        }
        catch (Exception e) {
        	logger.error(String.format("%s", e));
            return false;
        }
    }

    /**
     * 判断文件是否存在
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param path
     * @param name
     * @return
     */
    public static boolean isFileExist(String ip, int port, String username, String password, String path, String name) {
        FTPClient ftp = login(ip, port, username, password);
        if (ftp == null)
            return false;
        try {
            ftp.changeWorkingDirectory(path);
            // 列出该目录下所有文件
            FTPFile[] fs = ftp.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile ff : fs) {
                String filename = new String(ff.getName().getBytes(ISOCHARSET), UTF8CHARSET);
                if (filename.equals(name)) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
        	logger.error(String.format("%s", e));
            return false;
        }
    }

    private static String utfToiso8859(Object obj,String encode) {
        try {
            if (obj == null)
                return "";
            else
                return new String(obj.toString().getBytes(encode), ISOCHARSET);
        }
        catch (Exception e) {
        	logger.error(String.format("charset convert error:[%s]", e));
            return "";
        }
    }

    /**
     * 从FTP服务器下载文件
     * 
     * @param ip
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String ip, int port, String username, String password, String remotePath, String fileName, String localPath) {
        // 初始表示下载失败
        boolean result = false;
        // 创建FTPClient对象
        FTPClient ftp = login(ip, port, username, password);
        OutputStream is = null;
        if (ftp == null)
            return false;
        try {
            ftp.changeWorkingDirectory(remotePath);
            // 列出该目录下所有文件
            FTPFile[] fs = ftp.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    // 根据绝对路径初始化文件
                    File localFile = new File(localPath + File.separator + ff.getName());
                    // 输出流
                    is = new FileOutputStream(localFile);
                    // 下载文件
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            // 退出ftp
            ftp.logout();
            // 下载成功
            result = true;
        }
        catch (IOException e) {
            logger.error(String.format("when download file with IOException：[%s]", e));
        }
        finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                }
                catch (IOException ioe) {
                    logger.error(String.format("when download file disconnect with IOException：[%s]", ioe));
                }
            }
            if(is!=null){
            	try {
					is.close();
				} catch (IOException e) {
					logger.error(String.format("when download file colse outputStream with IOException：[%s]", e));
				}
            }
        }
        return result;
    }

    /**
     * 下载文件，写入输出流
     * 
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param remotePath
     * @param fileName
     * @param is
     */
    public static void downloadFile(String ip, int port, String username, String password, String remotePath, String fileName, OutputStream is) {
        // 创建FTPClient对象
        FTPClient ftp = login(ip, port, username, password);
        try {
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(remotePath);
            // 列出该目录下所有文件
            FTPFile[] fs = ftp.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile ff : fs) {
                String name = new String(ff.getName().toString().getBytes(ISOCHARSET), UTF8CHARSET);
                if (name.equals(fileName)) {
                    // 下载文件
                    ftp.retrieveFile(ff.getName(), is);
                }
            }
        }
        catch (IOException e) {
            logger.error(String.format("when download file with IOException：[%s]", e));
        }
        finally {
            try {
                is.flush();
                is.close();
            }
            catch (IOException e) {
                logger.error(String.format("when close fileOutputStream with IOException：[%s]", e));
            }
            disconnectFtp(ftp);
        }
    }

    /**
     * 列出远程路径下的文件列表
     * 
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param remotePath
     * @return
     */
    public static FTPFile[] listFilesByPath(String ip, int port, String username, String password, String remotePath) {
        FTPClient ftp = login(ip, port, username, password);
        return listFilesByPath(ftp, remotePath);
    }

    /**
     * 列出远程路径下的文件列表
     * 
     * @param ftp
     * @param remotePath
     * @return
     */
    public static FTPFile[] listFilesByPath(FTPClient ftp, String remotePath) {
        try {
            boolean success = ftp.changeWorkingDirectory(remotePath);
            if (success) {
                return ftp.listFiles();
            }
            else {
                return new FTPFile[0];
            }
        }
        catch (IOException e) {
            logger.error(String.format("when list subFile with IOException [%s]", e));
            return new FTPFile[0];
        }
        finally {
            disconnectFtp(ftp);
        }
    }

    /**
     * 登录ftp
     * 
     * @param ip
     *            ftp ip地址
     * @param port
     *            端口
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 登录成功返回 FTPClient对象，否则返回null
     */
    private static FTPClient login(String ip, int port, String username, String password) {
        try {
            // 创建FTPClient对象
            FTPClient ftp = new FTPClient();
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(ip)的方式直接连接FTP服务器
            ftp.connect(ip, port);
            // 登录ftp
            ftp.login(username, password);
            // 看返回的值是不是230，如果是，表示登陆成功,以2开头的返回值就会为真
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                return null;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            return ftp;
        }
        catch (SocketException e) {
            logger.error(String.format("when login fpt with SocketException ：[%s]", e));
            return null;
        }
        catch (IOException e) {
            logger.error(String.format("when login fpt with IOException：[%s]", e));
            return null;
        }
    }

    /**
     * 断开FTP链接
     * 
     * @param ftp
     */
    private static void disconnectFtp(FTPClient ftp) {
        if (ftp != null && ftp.isConnected()) {
            try {
                // 退出ftp
                ftp.logout();
                ftp.disconnect();
            }
            catch (IOException ioe) {
                logger.error(String.format("when logout FTP with IOException：[%s]", ioe));
            }
        }
    }

}
