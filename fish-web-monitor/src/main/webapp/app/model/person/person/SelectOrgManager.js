
Ext.define('Eway.model.person.person.SelectOrgManager', {
	extend: 'Ext.data.Model',
	fields: [
	       'guid', 'code','name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},'type',
	       'mobile','email','organizationName','organizationId','phone','state','jobNum','remark'
	],
	idProperty : 'guid',  
	proxy : {
		type : 'rest',
		url : 'api/person/person/selectOrgManager',

		reader : {
			type : 'json',
			root : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
