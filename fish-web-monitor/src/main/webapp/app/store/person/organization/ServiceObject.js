
Ext.define('Eway.store.person.organization.ServiceObject', {
	extend: 'Ext.data.Store',

	fields : [ 'id', 'name' ],
	proxy : {
		type : 'ajax',
		url : 'api/person/organization/queryServiceObject',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	},
	autoLoad : false
});

