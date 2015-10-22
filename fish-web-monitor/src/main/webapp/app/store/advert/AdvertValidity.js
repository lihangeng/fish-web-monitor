Ext.define('Eway.store.advert.AdvertValidity', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'ALWAYS', display:Eway.locale.advert.validityAlways},//'永久播放'}, 
	       {value:'TEMP', display:Eway.locale.advert.validityTemp}]//'临时播放'}]
});
