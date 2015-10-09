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