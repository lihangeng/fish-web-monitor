
CREATE TABLE DEV_CATALOG_SUMMARY_MONTH
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	CATALOG VARCHAR(40) NOT NULL,
	NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE DEV_CATALOG_SUMMARY_WEEK
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	CATALOG VARCHAR(40) NOT NULL,
	NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE DEV_TYPE_SUMMARY_MONTH
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_TYPE VARCHAR(40) NOT NULL,
	NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE DEV_TYPE_SUMMARY_WEEK
(
	ID BIGINT(20) NOT NULL AUTO_INCREMENT,
	DEV_TYPE VARCHAR(40) NOT NULL,
	NUM INT(11) DEFAULT NULL,
	SUMMARY_DATE VARCHAR(12) NOT NULL,
	ADD_NUM INT(11) DEFAULT NULL,
	SCRAPPED_NUM INT(11) DEFAULT NULL,
	ALL_NEW INT(11) DEFAULT NULL,
	ALL_SCRAPPED INT(11) DEFAULT NULL,
	PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
