Ext.define('Eway.store.advert.AdvertType', {
	extend: 'Ext.data.Store',
	
	model: 'Eway.model.Dict',
	
	data: [{value:'',display:Eway.locale.commen.all},
		   {value:'TRANS', display:'交易页面广告'}, 
	       {value:'WAIT_INSERT_CARD', display:'等待插卡广告'}, 
	       {value:'TEXT', display:'文字滚动广告'},
	       {value:'ANNOUNCEMENT',display:'公告'}]
});
