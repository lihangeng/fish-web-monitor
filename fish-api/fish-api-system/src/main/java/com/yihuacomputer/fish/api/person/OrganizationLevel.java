/**
 * 
 */
package com.yihuacomputer.fish.api.person;

/**
 * 机构级别
 * @author xuxigang
 *
 */
public enum OrganizationLevel {
    TOTAL(0, "总行"),
    BRANCH(1, "分行"), 
    SUB_BRANCH(2, "一级支行"), 
    SUBTWO_BRANCH(3, "二级支行"),
    NETWORK_NODE(4, "网点"),
    HELPYOURSELF_BANK(5,"自助银行"),
    YINYE_BANK(6,"营业部");

    private int id;

    private String text;

    private OrganizationLevel(int id, String text) {
        this.id = id;
        this.text = text;
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

    public static OrganizationLevel getById(int id) {
        for (OrganizationLevel each : OrganizationLevel.values()) {
            if (each.getId() == id) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
