Ext.define("Eway.view.machine.device.module.StatusIDC", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusidc',
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
				title : '读卡器模块(IDC)状态信息',
				titleAlign:'center',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height : 40
					},
					items : [ {
						fieldLabel : '媒体状态',
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '回收盒状态',
						name : 'retainBin'
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
						fieldLabel : '回收盒数量',
						name : 'cards',
						style : 'margin-top:2px'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});