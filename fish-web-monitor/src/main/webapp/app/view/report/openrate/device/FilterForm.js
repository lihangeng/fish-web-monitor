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
					fieldLabel : '统计方式',
					xtype : 'radiogroup',
					width : 280,
					items : [ {
						boxLabel : '年',
						name : 'statType',
						inputValue : 1
					}, {
						boxLabel : '月',
						name : 'statType',
						inputValue : 2
					}, {
						boxLabel : '日',
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
					fieldLabel : '年份',
					hidden : true,
					value : new Date(),
					editable : false,
					format : 'Y'
				}, {
					width : 280,
					name : 'month',
					xtype : 'datefield',
					fieldLabel : '月份',
					editable : false,
					hidden : true,
					value : new Date(),
					format : 'Y-m'
				}, {
					width : 280,
					name : 'day',
					xtype : 'datefield',
					fieldLabel : '日期',
					editable : false,
					value : new Date(),
					format : 'Y-m-d'
				}]
			} ,{
				columnWidth : .2,
				labelAlign : 'right',
				fieldLabel:'设备号',
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