package com.yihuacomputer.fish.web.command.format;
/**
 * 关机参数
 * @author YiHua
 *
 */
public class ShutdownParamForm {
	private CommandLevel shutdownType;

	public CommandLevel getShutdownType() {
		return shutdownType;
	}

	public void setShutdownType(CommandLevel shutdownType) {
		this.shutdownType = shutdownType;
	}

}
