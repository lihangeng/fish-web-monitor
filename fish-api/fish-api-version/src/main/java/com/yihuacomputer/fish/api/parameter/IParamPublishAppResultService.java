package com.yihuacomputer.fish.api.parameter;

import java.util.List;

public interface IParamPublishAppResultService {
	/**
	 * 新建应用参数执行结果
	 * @return
	 */
	IParamPublishAppResult make();
	/**
	 * 保存应用参数执行结果
	 * @param appResult
	 * @return
	 */
	IParamPublishAppResult save(IParamPublishAppResult appResult);
	/**
	 * 更新应用参数执行结果
	 * @param appResult
	 * @return
	 */
	IParamPublishAppResult update(IParamPublishAppResult appResult);
	
	/**
	 * 通过归属TaskID获取所有应用参数执行结果
	 * @param paramPublishResultId
	 * @return
	 */
	List<IParamPublishAppResult> list(long paramPublishResultId);
	/**
	 * 通过归属TaskID和AppName查找唯一AppResult
	 * @param paramPublishResultId
	 * @param name
	 * @return
	 */
	IParamPublishAppResult get(long paramPublishResultId,String name);
}
