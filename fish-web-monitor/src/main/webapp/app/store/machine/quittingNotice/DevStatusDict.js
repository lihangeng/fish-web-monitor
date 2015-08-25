
Ext.define('Eway.store.machine.quittingNotice.DevStatusDict', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'OPENING', display:'开通'}, 
	       {value:'DISABLED', display:'停用'}]
});
