Ext.define('Eway.model.person.person.BankPerson', {
	extend : 'Ext.data.Model',
	fields : [ 'guid', 'code', 'name', 'gender', {
		name : 'birthday',
		type : 'date',
		dateFormat : 'Y-m-d'
	}, 'type', 'mobile', 'email', 'organizationName', 'organizationId',
			'phone', 'state', 'jobNum', 'remark', 'personJobCode',
			'personJobName', 'reserve1', 'reserve2', 'reserve3' ],
	idProperty : 'guid',
	proxy : {
		type : 'rest',
		url : '../api/person/person',

		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}
});
