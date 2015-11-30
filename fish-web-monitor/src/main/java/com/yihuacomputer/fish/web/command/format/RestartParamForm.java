package com.yihuacomputer.fish.web.command.format;

/**
 * 重启参数
 * 
 * @author YiHua
 *
 */
public class RestartParamForm extends RemoteCommand {

    private CommandLevel restartType;

    public CommandLevel getRestartType() {
        return restartType;
    }

    public void setRestartType(CommandLevel restartType) {
        this.restartType = restartType;
    }
}
