package com.yihuacomputer.fish.api.person;


public enum PersonType {
    MANAGE(0,"管机员"),
    FIXMAN(1, "维修人员");
    
    private int id;
    private String text;
    
    private PersonType(int id ,String text){
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
    
    public static PersonType getById(int id){
        for(PersonType each : PersonType.values()){
            if(each.getId() == id){
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
    
}
