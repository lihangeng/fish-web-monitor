package com.yihuacomputer.fish.batch.base;

import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;
import com.yihuacomputer.fish.batch.entity.TransactionMonths;


@Component("MtransWriter")
public class MonthTransWriter implements ItemWriter<TransactionMonths> {
	private static final String SAVE_SQL = "INSERT INTO ATMC_TRANSACTION_MONTHS (VENDOR_NAME,DEV_TYPE,TRANS_CODE,CARD_TYPE,TRANS_DATE,TRANS_COUNT,TRANS_AMT,VENDOR_ID,DEV_TYPE_ID) VALUES(?,?,?,?,?,?,?,?,?)";

	private JdbcTemplate jdbcTemplate;

	public void save(final TransactionMonths months) {
		jdbcTemplate.update(SAVE_SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement month) throws SQLException {

				month.setString(1, months.getVendorName());
				month.setString(2, months.getDevType());
				month.setString(3, months.getTransCode());
				month.setString(4, months.getCardType());
				month.setLong(5, months.getTransDate());
				month.setLong(6, months.getTransCount());
				month.setLong(7, months.getTransAmt());
				month.setLong(8, months.getVendorId());
				month.setLong(9, months.getDevTypeId());
			}
		});
	}

	@Override
	public void write(List<? extends TransactionMonths> months) throws Exception {
		for (TransactionMonths month : months) {
			save(month);
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
