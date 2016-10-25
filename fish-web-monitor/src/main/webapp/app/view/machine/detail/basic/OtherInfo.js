Ext.define('Eway.view.machine.detail.basic.OtherInfo', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.detail_basic_otherInfo',

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
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.installStyle,
				name : 'setupTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.netType,
				name : 'netTypeName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.onBankSignal,
				name : 'awayFlagName'
			},{
				columnWidth : .25,
				fieldLabel : EwayLocale.machine.device.operation,
				name : 'workTypeName'
			},{
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
			},{
				columnWidth : .25,
				fieldLabel : '注册状态',
				name : 'registerStatus'
			},{
				columnWidth : .25,
				fieldLabel :'ATMC应用版本号',
				name : 'appRelease',
			   // a_link : true
				//value:'<a href="#" class="link">'+value+'</a>'
				renderer: function(value,meta,record) {
					return '<a href="#" class="link">'+value+'</font></a>';
				}
			}],
			listeners : {
				activate : function(panel) {}
			}
		});

		this.callParent(arguments);
	}
   
});
