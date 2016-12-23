package com.yihuacomputer.fish.api.monitor.business;
/**
 * 
 * @author wangchao
 *		证件类型
 */
/**
 * @author GQ
 *
 */
public enum IDCardType {

	/**
	 * 身份证
	 */
	IDCARD(1, "IDCardType.IDCARD"),

	/**
	 * 户口本
	 */
	RESIDENT_CARD(2, "IDCardType.RESIDENT_CARD"),

	/**
	 * 驾驶执照
	 */
	DRIVE_CARD(3, "IDCardType.DRIVE_CARD"),

	/**
	 * 护照
	 */
	PASSPORT(4, "IDCardType.PASSPORT"),

	/**
	 * 军官证
	 */
	OFFICER_CARD(5, "IDCardType.OFFICER_CARD"),

	/**
	 * 士兵证
	 */
	SOLDIER_CARD(6, "IDCardType.SOLDIER_CARD"),

	/**
	 * 法人营业执照
	 */
	PERSON_BUSINESS_LICENSE(7, "IDCardType.PERSON_BUSINESS_LICENSE"),

	/**
	 * 法人代码证
	 */
	PERSON_CODE_CARD(8, "IDCardType.PERSON_CODE_CARD"),

	/**
	 * 税务登记证
	 */
	TAXATION_REGISTRATION_CARD(9, "IDCardType.TAXATION_REGISTRATION_CARD");

	private int id;
	private String text;

	private IDCardType(int id, String text) {
		this.id = id;
		this.text = text;
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
    public static IDCardType getById(int id){
    	for(IDCardType each : IDCardType.values()){
    		if(each.getId() == id){
    			return each;
    		}
    	}
    	throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
