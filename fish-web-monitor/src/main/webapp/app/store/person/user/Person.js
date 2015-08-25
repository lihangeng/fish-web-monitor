
Ext.define('Eway.store.person.user.Person', {
	extend: 'Eway.store.base.Store',
	autoSync : false,
	model: 'Eway.model.person.person.Person',
    autoLoad: true
});