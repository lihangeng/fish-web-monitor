Ext.define('Eway.model.operatingPlan.LinkDevice', {
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