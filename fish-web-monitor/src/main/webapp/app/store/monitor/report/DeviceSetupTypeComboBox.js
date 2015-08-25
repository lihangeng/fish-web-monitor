
Ext.define('Eway.store.monitor.report.DeviceSetupTypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value: '', display:'全部'},
			{value:'0', display:'穿墙'}, 
	       {value:'1', display:'大堂'}]
});
