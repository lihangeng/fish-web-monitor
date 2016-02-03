Ext.define('Eway.view.monitor.transaction.colorset.Describe', {

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_transaction_colorset_describe',

	title: '返回码描述',

	initComponent : function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelWidth: 100,
					labelAlign: 'right',
					msgTarget : 'side'
				},
			items : [ {
				fieldLabel : '主机返回码',
				xtype : 'textfield',
				name : 'hostRet',
				editable : false,
				readOnly : true
			}, {
				fieldLabel : '主机返回码描述',
				xtype : 'textarea',
				name : 'hostRetDes',
				editable : false,
				autoScroll : true,
				readOnly : true
			},{
				fieldLabel : '本地返回码',
				xtype : 'textfield',
				name : 'localRet',
				editable : false,
				readOnly : true
			}, {
				fieldLabel : '本地返回码描述',
				xtype : 'textarea',
				name : 'localRetDes',
				autoScroll : true,
				editable : false,
				readOnly : true
			},{
				xtype : 'textarea',
				name : 'remark',
				fieldLabel : '备注',
				autoScroll : true,
				editable : false,
				readOnly : true
			} ]
		}
	});
		this.callParent(arguments);
	}
});