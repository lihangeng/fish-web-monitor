
Ext.define('Eway.store.bsAdvert.BsAdvertStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'1', display:'激活'}, 
	       {value:'0', display:'未激活'}]
});

