package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransactionColor;

public class TransactionColorForm {
	private long id;

	/**
	 * 交易返回码
	 */
	private String hostRet;

    /**
     * 交易返回码描述
     */
    private String hostRetDes;

	/**
	 * 背景颜色
	 */
	private String backgroundColor;

	/**
	 * 字体颜色
	 */
	private String fontColor;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 修改时间
	 */
	private String updateDateTime;

	/**
	 * 备注
	 */
	private String remark;


	/**
     * 本地返回码
     */
    private String localRet;

    /**
     * 本地背景颜色
     */
    private String localBackgroundColor;

    /**
     * 本地字体颜色
     */
    private String localFontColor;    

    /**
     * 本地返回码描述
     */
    private String localRetDes;


	public TransactionColorForm() {

	}

	public TransactionColorForm(ITransactionColor transactionColor) {
		setId(transactionColor.getId());
		setHostRet(transactionColor.getHostRet());
		setBackgroundColor(transactionColor.getBackgroundColor());
		setUpdateDateTime(transactionColor.getUpdateDateTime());
		setRemark(transactionColor.getRemark());
		setFontColor(transactionColor.getFontColor());
		setHostRetDes(transactionColor.getHostRetDes());
		setUserName(transactionColor.getUserName());

		setLocalBackgroundColor(transactionColor.getLocalBackgroundColor());
		setLocalFontColor(transactionColor.getLocalFontColor());
		setLocalRet(transactionColor.getLocalRet());
		setLocalRetDes(transactionColor.getLocalRetDes());

	}

	public void translate(ITransactionColor transactionColor) {
		transactionColor.setHostRet(getHostRet());
		transactionColor.setBackgroundColor(getBackgroundColor());
		transactionColor.setUpdateDateTime(DateUtils.get(new Date(), "yyyy-MM-dd HH:mm:ss"));
		transactionColor.setRemark(getRemark());
		transactionColor.setFontColor(getFontColor());
		transactionColor.setUserName(getUserName());
		transactionColor.setHostRetDes(getHostRetDes());

		transactionColor.setLocalBackgroundColor(getLocalBackgroundColor());
		transactionColor.setLocalFontColor(getLocalFontColor());
		transactionColor.setLocalRet(getLocalRet());
		transactionColor.setLocalRetDes(getLocalRetDes());
	}

	public static List<TransactionColorForm> convert(List<ITransactionColor> listColor) {

		List<TransactionColorForm> result = new ArrayList<TransactionColorForm>();

		for (ITransactionColor color : listColor) {
			result.add(new TransactionColorForm(color));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHostRet() {
		return hostRet;
	}

	public void setHostRet(String hostRet) {
		this.hostRet = hostRet;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public String getLocalRet() {
        return localRet;
    }

    public void setLocalRet(String localRet) {
        this.localRet = localRet;
    }

    public String getLocalBackgroundColor() {
        return localBackgroundColor;
    }

    public void setLocalBackgroundColor(String localBackgroundColor) {
        this.localBackgroundColor = localBackgroundColor;
    }

    public String getLocalFontColor() {
        return localFontColor;
    }

    public void setLocalFontColor(String localFontColor) {
        this.localFontColor = localFontColor;
    }
    
	public String getHostRetDes() {
		return hostRetDes;
	}

	public void setHostRetDes(String hostRetDes) {
		this.hostRetDes = hostRetDes;
	}

	public String getLocalRetDes() {
		return localRetDes;
	}

	public void setLocalRetDes(String localRetDes) {
		this.localRetDes = localRetDes;
	}
}
