Ext.define('Eway.store.advert.AdvertType', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [
			{value:'WAIT_INSERT_CARD', display:EwayLocale.advert.advertTypeIdle},//'等待插卡广告'}, 
			{value:'TEXT', display:EwayLocale.advert.advertTypeText}//'文字滚动广告'},

		  ]
});
