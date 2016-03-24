Ext.define('Eway.model.parameter.template.LinkDevice', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType'],
	proxy : {
		type : 'rest',
		url : 'api/parameter/template/linkedDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});