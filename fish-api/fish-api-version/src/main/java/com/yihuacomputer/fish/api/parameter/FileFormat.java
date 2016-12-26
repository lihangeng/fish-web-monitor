package com.yihuacomputer.fish.api.parameter;

/**
 * @author YiHua
 *
 */
public enum FileFormat {
	JSON("FileForm.JSON",0),
	
	PROPERTIES("FileForm.PROPERTIES",1),
	
	INI("FileForm.INI",2),
	
	XML("FileForm.XML",3);
	
	private int id;
	private String text;
	
	private FileFormat(String text, int id) {
		this.text = text;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	/**
	 * @param id
	 * @return
	 */
	public static FileFormat getById(int id){
		for(FileFormat each : FileFormat.values()){
			if(each.getId()==id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%id] error", id));
	}
}
