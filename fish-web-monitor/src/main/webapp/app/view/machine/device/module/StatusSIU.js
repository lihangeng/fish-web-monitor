Ext.define("Eway.view.machine.device.module.StatusSIU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statussiu',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : 'SIU能力状态信息',
				titleAlign:'center',
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
						height : 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '防护罩状态',
						name : 'vandalShield',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '操作员按钮状态',
						name : 'operatorSwitch'
					}, {
						fieldLabel : '环境灯状态',
						name : 'ambientLight'
					}, {
						fieldLabel : '箱门状态',
						name : 'cabinet'
					}, {
						fieldLabel : '安全门状态',
						name : 'safe'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						labelAlign : 'right',
						labelWidth: 140,
						height : 40
					},
					items : [ {
						fieldLabel : '插卡导引灯状态',
						name : 'idcGuidelight',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '取钞引导指示灯状态',
						name : 'cdmGuidelight'
					}, {
						fieldLabel : '凭条导引灯状态',
						name : 'receiptGuidelight'
					}, {
						fieldLabel : 'CIM导引灯状态',
						name : 'cimGuidelight'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});