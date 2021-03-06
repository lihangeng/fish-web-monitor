CREATE TABLE PARAM_PUBLISH(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	PUBLISH_DATE VARCHAR(20) NOT NULL,
	PUBLISHER_ID BIGINT(20) NOT NULL,
	PUBLISH_RET VARCHAR(15) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE PARAM_PUBLISH_RESULT(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	PARAM_PUBLISH_ID BIGINT(20) NOT NULL,
	DEVICE_ID BIGINT(20) NOT NULL,
	VERSION_NO BIGINT(15) NOT NULL,
	DOWNLOAD_START_TIME VARCHAR(20) DEFAULT NULL,
	DOWNLOAD_FINISH_TIME VARCHAR(20) DEFAULT NULL,
	IS_SUCCESS CHAR(1) DEFAULT NULL,
	REASON  VARCHAR(30) DEFAULT NULL,
	RET VARCHAR(15) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_APP_SYSTEM(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR (40) NOT NULL,
  CONFIG_PATH VARCHAR (80) NOT NULL,
  CONFIG_NAME VARCHAR (40) NOT NULL,
  CONFIG_FORM int (5) NOT NULL,
  REMARK VARCHAR (40) ,
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_ELEMENT(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  CREATE_TIME DATETIME DEFAULT NULL,
  LAST_MODIFY_TIME DATETIME DEFAULT NULL,
  PARAM_NAME VARCHAR (40) NOT NULL,
  PARAM_VALUE VARCHAR (128) NOT NULL,
  PARAM_TYPE VARCHAR (10) ,
  PARAM_CLASSIFY VARCHAR (10),
  PARAM_RIGHTS VARCHAR(10),
  PARAM_BELONGS VARCHAR(40),
  PARAM_TIMESTAMP BIGINT(20),
  REMARK VARCHAR (60),
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_TEMPLATE(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(30),
  REMARK VARCHAR (60),
  APPLY_FLAG CHAR (1),
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_TEMPLATE_DETAIL(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TEMPLATE_ID BIGINT(20),
  ELEMENT_ID BIGINT(20),
  PARAM_VALUE VARCHAR(60),
  PRIMARY KEY (ID),
  FOREIGN KEY (TEMPLATE_ID) REFERENCES PARAM_TEMPLATE(ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_TEMPLATE_EMLEMT_RELATION(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TEMPLATE_ID BIGINT(20),
  ELEMENT_ID BIGINT(20),
  REMARK VARCHAR (60),
  PRIMARY KEY (ID),
  FOREIGN KEY (TEMPLATE_ID) REFERENCES PARAM_TEMPLATE(ID),
  FOREIGN KEY (ELEMENT_ID) REFERENCES PARAM_ELEMENT(ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_TEMPLATE_DEVICE_RELATION(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  TEMPLATE_ID BIGINT(20),
  DEVICE_ID BIGINT(20),
  PRIMARY KEY (ID),
  FOREIGN KEY (TEMPLATE_ID) REFERENCES PARAM_TEMPLATE(ID),
  FOREIGN KEY (DEVICE_ID) REFERENCES DEV_INFO(ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE PARAM_CLASSIFY(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(40),
  REMARK VARCHAR(40),
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE PARAM_DEVICE_DETAIL(
  ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  DEVICE_ID BIGINT(20),
  ELEMENT_ID BIGINT(20),
  PARAM_VALUE VARCHAR (40) NOT NULL,
  VERSION_TIMESTAMP BIGINT(20),
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
