Ext.define('Eway.view.monitor.device.box.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_device_box_filterForm',
	height : 100,
	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			bodyStyle : {
				'background-color' : '#dfe8f5'
			},
			defaults:{
				bodyStyle : {
					'background-color' : '#dfe8f5',
					padding : '10px'
				}
			},
			items : [ {
				columnWidth : .4,
				defaults:{
					xtype : 'textfield',
					readOnly:true
				},
				items : [ {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.initAmount,
					name : 'initAmount'
				}, {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.amount,
					name : 'amount'
				}, {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.dispenseAmount,
					name : 'dispenseAmount',
					hidden : true
				} ]
			}, {
				columnWidth : .5,
				defaults:{
					xtype : 'textfield',
					readOnly:true,
					labelWidth:180
				},
				items : [ {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.rejectAmount,
					name : 'rejectAmount'
				}, {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.retractCount,
					name : 'retractCount',
					hidden : true
				}, {
					fieldLabel : EwayLocale.monitor.devMonitor.cash.minAmount,
					name : 'minAmount'
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});