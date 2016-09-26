Ext.define('Eway.view.monitor.transaction.colorset.Describe', {

	extend : 'Ext.window.Window',
	alias : 'widget.monitor_transaction_colorset_describe',

	title: EwayLocale.monitor.business.transactionColor.retDes,

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
				fieldLabel : EwayLocale.monitor.business.transactionColor.hostRet,
				xtype : 'textfield',
				name : 'hostRet',
				editable : false,
				readOnly : true
			}, {
				fieldLabel : EwayLocale.monitor.business.transactionColor.hostRetDes,
				xtype : 'textarea',
				name : 'hostRetDes',
				editable : false,
				autoScroll : true,
				readOnly : true
			},{
				fieldLabel : EwayLocale.monitor.business.transactionColor.localRet,
				xtype : 'textfield',
				name : 'localRet',
				editable : false,
				readOnly : true
			}, {
				fieldLabel : EwayLocale.monitor.business.transactionColor.localRetDes,
				xtype : 'textarea',
				name : 'localRetDes',
				autoScroll : true,
				editable : false,
				readOnly : true
			},{
				xtype : 'textarea',
				name : 'remark',
				fieldLabel : EwayLocale.monitor.business.transactionColor.remark,
				autoScroll : true,
				editable : false,
				readOnly : true
			} ]
		}
	});
		this.callParent(arguments);
	}
});