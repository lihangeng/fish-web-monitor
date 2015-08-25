
Ext.define('Eway.model.person.person.SerPerson', {
	extend: 'Ext.data.Model',
	fields: [
	       'guid', 'code','name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},'type',
	       'mobile','email','organizationName','organizationId','phone','state','jobNum','remark'
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
