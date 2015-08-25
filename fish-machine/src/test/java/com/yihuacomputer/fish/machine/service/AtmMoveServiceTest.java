package com.yihuacomputer.fish.machine.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.api.atmMove.IAtmMoveService;
import com.yihuacomputer.fish.machine.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class AtmMoveServiceTest extends BindSessionInTest2 {
	@Autowired
	private IAtmMoveService atmMoveService;

	@Test
	public void test() {

		IAtmMove move1 = atmMoveService.make();
		move1.setTerminalId("0001");
		move1.setDate(new Date());
		move1.setAddress("新街口");
//		move1.setOrganization("农行新街口支行");
		move1.setNotice("移机处理");
		move1.setResponsibility("张三");
		move1.setTargetAddress("夫子庙");
//		move1.setTargetOrganization("农行夫子庙支行");
		atmMoveService.add(move1);
//
//		IAtmMove move2 = atmMoveService.make();
//		move2.setTerminalId("0002");
//		move2.setDate(new Date());
//		move2.setAddress("夫子庙");
//		move2.setOrganization("农行夫子庙支行");
//		move2.setNotice("移机处理");
//		move2.setResponsibility("李四");
//		move2.setTargetAddress("新街口");
//		move2.setTargetOrganization("农行新街口支行");
//		atmMoveService.add(move2);
//
//		move2.setTerminalId("0003");
//		atmMoveService.update(move2);
//		IAtmMove newMove = atmMoveService.get(move2.getId());
//		assertEquals("0003", newMove.getTerminalId());
//
//		Iterable<IAtmMove> lists = atmMoveService.list();
//		List<IAtmMove> result = new ArrayList<IAtmMove>();
//		for (IAtmMove item : lists) {
//			result.add(item);
//		}
//		// assertEquals(2,result.size());
//
//		IFilter filter = new Filter();
//		filter.addFilterEntry(FilterFactory.eq("terminalId", "0001"));
//		Iterable<IAtmMove> resultFilter = atmMoveService.list(filter);
//		List<IAtmMove> resultType = new ArrayList<IAtmMove>();
//		for (IAtmMove item : resultFilter) {
//			resultType.add(item);
//		}
//		assertEquals(1, resultType.size());
//
//		IPageResult<IAtmMove> page = atmMoveService.page(0, 10, filter);
//		assertEquals(1, page.getTotal());
//
//		atmMoveService.remove(move1.getId());
//		Iterable<IAtmMove> listRemove = atmMoveService.list();
//		List<IAtmMove> removeLists = new ArrayList<IAtmMove>();
//		for (IAtmMove item : listRemove) {
//			removeLists.add(item);
//		}
//		assertEquals(1, removeLists.size());

	}
}
