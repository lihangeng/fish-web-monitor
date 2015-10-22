
Ext.define('Eway.view.report.plan.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.plan_update',
	
	requires: ['Eway.lib.Util'],
	
	title: Eway.locale.report.plan.changePlan,
	modal: true,
	resizable: false,
	constrainHeader: true,
	
	initComponent: function() {
		Ext.apply(this, {
			items :[ {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 400,
					labelWidth: 100,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.name,
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.startDate,
					xtype : 'datefield',
					name : 'startDate',
					format : 'Y-m-d',
					allowBlank : false,
					minValue : Ext.Date.format(new Date(), 'Y-m-d'),
					vtype : 'daterange',
					endDateField : 'endDate',
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
				},{
					fieldLabel : '<font color="red">*</font>'+Eway.locale.report.plan.endDate,
					xtype : 'datefield',
					name : 'endDate',
					format : 'Y-m-d',
					allowBlank : false,
					vtype : 'daterange',
					startDateField : 'startDate',
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
				},{
					xtype : 'textarea',
				    fieldLabel : Eway.locale.commen.remark,
				    name : 'note',
				    autoScroll : true,
					maxLength :30,
					allowBlank : true
				}],
				fbar: [{
					text: Eway.locale.button.update,
					iconCls :'sureBtn',
					action: 'update'
				},{
					text: Eway.locale.button.back,
					iconCls :'returnBtn',
					handler: this.onOver
				}]
			}]
		});
		
		this.callParent(arguments);
	},
	
	onOver: function() {
		this.up('window').close();
	}
});