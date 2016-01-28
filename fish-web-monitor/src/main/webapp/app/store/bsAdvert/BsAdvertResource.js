/**
 * 广告的Store定义
 */
Ext.define('Eway.store.bsAdvert.BsAdvertResource', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 50,
	model: 'Eway.model.bsAdvert.BsAdvertResource',
	groupField: 'screen',
    autoLoad: false
});