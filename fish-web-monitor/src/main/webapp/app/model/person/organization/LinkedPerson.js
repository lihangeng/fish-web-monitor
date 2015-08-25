
Ext.define('Eway.model.person.organization.LinkedPerson', {
	extend: 'Ext.data.Model',
	fields: [
	       'guid', 'code','name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},'type',
	       'mobile','email','organizationName','organizationId','phone','state'
	],
	idProperty : 'guid',  
	proxy : {
		type : 'rest',
		url : 'api/person/person',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
