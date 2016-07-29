package com.yihuacomputer.fish.web.monitor.form;


/**
 * 设备属性idc
 * 
 * @author pengwenchao
 * 
 */
/**
 * @author GQ
 *
 */
public class PropertyUkd
{
    /**
     * 获取读卡器类型
     */
    private String variant;

    /**
     * 是否具有退卡能力
     */
    private String canEject;

    /**
     * 是否具有吞卡能力
     */
    private String canCapture;

    /**
     * 获取最大吞卡张数
     */
    private int binCapacity;

    /**
     * 是否具有安全支持？？
     */
    private String security;
    
    /**
     *	是否可发卡 
     */
    private String dispenseCard; 

   

    public String getVariant()
    {
        return variant;
    }

    public void setVariant(String variant)
    {
        this.variant = variant;
    }

    public String isCanEject()
    {
        return canEject;
    }

    public void setCanEject(String canEject)
    {
        this.canEject = canEject;
    }

    public String isCanCapture()
    {
        return canCapture;
    }

    public void setCanCapture(String canCapture)
    {
        this.canCapture = canCapture;
    }

    public int getBinCapacity()
    {
        return binCapacity;
    }

    public void setBinCapacity(int binCapacity)
    {
        this.binCapacity = binCapacity;
    }

    public String isSecurity()
    {
        return security;
    }

    public void setSecurity(String security)
    {
        this.security = security;
    }

    public void setDispenseCard(String dispenseCard) {
		this.dispenseCard = dispenseCard;
	}

	@Override
    public String toString()
    {
        return "PropertyIdc [variant=" + variant + ", canEject=" + canEject
                + ", canCapture=" + canCapture + ", binCapacity=" + binCapacity
                + ", security=" + security + ", dispenseCard=" + dispenseCard + "]";
    }
}
