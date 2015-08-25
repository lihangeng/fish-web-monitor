package com.yihuacomputer.fish.api.fault;


public interface IUnusualNotifyMould {
	
	public long getId();

	public void setId(long id);
	
	/**
	 * 异常交易类型ID
	 * @return
	 */
	public String getUnusualTranscationTypeId() ;

	public void setUnusualTranscationTypeId(String unusualTranscationTypeId);
	/**
	 * 异常交易类型名称
	 * @return
	 */
	public String getUnusualTranscationTypeName() ;

	public void setUnusualTranscationTypeName(String unusualTranscationTypeName);

	/**
	 * 发送人员类型
	 * @return
	 */
	public ResponsorType getResponsorType();

	public void setResponsorType(ResponsorType responsorType);


	/**
	 * 是否发送短信
	 * @return
	 */
	
    public IsNotify getIsNotify();
    
    public void setIsNotify(IsNotify isNotify);
	
}
