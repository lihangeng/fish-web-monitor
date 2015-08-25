package com.yihuacomputer.fish.web.command.format;

public enum FileSysOperatorType {
	MKDIR(0,"新建文件夹"),
	MKFILE(1,"新建文件"),
	DELFILE(2,"删除文件"),
	FILEEXEC(3,"执行文件");
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
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
