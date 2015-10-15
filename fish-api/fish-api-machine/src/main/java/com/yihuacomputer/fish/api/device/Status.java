package com.yihuacomputer.fish.api.device;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.yihuacomputer.common.FishCfg;

/**
 * 设备状态
 *
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午02:37:36
 */
public enum Status
{
//    OPENING(1, "开通"), DISABLED(2, "停用"),SCRAPPED(3,"报废"),
//    ////////江苏农信//////
//    OPEN(4, "启用"),OPE_WAI_CHE(5,"启用待审核"),SCR_WAI_CHE(6,"报废待审核"),
//    UPD_WAI_CHE(7,"修改待审核"),OPE_CHE_NO(8,"启用审核不通过"),
//    SCR_CHE_NO(9,"报废审核不通过"),UPD_CHE_NO(10,"修改审核不通过"),
//    UPD_CHE_YES(11,"修改审核通过"),OPE_CHE_YES(12,"启用审核通过"),
//    SCR_CHE_YES(13,"报废审核通过");
    
    OPENING(1, "Status.OPENING"), DISABLED(2, "Status.DISABLED"),SCRAPPED(3,"Status.SCRAPPED"),
    ////////江苏农信//////
    OPEN(4, "Status.OPEN"),OPE_WAI_CHE(5,"Status.OPE_WAI_CHE"),SCR_WAI_CHE(6,"Status.SCR_WAI_CHE"),
    UPD_WAI_CHE(7,"Status.UPD_WAI_CHE"),OPE_CHE_NO(8,"Status.OPE_CHE_NO"),
    SCR_CHE_NO(9,"Status.SCR_CHE_NO"),UPD_CHE_NO(10,"Status.UPD_CHE_NO"),
    UPD_CHE_YES(11,"Status.UPD_CHE_YES"),OPE_CHE_YES(12,"Status.OPE_CHE_YES"),
    SCR_CHE_YES(13,"Status.SCR_CHE_YES"); 
   
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

    private Status(int id, String text)
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

    public static Status getById(int id)
    {
        for (Status each : Status.values())
        {
            if (each.getId() == id)
            {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
