
Ext.define('Eway.model.person.person.OrgManager', {
	extend: 'Ext.data.Model',
	fields: [ 'guid', 'code', 'name', 'gender',
	       {name: 'birthday', type: 'date', dateFormat: 'Y-m-d'}, 'type',
	       'mobile','email','organizationName','organizationId','phone','state','jobNum','remark'
	],
	idProperty : 'guid',  
	proxy : {
		type : 'rest',
		url : 'api/person/person/showOrgManger',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
