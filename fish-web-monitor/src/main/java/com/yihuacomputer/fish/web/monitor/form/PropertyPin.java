package com.yihuacomputer.fish.web.monitor.form;
/**
 * @author YiHua
 *
 */
public class PropertyPin
{
    /**
     * 是否支持EBC
     */
    private String canEBC;

    /**
     * 是否支持CBC
     */
    private String canCBC;

    /**
     * 是否支持MAC
     */
    private String canMAC;

    /**
     * 是否至此RSA
     */
    private String canRSA;

    /**
     * 是否支持DES验证
     */
    private String canVerifyDES;

    /**
     * 是否支持ECB验证
     */
    private String canVerifyECB;

    /**
     * 是否支持VISA验证
     */
    private String canVerifyVISA;

    /**
     * 是否支持DES偏移
     */
    private String canDESOffset;

    /**
     * 是否支持多重EBC
     */
    private String canTripleEBC;

    /**
     * 是否支持多重CBC
     */
    private String canTripleCBC;

    /**
     * 是否支持多重MAC
     */
    private String canTripleMAC;

    /**
     * 是否支持多重CFB
     */
    private String canTripleCFB;

    /**
     * 获取支持的功能键
     */
    private String[] functionKeys;

    public String getCanEBC()
    {
        return canEBC;
    }

    public void setCanEBC(String canEBC)
    {
        this.canEBC = canEBC;
    }

    public String getCanCBC()
    {
        return canCBC;
    }

    public void setCanCBC(String canCBC)
    {
        this.canCBC = canCBC;
    }

    public String getCanMAC()
    {
        return canMAC;
    }

    public void setCanMAC(String canMAC)
    {
        this.canMAC = canMAC;
    }

    public String getCanRSA()
    {
        return canRSA;
    }

    public void setCanRSA(String canRSA)
    {
        this.canRSA = canRSA;
    }

    public String getCanVerifyDES()
    {
        return canVerifyDES;
    }

    public void setCanVerifyDES(String canVerifyDES)
    {
        this.canVerifyDES = canVerifyDES;
    }

    public String getCanVerifyECB()
    {
        return canVerifyECB;
    }

    public void setCanVerifyECB(String canVerifyECB)
    {
        this.canVerifyECB = canVerifyECB;
    }

    public String getCanVerifyVISA()
    {
        return canVerifyVISA;
    }

    public void setCanVerifyVISA(String canVerifyVISA)
    {
        this.canVerifyVISA = canVerifyVISA;
    }

    public String getCanDESOffset()
    {
        return canDESOffset;
    }

    public void setCanDESOffset(String canDESOffset)
    {
        this.canDESOffset = canDESOffset;
    }

    public String getCanTripleEBC()
    {
        return canTripleEBC;
    }

    public void setCanTripleEBC(String canTripleEBC)
    {
        this.canTripleEBC = canTripleEBC;
    }

    public String getCanTripleCBC()
    {
        return canTripleCBC;
    }

    public void setCanTripleCBC(String canTripleCBC)
    {
        this.canTripleCBC = canTripleCBC;
    }

    public String getCanTripleMAC()
    {
        return canTripleMAC;
    }

    public void setCanTripleMAC(String canTripleMAC)
    {
        this.canTripleMAC = canTripleMAC;
    }

    public String getCanTripleCFB()
    {
        return canTripleCFB;
    }

    public void setCanTripleCFB(String canTripleCFB)
    {
        this.canTripleCFB = canTripleCFB;
    }

    public String[] getFunctionKeys()
    {
        return functionKeys;
    }

    public void setFunctionKeys(String[] functionKeys)
    {
        this.functionKeys = functionKeys;
    }
}
