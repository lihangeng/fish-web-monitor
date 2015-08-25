/**
 * 版本详情的模型定义
 */
Ext.define('Eway.store.version.VersionCharts', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 10,
	model: 'Eway.model.version.VersionCharts',
    autoLoad: false
});
