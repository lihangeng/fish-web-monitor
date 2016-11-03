/**
 * 版本的模型定义
 */
Ext.define('Eway.model.version.VersionInfo', {
	extend: 'Ext.data.Model',
	idProperty : 'id',
	fields: ['id', 'appRelease','lastTime','maxUpdateVersion'],
});