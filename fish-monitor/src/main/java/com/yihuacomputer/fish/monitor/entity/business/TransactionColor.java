package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.ITransactionColor;

/**
 * 交易返回码颜色设置
 *
 * @author pengwenchao
 *
 */
@Entity
@Table(name = "SM_TRANSACTION_COLOR")
public class TransactionColor implements ITransactionColor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_TRANSACTION_COLOR")
	@SequenceGenerator(name = "SEQ_SM_TRANSACTION_COLOR", sequenceName = "SEQ_SM_TRANSACTION_COLOR")
	@Column(name = "ID")
	private long id;

	/**
	 * 用户名称
	 */
	@Column(name = "USER_NAME", length = 20)
	private String userName;

	/**
	 * 交易返回码
	 */
	@Column(name = "HOST_RET", length = 20)
	private String hostRet;

	/**
	 * 背景颜色
	 */
	@Column(name = "BACKGROUND_COLOR", length = 20)
	private String backgroundColor;

	/**
	 * 字体颜色
	 */
	@Column(name = "FONT_COLOR", length = 20)
	private String fontColor;
	
    /**
     * 主机返回码描述
     */
    @Column(name = "HOST_RET_DESCRIBE", length = 60)
    private String hostRetDes;

	/**
	 * 修改时间
	 */
	@Column(name = "UPDATE_DATETIME", length = 30)
	private String updateDateTime;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 150)
	private String remark;



	/**
     * 本地返回码
     */
    @Column(name = "LOCAL_RET", length = 20)
    private String localRet;

    /**
     * 本地背景颜色
     */
    @Column(name = "LOCAL_BACKGROUND_COLOR", length = 20)
    private String localBackgroundColor;

    /**
     * 本地字体颜色
     */
    @Column(name = "LOCAL_FONT_COLOR", length = 20)
    private String localFontColor;    

    /**
     * 本地返回码描述
     */
    @Column(name = "LOCAL_RET_DESCRIBE", length = 60)
    private String localRetDes;


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

	public void setLocalFontColor(String localFontColor) {
        this.localFontColor = localFontColor;
    }
}
