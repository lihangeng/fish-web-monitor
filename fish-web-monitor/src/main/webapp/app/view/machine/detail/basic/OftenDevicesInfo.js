Ext.define('Eway.view.machine.detail.basic.OftenDevicesInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_oftenDevicesInfo',
	

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
			items : [ {
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.terminalId,
				name : 'terminalId'
			},{
				columnWidth : .25,
				fieldLabel : 'IP地址',
				name : 'ip'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.commen.devStatus,
				name : 'statusName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.atmGroup.devTypeName,
				name : 'devTypeName'
			}]
		});
		
		this.callParent(arguments);
	}
});
