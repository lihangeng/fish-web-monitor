Ext.define('Eway.model.person.organization.BankOrganization', {
	extend : 'Ext.data.Model',
	idProperty : 'guid',

	fields : [ 'guid', 'code', 'name', 'manager', 'parent', 'parentId',
			'orgLevel', 'userGuid', 'address', 'zip', 'organizationState',
			'organizationType', 'description',
			'serviceObjectId', 'serviceObjectName', 'manager', 'userGuid' ],
	proxy : {
		type : 'rest',
		url : 'api/person/organization',

		reader : {
			type : 'json',
			rootProperty: 'data'
		},
		wirter : {
			type : 'json'
		}
	}

});
