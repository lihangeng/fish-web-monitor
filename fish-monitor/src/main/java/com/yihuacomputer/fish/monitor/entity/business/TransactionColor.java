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

    @Override
	public long getId() {
		return id;
	}

    @Override
	public void setId(long id) {
		this.id = id;
	}

    @Override
	public String getHostRet() {
		return hostRet;
	}

    @Override
	public void setHostRet(String hostRet) {
		this.hostRet = hostRet;
	}

    @Override
	public String getUpdateDateTime() {
		return updateDateTime;
	}

    @Override
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

    @Override
	public String getRemark() {
		return remark;
	}

    @Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

    @Override
	public String getBackgroundColor() {
		return backgroundColor;
	}

    @Override
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

    @Override
	public String getFontColor() {
		return fontColor;
	}

    @Override
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

    @Override
	public String getUserName() {
		return userName;
	}

    @Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

    @Override
    public String getLocalRet() {
        return localRet;
    }

    @Override
    public void setLocalRet(String localRet) {
        this.localRet = localRet;
    }

    @Override
    public String getLocalBackgroundColor() {
        return localBackgroundColor;
    }

    @Override
    public void setLocalBackgroundColor(String localBackgroundColor) {
        this.localBackgroundColor = localBackgroundColor;
    }

    @Override
    public String getLocalFontColor() {
        return localFontColor;
    }

    @Override
    public String getHostRetDes() {
		return hostRetDes;
	}

    @Override
	public void setHostRetDes(String hostRetDes) {
		this.hostRetDes = hostRetDes;
	}

    @Override
	public String getLocalRetDes() {
		return localRetDes;
	}

    @Override
	public void setLocalRetDes(String localRetDes) {
		this.localRetDes = localRetDes;
	}

    @Override
	public void setLocalFontColor(String localFontColor) {
        this.localFontColor = localFontColor;
    }
}
