package com.yihuacomputer.fish.web.command.format;

/**
 * 钞箱详情
 * @author YiHua
 *
 */
public class CashBoxDetail {

	/**钞箱ID*/
	private String boxId;
	
	/**钞箱类型*/
	private BoxType type;
	private String typeName;
	
	/**钞箱状态*/
	private BoxStatus binStatus;
	private String binStatusName;
	
	/**初始化张数*/
	private int initialCount;
	
	/**存入张数*/
	private int cashInCount;
	
	/**当前张数*/
	private int currentCount;
	
	/**面值*/
	private int noteValue;
	
	/**币种*/
	private String currency;

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String getType() {
		return type.getText();
	}

	public void setType(BoxType type) {
		this.type = type;
	}

	public String getBinStatus() {
		return binStatus.getText();
	}

	public int getCashInCount() {
		return cashInCount;
	}

	public void setCashInCount(int cashInCount) {
		this.cashInCount = cashInCount;
	}

	public void setBinStatus(BoxStatus binStatus) {
		this.binStatus = binStatus;
	}

	public int getInitialCount() {
		return initialCount;
	}

	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getNoteValue() {
		return noteValue;
	}

	public void setNoteValue(int noteValue) {
		this.noteValue = noteValue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBinStatusName() {
		return binStatusName;
	}

	public void setBinStatusName(String binStatusName) {
		this.binStatusName = binStatusName;
	}	
	
}
