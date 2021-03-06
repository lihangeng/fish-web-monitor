Ext.define('Eway.view.report.openrate.org.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.report_openrate_org_filterform',
	requires : [ 'Eway.lib.Util','Eway.view.common.OrgComboOrgTree','Eway.view.field.atmType.DeviceAtmCatalogComboBox'],

	height : 'auto',
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .55,
				defaults : {
					labelAlign : 'right'
				},
				items : [ {
					fieldLabel : EwayLocale.report.openrate.device.statisticsMethod,
					xtype : 'radiogroup',
					width : 280,
					items : [ {
						boxLabel : EwayLocale.commen.year,
						name : 'statType',
						inputValue : 1
					}, {
						boxLabel : EwayLocale.commen.month,
						name : 'statType',
						inputValue : 2
					}, {
						boxLabel :EwayLocale.commen.day,
						name : 'statType',
						checked : true,
						inputValue : 3
					} ],
					listeners : {
						change : this.changeImport
					}
				}, {
					width : 280,
					name : 'year',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.yearTime,
					hidden : true,
					value : new Date(),
					editable : false,
					format : 'Y',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	return;
				            }
						}
					}
				}, {
					width : 280,
					name : 'month',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.monthTime,
					editable : false,
					hidden : true,
					value : new Date(),
					format : 'Y-m',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	return;
				            }
						}
					}
				}, {
					width : 280,
					name : 'day',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.dayTime,
					editable : false,
					value : Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.DAY, -1),'Y-m-d'),
					maxValue : Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.DAY, -1),'Y-m-d'),
					format : 'Y-m-d',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	return;
				            }
						}
					}
				} ]
			},
			{
				columnWidth : .45,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
				fieldLabel : EwayLocale.machine.device.onBankSignal,
				xtype : 'radiogroup',
				anchor : '69%',
				width:280,
				items : [ {
					boxLabel : EwayLocale.report.openrate.device.inBank,
					name : 'awayFlag',					
					inputValue : 1
				}, {
					boxLabel : EwayLocale.report.openrate.device.outBank,
					name : 'awayFlag',
					inputValue : 2
				}, {
					boxLabel :  EwayLocale.report.openrate.device.allBank,
					name : 'awayFlag',
					checked : true,
					inputValue : ""
						
				} ]
			}
			]}
			
			
			]});
		this.callParent(arguments);
	},
	changeImport : function(radio, newValue, oldValue, options) {
		var form = radio.up('form').getForm();
		var value = newValue.statType;
		var year = form.findField('year');
		var month = form.findField('month');
		var day = form.findField('day');

		if (value == '1') {
			year.setVisible(true);
			month.setVisible(false);
			day.setVisible(false);
		} else if (value == '2') {
			year.setVisible(false);
			month.setVisible(true);
			day.setVisible(false);
		} else if (value == '3') {
			year.setVisible(false);
			month.setVisible(false);
			day.setVisible(true);
		}
	}
});