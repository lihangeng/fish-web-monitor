package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public enum CommandType {

    /**
     * 正常重启
     */
    RESTART_NORMAL(1, "CommandType.RESTART_NORMAL"),

    /**
     * 强制重启
     */
    RESTART_ENFORCE(2, "CommandType.RESTART_ENFORCE"),

    /**
     * 开启服务
     */
    OPEN_SERVICES(3, "CommandType.OPEN_SERVICES"),

    /**
     * 暂停服务
     */
    CLOSE_SERVICES(4, "CommandType.CLOSE_SERVICES"),

    /**
     * 强制复位
     */
    RESET(5, "CommandType.RESET");

    private int id;

    private String text;

    private CommandType(int id, String text) {
        this.id = id;
        this.text = text;
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
    public static CommandType getById(int id) {
        for (CommandType each : CommandType.values()) {
            if (each.getId() == id) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
