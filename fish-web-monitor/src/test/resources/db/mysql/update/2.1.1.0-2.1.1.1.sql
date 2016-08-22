
ALTER TABLE DEV_BOX_INFO ADD COLUMN CASHIN_VALUE BIGINT(20) DEFAULT 0;
ALTER TABLE DEV_BOX_INFO ADD COLUMN BILL_VALUE BIGINT(20) DEFAULT 0;


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


INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_TYPE) VALUES ('17', '1', '加钞间隔预警', 'CASH_INIT_DAYS', '7', '钞箱预警');