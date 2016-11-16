ALTER TABLE SM_PARAM ADD COLUMN PARAM_DIS_VALUE  VARCHAR(128) DEFAULT NULL;

truncate table SM_PARAM;

INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('1', '0', '监控服务文件存放根路径', 'fish_home', 'd:\\fish_home','d:\\fish_home','系统配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('2', '1', 'Remote服务监听端口', 'atm_port', '8080','8080','客户端配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('3', '1', 'ATM APP日志存放规则', 'atm_log_file_cfg', 'bizLog_{terminalId}_{YYYY-MM-DD}.zip','bizLog_{terminalId}_{YYYY-MM-DD}.zip','备份规则配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('4', '1', 'ATM APP日志存放路径', 'atm_log_doc', 'c:\\yihua\\gump\\log','c:\\yihua\\gump\\log','备份规则配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('5', '1', '获取设备离线时间(单位:分钟)', 'device_offline', '30','30','客户端配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('8', '0', '系统注册序列号', 'register_serial', 'V10520-FEDDJD-A76104-PJFDGK-HEDFEF1150', 'V10520-FEDDJD-A76104-PJFDGK-HEDFEF1150','系统配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('9', '1', 'ATM开机与监控对时开关(ON/OFF)', 'check_time', 'ON','ON','客户端配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('10', '1', '故障处理并发数', 'status_handle_count', '30','30','系统配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('11', '1', '下载文件并发数', 'device_update_count', '30','30','系统配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('12', '0', 'BS广告服务器路径', 'bsAdvertServerPath', 'D:\\tomcat\\webapps\\ROOT\\advert','D:\\tomcat\\webapps\\ROOT\\advert','BS广告配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('13', '0', '参数发布并发数', 'param_update_count', '30','30','系统配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('14', '1', '邮件服务器地址', 'mail_system_address', 'smtp.yihuacomputer.com','smtp.yihuacomputer.com', '邮箱配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('15', '1', '发送邮件的用户名', 'mail_userName', 'xxxxxxxxx@yihuacomputer.com','xxxxxxxxx@yihuacomputer.com', '邮箱配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('16', '1', '发送邮件的密码', 'mail_password', 'Xxxxxxx','******', '邮箱配置');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('17', '1', '加钞间隔预警', 'cashinit_days', '7','7', '钞箱预警');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('18', '0', '加钞机构级别', 'cashinit_orglevel', '2','一级支行', '钞箱预警');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('19', '1', '默认日均存款金额(单位:元)', 'trading_volume_in', '50000','50000', '钞箱预警');
INSERT INTO SM_PARAM(ID,PARAM_CLASSIFY,DESCIPTION,PARAM_KEY,PARAM_VALUE,PARAM_DIS_VALUE,PARAM_TYPE) VALUES ('20', '1', '默认日均取款金额(单位:元)', 'trading_volume_out', '50000','50000', '钞箱预警');