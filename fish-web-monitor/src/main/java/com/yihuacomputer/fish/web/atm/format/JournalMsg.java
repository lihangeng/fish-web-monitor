package com.yihuacomputer.fish.web.atm.format;


/**
 * @author YiHua
 *
 */
public class JournalMsg{

	private String terminalId;

	private String content;

	private String createdTime;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
