package com.yihuacomputer.fish.api.person;


/**
 * @author YiHua
 *
 */
public enum PersonType {
    /**
     * 管机员
     */
    MANAGE(0,"PersonType.MANAGE"),
    /**
     * 维修人员
     */
    FIXMAN(1, "PersonType.FIXMAN");
    
    private int id;
    private String text;
    
    private PersonType(int id ,String text){
        this.id = id;
        this.text= text;
    }
    
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    /**
     * @param id
     * @return
     */
    public static PersonType getById(int id){
        for(PersonType each : PersonType.values()){
            if(each.getId() == id){
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
    
}
