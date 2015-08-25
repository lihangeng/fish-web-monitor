package com.yihuacomputer.fish.monitor.service;

import com.yihuacomputer.domain.test.BindSessionInTest2;

/**
 * 广告服务单元测试类
 *
 * @author xuxigang
 *
 */
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class DeviceOfflineCheckTest extends BindSessionInTest2 {

//	@Autowired
	private DeviceOfflineCheck deviceOfflineCheck;


//	@Test
	public void testCollectModuleStatus() {
		deviceOfflineCheck.deviceOffline();
	}

}
