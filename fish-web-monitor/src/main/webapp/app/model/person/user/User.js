
Ext.define('Eway.model.person.user.User', {
	extend: 'Ext.data.Model',
	fields: ['id', 'code','name','userGuid','mobile','email','organizationName','phone','userState','gender','roles'],
	proxy: {
		type : 'rest',
		url : 'api/person/user',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
    }
});