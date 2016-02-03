Ext.define('Eway.model.bsAdvert.LinkDevice', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType'],
	proxy : {
		type : 'rest',
		url : 'api/bsadvert/advertgroup/linkedDevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});