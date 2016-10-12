ALTER TABLE SM_USER_LOG ADD COLUMN CLIENT_IP varchar(20) DEFAULT null;
ALTER TABLE SM_USER_LOG ADD COLUMN SERVER_IP varchar(20) DEFAULT null;
ALTER TABLE SM_USER_LOG ADD COLUMN TIMES BIGINT(20) DEFAULT 0;

DELETE FROM SM_PERMISSION WHERE PERMISSION_ID LIKE 'F40%';
DELETE FROM SM_ROLE_PERMISSION WHERE PERMISSION_ID LIKE 'F40%';
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_TYPE) VALUES ('19', '1', '默认日均存款金额(单位:元)', 'trading_volume_in', '50000', '钞箱预警');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_TYPE) VALUES ('20', '1', '默认日均取款金额(单位:元)', 'trading_volume_out', '50000', '钞箱预警');
INSERT INTO DEV_CASH_INIT_RULE VALUES(3,'日均交易预警','根据日均交易量进行预警加钞','1','TRADINGVOLUME');
