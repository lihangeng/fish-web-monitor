Ext.define('Eway.view.advert.AdvertConfig', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.advert_advertConfig',

	requires : [ 'Eway.view.advert.AdvertResourceGrid' ],

	title : '广告配置',
	layout : 'border',

	initComponent : function() {
		Ext.apply(this, {
			items : [ /*{
					region : 'north',
					xtype : 'version_filterForm'
				},*/ {
					region : 'center',
					xtype : 'advert_advertResourceGrid'
				}]
		});

		this.callParent(arguments);
	},
	refresh : function(advertId){
		var grid = this.down("advert_advertResourceGrid");
		var store = grid.getStore();
		store.load({params : {advertId : advertId}});
	}
});