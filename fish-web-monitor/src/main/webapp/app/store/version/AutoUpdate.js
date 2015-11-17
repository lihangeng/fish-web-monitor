Ext.define('Eway.store.version.AutoUpdate', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [
	       {value:'true', display:EwayLocale.tip.right.yes},
	       {value:'false', display:EwayLocale.tip.right.no}]
});
