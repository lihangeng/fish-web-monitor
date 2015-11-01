Ext.define('Eway.model.operatingPlan.LinkedDevice', {
	extend : 'Ext.data.Model',
	
	fields : [ {
		name : 'id'
	}, 'terminalId','planId','ip','flag', {
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
	}, {
		name : 'installDate'
	}, 'address', 'status', 'devServiceName', {
		name : 'cashboxLimit',
		type : 'number'
	} ],
	proxy : {
		type : 'rest',
		url : 'api/plan/linkedDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});