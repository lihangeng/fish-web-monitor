
Ext.define('Eway.store.machine.atmBrand.StatusComboBox', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:1, display:EwayLocale.machine.atmBrand.comboxStatus.provider}, 
	       {value:3, display:EwayLocale.machine.atmBrand.comboxStatus.maintance}]
});
