package com.yihuacomputer.fish.monitor.entity.volume;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolume;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeExtractDataService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.monitor.entity.business.TransType;
import com.yihuacomputer.fish.monitor.entity.business.Transaction;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class DayTradingVolumeExtractDataService implements IDayTradingVolumeExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	IDayTradingVolumeService dayTradingVolumeService;

	@Override
	public void generalDayTradingVolume() {
		String lastyDate = DateUtils.getLastShortDate();
		generalDayTradingVolumeByDate(lastyDate);
	}

	/**
	 * 每日生成交易统计信息
	 */
	@Override
	public void generalDayTradingVolumeByDate(String date) {
		int lastDay = Integer.parseInt(date);
		StringBuffer sb = new StringBuffer();
		sb.append("select   trans.terminalId,sum(case when transType.inOutFlag=1 then trans.amt else 0 end),")
		.append("sum(case when transType.inOutFlag=2 then trans.amt else 0 end)").append(" from ")
		.append(Transaction.class.getSimpleName()).append(" trans, ")
		.append(TransType.class.getSimpleName()).append(" transType ")
		.append(" where trans.transCode=transType.transCode and trans.transDate=? group by trans.terminalId");
		List<Object> objList = dao.findByHQL(sb.toString(),new Object[]{lastDay});
		for(Object obj:objList){
			Object[] objs = (Object[])obj;
			IDayTradingVolume dayTradingVolume = dayTradingVolumeService.make();
			dayTradingVolume.setTerminalId(String.valueOf(objs[0]));
			dayTradingVolume.setAmtIn(Double.parseDouble(String.valueOf(objs[1])));
			dayTradingVolume.setAmtOut(Double.parseDouble(String.valueOf(objs[2])));
			dayTradingVolume.setTransDate(lastDay);
			dayTradingVolumeService.save(dayTradingVolume);
		}
	}

}
