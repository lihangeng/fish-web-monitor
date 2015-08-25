Ext.define('Eway.store.advert.AdvertValidity', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'ALWAYS', display:'永久播放'}, 
	       {value:'TEMP', display:'临时播放'}]
});
