
Ext.define('Eway.store.InstantMessage', {
	extend: 'Ext.data.Store',
	autoSync : false,
	pageSize: 5,
	model: 'Eway.model.InstantMessage',

    autoLoad: false
});