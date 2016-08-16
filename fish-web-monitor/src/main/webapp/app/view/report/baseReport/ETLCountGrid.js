Ext.define('Eway.view.report.baseReport.ETLCountGrid', {
	extend : 'Eway.view.base.Grid',
	alias : 'widget.trans_CountGrid',
	requires : [ 'Eway.lib.Util' ],
	border : true,
	autoFit:true,

	initComponent : function() {
		var store = Ext.create('Eway.store.report.baseReport.ETLJobCount');
		Ext.apply(this, {
			initRegion : true,
			store : store,
			columns : [{
				header : EwayLocale.batch.indexID,
				dataIndex : 'id',
				width : 180,
				align:'center',
			},{
				header : EwayLocale.batch.tradeTime,
				dataIndex : 'tradeTime',
				width : 180,
				align:'center',
			} ],
			buttonAlign : 'center',
			buttons: [{
				text: EwayLocale.batch.AgainOpera,
				action: 'confirm'
			}, {
				text: EwayLocale.button.back,
				handler: this.onOver,
			}]
		});

		this.callParent(arguments);
	},

	onOver: function() {
		this.up('window').close();
	}
});