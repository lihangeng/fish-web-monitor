Ext.define("Eway.view.machine.device.module.StatusRPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusrpr',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '<center>厂商状态信息</center>',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : '厂商故障码',
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>凭条打印机模块(RPR)状态信息</center>',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						height : 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '媒体状态',
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '回收单元状态',
						name : 'bin'
					}, {
						fieldLabel : '打印机纸状态',
						name : 'supplyLevel'
					} ]
				}, {
					columnWidth : .5,
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : '墨水',
						name : 'ink',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '色带',
						name : 'toner'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});