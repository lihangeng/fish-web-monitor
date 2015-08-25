/**
 * 管机员
 */
Ext.define('Eway.store.machine.PersonTM', {
	extend: 'Ext.data.Store',
	autoSync : false,
	model: 'Eway.model.machine.Person',
    autoLoad: false
});