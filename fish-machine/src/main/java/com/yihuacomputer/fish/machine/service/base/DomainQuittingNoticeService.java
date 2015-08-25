package com.yihuacomputer.fish.machine.service.base;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.machine.entity.QuittingNotice;
import com.yihuacomputer.fish.machine.service.api.IDomainQuittingNoticeService;

public abstract class DomainQuittingNoticeService implements IDomainQuittingNoticeService
{
    @Override
    public QuittingNotice make() {
        return new QuittingNotice(this);
    }

    @Override
    public IPageResult<IQuittingNotice> page(int offset, int limit, IFilter filter) {
        List<IQuittingNotice> lists = new ArrayList<IQuittingNotice>();
        EntityUtils.convert(list(filter), lists);
        return new PageResult<IQuittingNotice>(lists,offset,limit);
    }

}
