
Ext.define('Eway.store.machine.atmType.CashtypeComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:EwayLocale.machine.atmType.iscash}, 
	       {value:'2', display:EwayLocale.machine.atmType.nocash}]
});
