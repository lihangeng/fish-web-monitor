Ext.define('Eway.view.machine.detail.basic.StatusInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_statusInfo',
	
    tools: [ {
        itemId: 'refreshStatusInfo',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh
    },{
        itemId: 'collapse',
        type: 'collapse',
        hidden: true,
        tooltip: EwayLocale.deviceInfo.collapse,
        callback: function(panel) {
        	panel.down("detail_basic_hiddenStatusInfo").setHidden(true);
			panel.down('#expand').setHidden(false);
        	panel.down('#collapse').setHidden(true);
        }
    }, {
        type: 'expand',
        itemId: 'expand',
        tooltip: EwayLocale.deviceInfo.expand,
        callback: function (panel) {
        	 panel.down("detail_basic_hiddenStatusInfo").setHidden(false);
			 panel.down('#collapse').show();
	         panel.down('#expand').setHidden(true);
        }
    }],

	requires : ['Eway.view.machine.detail.basic.OftenStatusInfo','Eway.view.machine.detail.basic.HiddenStatusInfo'],
	layout: {
        type: 'column',
    },
    closable:false,
    bodyPadding: 10,

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 105,
				width : '25%'
			},
			items:[{
				xtype:'form',
				items : [{
					xtype:'detail_basic_oftenStatusInfo',
					hidden:false
				},{
					xtype:'detail_basic_hiddenStatusInfo',
					hidden:true
				}]
			}]
		});

		this.callParent(arguments);
	}
	
});
