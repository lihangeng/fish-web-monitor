ALTER TABLE SM_PERMISSION ADD COLUMN IS_TREE_NODE  CHAR(1) DEFAULT '1';

DELETE FROM SM_PERMISSION WHERE PERMISSION_ID='A12';
INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON,ICON_CLS,IS_TREE_NODE) VALUES ('Z', null, 'deviceView','单击模式','0','0','','0');
INSERT INTO SM_ROLE_PERMISSION(PERMISSION_ID,ROLE_ID) VALUES ('Z','1');