Ext.define('Eway.store.advert.AdvertValidity', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'ALWAYS', display:EwayLocale.advert.validityAlways},//'永久播放'}, 
	       {value:'TEMP', display:EwayLocale.advert.validityTemp}]//'临时播放'}]
});
