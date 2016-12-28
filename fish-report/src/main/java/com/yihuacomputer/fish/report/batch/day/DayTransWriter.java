package com.yihuacomputer.fish.report.batch.day;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

/**
 * @author YiHua
 *
 */
@Component("transWriter")
public class DayTransWriter implements ItemWriter<TransactionDays> {
	private static final String SAVE_SQL = "INSERT INTO ATMC_TRANSACTION_DAYS (VENDOR_NAME,DEV_TYPE,TRANS_CODE,CARD_TYPE,TRANS_DATE,TRANS_COUNT,TRANS_AMT,VENDOR_ID,DEV_TYPE_ID) VALUES (?,?,?,?,?,?,?,?,?)";
	private JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(DayTransWriter.class);

	/**
	 * @param days
	 */
	public void save(final TransactionDays days) {
		jdbcTemplate.update(SAVE_SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement day) throws SQLException {
				day.setString(1, days.getVendorName());
				day.setString(2, days.getDevType());
				day.setString(3, days.getTransCode());
				day.setString(4, days.getCardType());
				day.setLong(5, days.getTransDate());
				day.setLong(6, days.getTransCount());
				day.setLong(7, days.getTransAmt());
				day.setLong(8, days.getVendorId());
				day.setLong(9, days.getDevTypeId());
			}
		});
	}

	@Override
	public void write(List<? extends TransactionDays> days) throws Exception {
		logger.info("lllllll" + days.size());
		for (TransactionDays day : days) {
			save(day);
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
