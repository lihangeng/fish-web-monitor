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
        tooltip: '收起',
        callback: function(panel) {
        	Ext.getCmp('statusInfo').setHidden(true);
			panel.down('#expand').show();
        	panel.down('#collapse').setHidden(true);
        }
    }, {
        type: 'expand',
        itemId: 'expand',
        tooltip: '展开',
        callback: function (panel) {
        	Ext.getCmp('statusInfo').show();
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
			items : [{
				xtype:'detail_basic_oftenStatusInfo'
			},{
				xtype:'detail_basic_hiddenStatusInfo',
				id:'statusInfo',
				hidden:true
			}]
		});

		this.callParent(arguments);
	}
	
});
