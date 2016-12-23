package com.yihuacomputer.fish.api.advert;

/**
 * 广告复核状态
 *
 */
public enum CheckStatus {

	/**
	 * 未复核
	 */
	NO_CHECK(0,"CheckStatus.NO_CHECK"),
	/**
	 * 已复核
	 */
	CHECKED(1,"CheckStatus.CHECKED"),
	/**
	 * 无需复核
	 */
	IGNORE(2,"CheckStatus.IGNORE");

	private int id;

	private String text;

    private CheckStatus(int id,String text) {
    	this.id = id;
        this.text = text;
    }

    public int getId()
    {
        return id;
    }

    public String getText() {
        return text;
    }

    /**
     * @param id
     * @return
     */
    public static CheckStatus getById(int id)
    {
        for (CheckStatus each : CheckStatus.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
