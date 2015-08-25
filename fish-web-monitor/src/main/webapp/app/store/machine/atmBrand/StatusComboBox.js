
Ext.define('Eway.store.machine.atmBrand.StatusComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:1, display:'设备供应'}, 
	       {value:3, display:'设备服役'}]
});
