Ext.define('Eway.model.machine.TempDevice', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType','installDate','effectiveDate'],
	proxy : {
		type : 'rest',
		url : 'api/machine/tempdevice',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});