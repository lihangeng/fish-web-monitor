Ext.define('Eway.view.machine.detail.basic.HardwareInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_hardwareInfo',
	
    tools: [ {
        itemId: 'refresh',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
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

	requires : ['Eway.view.machine.detail.basic.StatusInfo','Eway.view.machine.detail.basic.StateInfo'],
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
				xtype:'detail_basic_stateInfo'
			},{
				xtype:'detail_basic_statusInfo',
				 id:'statusInfo',
				 hidden:true
			}]
		});

		this.callParent(arguments);
	}
	
});
