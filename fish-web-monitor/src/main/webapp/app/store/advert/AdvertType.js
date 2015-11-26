Ext.define('Eway.store.advert.AdvertType', {
	extend: 'Ext.data.Store',

	model: 'Eway.model.Dict',

	data: [
			//{value:'TRANS', display:EwayLocale.advert.advertTypeTrans},//'交易页面广告'}, 
			{value:'WAIT_INSERT_CARD', display:EwayLocale.advert.advertTypeIdle},//'等待插卡广告'}, 
			{value:'TEXT', display:EwayLocale.advert.advertTypeText}//'文字滚动广告'},
			//,{value:'ANNOUNCEMENT',display:EwayLocale.advert.advertTypeAnnou}//'公告'}]

		  ]
});
