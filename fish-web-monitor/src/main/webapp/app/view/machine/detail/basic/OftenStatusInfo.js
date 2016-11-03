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
				fieldLabel : '运行状态',
				name : 'runStatus'
			},{
				columnWidth : .25,
				fieldLabel : '模块状态',
				name : 'modStatus'
			},{
				columnWidth : .25,
				fieldLabel : '钞箱状态',
				name : 'boxStatus'
			},{
				columnWidth : .25,
				fieldLabel : '网络状态',
				name : 'netStatus'
			}]
		});
		
		this.callParent(arguments);
	}
});
