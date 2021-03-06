UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system' WHERE PERMISSION_ID = 'A';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device' WHERE PERMISSION_ID = 'B';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor' WHERE PERMISSION_ID = 'C';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application' WHERE PERMISSION_ID = 'D';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults' WHERE PERMISSION_ID = 'E';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports' WHERE PERMISSION_ID = 'F';


alter table ATMC_TRANSACTION_DAYS add column VENDOR_ID BIGINT DEFAULT NULL;
alter table ATMC_TRANSACTION_DAYS add column DEV_TYPE_ID BIGINT DEFAULT NULL;

alter table ATMC_TRANSACTION_MONTHS add column VENDOR_ID BIGINT DEFAULT NULL;
alter table ATMC_TRANSACTION_MONTHS add column DEV_TYPE_ID BIGINT DEFAULT NULL;

alter table CASE_FAULT_MONTH add column VENDOR_ID BIGINT DEFAULT NULL;
alter table CASE_FAULT_MONTH add column DEV_TYPE_ID BIGINT DEFAULT NULL;
