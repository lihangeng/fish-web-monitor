package com.yihuacomputer.fish.api.device;


/**
 * 停机类型
 * @author wangchao
 *
 */
public enum StopType {
//	HOLIDAY(0,"放假"),
//	DECORATE(1,"装修"),
//	POWER_CUT(2,"停电"),
//	TRABLE_NO_REPAIR(3,"故障未修复"),
//	OTHER(4,"其他");
	
	HOLIDAY(0,"StopType.HOLIDAY"),
	DECORATE(1,"StopType.DECORATE"),
	POWER_CUT(2,"StopType.POWER_CUT"),
	TRABLE_NO_REPAIR(3,"StopType.TRABLE_NO_REPAIR"),
	OTHER(4,"StopType.OTHER");

    public String getText(){
		return text;
    }
	private int id;
	private String text;
	
	private  StopType(int id,String text){
		this.id = id;
		this.text = text;
	}
	
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static StopType getById(int id)
	    {
	        for (StopType each : StopType.values())
	        {
	            if (each.getId() == id)
	            {
	                return each;
	            }
	        }
	        throw new IllegalArgumentException(String.format("id=[%d] error", id));
	    }
}
