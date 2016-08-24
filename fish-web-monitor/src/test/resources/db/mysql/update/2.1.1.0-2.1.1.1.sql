
ALTER TABLE DEV_BOX_INFO ADD COLUMN CASHIN_VALUE BIGINT(20) DEFAULT 0;
ALTER TABLE DEV_BOX_INFO ADD COLUMN BILL_VALUE BIGINT(20) DEFAULT 0;

ALTER TABLE ATMC_TRANS_TYPE ADD COLUMN IN_OUT_FLAG INT(1) DEFAULT 0;


CREATE TABLE DEV_BOX_INIT_RULE
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	RULE_DESC VARCHAR(100) NOT NULL,
	START_USING CHAR(1) DEFAULT '1',
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO DEV_BOX_INIT_RULE VALUES(1,'钞箱阈值预警','根据钞箱的阈值进行预警加钞','1');
INSERT INTO DEV_BOX_INIT_RULE VALUES(2,'加钞间隔预警','根据上次加钞至今时间间隔预警加钞','1');


INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_TYPE) VALUES ('17', '1', '加钞间隔预警', 'cashinit_days', '7', '钞箱预警');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_TYPE) VALUES ('18', '0', '加钞机构级别', 'cashinit_orglevel', '2', '钞箱预警');


CREATE TABLE ATMC_DAY_TRADING_VOLUME
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	TERMINAL_ID VARCHAR(100) NOT NULL,
	TRANS_DATE INT(8) NOT NULL,
	AMT_IN BIGINT(20) NOT NULL,
	AMT_OUT BIGINT(20) NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ATMC_MONTH_TRADING_VOLUME
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	MONTH_IN_AVG BIGINT(20) NOT NULL,
	MONTH_OUT_AVG BIGINT(20) NOT NULL,
	TERMINAL_ID VARCHAR(100) NOT NULL,
	TRANS_MONTH INT(8) NOT NULL,
	LAST_YEAR_IN_AVG BIGINT(20) NOT NULL,
	LAST_YEAR_OUT_AVG BIGINT(20) NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;