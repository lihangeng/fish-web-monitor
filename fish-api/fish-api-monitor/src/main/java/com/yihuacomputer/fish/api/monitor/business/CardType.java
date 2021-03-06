package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public enum CardType {

    /**
     * 本行借记卡 卡表对应1（本行借记卡）、3（本行准贷记卡 业务处理同借记卡：苏稀春）
     */
    LOCAL_BANK(0,"CardType.LOCAL_BANK"),
    /**
     * 本行信用卡 卡表对应2
     */
    LOCAL_CREDIT(1,"CardType.LOCAL_CREDIT"),

    /**
     * 本行准贷记卡  对应卡表文件中的3   业务同借记卡处理，注意：不支持理财账户交易。（周峰  江苏农行现场增加）
     */
    LOCAL_QUASICREDIT(2,"CardType.LOCAL_QUASICREDIT"),

    /**
     * 他行卡
     */
    OTHER_BANK(3,"CardType.OTHER_BANK"),
    /**
     * 本行异地
     */
    REMOTE_BANK(4,"CardType.REMOTE_BANK"),
    /**
     * 国际卡(也属于他行卡)
     */
    INTERNATIONAL(5,"CardType.INTERNATIONAL"),
    /**
     * 不能识别的卡
     */
    UNKONWN(6,"CardType.UNKONWN");


	private int id;

	private String text;

	private CardType(int id, String text) {
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
	public static CardType getById(int id){
		for(CardType each : CardType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
