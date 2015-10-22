/**
 * 版本类型的Store定义
 */
Ext.define('Eway.store.version.VersionAutoUpdate', {
	extend: 'Eway.store.base.Store',
	model: 'Eway.model.version.VersionAutoUpdate',
	autoSync : false,
	pageSize: 15,
    autoLoad: false
});