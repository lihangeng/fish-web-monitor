Ext.define('Eway.controller.monitor.transaction.TransactionColorSet', {
	extend : 'Eway.controller.base.FishController',

	stores : [ 'monitor.transaction.colorset.ColorSet' ],
	models : [ 'monitor.transaction.colorset.ColorSet' ],
	views : [ 'Eway.view.monitor.transaction.colorset.View' ],
	formConfig : {
		form : 'Eway.view.monitor.transaction.colorset.Form',
		xtype : 'monitor_taansaction_colorset_from',
		width : 500,
		height : 500,
		title : '交易返回码'
	},
	refs : [ {
		ref : 'ewayView',
		selector : 'monitor_transaction_colorset_view',
		autoCreate : true,
		xtype : 'monitor_transaction_colorset_view'
	} ],

	init : function() {

		// Ext.Ajax.request({
		// url: 'api/msg/transaction/queryTransType',
		// success: function(response){
		// var text = response.responseText;
		// me.getEwayView().down('transactionFilterForm').getForm().findField("transType").setValue(text);
		// }
		// });

		this.control({
			'monitor_transaction_colorset_view button[action=add]' : {
				click : this.onAdd,
				scope : this
			},
			'monitor_transaction_colorset_view button[action=query]' : {
				click : this.onQuery,
				scope : this
			},
			'monitor_transaction_colorset_view button[action=update]' : {
				click : this.onUpdate,
				scope : this
			},
			'monitor_transaction_colorset_view button[action=remove]' : {
				click : this.onRemove,
				scope : this
			}
		});
	}
});