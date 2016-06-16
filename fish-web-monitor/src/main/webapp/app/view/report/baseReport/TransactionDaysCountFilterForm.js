Ext.define('Eway.view.report.baseReport.TransactionDaysCountFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.baseReport_TransactionDaysCountFilterForm',
	requires : ['Eway.view.common.OrgComboOrgTree',
	            'Ext.ux.form.DateMonth'],
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
					hidden : true,
					xtype : 'radiogroup',
					width : 280,
					items : [ {
						boxLabel : EwayLocale.commen.year,
						name : 'statType',
						inputValue : 1
					}, {
						boxLabel : EwayLocale.commen.month,
						name : 'statType',
						checked : true,
						inputValue : 2
					}
					/*, {
						boxLabel : EwayLocale.commen.day,
						name : 'statType',
						checked : true,
						inputValue : 3
					}*/
					],
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
					xtype : 'monthfield',
					fieldLabel : EwayLocale.commen.monthTime,
					editable : false,
					value : new Date(),
					format : 'Y-m',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	return;
				            }
						}
					}
				}
				/*, {
					width : 280,
					name : 'day',
					xtype : 'datefield',
					fieldLabel : EwayLocale.commen.dayTime,
					editable : false,
					value : new Date(),
					format : 'Y-m-d'
				}*/
				]
			},{
				columnWidth : .5,
				items : [{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					//只带出银行机构
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.commen.orgNameBelongs,
					labelAlign : 'right',
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '0' ? true : false
				}]
			} ]
		});
		this.callParent(arguments);
	},
	changeImport : function(radio, newValue, oldValue, options) {
		var form = radio.up('form').getForm();
		var value = newValue.statType;
		var year = form.findField('year');
		var month = form.findField('month');
//		var day = form.findField('day');

		if (value == '1') {
			year.setVisible(true);
			month.setVisible(false);
//			day.setVisible(false);
		} else if (value == '2') {
			year.setVisible(false);
			month.setVisible(true);
//			day.setVisible(false);
		}
		/*else if (value == '3') {
			year.setVisible(false);
			month.setVisible(false);
//			day.setVisible(true);
		}*/
	}
});