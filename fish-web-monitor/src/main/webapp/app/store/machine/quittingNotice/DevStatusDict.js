
Ext.define('Eway.store.machine.quittingNotice.DevStatusDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPENING', display:Eway.locale.commen.comboxDevStatus.open}, 
	       {value:'DISABLED', display:Eway.locale.commen.comboxDevStatus.stop}]
});
