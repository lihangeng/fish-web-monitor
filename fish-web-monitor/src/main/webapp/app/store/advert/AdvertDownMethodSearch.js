Ext.define('Eway.store.advert.AdvertDownMethodSearch', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:Eway.locale.refs.selectAll}, 
		   /*{value:'APPEND', display:'追加'},*/ 
	       {value:'COVER', display:Eway.locale.advert.advertDownMethodCover}]//'覆盖'}]
});
