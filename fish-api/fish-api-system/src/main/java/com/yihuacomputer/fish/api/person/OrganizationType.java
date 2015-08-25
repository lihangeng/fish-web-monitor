package com.yihuacomputer.fish.api.person;

public enum OrganizationType
{
    BANK(0,"银行"),
    MAINTAINER(1, "维护商"),
    WORK(2,"怡化电脑");
    
    private int id;
    private String text;
    
    private OrganizationType(int id ,String text){
        this.id = id;
        this.text= text;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
