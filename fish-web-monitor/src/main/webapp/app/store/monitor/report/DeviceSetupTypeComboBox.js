
Ext.define('Eway.store.monitor.report.DeviceSetupTypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value: '', display:EwayLocale.commen.all},
			{value:'0', display:EwayLocale.machine.device.crossWall}, 
	       {value:'1', display:EwayLocale.machine.device.mainRoom}]
});
