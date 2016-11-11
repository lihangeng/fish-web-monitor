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
    /**
     * 总行
     */
    TOTAL(0, "OrganizationLevel.TOTAL"),
    /**
     * 分行
     */
    BRANCH(1, "OrganizationLevel.BRANCH"), 
    /**
     * 一级支行
     */
    SUB_BRANCH(2, "OrganizationLevel.SUB_BRANCH"), 
    /**
     * 二级支行
     */
    SUBTWO_BRANCH(3, "OrganizationLevel.SUBTWO_BRANCH"),
    /**
     * 网点
     */
    NETWORK_NODE(4, "OrganizationLevel.NETWORK_NODE"),
    /**
     * 自助银行
     */
    HELPYOURSELF_BANK(5,"OrganizationLevel.HELPYOURSELF_BANK"),
    /**
     * 营业部
     */
    YINYE_BANK(6,"OrganizationLevel.YINYE_BANK");

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
    public static OrganizationLevel getByText(String text) {
        for (OrganizationLevel each : OrganizationLevel.values()) {
            if (each.getText().equals(text)) {
                return each;
            }
        }
        return null;
    }

}
