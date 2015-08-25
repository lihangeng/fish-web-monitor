package com.yihuacomputer.fish.api.advert;

/**
 * 广告复核状态
 *
 */
public enum CheckStatus {

	NO_CHECK(0,"未复核"),
	CHECKED(1,"已复核"),
	IGNORE(2,"无需复核");

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

    public void setId(int id)
    {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

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
