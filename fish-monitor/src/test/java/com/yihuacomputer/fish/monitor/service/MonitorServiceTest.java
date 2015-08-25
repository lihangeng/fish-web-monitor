package com.yihuacomputer.fish.monitor.service;

import static org.junit.Assert.assertNotNull;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.IMonitorService;

/**
 * 广告服务单元测试类
 *
 * @author xuxigang
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class MonitorServiceTest extends BindSessionInTest2 {

//	@Autowired
	private IMonitorService monitor;

//	@Test
	public void testAdd() {
		assertNotNull(monitor);
	}

}
