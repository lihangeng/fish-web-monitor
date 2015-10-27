Ext.define('Eway.model.person.person.SelectSerOrgManager', {
	extend: 'Ext.data.Model',
	fields: [
	       'guid', 'code','name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'},'type',
	       'mobile','email','organizationName','organizationId','phone','state','jobNum','remark'
	],
	idProperty : 'guid',
	proxy : {
		type : 'rest',
		url : 'api/person/person/selectSerOrgManager',

		reader : {
			type : 'json',
			root : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
