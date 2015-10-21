CREATE TABLE ADV_ADVERT(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  ADVERT_DOWN_METHOD VARCHAR(32) NOT NULL,
  ADVERT_TYPE VARCHAR(32) NOT NULL,
  ADVERT_VALIDITY VARCHAR(32) NOT NULL,
  CREATED_TIME DATETIME DEFAULT NULL,
  VERSION_ID BIGINT(20) DEFAULT NULL,
  CREATE_USER_ID BIGINT(20) DEFAULT NULL,
  CREATE_ORG_ID BIGINT DEFAULT NULL,
  CHECK_STATUS BIGINT DEFAULT NULL,
  VIDEO_FLAG BIGINT DEFAULT 0,
  PRIMARY KEY (ID)
);

CREATE TABLE ADV_ADVERT_RESOURCE(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  BEGIN_DATE DATE DEFAULT NULL,
  BEGIN_TIME VARCHAR(32) DEFAULT NULL,
  CONTENT VARCHAR(140) DEFAULT NULL,
  END_DATE DATE DEFAULT NULL,
  END_TIME VARCHAR(32) DEFAULT NULL,
  PLAY_TIME INT(11) DEFAULT NULL,
  ADVERT_ID BIGINT(20) DEFAULT NULL,
  SCREEN VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ADVERT_ID) REFERENCES ADV_ADVERT (ID)
);

