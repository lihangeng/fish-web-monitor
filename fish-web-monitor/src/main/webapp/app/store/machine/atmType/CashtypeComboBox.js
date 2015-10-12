
Ext.define('Eway.store.machine.atmType.CashtypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:Eway.locale.machine.atmType.iscash}, 
	       {value:'2', display:Eway.locale.machine.atmType.nocash}]
});
