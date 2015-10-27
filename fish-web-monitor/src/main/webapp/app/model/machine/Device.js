Ext.define('Eway.model.machine.Device', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType','installDate'],
	proxy : {
		type : 'rest',
		url : 'api/machine/device',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});