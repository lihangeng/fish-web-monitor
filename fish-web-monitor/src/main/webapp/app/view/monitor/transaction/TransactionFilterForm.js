
Ext.define('Eway.view.monitor.transaction.TransactionFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.transactionFilterForm',

	bodyStyle : 'padding: 10px 10px 30px 10px',
	border : false,
	
	height : 35,
	
	requires: ['Eway.view.field.monitor.BlackList'],

	initComponent: function() {
		Ext.apply(this, {
			items : [ {
				layout : 'column',
				border : false,
				defaults : {
					border : false
				},
				items : [ {
						columnWidth : .5,
						items : [ {
							fieldLabel : EwayLocale.commen.terminalId,
							labelAlign : 'right',
							xtype : 'textfield',
							name : 'terminalId',
							msgTarget : 'side'
						} ]
					}, {
                    	columnWidth : .25,
                    	items : [ {
	                        xtype: 'radiogroup',
	                        fieldLabel: EwayLocale.monitor.business.transaction.transactionMonitor.scroll,
	                        labelAlign : 'right',
	                        items: [ {
	                        	boxLabel: EwayLocale.monitor.business.transaction.transactionMonitor.up, 
	                        	name: 'rbAuto', 
	                        	inputValue: 'UP', 
	                        	checked: true
                        	}, {
                        		boxLabel: EwayLocale.monitor.business.transaction.transactionMonitor.down, 
                        		name: 'rbAuto', 
                        		inputValue: 'DOWN'
                    		} ]
                    	} ]
                	}, {
						items : [ {
							xtype : 'hidden',
							name : 'transType',
							labelAlign : 'right'
						} ]
					}, {
						items : [ {
							xtype : 'hidden',
							name : 'hostRet',
							labelAlign : 'right'
						} ]
					} ]
				} ]
		});

		this.callParent(arguments);
	}
});