package com.yihuacomputer.fish.api.monitor.business;

public enum CardType {

    /**
     * 本行借记卡 卡表对应1（本行借记卡）、3（本行准贷记卡 业务处理同借记卡：苏稀春）
     */
    LOCAL_BANK(0,"本行借记卡"),
    /**
     * 本行信用卡 卡表对应2
     */
    LOCAL_CREDIT(1,"本行信用卡"),

    /**
     * 本行准贷记卡  对应卡表文件中的3   业务同借记卡处理，注意：不支持理财账户交易。（周峰  江苏农行现场增加）
     */
    LOCAL_QUASICREDIT(2,"本行准贷记卡"),

    /**
     * 他行卡
     */
    OTHER_BANK(3,"他行卡"),
    /**
     * 本行异地
     */
    REMOTE_BANK(4,"本行异地卡"),
    /**
     * 国际卡(也属于他行卡)
     */
    INTERNATIONAL(5,"国际卡"),
    /**
     * 不能识别的卡
     */
    UNKONWN(6,"未知卡");


	private int id;

	private String text;

	private CardType(int id, String text) {
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

	public static CardType getById(int id){
		for(CardType each : CardType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
