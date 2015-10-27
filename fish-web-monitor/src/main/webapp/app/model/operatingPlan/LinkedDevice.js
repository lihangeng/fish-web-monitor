Ext.define('Eway.model.operatingPlan.LinkedDevice', {
	extend : 'Ext.data.Model',
	
	fields : [ {
		name : 'id'
	}, 'terminalId','planId','ip','flag', {
		name : 'orgId'
	}, {
		name : 'orgName'
	}, {
		name : 'devTypeId',
		type : 'long'
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
		url : 'api/srcb/plan/linkedDevice',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});