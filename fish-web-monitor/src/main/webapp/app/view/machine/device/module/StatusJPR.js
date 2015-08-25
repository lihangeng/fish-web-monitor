Ext.define("Eway.view.machine.device.module.StatusJPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusjpr',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '厂商状态信息',
				titleAlign: 'center',
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
				title : '<center>日志打印机模块(JPR)状态信息</center>',
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
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '打印纸状态',
						name : 'supplyLevel',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '墨水',
						name : 'ink'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '媒体状态',
						name : 'media',
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