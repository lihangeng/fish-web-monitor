Ext.define('Eway.view.machine.detail.basic.CashLimitInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_cashLimitInfo',

	requires : [  ],
	title : '钞箱预警信息',
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .5,
				fieldLabel : EwayLocale.boxInfo.cashboxInLimit,
				name : 'maxAlarm',
				minHeight : 20,
				code : 'maxAlarm'
			},{
				columnWidth : .5,
				fieldLabel : EwayLocale.boxInfo.cashboxOutLimit,
				name : 'minAlarm',
				minHeight : 20,
				code : 'minAlarm'
			}],

			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
});
