Ext.define('Eway.model.report.baseReport.DeviceVendorCountReport', {
	extend : 'Ext.data.Model',
	fields : [ 'orgName','vendorName','devTypeName','deviceCount'],
	proxy : {
		type : 'rest',
		url : 'api/report/deviceTypeCount',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});