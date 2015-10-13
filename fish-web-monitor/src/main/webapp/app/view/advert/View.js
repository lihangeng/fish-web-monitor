Ext.define('Eway.view.advert.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.advert_view',

	requires : [ 'Eway.view.advert.Grid','Eway.view.advert.AdvertConfig', 'Eway.view.advert.FilterForm'/*,'Eway.view.advert.AdvertVersion'*/],

	title : Eway.locale.advert.title,//'广告管理',
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'north',
					xtype : 'advert_filterForm'
				}, {
					region : 'center',
					xtype : 'advert_grid',
					split: true
				},{
					region: 'south',
					xtype: 'tabpanel',
					height: '50%',
					plain:true,
					split: true,
					items:	[{
						title: Eway.locale.advert.configTitle,//'广告详细配置',
						xtype:'advert_advertConfig',
						action:'advertConfig'
					}/*,{
						title: '广告版本信息',
						xtype:'advert_advertVersion',
						action:'advertVersion'
					}*/]
				} ]
		});

		this.callParent(arguments);
	}
});