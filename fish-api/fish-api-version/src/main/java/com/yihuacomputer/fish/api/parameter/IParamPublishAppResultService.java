package com.yihuacomputer.fish.api.parameter;

import java.util.List;

public interface IParamPublishAppResultService {
	IParamPublishAppResult make();
	IParamPublishAppResult save(IParamPublishAppResult appResult);
	IParamPublishAppResult update(IParamPublishAppResult appResult);
	List<IParamPublishAppResult> list(long paramPublishResultId);
}
