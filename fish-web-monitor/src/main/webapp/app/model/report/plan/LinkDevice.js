Ext.define('Eway.model.report.plan.LinkDevice', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType'],
	proxy : {
		type : 'rest',
		url : 'api/report/plan/linkdeDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});