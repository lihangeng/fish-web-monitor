/**
 * 广告的Store定义
 */
Ext.define('Eway.store.advert.AdvertResource', {
	extend: 'Eway.store.base.Store',
	autoSync : false, //store和后台不自动同步
	pageSize: 200,
	model: 'Eway.model.advert.AdvertResource',
	
    autoLoad: false
});