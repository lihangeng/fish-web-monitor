Ext.define('Eway.model.person.organization.LinkedDevice', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, 'terminalId', 'ip', {
		name : 'orgId'
	}, {
		name : 'orgName'
	}, {
		name : 'devTypeId'
	}, {
		name : 'devTypeName'
	}, {
		name : 'devVendorName'
	}, {
		name : 'devCatalogName'
	}, 'address', 'status', 'devServiceName', 'devServiceId', {
		name : 'cashboxLimit',
		type : 'number'
	}, {
		name : 'installDate'
	} ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});