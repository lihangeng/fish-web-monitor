package com.yihuacomputer.fish.machine.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.device.StopType;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.machine.H2TestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class QuittingNoticeServiceTest extends BindSessionInTest2{
	@Autowired
	private IQuittingNoticeService quittingNoticeService;

    @Ignore
	@Test
	public void test(){

		Date openTime = DateUtils.getTimestamp("2012-02-27 12:00:00");
		Date stopTime = DateUtils.getTimestamp("2012-02-27 19:00:00");

		IQuittingNotice notice1 = quittingNoticeService.make();
		notice1.setDeviceCode("0001");
		notice1.setOpenTime(openTime);
		notice1.setResponsiblilityName("张三");
		notice1.setSetTime(new Date());
		notice1.setStopReason("故障");
		notice1.setStopTime(stopTime);
		notice1.setStopType(StopType.HOLIDAY);
		quittingNoticeService.add(notice1);


		IQuittingNotice notice2 = quittingNoticeService.make();
		notice2.setDeviceCode("0002");
		notice2.setOpenTime(openTime);
		notice2.setResponsiblilityName("李四");
		notice2.setSetTime(new Date());
		notice2.setStopReason("放假");
		notice2.setStopTime(stopTime);
		notice2.setStopType(StopType.HOLIDAY);
		quittingNoticeService.add(notice2);

		notice2.setStopReason("停电");
		quittingNoticeService.update(notice2);
		IQuittingNotice newType = quittingNoticeService.get(notice2.getId());
		assertEquals("停电",newType.getStopReason());

		Iterable<IQuittingNotice> lists = quittingNoticeService.list();
		List<IQuittingNotice> result = new ArrayList<IQuittingNotice>();
		for(IQuittingNotice item : lists){
			result.add(item);
		}
//		assertEquals(2,result.size());

		IFilter filter = new Filter();
		filter.eq("deviceCode", "0001");
		Iterable<IQuittingNotice> resultFilter = quittingNoticeService.list(filter);
		List<IQuittingNotice> resultType = new ArrayList<IQuittingNotice>();
		for(IQuittingNotice item : resultFilter){
			resultType.add(item);
		}
		assertEquals(1,resultType.size());

		IPageResult<IQuittingNotice> page = quittingNoticeService.page(0, 10, filter);
		assertEquals(1,page.getTotal());

		quittingNoticeService.remove(notice1.getId());
		Iterable<IQuittingNotice> listRemove = quittingNoticeService.list();
		List<IQuittingNotice> removeLists = new ArrayList<IQuittingNotice>();
		for(IQuittingNotice item : listRemove){
			removeLists.add(item);
		}
		assertEquals(1,removeLists.size());

		quittingNoticeService.remove(notice2.getDeviceCode());
		Iterable<IQuittingNotice> listRemove1 = quittingNoticeService.list();
		List<IQuittingNotice> removeLists1 = new ArrayList<IQuittingNotice>();
		for(IQuittingNotice item : listRemove1){
			removeLists1.add(item);
		}
		assertEquals(0,removeLists1.size());
	}
}
