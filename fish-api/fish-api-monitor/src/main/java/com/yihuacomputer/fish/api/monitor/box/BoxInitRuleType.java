package com.yihuacomputer.fish.api.monitor.box;

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
    
    UNKNOW(3);
    private BoxInitRuleType(int x){
    	this.seqNo= x;
    }
    private int seqNo;
    
    public int getNo(){
    	return seqNo;
    }
    
    public static BoxInitRuleType getBoxInitRuleType(int seqNo){
    	BoxInitRuleType[] boxInitRuleTypes = BoxInitRuleType.values();
    	for(BoxInitRuleType boxInitRuleType:boxInitRuleTypes){
    		if(boxInitRuleType.seqNo==seqNo){
    			return boxInitRuleType;
    		}
    	}
    	return BoxInitRuleType.UNKNOW;
    }
}
