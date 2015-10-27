Ext.define('Eway.model.person.organization.LinkedDeviceSer', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','netType','awayFlag','workType'],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/findByService',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});