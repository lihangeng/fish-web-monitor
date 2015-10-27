CREATE DATABASE IF NOT EXISTS ATMV default charset utf8 COLLATE utf8_bin; 
use atmv;
source fish_mysql_drop.sql;
source fish_mysql_db.sql;
source fish_mysql_idx.sql;
source fish_mysql_init.sql;