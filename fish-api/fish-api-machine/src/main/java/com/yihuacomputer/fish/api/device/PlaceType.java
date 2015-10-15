package com.yihuacomputer.fish.api.device;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 布放位置类型
 *
 * @author 刘波
 * @E-mail liubo-nj@yihuacomputer
 * @date 2014-7-4
 */
public enum PlaceType
{
	PRO_CAP(1, "PlaceType.PRO_CAP"), PLA_CIT(2, "PlaceType.PLA_CIT"), COU_TOW(3, "PlaceType.COU_TOW"), 
	VIL_TOW(4, "PlaceType.VIL_TOW"), VILLAGE(5, "PlaceType.VILLAGE");
	private final static String BASENAME = "enum";
    private final static ResourceBundle resource = ResourceBundle.getBundle(BASENAME, FishCfg.locale==null?Locale.CHINA:FishCfg.locale);

    public String getText(){
    	String result =  text == null || resource == null || !resource.containsKey(text) ? text : resource.getString(text);
    	try {
			return new String(result.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return text;
		}
    }
    private int id;

    private String text;

    private PlaceType(int id, String text)
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

    public static PlaceType getById(int id)
    {
        for (PlaceType each : PlaceType.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
