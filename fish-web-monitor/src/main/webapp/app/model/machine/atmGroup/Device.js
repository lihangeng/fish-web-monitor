Ext.define('Eway.model.machine.atmGroup.Device', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType'],
	proxy : {
		type : 'rest',
		url : 'api/machine/atmGroup/deviceByGroup',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});