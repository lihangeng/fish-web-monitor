Ext.define('Eway.store.advert.AdvertDownMethod', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [/*{value:'APPEND', display:'追加'}, */
	       {value:'COVER', display:EwayLocale.advert.advertDownMethodCover}]//'覆盖'}]

});
