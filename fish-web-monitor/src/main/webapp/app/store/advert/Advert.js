/**
 * 广告的Store定义
 */
Ext.define('Eway.store.advert.Advert', {
	extend: 'Eway.store.base.Store',
	pageSize: 15,
	model: 'Eway.model.advert.Advert',
	autoSync : false, //store和后台不自动同步
    autoLoad: true
});