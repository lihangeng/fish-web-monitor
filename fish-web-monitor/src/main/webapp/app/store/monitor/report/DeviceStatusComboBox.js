
Ext.define('Eway.store.monitor.report.DeviceStatusComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value: '', display:Eway.locale.commen.all},
			{value:'1', display:Eway.locale.commen.comboxDevStatus.open}, 
	       {value:'2', display:Eway.locale.commen.comboxDevStatus.stop}]
});
