package com.yihuacomputer.fish.api.person;

public enum OrganizationType
{
    /**
     * 银行
     */
    BANK(0,"OrganizationType.BANK"),
    /**
     * 维护商
     */
    MAINTAINER(1, "OrganizationType.MAINTAINER"),
    /**
     * 怡化电脑
     */
    WORK(2,"OrganizationType.WORK");
    
    private int id;
    private String text;
    
    private OrganizationType(int id ,String text){
        this.id = id;
        this.text= text;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static OrganizationType getById(int id){
        for(OrganizationType each : OrganizationType.values()){
            if(each.getId() == id){
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
    
}
