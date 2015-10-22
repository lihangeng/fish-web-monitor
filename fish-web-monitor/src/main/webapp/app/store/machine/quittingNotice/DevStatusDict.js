
Ext.define('Eway.store.machine.quittingNotice.DevStatusDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPENING', display:Eway.locale.machine.quittingNotice.on}, 
	       {value:'DISABLED', display:Eway.locale.machine.quittingNotice.off}]
});
