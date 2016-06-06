Ext.define('Eway.model.machine.atmRuntimeInfo.RuntimeInfo', {
	extend : 'Ext.data.Model',
	fields : [ 'id', 'terminalId', 'ip', 'orgId', 'orgName', 'devTypeId',
			'devTypeName', 'devVendorName', 'devCatalogName', 'address',
			'status', 'devServiceName', 'virtual', 'devServiceId',
			'cashboxLimit', 'serial', 'setupType', 'netType', 'awayFlag',
			'workType' ],
	proxy : {
		type : 'rest',
		url : 'api/machine/device/findByOrg',
		reader : {
			type : 'json',
			rootProperty : 'data'
		},
		extraParams : {
			organizationID : Eway.user.getOrgId()
		}
	}
});