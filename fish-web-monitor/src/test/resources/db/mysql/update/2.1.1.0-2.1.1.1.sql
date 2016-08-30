
ALTER TABLE DEV_BOX_INFO ADD COLUMN CASHIN_VALUE BIGINT(20) DEFAULT 0;
ALTER TABLE DEV_BOX_INFO ADD COLUMN BILL_VALUE BIGINT(20) DEFAULT 0;

ALTER TABLE ATMC_TRANS_TYPE ADD COLUMN IN_OUT_FLAG INT(1) DEFAULT 0;

CREATE TABLE DEV_BOX_INIT_RULE
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
	RULE_DESC VARCHAR(100) NOT NULL,
	START_USING CHAR(1) DEFAULT '1',
	RULE_TYPE VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO DEV_BOX_INIT_RULE VALUES(1,'钞箱阈值预警','根据钞箱的阈值进行预警加钞','1','CASHLIMIT');
INSERT INTO DEV_BOX_INIT_RULE VALUES(2,'加钞间隔预警','根据上次加钞至今时间间隔预警加钞','1','DAYSLIMIT');


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

CREATE TABLE DEV_CASH_INIT_PLAN
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	PLAN_DATE INT(10) NOT NULL,
	CASH_INIT_CODE VARCHAR(20) NOT NULL,
	ORG_ID BIGINT(20) NOT NULL,
	CASH_INIT_PLAN_AMT BIGINT(20) NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE DEV_CASH_INIT_PLAN_DEVICE
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	TERMINAL_ID VARCHAR(20) NOT NULL,
	ORG_NAME VARCHAR(40) NOT NULL,
	LAST_AMT BIGINT(20) DEFAULT NULL,
	AWAY_FLAG VARCHAR(20) DEFAULT NULL,
	DEV_TYPE VARCHAR(30) DEFAULT NULL,
	LAST_DATE VARCHAR(20) DEFAULT NULL,
	ADVICE_AMT BIGINT(20) NOT NULL,
	ACTUAL_AMT BIGINT(20) NOT NULL,
	INIT_FLAG INT(1) NOT NULL,
	ADDRESS VARCHAR(60) DEFAULT NULL,
	CASH_INIT_PLAN_ID BIGINT(20) DEFAULT NULL,
	CASH_INIT CHAR(1) DEFAULT '1',
	PRIMARY KEY (ID),
    FOREIGN KEY (CASH_INIT_PLAN_ID) REFERENCES DEV_CASH_INIT_PLAN (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ATMC_CASH_INIT_UNIQUE (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  AMT BIGINT(20) DEFAULT NULL,
  CASH_DATE VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  UUID VARCHAR(20) DEFAULT NULL,
  UNIQUE (TERMINAL_ID),
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS DEV_PLAN_RELATION;
DROP TABLE IF EXISTS DEV_STARTPLAN;

CREATE TABLE etl_avg_day_open_rate
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_avg_week_open_rate
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    START_DATE VARCHAR(10) DEFAULT NULL,
    END_DATE VARCHAR(10) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_avg_month_open_rate
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_device_open_rate_week
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	TERMINAL_ID VARCHAR(20) DEFAULT NULL,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    START_DATE VARCHAR(10) DEFAULT NULL,
    END_DATE VARCHAR(10) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    ORG_CODE VARCHAR(40) DEFAULT NULL,
    ORG_NAME VARCHAR(40) DEFAULT NULL,
    TYPE_ID BIGINT(20) DEFAULT 0,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_device_open_rate_month
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	TERMINAL_ID VARCHAR(20) DEFAULT NULL,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    ORG_CODE VARCHAR(40) DEFAULT NULL,
    ORG_NAME VARCHAR(40) DEFAULT NULL,
    TYPE_ID BIGINT(20) DEFAULT 0,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_dev_type_open_rate_week
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    START_DATE VARCHAR(10) DEFAULT NULL,
    END_DATE VARCHAR(10) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    TYPE_ID BIGINT(20) DEFAULT 0,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_dev_type_open_rate_month
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    TYPE_ID BIGINT(20) DEFAULT 0,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_org_open_rate_week
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    START_DATE VARCHAR(10) DEFAULT NULL,
    END_DATE VARCHAR(10) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    ORG_CODE VARCHAR(40) DEFAULT NULL,
    ORG_NAME VARCHAR(40) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_org_open_rate_month
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE VARCHAR(20) DEFAULT NULL,
    OPENTIMES BIGINT(20) DEFAULT NULL,
    HEALTHY_TIMEREAL BIGINT(20) DEFAULT NULL,
    ORG_CODE VARCHAR(40) DEFAULT NULL,
    ORG_NAME VARCHAR(40) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_trans_type_day
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE  BIGINT(20) DEFAULT NULL,
    TRANS_CODE VARCHAR(20) DEFAULT NULL,
    TRANS_COUNT BIGINT(20) DEFAULT NULL,
    TRANS_AMOUNT DOUBLE DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_trans_type_week
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE  BIGINT(20) DEFAULT NULL,
    TRANS_CODE VARCHAR(20) DEFAULT NULL,
    TRANS_COUNT BIGINT(20) DEFAULT NULL,
    TRANS_AMOUNT DOUBLE DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_trans_type_month
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE  BIGINT(20) DEFAULT NULL,
    TRANS_CODE VARCHAR(20) DEFAULT NULL,
    TRANS_COUNT BIGINT(20) DEFAULT NULL,
    TRANS_AMOUNT DOUBLE DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_retain_card_week
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE  BIGINT(20) DEFAULT NULL,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
    RETAIN_COUNT BIGINT(20) DEFAULT NULL,
    DEVICE_COUNT BIGINT(20) DEFAULT NULL,
    LAST_RETAIN_COUNT BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE etl_retain_card_month
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    STAT_DATE  BIGINT(20) DEFAULT NULL,
    DEV_TYPE_NAME VARCHAR(30) DEFAULT NULL,
    RETAIN_COUNT BIGINT(20) DEFAULT NULL,
    DEVICE_COUNT BIGINT(20) DEFAULT NULL,
    LAST_RETAIN_COUNT BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('B1306', null, 'cashInitPlanDeviceExport','导出','B13','1');

INSERT INTO SM_ROLE_PERMISSION(PERMISSION_ID,ROLE_ID) VALUES ('B1306','1');
