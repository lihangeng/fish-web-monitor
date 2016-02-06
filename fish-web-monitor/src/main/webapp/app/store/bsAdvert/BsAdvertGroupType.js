
Ext.define('Eway.store.bsAdvert.BsAdvertGroupType', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'0', display:'通用'}, 
	       {value:'1', display:'默认'}]
});
