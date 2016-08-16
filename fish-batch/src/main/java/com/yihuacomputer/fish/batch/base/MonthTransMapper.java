package com.yihuacomputer.fish.batch.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.yihuacomputer.fish.batch.entity.TransactionMonths;
import com.yihuacomputer.fish.batch.service.MonthTransService;

@Component("MtransRowMapper")
public class MonthTransMapper implements RowMapper<TransactionMonths> {

	@Override
	public TransactionMonths mapRow(ResultSet rs, int arg1) throws SQLException {
		TransactionMonths month=new TransactionMonths();
		month.setTransAmt(rs.getLong("sum(TRANS_AMT)"));
		month.setTransCount(rs.getLong("sum(TRANS_COUNT)"));
		month.setTransCode(rs.getString("TRANS_CODE"));
		month.setCardType(rs.getString("CARD_TYPE"));
		month.setDevType(rs.getString("DEV_TYPE"));
		month.setVendorName(rs.getString("VENDOR_NAME"));
		month.setDevTypeId(rs.getLong("DEV_TYPE_ID"));
		month.setVendorId(rs.getLong("VENDOR_ID"));
		month.setTransDate(Long.parseLong(MonthTransService.tradeTime));
		return month;
	}

}
