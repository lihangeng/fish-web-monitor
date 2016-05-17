package com.yihuacomputer.fish.web.command.format;

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

    @Override
    public String toString()
    {
        return super.toString() + ":" + text;
    }

}
