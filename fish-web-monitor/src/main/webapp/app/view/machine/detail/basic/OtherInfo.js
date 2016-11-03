Ext.define('Eway.view.machine.detail.basic.OtherInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_otherInfo',

	tools: [ {
        itemId: 'refreshOtherInfo',
        type: 'refresh',
        tooltip: EwayLocale.button.refresh,
        callback: function() {
            // do refresh
        }
    }],
	requires : [  ],
	title : '其他信息',
    layout: {
        type: 'column',
    },
    closable:false,
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'left',
				xtype : 'displayfield',
				labelWidth : 150,
				readOnly : true,
				width : '50%'
			},
			items : [ {
				columnWidth : .25,
				fieldLabel : EwayLocale.boxInfo.cashboxInLimit,
				name : 'maxAlarm',
				minHeight : 20,
				code : 'maxAlarm'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.boxInfo.cashboxOutLimit,
				name : 'minAlarm',
				minHeight : 20,
				code : 'minAlarm'
			},{
				columnWidth : .25,
				fieldLabel : '钱箱初始金额',
				name : 'boxInitCount',
				minHeight : 20,
				code : 'boxInitCount'
			},{
				columnWidth : .25,
				fieldLabel : '钱箱当前金额',
				name : 'boxCurrentCount',
				minHeight : 20,
				code : 'boxCurrentCount'
			},{
				columnWidth : .25,
				fieldLabel : '设备吞卡数量',
				name : 'retainCardCount'
			}],
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
   
});
