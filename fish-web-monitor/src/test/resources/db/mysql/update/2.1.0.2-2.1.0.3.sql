DROP TABLE VER_DEVICE_SOFT_VERSION;
DROP TABLE VER_DEVICE_VERSION;
DROP TABLE VER_JOB;
DROP TABLE VER_TASK;
DROP TABLE VER_TASK_DETAIL;

CREATE TABLE VER_DEVICE_SOFT_VERSION
(
	ID                BIGINT(20) NOT NULL AUTO_INCREMENT,
	CREATED_TIME      DATETIME NOT NULL,
	REMARK            VARCHAR (40) DEFAULT 'NULL',
	LAST_UPDATED_TIME DATETIME NOT NULL,
	TERMINAL_ID       VARCHAR (20) NOT NULL,
	TYPE_NAME         VARCHAR (40) NOT NULL,
	VERSION_NO        VARCHAR (40) NOT NULL,
	VERSION_STR        VARCHAR (40) NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE VER_DEVICE_VERSION
(
	ID                BIGINT(20) NOT NULL AUTO_INCREMENT,
	CREATED_TIME      DATETIME NOT NULL,
	REMARK            VARCHAR (120) DEFAULT 'NULL',
	DEVICE_ID         BIGINT NOT NULL,
	LAST_UPDATED_TIME DATETIME NOT NULL,
	TASK_STATUS       VARCHAR (20) DEFAULT 'NULL',
	VERSION_ID        BIGINT NOT NULL,
	COMPLETE_TASK_ID  BIGINT DEFAULT 0,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE VER_JOB(
	ID                BIGINT(20) NOT NULL AUTO_INCREMENT,
	CREATED_TIME      DATE DEFAULT NULL,
	REMARK            VARCHAR (120) DEFAULT 'NULL',
	FINISH_TIME       DATETIME DEFAULT NULL,
	JOB_NAME          VARCHAR (20) DEFAULT 'NULL',
	JOB_PRIORITY      INTEGER DEFAULT NULL,
	JOB_STATUS        INTEGER DEFAULT NULL,
	JOB_TYPE          INTEGER DEFAULT NULL,
	PLAN_TIME         DATE DEFAULT NULL,
	START_TIME        DATETIME DEFAULT NULL,
	VERSION_ID        BIGINT DEFAULT NULL,
	DEPLOY_START_DATE DATE DEFAULT NULL,
	DEPLOY_END_DATE   DATE DEFAULT NULL,
	CREATE_USER_ID    BIGINT DEFAULT NULL,
	CANCEL_PRE_VER    BIGINT DEFAULT 0,
	REBOOT_UPDATE     BIGINT DEFAULT 0,
	REBOOT_UPDATE     BIGINT DEFAULT 0,
	DOWN_COUNTER INT(5) DEFAULT 0,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE VER_TASK(
	ID                    BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEVICE_ID             BIGINT NOT NULL,
	JOB_TIME              DATETIME DEFAULT NULL,
	REASON                VARCHAR (90) DEFAULT 'NULL',
	VERSION_BEFORE_UPDATE VARCHAR (70) DEFAULT 'NULL',
	EXCEPT_VERSION        VARCHAR (70) DEFAULT 'NULL',
	TASK_STATUS           VARCHAR (20) DEFAULT 'NULL',
	TASK_TYPE             VARCHAR (15) DEFAULT 'NULL',
	EAGER_RESTART         CHAR (1) DEFAULT '0',
	IS_SUCCESS            CHAR(1) DEFAULT '0',
	JOB_ID                BIGINT DEFAULT NULL,
	VERSION_ID            BIGINT NOT NULL,
	PROCESS               DOUBLE DEFAULT '0.0',
	DOWNLOAD_START_TIME   VARCHAR (30) DEFAULT 'NULL',
	DOWNLOAD_FINISH_TIME  VARCHAR (30) DEFAULT 'NULL',
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE VER_TASK_DETAIL
(
	ID           BIGINT(20) NOT NULL AUTO_INCREMENT,
	CREATED_TIME DATETIME DEFAULT NULL,
	REMARK       VARCHAR (40) DEFAULT 'NULL',
	IS_SUCCESS   CHAR(1) DEFAULT '0',
	TASK_ACTION  VARCHAR (20) DEFAULT 'NULL',
	TASK_ID      BIGINT NOT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE INDEX IDX_VER_DEVICE_SOFT_VERSION_1 ON VER_DEVICE_SOFT_VERSION(TERMINAL_ID);
CREATE INDEX IDX_VER_DEVICE_SOFT_VERSION_2 ON VER_DEVICE_SOFT_VERSION(TYPE_NAME);
CREATE INDEX IDX_VER_DEVICE_SOFT_VERSION_3 ON VER_DEVICE_SOFT_VERSION(VERSION_NO);
CREATE INDEX IDX_VER_DEVICE_VERSION_1 ON VER_DEVICE_VERSION(DEVICE_ID);
CREATE INDEX IDX_VER_DEVICE_VERSION_2 ON VER_DEVICE_VERSION(VERSION_ID);
CREATE INDEX IDX_VER_TASK_1 ON VER_TASK(DEVICE_ID);
CREATE INDEX IDX_VER_TASK_2 ON VER_TASK(JOB_ID);