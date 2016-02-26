
Ext.define('Eway.store.bsAdvert.BsAdvertGroupType', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:EwayLocale.bsAdvertGroup.commenType}, 
	       {value:'1', display:EwayLocale.bsAdvertGroup.defaultType}]
});
