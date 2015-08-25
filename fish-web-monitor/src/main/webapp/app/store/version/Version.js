/**
 * 版本类型的Store定义
 */
Ext.define('Eway.store.version.Version', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.Version',
	autoSync : false,
	pageSize: 15,
    autoLoad: true
});