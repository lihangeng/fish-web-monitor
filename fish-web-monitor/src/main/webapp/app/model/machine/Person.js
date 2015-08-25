Ext.define('Eway.model.machine.Person', {
	extend : 'Ext.data.Model',
	fields : [ 'guid', 'code', 'name', 'gender', {
		name : 'birthday',
		type : 'date',
		dateFormat : 'Y-m-d'
	}, 'type', 'mobile', 'email', 'organizationName', 'organizationCode',
			'phone' ],
	idProperty : 'guid',
	proxy : {
		type : 'rest',
		url : 'api/machine/device/queryPerson',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
