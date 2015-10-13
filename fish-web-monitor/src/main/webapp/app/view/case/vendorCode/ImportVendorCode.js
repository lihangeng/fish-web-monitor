Ext.define('Eway.view.case.vendorCode.ImportVendorCode', {
	extend : 'Ext.window.Window',
	alias : 'widget.vendorCode_ImportVendorCode',

	title : Eway.locale.cases.vendorCode.exportProviderInfo,
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
                    fieldLabel : Eway.locale.cases.vendorCode.provider,
                    emptyText : Eway.locale.combox.combox,
                    name : 'name',
                    hiddenValue : 'vendor',
                    editable : false,
                    filters : '{"type" : "1"}',
                    rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
                  }, {
					xtype : 'filefield',
					buttonText : Eway.locale.combox.explorer,
					fieldLabel : Eway.locale.cases.vendorCode.exportFile,
					emptyText : Eway.locale.tip.exportFiles,
					width : 400,
					allowBlank : false,
					name : 'file',
					regex : /^([\w|\W]*)(\.xlsx)|([\w|\W]*)(\.xls)$/,
					regexText : Eway.locale.tip.exportFiles
				} ],
				buttonAlign : 'center',
				buttons : [ {
					text : Eway.locale.cases.confirm,
					// iconCls : 'sureBtn',
					action : 'import'
				}, {
					text : Eway.locale.cases.cancel,
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