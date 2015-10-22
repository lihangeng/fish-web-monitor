/**
 * 广告的Store定义
 */
Ext.define('Eway.store.advert.AdvertResource', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 50,
	model: 'Eway.model.advert.AdvertResource',
	groupField: 'screen',
    autoLoad: false
});