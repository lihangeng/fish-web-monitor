Ext.define('Eway.view.monitor.newTransaction.View', {
	extend : 'Eway.view.base.Panel',
	alias : 'widget.monitor_new_transaction_view',

	requires : [ 'Eway.view.monitor.newTransaction.Grid',
			'Eway.view.monitor.newTransaction.RightGrid',
            'Eway.view.monitor.newTransaction.Chart'],

	title : EwayLocale.monitor.business.newTransaction.title,
	layout : 'border',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
			    layout: 'border',
				region : 'center',
				items: [ {
			        region : 'center',
			        xtype : 'monitor_new_transaction_grid'
			    }, {
			        region : 'south',
	                collapsible: true,
	                header: false,
	                split:true,
	                xtype : 'monitor_new_transaction_chart',
	                height : 160
			    } ]
			}, {
			    region : 'east',
			    collapsible: true,
                header: false,
                split:true,
                width: 200,
			    xtype : 'monitor_new_transaction_rightgrid'
			} ]
		});
		this.callParent(arguments);
	}
});