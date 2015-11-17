Ext.define('Eway.store.advert.AdvertDownMethodSearch', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'', display:EwayLocale.refs.selectAll}, 
		   /*{value:'APPEND', display:'追加'},*/ 
	       {value:'COVER', display:EwayLocale.advert.advertDownMethodCover}]//'覆盖'}]
});
