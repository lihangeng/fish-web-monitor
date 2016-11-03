Ext.define('Eway.view.machine.detail.ControllerInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_ControllerInfo',

	renderTo: document.body,
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
        	Ext.getCmp('controllerInfo').setHidden(true);
        	panel.down('#expand').show();
        	panel.down('#collapse').setHidden(true);
        }
    }, {
        type: 'expand',
        itemId: 'expand',
        tooltip: '展开',
        callback: function (panel) {
        	Ext.getCmp('controllerInfo').show();
            panel.down('#collapse').show();
            panel.down('#expand').setHidden(true);
        }
    }],

	requires : ['Eway.view.machine.detail.basic.controllerInfo','Eway.view.machine.detail.basic.OftenControllerInfo'],
	title : EwayLocale.deviceInfo.controllerInfo,
	layout: {
        type: 'column',
    },
    closable:false,
    bodyPadding: 10,

  

	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
			},
			items : [{
						xtype:'detail_basic_OftenControllerInfo'
					},{
						xtype:'detail_basic_controllerInfo',
						id:'controllerInfo',
						hidden:true
			}]
				
		});

		this.callParent(arguments);
	}
    
});
