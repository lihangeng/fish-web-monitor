package com.yihuacomputer.fish.api.system.im;


public enum AnnounceStatus {

	/**
	 * 新建
	 */
	NEW(0,"AnnounceStatus.NEW"),

	/**
	 * 已发布
	 */
	RELEASE(1,"AnnounceStatus.RELEASE");

	private int  id;

    private String text;

    private AnnounceStatus(int id, String text) {
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

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public static AnnounceStatus getById(int id) {
		for (AnnounceStatus each : AnnounceStatus.values()) {
			if (each.getId() == id) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
}
