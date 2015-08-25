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
					fieldLabel : '加钞总金额',
					name : 'initAmount'
				}, {
					fieldLabel : '剩钞金额',
					name : 'amount'
				}, {
					fieldLabel : '出钞总金额',
					name : 'dispenseAmount'
				} ]
			}, {
				columnWidth : .4,
				defaults:{
					xtype : 'textfield',
					readOnly:true
				},
				items : [ {
					fieldLabel : '废钞金额',
					name : 'rejectAmount'
				}, {
					fieldLabel : '钞票回收次数',
					name : 'retractCount',
					hidden : true
				}, {
					fieldLabel : '最小取款金额',
					name : 'minAmount'
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});