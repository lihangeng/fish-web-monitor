Ext.define('Eway.model.report.plan.LinkDevice', {
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
	}, {
		name : 'installDate'
	}, 'address', 'status', 'devServiceName', {
		name : 'cashboxLimit',
		type : 'number'
	} ],
	proxy : {
		type : 'rest',
		url : 'api/report/plan/linkdeDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});