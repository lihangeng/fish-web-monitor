Ext.define('Eway.view.machine.detail.basic.ControllerInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_ControllerInfo',

	renderTo: document.body,
    tools: [ {
        itemId: 'refresh',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh
    },{
        itemId: 'collapse',
        type: 'collapse',
        hidden: true,
        tooltip: EwayLocale.deviceInfo.collapse,
        callback: function(panel) {
        	panel.down('detail_basic_hiddenControllerInfo').setHidden(true);
        	panel.down('#expand').show();
        	panel.down('#collapse').setHidden(true);
        }
    }, {
        type: 'expand',
        itemId: 'expand',
        tooltip: EwayLocale.deviceInfo.expand,
        callback: function (panel) {
        	panel.down('detail_basic_hiddenControllerInfo').show();
            panel.down('#collapse').show();
            panel.down('#expand').setHidden(true);
        }
    }],

	requires : [
	            'Eway.view.machine.detail.basic.HiddenControllerInfo',
	            'Eway.view.machine.detail.basic.OftenControllerInfo'],
	title : EwayLocale.deviceInfo.controllerInfo,
	layout: {
        type: 'column'
    },
    closable:false,
    bodyPadding: 10,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true
			},

			items : [ {
				xtype:'form',
				items : [{
							xtype:'detail_basic_OftenControllerInfo'
						},{
							xtype:'detail_basic_hiddenControllerInfo',
							hidden:true
				}]
			}]
		});

		this.callParent(arguments);
	}
    
});
