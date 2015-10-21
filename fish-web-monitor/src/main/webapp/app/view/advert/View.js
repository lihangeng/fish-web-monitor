Ext.define('Eway.view.advert.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.advert_view',

	requires : [ 'Eway.view.advert.Grid','Eway.view.advert.AdvertResourceGrid', 'Eway.view.advert.FilterForm'/*,'Eway.view.advert.AdvertVersion'*/],

	title : Eway.locale.advert.title,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
					region : 'west',
					width : '40%',
					xtype:'container',
					layout:'border',
					items: [{
							region : 'north',
							xtype : 'advert_filterForm'
						}, {
							region : 'center',
							xtype : 'advert_grid'
						}],
					split: true
				},{
					region: 'center',
					/*title: '广告详细配置',*/
					xtype:'advert_advertResourceGrid',
					action:'advertConfig'
				} ]
		});

		this.callParent(arguments);
	}
});