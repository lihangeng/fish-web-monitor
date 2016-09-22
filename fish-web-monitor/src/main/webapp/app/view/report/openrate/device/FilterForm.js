Ext.define('Eway.view.report.openrate.device.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.report_openrate_device_filterform',
	requires:['Eway.view.field.atmType.DeviceAtmVendorComboBox',
	          'Eway.view.field.device.DeviceAtmType',
	          'Eway.view.field.atmType.DeviceAtmCatalogComboBox',
	          'Eway.view.common.OrgComboOrgTree',
	          'Eway.lib.Util'],
	height : 'auto',
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				columnWidth : .28,
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
				},{
					columnWidth : .2,
					labelAlign : 'right',
					fieldLabel:EwayLocale.commen.terminalId,
					labelAlign : 'right',
					xtype : 'textfield',
					name:'terminalId',

				}]
			} ,
			{
				columnWidth : .33,
				defaults : {
					labelAlign : 'right',
					labelWidth:140
				},
			items:[{
				xtype : 'field_device_deviceatmtype',
				emptyText :  EwayLocale.combox.select

			},{
				xtype : 'field_atmType_DeviceAtmCatalogComboBox',
				fieldLabel : EwayLocale.machine.atmGroup.devCatalogName

			},{
				xtype : 'field_atmType_DeviceAtmVendorComboBox',
				fieldLabel : EwayLocale.machine.atmGroup.devVendorName

			}]},{
				columnWidth : .33,
				defaults : {
					labelAlign : 'right',
					labelWidth:160
				},
			items:[{

				style : 'padding-top:0px',
				xtype : 'hiddenfield',
				name : 'organizationID',
				value : Eway.user.getOrgId()
			}, {
				style : 'padding-top:0px',
				xtype : 'hiddenfield',
				name : 'organization'
			},{
				xtype : 'common_orgComboOrgTree',
				fieldLabel : EwayLocale.machine.atmGroup.orgName,
				emptyText : EwayLocale.combox.select,
				name : 'orgName',
				hiddenValue : 'organization',
				editable : false,
				filters : '{"type" : "0"}',
				rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
			},{
				xtype : 'radiogroup',
				fieldLabel : EwayLocale.report.openrate.device.openRate,
				anchor : '69%',
				items : [ {
					boxLabel :EwayLocale.report.openrate.device.lt,
					name : 'compare',
					checked : true,
					width: 50,
					inputValue : 0
				}, {
					boxLabel :EwayLocale.report.openrate.device.gt,
					name : 'compare',
					width: 50,
					inputValue : 1
				},{
					xtype : 'textfield',
					name:'openrate',
					width: 60,
					regex:/(^(\d|[1-9]\d|100)(\.\d{1,2})?$)/,
					regexText: EwayLocale.report.openrate.device.regex
					
				}, 
				{
					xtype : 'displayfield',
					value : '%'
				}
				]

			},{
				xtype : 'radiogroup',
				fieldLabel : EwayLocale.machine.device.onBankSignal,
				anchor : '71%',
				items : [ {
					boxLabel :EwayLocale.report.openrate.device.inBank,
					name : 'awayFlag',
					inputValue : 1
				}, {
					boxLabel :EwayLocale.report.openrate.device.outBank,
					name : 'awayFlag',
					inputValue : 2
				}, {
					boxLabel :EwayLocale.report.openrate.device.allBank,
					name : 'awayFlag',
					checked : true,
					width:90,
					inputValue :""
				}]

			}]}
			]
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