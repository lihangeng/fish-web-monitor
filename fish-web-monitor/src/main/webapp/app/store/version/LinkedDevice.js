/**
 * 已经关联到作业的设备Store
*/
Ext.define('Eway.store.version.LinkedDevice', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 5,
	model: 'Eway.model.version.LinkedDevice',
    autoLoad: true
});