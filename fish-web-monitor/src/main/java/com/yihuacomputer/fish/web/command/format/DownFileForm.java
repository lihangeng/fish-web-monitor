package com.yihuacomputer.fish.web.command.format;


public class DownFileForm
{
    private String termId;
    private String fileName;
    private String srcPath;
    private String desPath;
    private String ret;
    private String flag;
    private String md5;
    private boolean compress;
    
    public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public boolean isCompress() {
		return compress;
	}
	public void setCompress(boolean compress) {
		this.compress = compress;
	}
	public String getTermId()
    {
        return termId;
    }
    public void setTermId(String termId)
    {
        this.termId = termId;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getSrcPath()
    {
        return srcPath;
    }
    public void setSrcPath(String srcPath)
    {
        this.srcPath = srcPath;
    }
    public String getDesPath()
    {
        return desPath;
    }
    public void setDesPath(String desPath)
    {
        this.desPath = desPath;
    }
    public String getRet()
    {
        return ret;
    }
    public void setRet(String ret)
    {
        this.ret = ret;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
}
