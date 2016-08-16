package com.yihuacomputer.fish.api.monitor.box;

/**
 * 钞箱类型定义
 * @author zhangcheng
 *
 */
public enum BoxType
{

    /**
     * 现金取款箱
     */
    BILLCASSETTE("BoxType.BILLCASSETTE"),

    /**
     * 现金存款箱
     */
    CASHINCASSETTE("BoxType.CASHINCASSETTE"),

    /**
     * 现金循环箱
     */
    RECYCLECASSETTE("BoxType.RECYCLECASSETTE"),

    /**
     * 回收箱
     */
    RETRACTCASSETTE("BoxType.RETRACTCASSETTE"),

    /**
     * 拒钞箱、废钞箱
     */
    REJECTCASSETTE("BoxType.REJECTCASSETTE"),

    /**
     * 其他类型箱
     */
    OTHERTYPECASSETTE("BoxType.OTHERTYPECASSETTE");

    private String text;

    /**
     * 构造方法.<br/>
     * 每一个枚举实际上相当于public static final常量，而且枚举常量不能由外部初始化
     * @param name
     */
    private BoxType(String name)
    {
        this.text = name;
    }

    public String getText()
    {
        return this.text;
    }


    /**
     * 根据字符串得出钞箱类型
     * @param enumItem
     * @return
     */
    public static BoxType getBoxType(String enumItem){
    	BoxType[] bts = BoxType.values();
    	for(BoxType bt:bts){
    		if(bt.toString().equals(enumItem)){
    			return bt;
    		}
    	}
    	return OTHERTYPECASSETTE;
    }
    
    /**
     * 判断当前钞箱是否是有效的钞箱（可进行存取款的）
     * @param boxType
     * @return
     */
    public static boolean isEffect(BoxType boxType){
    	if(BoxType.BILLCASSETTE.equals(boxType)||
    			BoxType.CASHINCASSETTE.equals(boxType)||
    			BoxType.RECYCLECASSETTE.equals(boxType)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
