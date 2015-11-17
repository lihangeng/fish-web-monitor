Ext.define('Eway.view.report.openrate.device.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.report_openrate_device_filterform',
	height : 70,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .5,
				defaults : {
					labelAlign : 'right'
				},
				items : [{
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
						boxLabel : EwayLocale.commen.day,
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
					format : 'Y'
				}, {
					width : 280,
					name : 'month',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.monthTime,
					editable : false,
					hidden : true,
					value : new Date(),
					format : 'Y-m'
				}, {
					width : 280,
					name : 'day',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.dayTime,
					editable : false,
					value : Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.DAY, -1),'Y-m-d'),
					format : 'Y-m-d'
				}]
			} ,{
				columnWidth : .2,
				labelAlign : 'right',
				fieldLabel:EwayLocale.commen.terminalId,
				labelAlign : 'right',
				xtype : 'textfield',
				name:'terminalId',
				width:280
				
			}]
		});
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