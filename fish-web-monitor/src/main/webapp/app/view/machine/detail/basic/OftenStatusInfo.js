Ext.define('Eway.view.machine.detail.basic.OftenStatusInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_oftenStatusInfo',
	

	requires : [],
	layout: {
        type: 'column',
    },
    closable:false,
    //bodyPadding: 10,
    

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 105,
				width : '25%'
			},
			items : [{
				columnWidth : .25,
				a_link : true,
				fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.runStatus,
				name : 'runStatus'
			},{
				columnWidth : .25,
				a_link : true,
				fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.modStatus,
				name : 'modStatus'
			},{
				columnWidth : .25,
				a_link : true,
				fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.boxStatus,
				name : 'boxStatus'
			},{
				columnWidth : .25,
				a_link : true,
				fieldLabel : EwayLocale.monitor.devMonitor.comboxStatus.netStatus,
				name : 'netStatus'
			}]
		});
		
		this.callParent(arguments);
	}
});
