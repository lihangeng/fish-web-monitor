package com.yihuacomputer.fish.api.monitor.box;

/**
 * @author GQ
 * 设备加钞类型
 */
public enum BoxInitRuleType {
	/**
     * 钞箱上下限额预警
     */
    CASHLIMIT(0),
    /**
     * 清机加钞时间间隔预警
     */
    DAYSLIMIT(1),
    /**
     * 手工加钞
     */
    MANAUL(2),
    
    /**
     * 日均交易量预警加钞
     */
    TRADINGVOLUME(3);
    private BoxInitRuleType(int x){
    	this.seqNo= x;
    }
    private int seqNo;
    
    public int getNo(){
    	return seqNo;
    }
    
    /**
     * @param seqNo
     * @return
     */
    public static BoxInitRuleType getBoxInitRuleType(int seqNo){
    	BoxInitRuleType[] boxInitRuleTypes = BoxInitRuleType.values();
    	for(BoxInitRuleType boxInitRuleType:boxInitRuleTypes){
    		if(boxInitRuleType.seqNo==seqNo){
    			return boxInitRuleType;
    		}
    	}
    	return BoxInitRuleType.TRADINGVOLUME;
    }
}
