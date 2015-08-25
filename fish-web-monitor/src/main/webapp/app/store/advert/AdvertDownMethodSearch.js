Ext.define('Eway.store.advert.AdvertDownMethodSearch', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:'全部'}, 
		   /*{value:'APPEND', display:'追加'},*/ 
	       {value:'COVER', display:'覆盖'}]
});
