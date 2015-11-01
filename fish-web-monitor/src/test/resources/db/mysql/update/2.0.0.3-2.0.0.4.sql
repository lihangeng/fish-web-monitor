--默认增加的人员类型为普通用户
ALTER TABLE SM_USER ADD  USER_TYPE INT(5) DEFAULT 1;
ALTER TABLE VER_DEVICE_SOFT_VERSION ADD  VERSION_STR VARCHAR(40) DEFAULT NULL;
ALTER TABLE VER_VERSION ADD  VERSION_STR VARCHAR(40) DEFAULT NULL;

ALTER TABLE VER_VERSION ADD  DOWNLOAD_COUNTER INT(10) DEFAULT 1;
ALTER TABLE VER_TASK ADD FIRST_TIME DATETIME DEFAULT NULL;
ALTER TABLE VER_TASK ADD TASK_COUNT BIGINT(20) DEFAULT 1;
ALTER TABLE VER_TASK ADD BATCH_NAME VARCHAR(20) DEFAULT NULL;
ALTER TABLE VER_DEVICE_SOFT_VERSION CHANGE TERMINAL_ID DEVICE_ID BIGINT(20) NOT NULL;
--以下三行非注释，请勿删除-------------------------------------------------------
--此处要查询外键名称 show create table ver_task ;
--ALTER TABLE VER_TASK drop FOREIGN KEY
--ALTER TABLE VER_TASK drop JOB_ID ;
DROP TABLE VER_JOB;
DROP TABLE VER_DEVICE_SOFT_VERSION;

INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('D06', null, 'versionAutoUpdate','自动更新信息','D','0');
INSERT INTO SM_ROLE_PERMISSION(PERMISSION_ID,ROLE_ID) VALUES ('D06','1');


INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('D07', null, 'versionDistribute','版本分布图','D','0');
INSERT INTO SM_ROLE_PERMISSION(PERMISSION_ID,ROLE_ID) VALUES ('D07','1');

INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE) VALUES ('10', '1', '故障处理并发数', 'status_handle_count', '30');

ALTER TABLE CASE_FAULT ADD CLOSE_TYPE VARCHAR(20);



CREATE TABLE TEMP_DEV_INFO (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  ADDRESS VARCHAR(128) DEFAULT NULL,
  AWAY_FLAG VARCHAR(15) DEFAULT NULL,
  CASHBOX_LIMIT INT(11) DEFAULT NULL,
  IP VARCHAR(20) DEFAULT NULL,
  SETUP_TYPE VARCHAR(10) DEFAULT NULL,
  STATUS INT(11) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  WORK_TYPE VARCHAR(16) DEFAULT NULL,
  DEV_SERVICE_ID BIGINT(20) DEFAULT NULL,
  DEV_TYPE_ID BIGINT(20) DEFAULT NULL,
  ORG_ID BIGINT(20) DEFAULT NULL,
  VIRTUAL VARCHAR(25) DEFAULT NULL,
  MAC VARCHAR(50) DEFAULT NULL,
  NET_TYPE INT(11) DEFAULT NULL,
  SERIAL VARCHAR(40) DEFAULT NULL,
  INSTALL_DATE DATE DEFAULT NULL,
  EFFECTIVE_DATE DATE DEFAULT NULL,
  PRIMARY KEY (ID),
  UNIQUE KEY TERMINAL_ID (TERMINAL_ID),
  FOREIGN KEY (DEV_TYPE_ID) REFERENCES DEV_TYPE (ID),
  FOREIGN KEY (DEV_SERVICE_ID) REFERENCES SM_ORG (ID),
  FOREIGN KEY (ORG_ID) REFERENCES SM_ORG (ID)
);

