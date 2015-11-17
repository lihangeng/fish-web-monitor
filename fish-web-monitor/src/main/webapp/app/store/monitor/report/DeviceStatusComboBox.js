
Ext.define('Eway.store.monitor.report.DeviceStatusComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value: '', display:EwayLocale.commen.all},
			{value:'2', display:EwayLocale.commen.comboxDevStatus.open}, 
	       {value:'3', display:EwayLocale.commen.comboxDevStatus.stop}]
});
