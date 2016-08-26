CREATE DATABASE IF NOT EXISTS ATMV default charset utf8 COLLATE utf8_bin; 
use ATMV;
source fish_mysql_drop.sql;
source fish_mysql_db.sql;
source fish_mysql_idx.sql;
source fish_mysql_init.sql;
source quartz_mysql_innodb.sql;
source batch-mysql.sql;