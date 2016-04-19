package com.yihuacomputer.fish.api.parameter;

public interface IParamPublishResultService {
	public IParamPublishResult make();
	public IParamPublishResult save(IParamPublishResult publishResult);
	public IParamPublishResult get(long id);
	public IParamPublishResult update(IParamPublishResult publishResult);
	public IParamPublishResult update(long id,String ret);
}
