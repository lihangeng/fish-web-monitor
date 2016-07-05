alter TABLE ATMC_SETTLE_BOX modify BOX_CURRENCY VARCHAR(10) DEFAULT NULL;
alter TABLE ATMC_CASH_INIT_BOX modify BOX_CURRENCY VARCHAR(10) DEFAULT NULL;

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-bankOrg' WHERE PERMISSION_ID = 'A01';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-bankPer' WHERE PERMISSION_ID = 'A02';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-serviceOrg' WHERE PERMISSION_ID = 'A03';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-servicePer' WHERE PERMISSION_ID = 'A04';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-user' WHERE PERMISSION_ID = 'A05';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-role' WHERE PERMISSION_ID = 'A08';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-system-configuration' WHERE PERMISSION_ID = 'A09';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-device' WHERE PERMISSION_ID = 'B01';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-atmBrand' WHERE PERMISSION_ID = 'B03';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-atmType' WHERE PERMISSION_ID = 'B04';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-atmGroup' WHERE PERMISSION_ID = 'B05';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-quitting' WHERE PERMISSION_ID = 'B06';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-retainCard' WHERE PERMISSION_ID = 'B08';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-atmLog' WHERE PERMISSION_ID = 'B09';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-device-servicePlan' WHERE PERMISSION_ID = 'B10';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-device' WHERE PERMISSION_ID = 'C01';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-summaryInfo' WHERE PERMISSION_ID = 'C02';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-historyTrans' WHERE PERMISSION_ID = 'C03';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-monitorTrans' WHERE PERMISSION_ID = 'C04';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-cashInit' WHERE PERMISSION_ID = 'C05';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-settlement' WHERE PERMISSION_ID = 'C06';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-remoteCommand' WHERE PERMISSION_ID = 'C07';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-newMonitorTrans' WHERE PERMISSION_ID = 'C08';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-monitor-transCodeColor' WHERE PERMISSION_ID = 'C09';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-version' WHERE PERMISSION_ID = 'D01';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-advert' WHERE PERMISSION_ID = 'D02';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-versionMonitor' WHERE PERMISSION_ID = 'D03';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-deviceVersion' WHERE PERMISSION_ID = 'D04';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-versionType' WHERE PERMISSION_ID = 'D05';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-versionDistribute' WHERE PERMISSION_ID = 'D06';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-bsAdvertGroup' WHERE PERMISSION_ID = 'D07';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-application-bsAdvert' WHERE PERMISSION_ID = 'D08';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults-notifyMould' WHERE PERMISSION_ID = 'E01';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults-casenotify' WHERE PERMISSION_ID = 'E02';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults-faultConfig' WHERE PERMISSION_ID = 'E03';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults-casefault' WHERE PERMISSION_ID = 'E04';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-faults-vendorCode' WHERE PERMISSION_ID = 'E05';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-vendor' WHERE PERMISSION_ID = 'F00';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-retainCard' WHERE PERMISSION_ID = 'F10';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-faultRate' WHERE PERMISSION_ID = 'F12';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-transaction' WHERE PERMISSION_ID = 'F20';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-transactionResult' WHERE PERMISSION_ID = 'F30';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-dayTrans' WHERE PERMISSION_ID = 'F60';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-dayHourTrans' WHERE PERMISSION_ID = 'F70';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-caseTrend' WHERE PERMISSION_ID = 'F80';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-caseStatistics' WHERE PERMISSION_ID = 'F90';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-deviceOpenRate' WHERE PERMISSION_ID = 'F95';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-typeOpenRate' WHERE PERMISSION_ID = 'F96';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-reports-orgOpenRate' WHERE PERMISSION_ID = 'F97';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-appSystem' WHERE PERMISSION_ID = 'G01';

UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-paramElement' WHERE PERMISSION_ID = 'G02';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-paramClassify' WHERE PERMISSION_ID = 'G03';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-devParam' WHERE PERMISSION_ID = 'G04';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-paramTemplate' WHERE PERMISSION_ID = 'G05';
UPDATE SM_PERMISSION SET ICON_CLS = 'menu-param-paramMonitor' WHERE PERMISSION_ID = 'G06';