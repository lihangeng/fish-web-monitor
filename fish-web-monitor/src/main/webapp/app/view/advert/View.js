Ext.define('Eway.view.advert.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.advert_view',

	requires : [ 'Eway.view.advert.Grid','Eway.view.advert.AdvertResourceGrid', 'Eway.view.advert.FilterForm'/*,'Eway.view.advert.AdvertVersion'*/],

	title : Eway.locale.advert.title,
	layout : {
		type:'table',
		columns:1
	},
	scrollable : 'y',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
							xtype : 'advert_filterForm'
						}, {
							xtype : 'advert_grid'
						},{
							xtype:'advert_advertResourceGrid',
							action:'advertConfig'
						} 
					
				]
		});

		this.callParent(arguments);
	}
});