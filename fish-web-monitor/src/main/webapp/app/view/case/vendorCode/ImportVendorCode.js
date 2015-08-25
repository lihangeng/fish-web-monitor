Ext.define('Eway.view.case.vendorCode.ImportVendorCode', {
	extend : 'Ext.window.Window',
	alias : 'widget.vendorCode_ImportVendorCode',

	title : '导入厂商故障信息',
	modal : true,
	resizable : false,
	constrainHeader : true,

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults : {
					width : 400,
					labelWidth : 60,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'vendor'
				  },
                  {
                    xtype : 'common_orgComboOrgTree',
                    labelAlign : 'right',
                    fieldLabel : '厂商',
                    emptyText : '--请选择--',
                    name : 'name',
                    hiddenValue : 'vendor',
                    editable : false,
                    filters : '{"type" : "1"}',
                    rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
                  }, {
					xtype : 'filefield',
					buttonText : '浏览...',
					fieldLabel : '导入文件',
					emptyText : '请选择导入文件,只支持.xls和.xlsx格式的文件',
					width : 400,
					allowBlank : false,
					name : 'file',
					regex : /^([\w|\W]*)(\.xlsx)|([\w|\W]*)(\.xls)$/,
					regexText : '只能导入.xls格式和.xlsx格式的文件'
				} ],
				buttonAlign : 'center',
				buttons : [ {
					text : '确认',
					// iconCls : 'sureBtn',
					action : 'import'
				}, {
					text : '取消',
					// iconCls : 'returnBtn',
					handler : this.onOver
				} ]
			}
		});

		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});