package com.yihuacomputer.fish.api.monitor.business;
/**
 * 
 * @author wangchao
 *		证件类型
 */
public enum IDCardType {

	IDCARD(1, "身份证"),

	RESIDENT_CARD(2, "户口本"),

	DRIVE_CARD(3, "驾驶执照"),

	PASSPORT(4, "护照"),

	OFFICER_CARD(5, "军官证"),

	SOLDIER_CARD(6, "士兵证"),

	PERSON_BUSINESS_LICENSE(7, "法人营业执照"),

	PERSON_CODE_CARD(8, "法人代码证"),

	TAXATION_REGISTRATION_CARD(9, "税务登记证");

	private int id;
	private String text;

	private IDCardType(int id, String text) {
		this.id = id;
		this.text = text;
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
	
    public static IDCardType getById(int id){
    	for(IDCardType each : IDCardType.values()){
    		if(each.getId() == id){
    			return each;
    		}
    	}
    	throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
