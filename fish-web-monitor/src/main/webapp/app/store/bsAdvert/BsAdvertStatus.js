
Ext.define('Eway.store.bsAdvert.BsAdvertStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:EwayLocale.bsAdvertGroup.Actived}, 
	       {value:'0', display:EwayLocale.bsAdvertGroup.unActived}]
});

