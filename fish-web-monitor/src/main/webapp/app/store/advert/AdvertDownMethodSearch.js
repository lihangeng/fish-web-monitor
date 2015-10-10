Ext.define('Eway.store.advert.AdvertDownMethodSearch', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.commen.all}, 
		   /*{value:'APPEND', display:'追加'},*/ 
	       {value:'COVER', display:'覆盖'}]
});
