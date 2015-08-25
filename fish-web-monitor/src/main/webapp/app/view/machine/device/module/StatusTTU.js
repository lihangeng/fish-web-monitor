Ext.define("Eway.view.machine.device.module.StatusTTU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusttu',
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
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '厂商故障码',
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>文本终端单元(TTU)状态信息</center>',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right'
					}
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});