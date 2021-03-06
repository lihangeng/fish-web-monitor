package com.yihuacomputer.fish.monitor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;

/**
 * 广告服务单元测试类
 *
 * @author xuxigang
 *
 */
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class TransTypeServiceTest extends BindSessionInTest2 {

	//@Autowired
	private ITransTypeService service;

	//@Test
	public void testCRUD() {
		ITransType type = service.make("CDN","存款");
		service.add(type);
		assertTrue(type.getId() > 0);

		List<ITransType> lists = service.list(new Filter());
		assertEquals(1,lists.size());

        IPageResult<ITransType> page = service.page(0, 10, new Filter());
        assertEquals(1,page.getTotal());

	}

}
