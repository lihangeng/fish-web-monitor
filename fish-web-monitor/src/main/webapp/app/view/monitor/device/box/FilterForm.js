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
				columnWidth : .5,
				defaults:{
					xtype : 'textfield',
					readOnly:true
				},
				items : [ {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.initAmount,
					name : 'initAmount'
				}, {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.amount,
					name : 'amount'
				}, {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.dispenseAmount,
					name : 'dispenseAmount'
				} ]
			}, {
				columnWidth : .4,
				defaults:{
					xtype : 'textfield',
					readOnly:true
				},
				items : [ {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.rejectAmount,
					name : 'rejectAmount'
				}, {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.retractCount,
					name : 'retractCount',
					hidden : true
				}, {
					fieldLabel : Eway.locale.monitor.devMonitor.cash.minAmount,
					name : 'minAmount'
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});