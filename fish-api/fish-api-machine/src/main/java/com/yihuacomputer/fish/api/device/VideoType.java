package com.yihuacomputer.fish.api.device;


/**
 *
 * 是否支持视频播放
 *
 * @author liubo
 * @E-mail liubo-nj@yihuacomputer
 * @date 2014-7-3
 */
public enum VideoType
{
    VIDEO_YES(1, "VideoType.VIDEO_YES"), VIDEO_NO(2, "VideoType.VIDEO_NO");
    public String getText(){
		return text;
    }
    private int id;

    private String text;

    private VideoType(int id, String text)
    {
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

    public void setText(String text)
    {
        this.text = text;
    }

    public static VideoType getById(int id)
    {
        for (VideoType each : VideoType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
