
Ext.define('Eway.store.monitor.report.DeviceStatusComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value: '', display:'全部'},
			{value:'1', display:'开通'}, 
	       {value:'2', display:'停用'}]
});