CREATE TABLE ATMC_JOURNAL(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  CONTENT VARCHAR(1024) DEFAULT NULL,
  CREATED_TIME VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_JOURNAL_DAY(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  JOURNAL_DAY INT DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_APP_LOGS(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  BACKUP_RESULT VARCHAR(15) DEFAULT NULL,
  DATE_TIME VARCHAR(20) DEFAULT NULL,
  DO_TIMES INT(11) DEFAULT NULL,
  LOG_SIZE BIGINT(20) DEFAULT NULL,
  LAST_DO_DATE VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  CROWN_IMPOERT CHAR(1) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_CASH_INIT (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  AMT BIGINT(20) DEFAULT NULL,
  CASH_DATE VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  UUID VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_CASH_INIT_BOX (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  BOX_INIT_AMT BIGINT(20) DEFAULT NULL,
  BOX_CURRENCY VARCHAR(5) DEFAULT NULL,
  BOX_ID VARCHAR(15) DEFAULT NULL,
  INIT_ID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (INIT_ID) REFERENCES ATMC_CASH_INIT (ID)
);

CREATE TABLE ATMC_DAYBACKUP_LOG (
  DAYBACKUP_DATE VARCHAR(20) NOT NULL,
  DEVICE_COUNT INT(11) DEFAULT NULL,
  DO_TIME VARCHAR(20) DEFAULT NULL,
  END_TIME VARCHAR(20) DEFAULT NULL,
  RESULT VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (DAYBACKUP_DATE)
);

CREATE TABLE ATMC_RETAIN_CARD (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  ACCOUNT_NO VARCHAR(20) DEFAULT NULL,
  DISTRIBUTION_BANK VARCHAR(40) DEFAULT NULL,
  CARD_RETAIN_TIME DATETIME DEFAULT NULL,
  CARD_TYPE VARCHAR(30) DEFAULT NULL,
  CARD_RETAIN_TYPE VARCHAR(30) DEFAULT NULL,
  CUST_NAME VARCHAR(20) DEFAULT NULL,
  CUST_PAPERS VARCHAR(80) DEFAULT NULL,
  CUST_PHONE VARCHAR(20) DEFAULT NULL,
  ORG_ID BIGINT(20) DEFAULT NULL,
  ORG_SUBSIDIARYORGANID VARCHAR(20) DEFAULT NULL,
  HANDOVER_ORG_ID BIGINT(20) DEFAULT NULL,
  REASON VARCHAR(50) DEFAULT NULL,
  RET VARCHAR(5) DEFAULT NULL,
  STATUS VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TREATMENT_PEOPLE VARCHAR(20) DEFAULT NULL,
  TREATMENT_TIME DATETIME DEFAULT NULL,
  TREATMENT_ADDRESS VARCHAR(60) DEFAULT NULL,
  PHONE VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ORG_ID) REFERENCES SM_ORG (ID),
  FOREIGN KEY (HANDOVER_ORG_ID) REFERENCES SM_ORG (ID)
);

CREATE TABLE ATMC_SETTLEMENT (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  WITHDRAWAL_AMT BIGINT(20) DEFAULT NULL,
  SETTLE_DATE VARCHAR(20) DEFAULT NULL,
  DEPOSIT BIGINT(20) DEFAULT NULL,
  DEPOSIT_AMT BIGINT(20) DEFAULT NULL,
  LEFT_AMT BIGINT(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TRANS_COUNT BIGINT(20) DEFAULT NULL,
  TRANS_AMT BIGINT(20) DEFAULT NULL,
  UUID VARCHAR(20) DEFAULT NULL,
  WITHDRAWAL BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_SETTLE_BOX (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  BOX_LEFT_AMT BIGINT(20) DEFAULT NULL,
  BOX_CURRENCY VARCHAR(5) DEFAULT NULL,
  BOX_ID VARCHAR(15) NOT NULL,
  SETTLE_ID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (SETTLE_ID) REFERENCES ATMC_SETTLEMENT (ID)
);

CREATE TABLE ATMC_STATUS_HIST (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  RUN_STATUS VARCHAR(15) DEFAULT NULL,
  STATUS_TIME VARCHAR(20) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_TRANSACTION (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
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
);

CREATE TABLE ATMC_TRANS_TYPE(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TRANS_CODE VARCHAR(20) NOT NULL,
  CODE_DESC VARCHAR(30) NOT NULL,
  TRANS_SEQ INT(11) DEFAULT 0,
  UNIQUE KEY TRANS_CODE(TRANS_CODE),
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_TRANS_HOSTRET(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  HOSTRET_CODE VARCHAR(20) NOT NULL,
  HOSTRET_NAME VARCHAR(30) NOT NULL,
  UNIQUE KEY TRANS_CODE(HOSTRET_CODE),
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_CROWN_NUMBER (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  CREATE_DATE INT NOT NULL,
  CREATE_TIME VARCHAR(20) NOT NULL,
  TRANS_ID VARCHAR(20) NOT NULL,
  TRANS_HOST_ID VARCHAR(20) NOT NULL,
  TRANS_ACCOUNT VARCHAR(20) DEFAULT NULL,
  CROWN_ID VARCHAR(10) NOT NULL,
  CURRENCY VARCHAR(5) DEFAULT NULL,
  TRANS_TYPE VARCHAR(20) DEFAULT NULL,
  VERSION VARCHAR(5) DEFAULT NULL,
  STATE VARCHAR(5) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_BLACK_CARDS (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  ADD_DATE DATETIME DEFAULT NULL,
  CARD_NO VARCHAR(20) NOT NULL,
  USER_NAME VARCHAR(20) DEFAULT NULL,
  CARD_ORGANIZATION VARCHAR(60) DEFAULT NULL,
  UNIQUE KEY CODE (CARD_NO),
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_OPEN_RATE(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  STAT_DATE VARCHAR(20) DEFAULT NULL,
  OPENTIMES INT(11) DEFAULT NULL,
  HEALTHY_TIMEREAL INT(11) DEFAULT NULL,
  UNKNOWN_TIMEREAL INT(11) DEFAULT NULL,
  MAINTAIN_TIMEREAL INT(11) DEFAULT NULL,
  FAULT_TIMEREAL INT(11) DEFAULT NULL,
  ATMP_TIMEREAL INT(11) DEFAULT NULL,
  STOP_TIMEREAL INT(11) DEFAULT NULL,
  NO_SCREENREAL INT(11) DEFAULT NULL,
  PROGRAM_OPENTIME VARCHAR(20) DEFAULT NULL,
  PROGRAM_CLOSETIME VARCHAR(20) DEFAULT NULL,
  PROGRAM_TIMES INT(11) DEFAULT NULL,
  PROGRAM_TIMEREAL INT(11) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_HARDWARE (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  RELEASE_DATE VARCHAR(20) DEFAULT NULL,
  VENDOR VARCHAR(50) DEFAULT NULL,
  VERSION VARCHAR(256) DEFAULT NULL,
  HARDISK BIGINT(20) DEFAULT NULL,
  MEMORY_FREE BIGINT(20) DEFAULT NULL,
  MEMORY_SIZE BIGINT(20) DEFAULT NULL,
  MEMORY_USED BIGINT(20) DEFAULT NULL,
  MEMORY_USED_PERCENT DOUBLE DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_HARDWARE_CPU (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CACHE_SIZE BIGINT(20) DEFAULT NULL,
  COMBINED VARCHAR(50) DEFAULT NULL,
  FREQUENCY INT(11) DEFAULT NULL,
  IDLE VARCHAR(10) DEFAULT NULL,
  MODEL VARCHAR(50) DEFAULT NULL,
  CPU_SYS VARCHAR(10) DEFAULT NULL,
  TOTAL_CORES INT(11) DEFAULT NULL,
  CPU_USER VARCHAR(10) DEFAULT NULL,
  VENDOR VARCHAR(50) DEFAULT NULL,
  TERMINAL_ID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (TERMINAL_ID) REFERENCES DEV_HARDWARE (ID)
);

CREATE TABLE DEV_HARDWARE_DISK (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  FILE_SYS VARCHAR(15) DEFAULT NULL,
  FREE_SIZE BIGINT(20) DEFAULT NULL,
  LABEL VARCHAR(20) DEFAULT NULL,
  LABEL_NAME VARCHAR(30) DEFAULT NULL,
  MEMO VARCHAR(30) DEFAULT NULL,
  NAME VARCHAR(30) DEFAULT NULL,
  TOTAL_SIZE BIGINT(20) DEFAULT NULL,
  TERMINAL_ID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (TERMINAL_ID) REFERENCES DEV_HARDWARE (ID)
);

CREATE TABLE DEV_ILLEGAL_PROCESS (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CPU_RATE DOUBLE DEFAULT NULL,
  PROCESS_DATE VARCHAR(20) DEFAULT NULL,
  MEMORY_RATE BIGINT(20) DEFAULT NULL,
  NAME VARCHAR(50) NOT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  PROCESS_USER VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_MOVE (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  SRC_ADDR VARCHAR(50) DEFAULT NULL,
  MOVE_DATE DATETIME DEFAULT NULL,
  NOTE VARCHAR(50) DEFAULT NULL,
  SRC_ORG_ID BIGINT(20) DEFAULT NULL,
  RES_PERSON VARCHAR(10) DEFAULT NULL,
  TARGET_ADDR VARCHAR(50) DEFAULT NULL,
  TARGET_ORG_ID BIGINT(20) DEFAULT NULL,
  DEV_NO VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_REGISTER (
  TERMINAL_ID VARCHAR(20) NOT NULL,
  ATMC_VERSION VARCHAR(20) DEFAULT NULL,
  REG_STATUS VARCHAR(15) DEFAULT NULL,
  REG_TIMES INT(11) DEFAULT NULL,
  SERIAL_NUMBER VARCHAR(40) DEFAULT NULL,
  SIGN_DATE VARCHAR(20) DEFAULT NULL,
  SIGN_TIMES INT(11) DEFAULT NULL,
  PRIMARY KEY (TERMINAL_ID)
);

CREATE TABLE DEV_RUNTIMEPARAM (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  DEV_MODULE VARCHAR(20) DEFAULT NULL,
  DEV_KEY VARCHAR(50) DEFAULT NULL,
  DEV_VALUE VARCHAR(50) DEFAULT NULL,
  DEFAULT_VALUE VARCHAR(50) DEFAULT NULL,
  DEV_LABEL VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_SOFTWARE (
  TERMINAL_ID VARCHAR(20) NOT NULL,
  ANTI_SYSTEM VARCHAR(50) DEFAULT NULL,
  ANTI_VERSION VARCHAR(20) DEFAULT NULL,
  ATMC_VERSION VARCHAR(30) DEFAULT NULL,
  CHK_CASH_DATA VARCHAR(50) DEFAULT NULL,
  OS_ARCH VARCHAR(20) DEFAULT NULL,
  OS_DESCRIPTION VARCHAR(30) DEFAULT NULL,
  SYS_PATCH_VERSION VARCHAR(30) DEFAULT NULL,
  SYS_PATCH_LEVEL VARCHAR(20) DEFAULT NULL,
  OS_TYPE VARCHAR(15) DEFAULT NULL,
  OS_VENDOR VARCHAR(30) DEFAULT NULL,
  OS_VENDOR_NAME VARCHAR(50) DEFAULT NULL,
  SYS_VERSION VARCHAR(30) DEFAULT NULL,
  SP_DATE VARCHAR(20) DEFAULT NULL,
  SP_PATCH VARCHAR(30) DEFAULT NULL,
  SP_VERSION VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (TERMINAL_ID)
);

CREATE TABLE DEV_STOP (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  DEV_NO VARCHAR(20) DEFAULT NULL,
  OPEN_TIME DATETIME DEFAULT NULL,
  RESP_NAME VARCHAR(20) DEFAULT NULL,
  SET_TIME DATETIME DEFAULT NULL,
  STOP_REASION VARCHAR(60) DEFAULT NULL,
  STOP_TIME DATETIME DEFAULT NULL,
  STOP_TYPE INT(11) DEFAULT NULL,
  DEV_STATUS VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_WHITE_PROCESS (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CPU_USE DOUBLE DEFAULT NULL,
  PROCESS_DATE VARCHAR(20) DEFAULT NULL,
  NOTE VARCHAR(50) DEFAULT NULL,
  MEN_USE BIGINT(20) DEFAULT NULL,
  NAME VARCHAR(50) NOT NULL,
  PROCESS_USER VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_XFS_PROPERTISE (
  TERMINAL_ID VARCHAR(20) NOT NULL,
  CDM_EXIST CHAR(1) DEFAULT NULL,
  CIM_EXIST CHAR(1) DEFAULT NULL,
  IDC_EXIST CHAR(1) DEFAULT NULL,
  NFC_EXIST CHAR(1) DEFAULT NULL,
  PBK_EXIST CHAR(1) DEFAULT NULL,
  JPR_EXIST CHAR(1) DEFAULT NULL,
  PIN_EXIST CHAR(1) DEFAULT NULL,
  RPR_EXIST CHAR(1) DEFAULT NULL,
  SIU_EXIST CHAR(1) DEFAULT NULL,
  TTU_EXIST CHAR(1) DEFAULT NULL,
  FGP_EXIST CHAR(1) DEFAULT NULL,
  ICC_EXIST CHAR(1) DEFAULT NULL,
  ISC_EXIST CHAR(1) DEFAULT NULL,
  PRIMARY KEY (TERMINAL_ID)
);

CREATE TABLE DEV_XFS_STATUS (
  TERMINAL_ID VARCHAR(20) NOT NULL,
  BOX_CURRENT_COUNT BIGINT(20) DEFAULT NULL,
  BOX_INIT_COUNT BIGINT(20) DEFAULT NULL,
  BOX_STATUS VARCHAR(15) DEFAULT NULL,
  CDM_STATUS VARCHAR(15) DEFAULT NULL,
  CDM_CODE VARCHAR(20) DEFAULT NULL,
  CIM_STATUS VARCHAR(15) DEFAULT NULL,
  CIM_CODE VARCHAR(20) DEFAULT NULL,
  DATE_TIME VARCHAR(20) DEFAULT NULL,
  IDC_CAPTURE_BIN_COUNT INT(11) DEFAULT NULL,
  IDC_STATUS VARCHAR(15) DEFAULT NULL,
  IDC_CODE VARCHAR(20) DEFAULT NULL,
  NFC_STATUS VARCHAR(15) DEFAULT NULL,
  NFC_CODE VARCHAR(20) DEFAULT NULL,
  JPR_STATUS VARCHAR(15) DEFAULT NULL,
  JPR_CODE VARCHAR(20) DEFAULT NULL,
  MOD_STATUS VARCHAR(15) DEFAULT NULL,
  NET_STATUS VARCHAR(15) DEFAULT NULL,
  PIN_STATUS VARCHAR(15) DEFAULT NULL,
  PIN_CODE VARCHAR(20) DEFAULT NULL,
  PRP_STATUS VARCHAR(15) DEFAULT NULL,
  PRP_CODE VARCHAR(20) DEFAULT NULL,
  PBK_STATUS VARCHAR(15) DEFAULT NULL,
  PBK_CODE VARCHAR(20) DEFAULT NULL,
  RUN_STATUS VARCHAR(15) DEFAULT NULL,
  SIU_STATUS VARCHAR(15) DEFAULT NULL,
  SIU_CODE VARCHAR(20) DEFAULT NULL,
  TTU_STATUS VARCHAR(15) DEFAULT NULL,
  TTU_CODE VARCHAR(20) DEFAULT NULL,
  ICC_STATUS VARCHAR(15) DEFAULT NULL,
  ICC_CODE VARCHAR(20) DEFAULT NULL,
  ICC_RETAIN_CARD_COUNT INT(11) DEFAULT NULL,
  ICC_HW_CODE VARCHAR(20) DEFAULT NULL,
  ICC_CAPTURE_BIN_COUNT INT(11) DEFAULT NULL,
  FGP_STATUS VARCHAR(15) DEFAULT NULL,
  FGP_CODE VARCHAR(20) DEFAULT NULL,
  FGP_HW_CODE VARCHAR(20) DEFAULT NULL,
  ISC_STATUS VARCHAR(15) DEFAULT NULL,
  ISC_CODE VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (TERMINAL_ID)
);

CREATE TABLE SM_STATUS_FILTER (
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
  PRIMARY KEY (USER_ID)
);



CREATE TABLE VER_VERSION_TYPE (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  REMARK VARCHAR(128) DEFAULT NULL,
  TYPE_NAME VARCHAR(64) NOT NULL,
  AUTO_DEPLOY CHAR(1) DEFAULT '0',
  IS_DISPLAY CHAR(1) DEFAULT NULL,
  IS_SYSTEM CHAR(1) DEFAULT NULL,
  CUSTOM_VERSION CHAR(1) DEFAULT NULL,
  DEFAULT_INSTALL_PATH VARCHAR(50) DEFAULT NULL,
  VERSION_CATALOG VARCHAR(30) DEFAULT NULL ,
  PRIMARY KEY (ID),
  UNIQUE KEY TYPE_NAME (TYPE_NAME)
);

CREATE TABLE VER_TYPE_RESTRICTION(
   ID BIGINT(20) NOT NULL AUTO_INCREMENT,
   RESTRICTION_COLUMN  VARCHAR(30) NOT NULL,
   RESTRICTION_NAME VARCHAR(30) NOT NULL,
   VERSION_TYPE_ID BIGINT(20),
   PRIMARY KEY (ID),
   FOREIGN KEY (VERSION_TYPE_ID) REFERENCES VER_VERSION_TYPE (ID)
);

CREATE TABLE VER_VERSION (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  AUTO_DOWN CHAR(1) DEFAULT NULL,
  CREATED_TIME DATETIME DEFAULT NULL,
  RELEASE_DATE DATETIME DEFAULT NULL,
  REMARK VARCHAR(100) DEFAULT NULL,
  SERVER_PATH VARCHAR(64) DEFAULT NULL,
  ORIGINAL_FILE_NAME VARCHAR(60) DEFAULT NULL,
  VERSION_NO VARCHAR(40) NOT NULL,
  VERSION_PATH VARCHAR(50) NOT NULL,
  VERSION_STATUS INT(11) DEFAULT NULL,
  DEPEND_VERSION_ID BIGINT(20) DEFAULT NULL,
  UNCOMPRESS CHAR(1) DEFAULT NULL,
  EAGER_RESTART CHAR(1) DEFAULT '0',
  VERSION_TYPE_ID BIGINT(20) NOT NULL,
  EXEC_BEFORE VARCHAR(50) DEFAULT NULL,
  EXEC_AFTER VARCHAR(50) DEFAULT NULL,
  CREATE_USER_ID BIGINT(20) DEFAULT NULL,
  MD5_CHECK_NUM VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (VERSION_TYPE_ID) REFERENCES VER_VERSION_TYPE (ID),
  FOREIGN KEY (DEPEND_VERSION_ID) REFERENCES VER_VERSION (ID)
);

CREATE TABLE VER_DEVICE_SOFT_VERSION (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CREATED_TIME DATETIME NOT NULL,
  REMARK VARCHAR(40) DEFAULT NULL,
  LAST_UPDATED_TIME DATETIME NOT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TYPE_NAME VARCHAR(40) NOT NULL,
  VERSION_NO VARCHAR(40) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE VER_DEVICE_VERSION (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CREATED_TIME DATETIME NOT NULL,
  REMARK VARCHAR(40) DEFAULT NULL,
  DEVICE_ID BIGINT(20) NOT NULL,
  LAST_UPDATED_TIME DATETIME NOT NULL,
  TASK_STATUS VARCHAR(20) DEFAULT NULL,
  VERSION_ID BIGINT(20) NOT NULL,
  COMPLETE_TASK_ID BIGINT(20) DEFAULT 0,
  PRIMARY KEY (ID)
);

CREATE TABLE VER_JOB (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CREATED_TIME DATETIME DEFAULT NULL,
  JOB_NAME VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE VER_TASK (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  DEVICE_ID BIGINT(20) NOT NULL,
  CREATE_TIME DATETIME DEFAULT NULL,
  PLAN_TIME DATETIME DEFAULT NULL,
  JOB_TIME DATETIME DEFAULT NULL,
  REASON VARCHAR(40) DEFAULT NULL,
  VERSION_BEFORE_UPDATE VARCHAR(70) DEFAULT NULL,
  EXCEPT_VERSION VARCHAR(70) DEFAULT NULL,
  TASK_STATUS VARCHAR(20) DEFAULT NULL,
  TASK_TYPE VARCHAR(15) DEFAULT NULL,
  EAGER_RESTART CHAR(1) DEFAULT '0',
  IS_SUCCESS CHAR(1) DEFAULT NULL,
  JOB_ID BIGINT(20) DEFAULT NULL,
  VERSION_ID BIGINT(20) NOT NULL,
  EXCUTE_MACHINE VARCHAR(16) DEFAULT NULL,
  DOWN_SOURCE VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (VERSION_ID) REFERENCES VER_VERSION (ID),
  FOREIGN KEY (JOB_ID) REFERENCES VER_JOB (ID)
);

CREATE TABLE VER_TASK_DETAIL (
    ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    CREATED_TIME DATETIME DEFAULT NULL,
    REMARK VARCHAR(40) DEFAULT NULL,
    IS_SUCCESS CHAR(1) DEFAULT NULL,
    TASK_ACTION VARCHAR(20) DEFAULT NULL,
    TASK_ID BIGINT(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE VER_DEPLOYDATE_HISTORY (
    ID BIGINT(20) NOT NULL AUTO_INCREMENT,
    NOTICE_TIME DATETIME DEFAULT NULL,
    JOB_ID BIGINT(20) NOT NULL,
    TASK_ID BIGINT(20) NOT NULL,
    DEPLOY_START_DATE DATETIME DEFAULT NULL,
    NOTICE_STATUS CHAR(15) DEFAULT NULL,
    REASON VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DEV_MAP_MARKER (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  MARKED_ID VARCHAR(20) NOT NULL,
  MARKER_TYPE VARCHAR(10) NOT NULL,
  LATITUDE VARCHAR(50) DEFAULT NULL,
  LONGTITUDE VARCHAR(50) DEFAULT NULL,
  ZOOM INT(5) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_STARTPLAN (
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(30) DEFAULT NULL,
  NOTE VARCHAR(30) DEFAULT NULL,
  START_DATE VARCHAR(20) DEFAULT NULL,
  END_DATE VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE DEV_PLAN_RELATION (
  DEV_PLAN_RELATION_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  DEV_ID BIGINT(20) DEFAULT NULL,
  PLAN_ID BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (DEV_PLAN_RELATION_ID)
);

CREATE TABLE CASE_FILTER(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CODE VARCHAR(30) NOT NULL,
  REMARK VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (ID),
  UNIQUE (CODE)
);

CREATE TABLE CASE_FAULT_CLASSIFY(
  ID VARCHAR(10) NOT NULL,
  CLASSIFY_NAME VARCHAR(30) NOT NULL,
  RESPONSOR_TYPE VARCHAR(20) NOT NULL,
  RESOLVE_HOUR DOUBLE NOT NULL,
  UPGREAD INT(5) DEFAULT 0,
  NOTIFY_TIMES INT(5) DEFAULT 1,
  NOTIFY_WAY VARCHAR(10) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE (CLASSIFY_NAME)
);

CREATE TABLE CASE_NOTIFY_MOULD(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CLASSIFY_ID VARCHAR(10) NOT NULL,
  NOTIFY_TYPE VARCHAR(10) NOT NULL,
  NOTIFY_WAY VARCHAR(10) NOT NULL,
  NOTIFY_SET VARCHAR(200) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE CASE_FAULT(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TERMINAL_ID VARCHAR(20) DEFAULT NULL,
  DEV_MOD VARCHAR(5) DEFAULT NULL,
  CLASSIFY_ID VARCHAR(10) NOT NULL,
  FAULT_CODE VARCHAR(20) NOT NULL,
  VENDOR_HW_CODE VARCHAR(20) DEFAULT NULL,
  FAULT_TIME DATETIME DEFAULT NULL,
  CLOSED_TIME DATETIME DEFAULT NULL,
  DURATION DOUBLE DEFAULT NULL,
  FAULT_STATUS VARCHAR(20) NOT NULL,
  UPGRADE INT(5) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (CLASSIFY_ID) REFERENCES CASE_FAULT_CLASSIFY(ID)
);

CREATE TABLE CASE_NOTIFY(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
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
);

CREATE TABLE CASE_VENDORCODE(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  VENDOR_ID BIGINT DEFAULT NULL,
  VENDORCODE_CODE VARCHAR(20),
  VENDORCODE_DESCRIPTION VARCHAR(80) DEFAULT NULL,
  VENDORCODE_SOLUTION VARCHAR(80) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_COUNTER_FEIT_MONEY (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  AMT DOUBLE DEFAULT NULL,
  CREDIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  CURRENCY VARCHAR(5) DEFAULT NULL,
  DATE_TIME TIMESTAMP NOT NULL,
  DEBIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  HOST_RET VARCHAR(10) DEFAULT NULL,
  LOCAL_RET VARCHAR(10) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TRANS_CODE VARCHAR(20) DEFAULT NULL,
  TRANS_ID VARCHAR(20) NOT NULL,
  TIP_FEE DOUBLE DEFAULT NULL,
  TRANS_DATE INT NOT NULL DEFAULT 0,
  COUNTER_FEIT_MONEY INTEGER DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE ATMC_TRANSACTION_CROWN(
ID BIGINT NOT NULL AUTO_INCREMENT,
SERIAL INTEGER DEFAULT NULL,
CROWN_ID VARCHAR(50) DEFAULT NULL,
NOTE_ID BIGINT DEFAULT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (NOTE_ID) REFERENCES ATMC_COUNTER_FEIT_MONEY (ID)
);

CREATE TABLE ATMC_UNCOMMON_TRANS(
  ID BIGINT NOT NULL AUTO_INCREMENT,
  AMT DOUBLE DEFAULT NULL,
  CREDIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  CURRENCY VARCHAR(5) DEFAULT NULL,
  DATE_TIME TIMESTAMP NOT NULL,
  DEBIT_ACCOUNT VARCHAR(20) DEFAULT NULL,
  HOST_RET VARCHAR(10) DEFAULT NULL,
  LOCAL_RET VARCHAR(10) DEFAULT NULL,
  TERMINAL_ID VARCHAR(20) NOT NULL,
  TRANS_CODE VARCHAR(20) DEFAULT NULL,
  TRANS_ID VARCHAR(20) NOT NULL,
  TIP_FEE DOUBLE DEFAULT NULL,
  TRANS_DATE INT NOT NULL DEFAULT 0,
  CUS_NAME VARCHAR(20) DEFAULT NULL,
  CUS_PHONE VARCHAR(20) DEFAULT NULL,
  CARD_TYPE VARCHAR(10) DEFAULT NULL,
  RESULT_FROM_V VARCHAR(10) DEFAULT NULL,
  REV_FLAG VARCHAR(10) DEFAULT NULL,
  SUGGEST VARCHAR(600) DEFAULT NULL,
  PRIMARY KEY (ID)
);


create table VER_VERSIONTYPE_ATMTYPE(

  ID BIGINT NOT NULL AUTO_INCREMENT,
  ATM_TYPE_ID BIGINT DEFAULT NULL,
  VERSION_TYPE_ID BIGINT DEFAULT NULL,
  PRIMARY KEY (ID)
);
