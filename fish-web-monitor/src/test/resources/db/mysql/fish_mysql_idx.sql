CREATE INDEX IDX_SM_ORG_1 ON SM_ORG(CODE);
CREATE INDEX IDX_SM_ORG_2 ON SM_ORG(ORG_FLAG);
CREATE INDEX IDX_SM_ORG_3 ON SM_ORG(PARENT_ID);

CREATE INDEX IDX_SM_PERSON_1 ON SM_PERSON(ORGANIZATION_ID);
CREATE INDEX IDX_SM_PERSON_2 ON SM_PERSON(PERSON_TYPE);

CREATE INDEX IDX_SM_PARAM_1 ON SM_PARAM(PARAM_KEY);

CREATE INDEX IDX_DEV_CATALOG_1 ON DEV_CATALOG(CATALOG_NO);

CREATE INDEX IDX_DEV_INFO_1 ON DEV_INFO(ORG_ID);
CREATE INDEX IDX_DEV_INFO_2 ON DEV_INFO(STATUS);
CREATE INDEX IDX_DEV_INFO_3 ON DEV_INFO(DEV_TYPE_ID);

CREATE INDEX IDX_DEV_XFS_STATUS_1 ON DEV_XFS_STATUS(DATE_TIME);

CREATE INDEX IDX_ATMC_APP_LOGS_1 ON ATMC_APP_LOGS(TERMINAL_ID);
CREATE INDEX IDX_ATMC_APP_LOGS_2 ON ATMC_APP_LOGS(DATE_TIME);

CREATE INDEX IDX_ATMC_CROWN_NUMBER_1 ON ATMC_CROWN_NUMBER(TERMINAL_ID);
CREATE INDEX IDX_ATMC_CROWN_NUMBER_2 ON ATMC_CROWN_NUMBER(CREATE_DATE);

CREATE INDEX IDX_ATMC_RETAIN_CARD_1 ON ATMC_RETAIN_CARD(TERMINAL_ID);
CREATE INDEX IDX_ATMC_RETAIN_CARD_2 ON ATMC_RETAIN_CARD(CARD_RETAIN_TIME);

CREATE INDEX IDX_CARD_STATUS ON ATMC_RETAIN_CARD(STATUS);

CREATE INDEX IDX_ATMC_STATUS_HIST_1 ON ATMC_STATUS_HIST(TERMINAL_ID);

CREATE INDEX IDX_ATMC_TRANSACTION_1 ON ATMC_TRANSACTION(TERMINAL_ID);
CREATE INDEX IDX_ATMC_TRANSACTION_2 ON ATMC_TRANSACTION(TRANS_ID);
CREATE INDEX IDX_ATMC_TRANSACTION_3 ON ATMC_TRANSACTION(DEBIT_ACCOUNT);
CREATE INDEX IDX_ATMC_TRANSACTION_4 ON ATMC_TRANSACTION(DATE_TIME);

CREATE INDEX IDX_DEV_OPEN_RATE_1 ON DEV_OPEN_RATE(STAT_DATE);

CREATE INDEX IDX_VER_TASK_1 ON VER_TASK(DEVICE_ID);
CREATE INDEX IDX_VER_TASK_2 ON VER_TASK(JOB_ID);

CREATE INDEX IDX_ATMC_CASH_INIT_1 ON ATMC_CASH_INIT(TERMINAL_ID);
CREATE INDEX IDX_ATMC_SETTLEMENT_1 ON ATMC_SETTLEMENT(TERMINAL_ID);

CREATE INDEX IDX_CASE_NOTIFY_1 ON CASE_NOTIFY(TERMINAL_ID);

CREATE INDEX IDX_CASE_FAULT_1 ON CASE_FAULT(TERMINAL_ID);


