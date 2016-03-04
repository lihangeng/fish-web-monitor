Ext.define('Eway.store.version.monitor.Task', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 50,
	model: 'Eway.model.version.monitor.Task',

    autoLoad: false
});