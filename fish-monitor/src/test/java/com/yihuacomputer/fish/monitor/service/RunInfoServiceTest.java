package com.yihuacomputer.fish.monitor.service;

import java.util.Date;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;

//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class RunInfoServiceTest extends BindSessionInTest2{

//	@Autowired
	private IRunInfoService runInfoService;

	/**
	 * make()
	 * save()
	 */
//	@Test
	public void testRunInfoService(){
		IRunInfo runinfo = runInfoService.make();
		runinfo.setRunStatus(RunStatus.Maintain);
		runinfo.setStatusTime(DateUtils.getTimestamp(new Date()));
		runinfo.setTerminalId("00000QQQ");
		runInfoService.save(runinfo);
	}
}
