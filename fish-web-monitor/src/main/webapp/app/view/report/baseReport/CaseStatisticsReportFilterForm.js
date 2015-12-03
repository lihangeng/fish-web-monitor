Ext.define('Eway.view.report.baseReport.CaseStatisticsReportFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_CaseStatisticsReportFilterForm',
	requires : [],
	height : 70,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {



		Ext.apply(this, {
			items : [ {
				columnWidth : .35,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
					xtype : 'fieldcontainer',
					fieldLabel : EwayLocale.report.baseReport.statisticsDays,
					labelWidth : 100,
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
						width : 100,
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
						width : 100,
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
			},{
				columnWidth : .65,
				items : [{
							fieldLabel : '<font color="red">*</font>'+EwayLocale.report.baseReport.angle,
							xtype : 'radiogroup',
							allowBlank : false,
							anchor : '100%',
							width : 700,
							labelWidth : 150,
							items : [ {
								boxLabel : EwayLocale.report.baseReport.device,
								name : 'angle',
								checked : true,
								inputValue : 1
							}, {
								boxLabel : EwayLocale.report.baseReport.vendor,
								name : 'angle',
								inputValue : 2
							}, {
								boxLabel : EwayLocale.report.baseReport.devType,
								name : 'angle',
								inputValue : 3
							}, {
								boxLabel : EwayLocale.report.baseReport.mod,
								name : 'angle',
								inputValue : 4
							}]
						},{
							fieldLabel : EwayLocale.report.baseReport.rank,
							xtype : 'radiogroup',
							allowBlank : false,
							anchor : '100%',
							width : 300,
							labelWidth : 150,
							items : [ {
								boxLabel : '5',
								name : 'rank',
								inputValue : 5
							}, {
								boxLabel : '10',
								name : 'rank',
								checked : true,
								inputValue : 10
							}, {
								boxLabel : '20',
								name : 'rank',
								inputValue : 20
							}]
				}]
			} ]
		});
		this.callParent(arguments);
	},
	changeImport : function(radio, newValue, oldValue, options) {
		var form = radio.up('form').getForm();
		var value = newValue.statType;
//		var year = form.findField('year');
		var month = form.findField('month');
		var day = form.findField('day');

		if (value == '1') {
			year.setVisible(true);
			month.setVisible(false);
//			day.setVisible(false);
		} else if (value == '2') {
//			year.setVisible(false);
			month.setVisible(true);
			day.setVisible(false);
		}
		else if (value == '3') {
//			year.setVisible(false);
			month.setVisible(false);
			day.setVisible(true);
		}
	}
});