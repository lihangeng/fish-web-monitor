/**
 * 维护员
 */
Ext.define('Eway.store.machine.PersonM', {
	extend: 'Ext.data.Store',
	autoSync : false,
	model: 'Eway.model.machine.Person',
    autoLoad: false
});