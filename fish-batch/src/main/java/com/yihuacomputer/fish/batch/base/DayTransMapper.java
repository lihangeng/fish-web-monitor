package com.yihuacomputer.fish.batch.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.yihuacomputer.fish.batch.entity.TransactionDays;


@Component("transRowMapper")
public class DayTransMapper implements RowMapper<TransactionDays> {

	@Override
	public TransactionDays mapRow(ResultSet rs, int arg1) throws SQLException {
		TransactionDays tdDays = new TransactionDays();
		tdDays.setTransAmt(rs.getLong("sum(AMT)"));
		tdDays.setTransCount(rs.getLong("count(ATMC_TRANSACTION.ID)"));
		tdDays.setTransCode(rs.getString("TRANS_CODE"));
		tdDays.setCardType(rs.getString("CARD_TYPE"));
		tdDays.setDevType(rs.getString("typeName"));
		tdDays.setVendorName(rs.getString("vendorName"));
		tdDays.setDevTypeId(rs.getLong("devTypeId"));
		tdDays.setVendorId(rs.getLong("devVendorId"));
		tdDays.setTransDate(rs.getLong("TRANS_DATE"));
		return tdDays;
	}

}
