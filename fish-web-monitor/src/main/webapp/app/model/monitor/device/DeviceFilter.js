Ext.define('Eway.model.monitor.device.DeviceFilter', {
	extend : 'Ext.data.Model',
	fields : [ 'userId', 'orgId', 'orgName', 'brandItem', 'brandItemName', 'classifyItem', 'classifyItemName', 'ingItem', 'sellItem', 'atmGroup', 'atmGroupName', 'runStatusFilterForm',
	'netStatusFilterForm', 'modStatusFilterForm', 'boxStatusFilterForm', 'id', 'filterName' ],
	proxy : {
		type : 'rest',
		url : 'api/monitor/device/monitorFilter',
		reader : {
			type : 'json',
			rootProperty : 'data'
		}
	}
});