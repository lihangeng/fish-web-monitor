Ext.define('Eway.model.person.person.PersonJob', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'code', 'name', 'remark' ],
	idProperty : 'id',
	proxy : {
		type : 'rest',
		url : 'api/person/person/queryPersonJob',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
