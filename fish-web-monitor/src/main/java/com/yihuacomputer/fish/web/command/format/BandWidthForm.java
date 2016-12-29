package com.yihuacomputer.fish.web.command.format;

import com.yihuacomputer.common.http.HttpFileRet;

/**
 * @author YiHua
 *
 */
public class BandWidthForm {

    private long downLoadTime;

    private String flag;

    private String termId;

    private String fileName;

    private String srcPath;

    private String desPath;

    private HttpFileRet ret;
    
    private double filesize;

    public double getFilesize() {
        return filesize;
    }

    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    public long getDownLoadTime() {
        return downLoadTime;
    }

    public void setDownLoadTime(long downLoadTime) {
        this.downLoadTime = downLoadTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getDesPath() {
        return desPath;
    }

    public void setDesPath(String desPath) {
        this.desPath = desPath;
    }

    public HttpFileRet getRet() {
        return ret;
    }

    public void setRet(HttpFileRet ret) {
        this.ret = ret;
    }
}
