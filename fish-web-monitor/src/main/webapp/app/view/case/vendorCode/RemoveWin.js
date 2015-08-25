Ext.define('Eway.view.case.vendorCode.RemoveWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.vendorCode_RemoveWin',

	title : '删除厂商故障信息',
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
                    fieldLabel : '厂商',
                    emptyText : '--请选择--',
                    name : 'name',
                    hiddenValue : 'vendor',
                    editable : false,
                    filters : '{"type" : "1"}',
                    rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false
                  }],
				buttonAlign : 'center',
				buttons : [ {
					text : '确认',
//					glyph : 0xf014,
					action : 'delete'
				}, {
					text : '取消',
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