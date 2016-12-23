package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public enum CommandResult {

    /**
     * 执行中
     */
    EXEC_LOADING(1, "CommandResult.EXEC_LOADING"),

    /**
     * 执行成功
     */
    EXEC_SUCCESSFULLY(2, "CommandResult.EXEC_SUCCESSFULLY"),

    /**
     * 执行失败
     */
    EXEC_FAIL(3, "CommandResult.EXEC_FAIL"),

    /**
     * 连接失败
     */
    CONNECT_FAIL(4, "CommandResult.CONNECT_FAIL");

    private int id;

    private String text;

    private CommandResult(int id, String text) {
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
    public static CommandResult getById(int id) {
        for (CommandResult each : CommandResult.values()) {
            if (each.getId() == id) {
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
