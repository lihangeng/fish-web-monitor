
Ext.define('Eway.model.person.user.UserLog', {
	extend: 'Ext.data.Model',
	fields: ['id', 'operContent','operCode','operName','operTime','operResult','clientIP','serverIp','times'],
	proxy: {
		type : 'rest',
		url : 'api/person/userLog',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
    }
});