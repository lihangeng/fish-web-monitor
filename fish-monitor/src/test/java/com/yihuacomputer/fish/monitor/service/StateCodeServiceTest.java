package com.yihuacomputer.fish.monitor.service;

import static org.junit.Assert.assertEquals;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;
import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2TestConfig.class})
public class StateCodeServiceTest extends BindSessionInTest2{

//	@Autowired
	private IStateCodeService statCode;

//	@Test
	public void testGetStateCode(){
//		IStateAnalysis ana =this.statCode.getStateCode("01213111",DeviceMod.IDC);
//		System.out.println(ana.getDescription());
//		System.out.println(ana.getSolution());
//		assertEquals("有卡.回收槽卡将满.",ana.getDescription());
	}
}
