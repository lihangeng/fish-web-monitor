Ext.define('Eway.view.monitor.transaction.colorset.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_transaction_colorset_filterform',
	requires : [],
	height : 40,
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				fieldLabel : EwayLocale.monitor.business.transactionColor.hostRet,
				xtype : 'textfield',
				name : 'hostRet',
				labelAlign : 'right',
				width : 280
			} ]
		});
		this.callParent(arguments);
	}
});