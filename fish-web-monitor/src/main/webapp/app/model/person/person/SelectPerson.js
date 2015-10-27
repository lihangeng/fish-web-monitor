
Ext.define('Eway.model.person.person.SelectPerson', {
	extend: 'Ext.data.Model',
	fields: [
	       'guid', 'code','name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},'type',
	       'mobile','email','organizationName','organizationId','deviceId','phone','state','jobNum','remark'
	],
	idProperty : 'guid',  
	proxy : {
		type : 'rest',
		url : 'api/person/person/selectPerson',

		reader : {
			type : 'json',
			root : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
