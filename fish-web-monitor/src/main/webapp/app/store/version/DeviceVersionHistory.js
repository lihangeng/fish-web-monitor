/**
 * 设备版本的Store定义
 */
Ext.define('Eway.store.version.DeviceVersionHistory', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.DeviceVersionHistory',
	autoSync : false,
	pageSize: 500,
    autoLoad: false,
    groupField: 'versionType'
});