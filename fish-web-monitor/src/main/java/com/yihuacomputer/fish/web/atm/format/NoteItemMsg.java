package com.yihuacomputer.fish.web.atm.format;

import java.util.List;

/**
 * 
 * @author YiHua
 * 
 */
public class NoteItemMsg {
    private int serial;
	
	private String crownId;
	
	private List<NoteItemMsg> noteItemList;
	
//	public String version;
//	
//	public String value;
//	
//	public String image;
	
	
	public NoteItemMsg() {
	}
	
	public List<NoteItemMsg> getNoteItemList()
    {
        return noteItemList;
    }
	public void setCrownId(String noteCode) {
		this.crownId = noteCode;
		
	}

	public String getCrownId() {
		return crownId;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		
	}
	
	public int getSerial() {
		return serial;
	}


}
