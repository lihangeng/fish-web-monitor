Ext.define('Eway.view.machine.detail.basic.OftenControllerInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_OftenControllerInfo',
	

	requires : [],
	layout: {
        type: 'column'
    },
    closable:false,

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 105,
				columnWidth : .25
			},
			items : []
		});
		
		this.callParent(arguments);
	}
});
