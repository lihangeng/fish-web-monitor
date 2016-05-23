alter TABLE SM_PERMISSION modify PER_ACTION VARCHAR (64) DEFAULT NULL;
alter TABLE SM_PERMISSION modify CODE VARCHAR (64) DEFAULT NULL;
alter TABLE SM_PERMISSION modify DESCRIPTION VARCHAR (64) DEFAULT NULL;
alter table SM_PERMISSION add column IS_LEAF CHAR(1) DEFAULT '1';
alter table SM_PERMISSION add column VIEW_NAME VARCHAR(64) DEFAULT NULL;
alter table SM_PERMISSION add column ICON_CLS VARCHAR(64) DEFAULT NULL;
alter table SM_PERMISSION add column SELECT_CLS VARCHAR(64) DEFAULT NULL;
alter table SM_PERMISSION add column ROUTE_ID VARCHAR(64) DEFAULT NULL;
UPDATE SM_PERMISSION SET IS_LEAF = '0' WHERE PERMISSION_ID IN("0","A","B","C","D","E","F","G");

CREATE TABLE ATMC_TRANSACTION_DAYS (
ID bigint NOT NULL AUTO_INCREMENT,
TERMINAL_ID VARCHAR(20) COLLATE utf8_bin NOT NULL,
TRANS_DATE INT DEFAULT '0' NOT NULL,
TRANS_COUNT INT DEFAULT '0' NOT NULL,
PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ATMC_TRANSACTION_MONTHS (
ID bigint NOT NULL AUTO_INCREMENT,
TERMINAL_ID VARCHAR(20) COLLATE utf8_bin NOT NULL,
TRANS_DATE INT DEFAULT '0' NOT NULL,
TRANS_COUNT INT DEFAULT '0' NOT NULL,
PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  CASE_FAULT_MONTH (
        ID bigint NOT NULL AUTO_INCREMENT,
        TERMINAL_ID VARCHAR(20) COLLATE utf8_bin,
        DEV_MOD VARCHAR(5) COLLATE utf8_bin,
        CLASSIFY_ID VARCHAR(10) COLLATE utf8_bin NOT NULL,
        FAULT_DATE BIGINT(20) NOT NULL,
        FAULT_COUNT BIGINT(10) NOT NULL,
        PRIMARY KEY (ID)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
