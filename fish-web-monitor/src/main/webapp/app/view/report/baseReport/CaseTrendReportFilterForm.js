Ext.define('Eway.view.report.baseReport.CaseTrendReportFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.caseTrendReportFilterForm',
	height : 40,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .8,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
					xtype : 'fieldcontainer',
					fieldLabel : EwayLocale.report.baseReport.statisticsDays,
					layout : 'hbox',
					defaults : {
						hideLabel : true
					},
					items : [ {
						fieldLabel : 'endDateTime',
						displayField : 'display',
						valueField : 'value',
						xtype : 'datefield',
						format : 'Y-m-d',
						editable : false,
						name : 'startDateTime',
						value : Ext.Date.add(new Date(), Ext.Date.DAY, -7),
						vtype : 'daterange',
						endDateField : 'endDateTime',
						listeners : {
						blur : {
				            fn: function(This, options){
				            	var value = this.getValue();
				            	if (!value) {
				            		var endField = this.up('form').getForm().findField(this.endDateField);
				            		endField.setMinValue(null);
				            	}
				            }
						}
					}
					}, {
						xtype : 'displayfield',
						value : EwayLocale.monitor.business.transaction.toNum
					}, {
						fieldLabel : 'minute',
						displayField : 'display',
						valueField : 'value',
						fieldLabel : EwayLocale.monitor.business.transaction.amt,
						xtype : 'datefield',
						format : 'Y-m-d',
						editable : false,
						name : 'endDateTime',
						value : new Date(),
						vtype : 'daterange',
						startDateField : 'startDateTime',
						listeners : {
						blur : {
				            fn: function(This, options){
				            	var value = this.getValue();
				            	if (!value) {
				            		var startField = this.up('form').getForm().findField(this.startDateField);
				            		startField.setMaxValue(null);
				            	}
				            }
						}
					}
					} ]

				}]
			}]
		});
		this.callParent(arguments);
	}
});