
Ext.define('Eway.store.machine.atmType.CashtypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:'现金'}, 
	       {value:'2', display:'非现金'}]
});
