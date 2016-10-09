Ext.define('Eway.model.machine.Device', {
	extend : 'Ext.data.Model',
	fields : ['id','terminalId', 'ip','orgId','orgName','devTypeId','devTypeName',
	          'devVendorName','devCatalogName','address','status','statusName',
	          'devServiceName','virtual','devServiceId','cashboxLimit',
	          'serial','setupType','setupTypeName','netType','netTypeName',
	          'awayFlag','awayFlagName','workType','workTypeName','installDate',
	          
	          'maxAlarm','minAlarm',
	          
	          
	          ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});