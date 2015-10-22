Ext.define('Eway.view.case.vendorCode.RemoveWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.vendorCode_RemoveWin',

	title : Eway.locale.cases.vendorCode.deleteFaultInfo,
	modal : true,
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
				items : [ {
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'vendor'
				  },
                  {
                    xtype : 'common_orgComboOrgTree',
                    labelAlign : 'right',
                    fieldLabel :  Eway.locale.cases.vendorCode.provider,
                    emptyText : Eway.locale.combox.select,
                    name : 'name',
                    hiddenValue : 'vendor',
                    editable : false,
                    filters : '{"type" : "1"}',
                    rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
                  }],
				buttonAlign : 'center',
				buttons : [ {
					text : Eway.locale.cases.confirm,
//					glyph : 0xf014,
					action : 'delete'
				}, {
					text : Eway.locale.cases.cancel,
//					iconCls : 'returnBtn',
					handler : this.onOver
				} ]
			}
		});
		this.callParent(arguments);
	},

	onOver : function() {
		this.up('window').close();
	}
});