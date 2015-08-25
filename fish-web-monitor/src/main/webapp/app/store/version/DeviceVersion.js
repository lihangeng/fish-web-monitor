/**
 * 设备版本的Store定义
 */
Ext.define('Eway.store.version.DeviceVersion', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.DeviceVersion',
	autoSync : false,
	pageSize: 30,
    autoLoad: true
});