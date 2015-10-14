Ext.define('Eway.store.monitor.device.FilterSellItem',{

	extend : 'Ext.data.Store',
	model : 'Eway.model.monitor.device.FilterItem',
	
	data : [{
			name : Eway.locale.commen.all,
			value : '0'
		},{
			name : Eway.locale.machine.device.operationSelf,
			value : '1'
		},{
			name : Eway.locale.machine.device.cooperation,
			value : '2'
		},{
			name : Eway.locale.machine.device.epiboly,
			value : '3'
		}]
});