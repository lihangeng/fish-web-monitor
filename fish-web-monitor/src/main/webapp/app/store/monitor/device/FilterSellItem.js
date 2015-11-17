Ext.define('Eway.store.monitor.device.FilterSellItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : EwayLocale.commen.all,
			value : '0'
		},{
			name : EwayLocale.machine.device.operationSelf,
			value : '1'
		},{
			name : EwayLocale.machine.device.cooperation,
			value : '2'
		},{
			name : EwayLocale.machine.device.epiboly,
			value : '3'
		}]
});