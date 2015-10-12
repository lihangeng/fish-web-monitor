/**
 * 设备版本的Store定义
 */
Ext.define('Eway.store.version.DistributeStatusDetail', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.DistributeStatusDetail',
	autoSync : false,
	pageSize: 50,
    autoLoad: false
});