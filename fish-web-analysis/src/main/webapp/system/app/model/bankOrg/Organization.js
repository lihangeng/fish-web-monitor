Ext.define('Eway.model.bankOrg.Organization', {
	extend : 'Ext.data.Model',

	fields : [ 'id','guid', 'code', 'name', 'zip','displayName']
	
	/*proxy : {
		type : 'rest',
		url : 'api/person/organization',

		reader : {
			type : 'json',
			rootProperty: 'data'
		},
		wirter : {
			type : 'json'
		}
	}*/
	
});
