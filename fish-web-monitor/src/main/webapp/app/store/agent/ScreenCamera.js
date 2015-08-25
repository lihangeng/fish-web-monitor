Ext.define('Eway.store.agent.ScreenCamera', {
	extend : 'Ext.data.Store',
	model : 'Eway.model.agent.ScreenCamera',
	groupField : 'groupField',
	sorters: ['groupField'],
	autoLoad : false,
	autoSync : false
});