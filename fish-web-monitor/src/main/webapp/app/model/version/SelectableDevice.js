Ext.define('Eway.model.version.SelectableDevice', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ 'id',
	           	'code', 'ip',
	           	'port',
	           	'address', 'installDate',
	           	'deviceVersion',
	           	'orgName','deviceType','selectable','taskable','targetVersion','taskStatus','reason'
	           	],
	proxy : {
		type : 'rest',
		url : 'api/version/download/selectable',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		wirter : {
			type : 'json'
		}
	}

});