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
    BILLCASSETTE("取款"),

    /**
     * 现金存款箱
     */
    CASHINCASSETTE("存款"),

    /**
     * 现金循环箱
     */
    RECYCLECASSETTE("循环"),

    /**
     * 回收箱
     */
    RETRACTCASSETTE("回收"),

    /**
     * 拒钞箱、废钞箱
     */
    REJECTCASSETTE("废钞"),

    /**
     * 其他类型箱
     */
    OTHERTYPECASSETTE("其他");

    private String name;

    /**
     * 构造方法.<br/>
     * 每一个枚举实际上相当于public static final常量，而且枚举常量不能由外部初始化
     * @param name
     */
    private BoxType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return super.toString() + ":" + name;
    }

}
