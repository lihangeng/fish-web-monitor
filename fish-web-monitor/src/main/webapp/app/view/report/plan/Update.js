
Ext.define('Eway.view.report.plan.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.plan_update',
	
	requires: ['Eway.lib.Util'],
	
	title: '更改方案',
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
					fieldLabel : '<font color="red">*</font> 名称',
					xtype : 'textfield',
					name : 'name',
					maxLength : 30,
					allowBlank : false
				},{
					fieldLabel : '<font color="red">*</font>有效开始时间',
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
					fieldLabel : '<font color="red">*</font>有效结束时间',
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
				    fieldLabel : '备注',
				    name : 'note',
				    autoScroll : true,
					maxLength :30,
					allowBlank : true
				}],
				fbar: [{
					text: '更改',
					iconCls :'sureBtn',
					action: 'update'
				},{
					text: '返回',
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