//package com.yihuacomputer.fish.monitor.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Service;
//
//import com.yihuacomputer.fish.api.monitor.xfs.IStateAnalysis;
//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
//import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
//import com.yihuacomputer.fish.monitor.entity.xfs.StateAnalysis;
//import com.yihuacomputer.fish.monitor.entity.xfs.StateCode;
//
//@Service
//public class StateCodeService implements IStateCodeService{
//
//	private Map<String,StateCode> statCodes = new HashMap<String,StateCode>();
//
//	@PostConstruct
//	public void init() {
//		this.initIdcCode();
//		this.initCdmCode();
//		this.initCimCode();
//		this.initRprCode();
//		this.initJprCode();
//		this.initPinCode();
//		this.initTtuCode();
//		this.initSiuCode();
//		this.initIccCode();
//		this.initIscCode();
//		this.initFgpCode();
//	}
//
//	/**
//	 * 初始化IDC读卡器状态码
//	 */
//	private void initIdcCode(){
//		statCodes.put("IDC51",new StateCode("IDC51","13","未联机","检查是否联机"));
//		statCodes.put("IDC52",new StateCode("IDC52","13","未通电","检查是否加电"));
//		statCodes.put("IDC53",new StateCode("IDC53","13","硬件故障","检查硬件是否正常"));
//		statCodes.put("IDC54",new StateCode("IDC54","6","使用错误","检查是否有非法卡操作"));
//		statCodes.put("IDC55",new StateCode("IDC55","13","无设备","检查硬件联机是否正常"));
//		statCodes.put("IDC56",new StateCode("IDC56","13","无读磁道能力","检查硬件或SP是否正常"));
//		statCodes.put("IDC61",new StateCode("IDC61","6","有卡","取出卡"));
//		statCodes.put("IDC62",new StateCode("IDC62","6","口有卡","取走卡"));
//		statCodes.put("IDC63",new StateCode("IDC63","6","卡被堵塞","取出卡"));
//		statCodes.put("IDC64",new StateCode("IDC64","6","未知状态","检查模块是否离线"));
//		statCodes.put("IDC65",new StateCode("IDC65","6","不支持Media","检查Media"));
//		statCodes.put("IDC71",new StateCode("IDC71","6","回收槽卡满","清理回收箱"));
//		statCodes.put("IDC72",new StateCode("IDC72","6","回收槽卡将满","清理回收箱"));
//		statCodes.put("IDC73",new StateCode("IDC73","13","无回收箱","检查回收箱脱落"));
//	}
//
//	/**
//	 * 初始化CDM取款模块状态码
//	 */
//	private void initCdmCode(){
//		statCodes.put("CDM51",new StateCode("CDM51","14","未联机","检查是否联机"));
//		statCodes.put("CDM52",new StateCode("CDM52","14","未通电","检查是否加电"));
//		statCodes.put("CDM53",new StateCode("CDM53","14","硬件故障","检查硬件是否正常"));
//		statCodes.put("CDM54",new StateCode("CDM54","6","使用错误","检查硬件是否正常"));
//		statCodes.put("CDM55",new StateCode("CDM55","14","无设备","检查硬件是否正常"));
//		statCodes.put("CDM61",new StateCode("CDM61","6","安全门为打开状态","关闭安全门"));
//		statCodes.put("CDM64",new StateCode("CDM64","6","安全门为未知状态","检查安全门状态"));
//		statCodes.put("CDM65",new StateCode("CDM65","6","不支持安全门","检查安全门状态"));
//		statCodes.put("CDM71",new StateCode("CDM71","9","至少一个钞箱空或钞少","加钞"));
//		statCodes.put("CDM72",new StateCode("CDM72","14","钞箱不能出钞","检查钞箱情况"));
//		statCodes.put("CDM73",new StateCode("CDM73","14","钞箱状态未知","检查硬件是否正常"));
//		statCodes.put("CDM81",new StateCode("CDM81","6","缓存器有钞","取出缓存器的钞"));
//		statCodes.put("CDM82",new StateCode("CDM82","6","缓存器状态未知","检查缓存器是否有异物"));
//		statCodes.put("CDM83",new StateCode("CDM83","6","不支持缓存器","检查缓存器"));
//		statCodes.put("CDM90",new StateCode("CDM90","6","出钞门为打开状态","关闭出钞门"));
//		statCodes.put("CDM92",new StateCode("CDM92","6","出钞门为被堵塞状态","检查出钞门是否正常"));
//		statCodes.put("CDM93",new StateCode("CDM93","6","出钞门为未知状态","检查出钞门是否正常"));
//		statCodes.put("CDM94",new StateCode("CDM94","6","不支持出钞门","检查出钞门是否正常"));
//		statCodes.put("CDMA1",new StateCode("CDMA1","6","传送带异常","检查传送带及通道"));
//		statCodes.put("CDMB1",new StateCode("CDMB1","6","出钞口非空","清理出钞口"));
//		statCodes.put("CDMC1",new StateCode("CDMC1","5","回收箱满(或拒钞箱)","清理回收箱(或拒钞箱)"));
//		statCodes.put("CDMC2",new StateCode("CDMC2","14","回收箱(或拒钞箱)不存在","检查回收箱(或拒钞箱)是否正常"));
//		statCodes.put("CDMC3",new StateCode("CDMC3","14","回收箱(或拒钞箱)不可操作","检查回收箱(或拒钞箱)是否正常"));
//		statCodes.put("CDMC4",new StateCode("CDMC4","10","钞箱空","加钞"));
//		statCodes.put("CDMC5",new StateCode("CDMC5","14","钞箱不存在","检查钞箱是否正常"));
//		statCodes.put("CDMC6",new StateCode("CDMC6","14","钞箱不可操作","检查钞箱是否正常"));
//		statCodes.put("CDMC7",new StateCode("CDMC7","9","钞箱钞少","加钞"));
//	}
//	/**
//	 * 初始CIM存款模块状态码
//	 */
//	private void initCimCode(){
//		statCodes.put("CIM51",new StateCode("CIM51","14","未联机","检查是否联机"));
//		statCodes.put("CIM52",new StateCode("CIM52","14","未通电","检查是否加电"));
//		statCodes.put("CIM53",new StateCode("CIM53","14","硬件故障","检查硬件是否正常"));
//		statCodes.put("CIM54",new StateCode("CIM54","14","使用错误","检查硬件是否正常"));
//		statCodes.put("CIM55",new StateCode("CIM55","14","无设备","检查硬件是否正常"));
//		statCodes.put("CIM61",new StateCode("CIM61","14","有一个钞箱状态异常","检查接触是否正常"));
//		statCodes.put("CIM62",new StateCode("CIM62","14","所有钞箱都不可操作","检查钞箱是否正常"));
//		statCodes.put("CIM63",new StateCode("CIM63","14","存款钞箱状态未知","检查钞箱状态是否正常"));
//		statCodes.put("CIM71",new StateCode("CIM71","6","安全门为打开状态","关闭安全门"));
//		statCodes.put("CIM72",new StateCode("CIM72","6","安全门为异常状态","检查安全门"));
//		statCodes.put("CIM73",new StateCode("CIM73","6","安全门为未知状态","检查安全门状态"));
//		statCodes.put("CIM74",new StateCode("CIM74","6","不支持安全门","检查安全门"));
//		statCodes.put("CIM81",new StateCode("CIM81","6","验钞缓存器非空","清理缓存器"));
//		statCodes.put("CIM82",new StateCode("CIM82","6","验钞缓存器满","清理缓存器"));
//		statCodes.put("CIM83",new StateCode("CIM83","6","验钞缓存器状态未知","检查缓存状态"));
//		statCodes.put("CIM84",new StateCode("CIM84","6","不支持验钞缓存器","检查缓存"));
//		statCodes.put("CIM90",new StateCode("CIM90","6","入钞门为打开状态","关闭入钞门"));
//		statCodes.put("CIM92",new StateCode("CIM92","6","入钞门为被堵塞状态","清理入钞通道"));
//		statCodes.put("CIM93",new StateCode("CIM93","6","入钞门为未知状态","检查入钞状态"));
//		statCodes.put("CIM94",new StateCode("CIM94","6","不支持入钞门","检查入钞门"));
//		statCodes.put("CIMA1",new StateCode("CIMA1","6","传送带异常","检查传送带"));
//		statCodes.put("CIMA2",new StateCode("CIMA2","6","传送带状态未知","检查传送带"));
//		statCodes.put("CIMA3",new StateCode("CIMA3","6","不支持传送带","检查传送带"));
//		statCodes.put("CIMB1",new StateCode("CIMB1","6","入钞口非空","清理入钞口"));
//		statCodes.put("CIMB2",new StateCode("CIMB2","6","入钞口状态未知","检查入钞口"));
//		statCodes.put("CIMB3",new StateCode("CIMB3","6","不支持入钞口","检查入钞口"));
//		statCodes.put("CIMC1",new StateCode("CIMC1","5","回收箱满(或拒钞箱)","清理回收箱(或拒钞箱)"));
//		statCodes.put("CIMC2",new StateCode("CIMC2","14","回收箱(或拒钞箱)不存在","检查回收箱(或拒钞箱)是否正常"));
//		statCodes.put("CIMC3",new StateCode("CIMC3","14","回收箱(或拒钞箱)不可操作","检查回收箱(或拒钞箱)是否正常"));
//		statCodes.put("CIMC4",new StateCode("CIMC4","11","存款箱满","清理存款箱"));
//		statCodes.put("CIMC5",new StateCode("CIMC5","14","存款箱不存在","检查存款箱是否正常"));
//		statCodes.put("CIMC6",new StateCode("CIMC6","14","存款箱不可操作","检查存款箱是否正常"));
//		statCodes.put("CIMF1",new StateCode("CIMF1","5","存款回收次数超限","检查存款箱是否正常"));
//		statCodes.put("CIMF2",new StateCode("CIMF2","5","存款超时吞钞","检查回收箱中的废钞"));
//	}
//	/**
//	 * 初始化RRP凭条打印状态码
//	 */
//	private void initRprCode(){
//		statCodes.put("RPR51",new StateCode("RPR51","12" ,"未联机","检查是否联机"));
//		statCodes.put("RPR52",new StateCode("RPR52","12" ,"未通电","检查是否加电"));
//		statCodes.put("RPR53",new StateCode("RPR53","12" ,"硬件故障","检查硬件是否正常"));
//		statCodes.put("RPR54",new StateCode("RPR54","6","使用错误","检查是否卡纸"));
//		statCodes.put("RPR55",new StateCode("RPR55","12" ,"无设备","检查联机是否正常"));
//		statCodes.put("RPR60",new StateCode("RPR60","3","无凭条打印纸","检查打印纸状态或加纸"));
//		statCodes.put("RPR62",new StateCode("RPR62","6","口有凭条","清理出纸口和通道"));
//		statCodes.put("RPR63",new StateCode("RPR63","6","凭条被堵塞","清理凭条打印通道"));
//		statCodes.put("RPR64",new StateCode("RPR64","6","未知状态","检查硬件是否正常"));
//		statCodes.put("RPR65",new StateCode("RPR65","6","不支持设备","检查硬件是否正常"));
//		statCodes.put("RPR66",new StateCode("RPR66","6","凭条复位已回收","检查凭条"));
//		statCodes.put("RPR71",new StateCode("RPR71","7","纸少","加纸"));
//		statCodes.put("RPR72",new StateCode("RPR72","8","打印机纸未知状态","检查凭条纸状态加纸"));
//		statCodes.put("RPR73",new StateCode("RPR73","8","打印机纸状态未知","检查打印纸状态"));
//		statCodes.put("RPR74",new StateCode("RPR74","6","不支持该设备","检查硬件是否正常"));
//		statCodes.put("RPR75",new StateCode("RPR75","6","凭条被堵塞","清理通道"));
//		statCodes.put("RPR81",new StateCode("RPR81","6","回收箱将满","清理回收箱"));
//		statCodes.put("RPR82",new StateCode("RPR82","6","回收箱满","清理回收箱"));
//		statCodes.put("RPR83",new StateCode("RPR83","12" ,"无回收箱","检查回收箱"));
//		statCodes.put("RPR84",new StateCode("RPR84","12" ,"回收箱状态未知","检查硬件是否正常"));
//		statCodes.put("RPR91",new StateCode("RPR91","3","色带少","添加色带"));
//		statCodes.put("RPR92",new StateCode("RPR92","4","色带尽","更换色带"));
//		statCodes.put("RPR93",new StateCode("RPR93","4","不支持色带","检查色带"));
//		statCodes.put("RPR94",new StateCode("RPR94","4","色带状态未知","检查硬件是否正常"));
//		statCodes.put("RPRA1",new StateCode("RPRA1","3","墨水少","加墨"));
//		statCodes.put("RPRA2",new StateCode("RPRA2","4","墨水尽","加墨"));
//	}
//	/**
//	 * 初始化JPR日志打印机状态码
//	 */
//	private void initJprCode(){
//		statCodes.put("JPR51",new StateCode("JPR51","12" ,"未联机","检查是否联机"));
//		statCodes.put("JPR52",new StateCode("JPR52","12" ,"未通电","检查是否通电"));
//		statCodes.put("JPR53",new StateCode("JPR53","12" ,"硬件故障","检查硬件是否正常"));
//		statCodes.put("JPR54",new StateCode("JPR54","6","使用错误",""));
//		statCodes.put("JPR55",new StateCode("JPR55","12" ,"无设备",""));
//		statCodes.put("JPR60",new StateCode("JPR60","8","无打印纸","加纸"));
//		statCodes.put("JPR62",new StateCode("JPR62","6","口有打印纸","检查硬件是否正常,取走口中打印纸"));
//		statCodes.put("JPR63",new StateCode("JPR63","6","打印纸被堵塞","清理通道"));
//		statCodes.put("JPR64",new StateCode("JPR64","6","未知状态","检查硬件是否正常"));
//		statCodes.put("JPR65",new StateCode("JPR65","6","不支持","检查硬件是否正常"));
//		statCodes.put("JPR71",new StateCode("JPR71","7","纸少","加纸"));
//		statCodes.put("JPR72",new StateCode("JPR72","8","纸尽","加纸"));
//		statCodes.put("JPR73",new StateCode("JPR73","8","纸状态未知","检查打印纸是否正常"));
//		statCodes.put("JPR74",new StateCode("JPR74","8","不支持该设备","检查硬件是否正常"));
//		statCodes.put("JPR75",new StateCode("JPR75","6","纸被堵塞","清理打印通道"));
//		statCodes.put("JPR81",new StateCode("JPR81","6","回收箱将满","清理回收箱"));
//		statCodes.put("JPR82",new StateCode("JPR82","6","回收箱满","清理回收箱"));
//		statCodes.put("JPR83",new StateCode("JPR83","6","无回收箱","检查回收箱"));
//		statCodes.put("JPR84",new StateCode("JPR84","6","回收箱状态未知","检查回收箱"));
//		statCodes.put("JPR91",new StateCode("JPR91","3","色带少","添加色带"));
//		statCodes.put("JPR92",new StateCode("JPR92","4","色带尽","更换色带"));
//		statCodes.put("JPR93",new StateCode("JPR93","4","不支持色带","检查硬件是否正常"));
//		statCodes.put("JPR94",new StateCode("JPR94","4","色带状态未知","检查硬件是否正常"));
//		statCodes.put("JPRA1",new StateCode("JPRA1","3","墨水少","添加墨水"));
//		statCodes.put("JPRA2",new StateCode("JPRA2","4","墨水尽","添加墨水"));
//		statCodes.put("JPRA3",new StateCode("JPRA3","4","不支持墨水","检查墨水"));
//		statCodes.put("JPRA4",new StateCode("JPRA4","4","墨水状态未知","检查墨水"));
//	}
//	/**
//	 * 初始PIN密码键盘状态码
//	 */
//	private void initPinCode(){
//		statCodes.put("PIN51",new StateCode("PIN51","13","未联机","检查是否联机"));
//		statCodes.put("PIN52",new StateCode("PIN52","13","未通电","检查是否加电"));
//		statCodes.put("PIN53",new StateCode("PIN53","13","硬件故障","检查硬件是否正常"));
//		statCodes.put("PIN54",new StateCode("PIN54","13","使用错误","检查硬件是否正常"));
//		statCodes.put("PIN55",new StateCode("PIN55","13","无设备","检查硬件是否正常"));
//	}
//	/**
//	 * 初始TTT文本终端状态码
//	 */
//	private void initTtuCode(){
//		statCodes.put("TTU51",new StateCode("TTU51","12","未联机","检查是否联机"));
//		statCodes.put("TTU52",new StateCode("TTU52","12","未通电","检查是否通电"));
//		statCodes.put("TTU53",new StateCode("TTU53","12","硬件故障","检查是否正常"));
//		statCodes.put("TTU54",new StateCode("TTU54","12","使用错误","检查是否正常"));
//		statCodes.put("TTU55",new StateCode("TTU55","12","无设备","检查硬件是否正常"));
//	}
//
//	/**
//	 * 初始化SIU传感器状态码
//	 */
//	private void initSiuCode(){
//		statCodes.put("SIU51",new StateCode("SIU51","12","未联机","检查是否联机"));
//		statCodes.put("SIU52",new StateCode("SIU52","12","未通电","检查是否通电"));
//		statCodes.put("SIU53",new StateCode("SIU53","12","硬件故障","检查硬件是否正常"));
//		statCodes.put("SIU54",new StateCode("SIU54","12","使用错误","检查硬件是否正常"));
//		statCodes.put("SIU55",new StateCode("SIU55","12","无此设备","检查硬件是否正常"));
//	}
//
//	/**
//	 * 初始化ICC发卡读卡器状态码
//	 */
//	private void initIccCode(){
//		statCodes.put("ICC51",new StateCode("ICC51","13","未联机","检查是否联机"));
//		statCodes.put("ICC52",new StateCode("ICC52","13","未通电","检查是否加电"));
//		statCodes.put("ICC53",new StateCode("ICC53","13","硬件故障","检查硬件是否正常"));
//		statCodes.put("ICC54",new StateCode("ICC54","6","使用错误","检查是否有非法卡操作"));
//		statCodes.put("ICC56",new StateCode("ICC56","13","无读磁道能力","检查硬件或SP是否正常"));
//	}
//
//	/**
//	 * 初始化ISC发卡读卡器状态码
//	 */
//	private void initIscCode(){
//		statCodes.put("ISC51",new StateCode("ISC51","13","未联机","检查是否联机"));
//		statCodes.put("ISC52",new StateCode("ISC52","13","未通电","检查是否加电"));
//		statCodes.put("ISC53",new StateCode("ISC53","13","硬件故障","检查硬件是否正常"));
//		statCodes.put("ISC54",new StateCode("ISC54","6","使用错误","检查是否有非法卡操作"));
//	}
//
//	/**
//	 * 初始化FGP发卡读卡器状态码
//	 */
//	private void initFgpCode(){
//		statCodes.put("FGP51",new StateCode("FGP51","13","未联机","检查是否联机"));
//		statCodes.put("FGP52",new StateCode("FGP52","13","未通电","检查是否加电"));
//		statCodes.put("FGP53",new StateCode("FGP53","13","硬件故障","检查硬件是否正常"));
//		statCodes.put("FGP54",new StateCode("FGP54","6","使用错误","检查硬件是否正常"));
//	}
//
//	@Override
//	public IStateAnalysis getStateCode(String stateCode,DeviceMod deviceMod) {
//
//		StateAnalysis stAna = new StateAnalysis();
//		if(stateCode==null||deviceMod==null){
//			stAna.setDescription("No explanation");
//			stAna.setSolution("No Explication");
//			return stAna;
//		}
//		String mod = deviceMod.toString();
//		StringBuffer desc = new StringBuffer();
//		StringBuffer solution = new StringBuffer();
//		String code=null;
//		StateCode codeInfo =null;
//
//		int codeAt = 5;//由于客户端在组织状态码时少组一位，所以从第五位开始计算
//		for(int codeIdx=0;codeIdx<stateCode.length();codeIdx++){
//			code=mod+Integer.toHexString(codeAt).toUpperCase()+stateCode.charAt(codeIdx);
//			codeAt++;
//			codeInfo = this.statCodes.get(code.toUpperCase());
//			if(codeInfo ==null){
//				continue;
//			}
//			desc.append(codeInfo.getStateDescription()).append(".");
//			solution.append(codeInfo.getSolution()).append(".");
//		}
//		stAna.setDescription(desc.toString());
//		stAna.setSolution(solution.toString());
//		return stAna;
//	}
//}
