ALTER TABLE DEV_XFS_PROPERTISE ADD COLUMN UKR_EXIST CHAR(1) DEFAULT NULL;
ALTER TABLE DEV_XFS_PROPERTISE ADD COLUMN UKD_EXIST CHAR(1) DEFAULT NULL;

ALTER TABLE DEV_XFS_STATUS ADD COLUMN UKR_STATUS VARCHAR(15) DEFAULT NULL;
ALTER TABLE DEV_XFS_STATUS ADD COLUMN UKR_CODE VARCHAR(20) DEFAULT NULL;
ALTER TABLE DEV_XFS_STATUS ADD COLUMN UKD_STATUS VARCHAR(15) DEFAULT NULL;
ALTER TABLE DEV_XFS_STATUS ADD COLUMN UKD_CODE VARCHAR(20) DEFAULT NULL;
INSERT INTO DEV_MODULE(ID,NAME,MOD_NO,NOTE,IS_CASE) VALUES ('160', 'UKD', '0014', '发UKEY模块','1');
INSERT INTO DEV_MODULE(ID,NAME,MOD_NO,NOTE,IS_CASE) VALUES ('170', 'UKR', '0015', '读UKEY模块','1');

CREATE TABLE DEV_CATALOG_SUMMARY_MONTH
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_CATALOG VARCHAR(40) NOT NULL,
	DEV_NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE DEV_CATALOG_SUMMARY_WEEK
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_CATALOG VARCHAR(40) NOT NULL,
	DEV_NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE DEV_TYPE_SUMMARY_MONTH
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_TYPE VARCHAR(40) NOT NULL,
	DEV_NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE DEV_TYPE_SUMMARY_WEEK
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_TYPE VARCHAR(40) NOT NULL,
	DEV_NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

update sm_param set PARAM_TYPE='邮箱配置' where id=14 or id=15 or id=16;


CREATE TABLE DEV_BOX_INFO
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEVICE_ID BIGINT(20) NOT NULL,
	MAX_ALARM BIGINT(20) DEFAULT 0,
	MIN_ALARM BIGINT(20) DEFAULT 0,
	BOX_CHANGE CHAR(1) DEFAULT '1',
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE DEV_BOX_DETAIL_INFO
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	CASH_ID VARCHAR(40) NOT NULL,
	CURRENCY VARCHAR(40) DEFAULT NULL,
	BILL_VALUE INT(11) DEFAULT 0,
	NUMBER INT(11) DEFAULT 0,
	BOX_TYPE VARCHAR(40) DEFAULT NULL,
	MAXINUM INT(11) DEFAULT 0,
	EFFECT CHAR(1) DEFAULT '1',
	DEV_BOX_INFO_ID BIGINT(20) DEFAULT NULL ,
	PRIMARY KEY (ID),
    FOREIGN KEY (DEV_BOX_INFO_ID) REFERENCES DEV_BOX_INFO (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



