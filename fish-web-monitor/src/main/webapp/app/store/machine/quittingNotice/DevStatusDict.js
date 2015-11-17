
Ext.define('Eway.store.machine.quittingNotice.DevStatusDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPENING', display:EwayLocale.machine.quittingNotice.on}, 
	       {value:'DISABLED', display:EwayLocale.machine.quittingNotice.off}]
});
