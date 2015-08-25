/**
 * 还没有关联作业的设备Store
*/
Ext.define('Eway.store.version.SelectableDevice', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 30,
	model: 'Eway.model.version.SelectableDevice',
	
    autoLoad: true

});