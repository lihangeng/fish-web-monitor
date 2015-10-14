Ext.define('Eway.store.version.AutoUpdate', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'true', display:Eway.locale.tip.right.yes},
	       {value:'false', display:Eway.locale.tip.right.no}]
});