CREATE TABLE HIST_ATMC_TRANSACTION (
  ID BIGINT(20) NOT NULL,
  AMT DOUBLE DEFAULT NULL,
  CREDIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  CURRENCY VARCHAR(5) DEFAULT NULL,
  DATE_TIME DATETIME DEFAULT NULL,
  DEBIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  HOST_RET VARCHAR(10) DEFAULT NULL,
  LOCAL_RET VARCHAR(10) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TRANS_CODE VARCHAR(20) DEFAULT NULL,
  TRANS_ID VARCHAR(20) NOT NULL,
  TIP_FEE DOUBLE DEFAULT NULL,
  TRANS_DATE INT NOT NULL DEFAULT 0,
  COST_TIME INT DEFAULT 0,
  CARD_TYPE VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE HIST_SM_USER_LOG (
  ID BIGINT(20) NOT NULL,
  OPER_TIME TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  OPER_CODE VARCHAR(40) NOT NULL,
  OPER_NAME VARCHAR(40) NOT NULL,
  OPER_CONTENT VARCHAR(100) NOT NULL,
  OPER_RESULT VARCHAR(10) NOT NULL,
  PRIMARY KEY  (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE HIST_CASE_NOTIFY(
  ID BIGINT(20) NOT NULL,
  FAULT_ID BIGINT DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  CREATE_TIME DATETIME DEFAULT NULL,
  SEND_TIME DATETIME DEFAULT NULL,
  CONTENT VARCHAR(1024) NOT NULL,
  NOTIFY_WAY VARCHAR(10) NOT NULL,
  MOBILE VARCHAR(20) DEFAULT NULL,
  MAIL VARCHAR(40) DEFAULT NULL,
  NOTIFY_TIMES INT(5)  DEFAULT NULL,
  SEND_TIMES INT(5)  DEFAULT NULL,
  SEND_INTERVAL INT(5)  DEFAULT NULL,
  SEND_PERSON varchar(20) DEFAULT NULL,
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE HIST_CASE_FAULT(
  ID BIGINT(20) NOT NULL,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  DEV_MOD VARCHAR(5) DEFAULT NULL,
  CLASSIFY_ID VARCHAR(10) NOT NULL,
  FAULT_CODE VARCHAR(20) NOT NULL,
  VENDOR_HW_CODE VARCHAR(20) DEFAULT NULL,
  FAULT_TIME DATETIME DEFAULT NULL,
  FAULT_DATE BIGINT(20) DEFAULT NULL,
  CLOSED_TIME DATETIME DEFAULT NULL,
  DURATION DOUBLE DEFAULT NULL,
  FAULT_STATUS VARCHAR(20) NOT NULL,
  UPGRADE INT(5) DEFAULT NULL,
  CLOSE_TYPE VARCHAR(20),
  PRIMARY KEY (ID),
  FOREIGN KEY (CLASSIFY_ID) REFERENCES CASE_FAULT_CLASSIFY(ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE HIST_ATMC_STATUS_HIST (
  ID BIGINT(20) NOT NULL,
  RUN_STATUS VARCHAR(15) DEFAULT NULL,
  STATUS_TIME VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE VIEW ATMC_TRANSACTION_VIEW
    (
    	ID,
        AMT,
				CREDIT_ACCOUNT,
				CURRENCY,
				DATE_TIME,
				DEBIT_ACCOUNT,
				HOST_RET,
				LOCAL_RET,
				TERMINAL_ID,
				TRANS_CODE,
				TRANS_ID,
				TIP_FEE,
				TRANS_DATE,
				COST_TIME,
				CARD_TYPE
    ) AS
SELECT
	ID,
    AMT,
		CREDIT_ACCOUNT,
		CURRENCY,
		DATE_TIME,
		DEBIT_ACCOUNT,
		HOST_RET,
		LOCAL_RET,
		TERMINAL_ID,
		TRANS_CODE,
		TRANS_ID,
		TIP_FEE,
		TRANS_DATE,
		COST_TIME,
		CARD_TYPE
FROM
    ATMC_TRANSACTION
UNION
SELECT
	ID,
    AMT,
		CREDIT_ACCOUNT,
		CURRENCY,
		DATE_TIME,
		DEBIT_ACCOUNT,
		HOST_RET,
		LOCAL_RET,
		TERMINAL_ID,
		TRANS_CODE,
		TRANS_ID,
		TIP_FEE,
		TRANS_DATE,
		COST_TIME,
		CARD_TYPE
FROM
    HIST_ATMC_TRANSACTION;
    
--订阅管理
CREATE TABLE SM_STATUS_FILTER (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  FILTER_NAME VARCHAR(20) NOT NULL,
  USER_ID VARCHAR(20) NOT NULL,
  AWAY_FLAG INT(11) DEFAULT NULL,
  BOX_ALL CHAR(1) DEFAULT NULL,
  BOX_HEALTHY CHAR(1) DEFAULT NULL,
  BOX_EMPTY CHAR(1) DEFAULT NULL,
  BOX_FATAL CHAR(1) DEFAULT NULL,
  BOX_FULL CHAR(1) DEFAULT NULL,
  BOX_HIGH CHAR(1) DEFAULT NULL,
  BOX_LOW CHAR(1) DEFAULT NULL,
  BOX_UNKNOW CHAR(1) DEFAULT NULL,
  DEV_TYPE BIGINT(20) DEFAULT NULL,
  DEV_VENDOR BIGINT(20) DEFAULT NULL,
  DEV_GROUP_ID BIGINT(20) DEFAULT NULL,
  DEV_LIMIT INT(11) DEFAULT NULL,
  MOD_ALL CHAR(1) DEFAULT NULL,
  MOD_FATAL CHAR(1) DEFAULT NULL,
  MOD_HEALTH CHAR(1) DEFAULT NULL,
  MOD_UNKNOW CHAR(1) DEFAULT NULL,
  MOD_WARN CHAR(1) DEFAULT NULL,
  MOD_NODEVICE CHAR(1) DEFAULT NULL,
  NET_ALL CHAR(1) DEFAULT NULL,
  NET_FATAL CHAR(1) DEFAULT NULL,
  NET_HEALTH CHAR(1) DEFAULT NULL,
  NET_UNKNOW CHAR(1) DEFAULT NULL,
  NET_WARN CHAR(1) DEFAULT NULL,
  OFFSET INT(11) DEFAULT NULL,
  ORG_ID VARCHAR(40) DEFAULT NULL,
  RUN_ALL CHAR(1) DEFAULT NULL,
  RUN_STOP_ATMP CHAR(1) DEFAULT NULL,
  RUN_CUSTOMER CHAR(1) DEFAULT NULL,
  RUN_HALF CHAR(1) DEFAULT NULL,
  RUN_HEALTH CHAR(1) DEFAULT NULL,
  RUN_INITAL CHAR(1) DEFAULT NULL,
  RUN_MAINTAIN CHAR(1) DEFAULT NULL,
  RUN_REBOOT CHAR(1) DEFAULT NULL,
  RUN_SHUTDOWN CHAR(1) DEFAULT NULL,
  RUN_STOP CHAR(1) DEFAULT NULL,
  RUN_STOP_MANMADE CHAR(1) DEFAULT NULL,
  RUN_STOP_MOD CHAR(1) DEFAULT NULL,
  RUN_STOP_UNCAHSIN CHAR(1) DEFAULT NULL,
  RUN_UNKNOW CHAR(1) DEFAULT NULL,
  RUN_VDM CHAR(1) DEFAULT NULL,
  WORK_TYPE INT(11) DEFAULT NULL,
  PRIMARY KEY (ID)
);
CREATE INDEX IDX_SM_STATUS_FILTER_1 ON SM_STATUS_FILTER(USER_ID);

