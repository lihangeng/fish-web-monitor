package com.yihuacomputer.fish.web.command.format;

public enum FileSysOperatorType {
	/**
	 * 新建文件夹
	 */
	MKDIR(0,"FileSysOperatorType.MKDIR"),
	/**
	 * 新建文件
	 */
	MKFILE(1,"FileSysOperatorType.MKFILE"),
	/**
	 * 删除文件
	 */
	DELFILE(2,"FileSysOperatorType.DELFILE"),
	/**
	 * 执行文件
	 */
	FILEEXEC(3,"FileSysOperatorType.FILEEXEC");
	private int id;
	private String text;
	FileSysOperatorType(int id,String text){
		this.id = id;
		this.text = text;
	}
	public static FileSysOperatorType getById(int id){
		FileSysOperatorType[] fileSOTs = FileSysOperatorType.values();
		for(FileSysOperatorType fSOT:fileSOTs){
			if(fSOT.id==id){
				return fSOT;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
	public int getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}

}
