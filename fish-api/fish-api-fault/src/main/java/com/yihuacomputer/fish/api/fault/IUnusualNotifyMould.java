package com.yihuacomputer.fish.api.fault;


/**
 * @author YiHua
 *
 */
public interface IUnusualNotifyMould {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 异常交易类型ID
	 * @return
	 */
	public String getUnusualTranscationTypeId() ;

	/**
	 * @param unusualTranscationTypeId
	 */
	public void setUnusualTranscationTypeId(String unusualTranscationTypeId);
	/**
	 * 异常交易类型名称
	 * @return
	 */
	public String getUnusualTranscationTypeName() ;

	/**
	 * @param unusualTranscationTypeName
	 */
	public void setUnusualTranscationTypeName(String unusualTranscationTypeName);

	/**
	 * 发送人员类型
	 * @return
	 */
	public ResponsorType getResponsorType();

	/**
	 * @param responsorType
	 */
	public void setResponsorType(ResponsorType responsorType);


	/**
	 * 是否发送短信
	 * @return
	 */
	
    public IsNotify getIsNotify();
    
    /**
     * @param isNotify
     */
    public void setIsNotify(IsNotify isNotify);
	
}
