package com.yihuacomputer.fish.api.parameter;

public enum FileForm {
	JSON("FileForm.JSON",0),
	
	PROPERTIES("FileForm.PROPERTIES",1),
	
	INI("FileForm.INI",2),
	
	XML("FileForm.XML",3);
	
	private int id;
	private String text;
	
	private FileForm(String text, int id) {
		this.text = text;
		this.id = id;
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
	
	public static FileForm getById(int id){
		for(FileForm each : FileForm.values()){
			if(each.getId()==id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%id] error", id));
	}
}
