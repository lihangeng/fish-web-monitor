
Ext.define('Eway.view.machine.quittingNotice.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.quittingNotice_add',

	requires: ['Eway.view.field.quittingNotice.StopType',
			   'Ext.ux.form.DateTimeField',
			   'Eway.view.field.quittingNotice.DevStatus',
	           'Eway.view.field.quittingNotice.DeviceCode',
	           'Eway.lib.Util'],

	title: EwayLocale.machine.quittingNotice.addCloseMsg,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(Ext.form.field.VTypes,{
		    daterange: function(val, field) {
		        var date = field.parseDate(val);
		        if (!date) {
		            return false;
		        }
		        if (field.startDateField && (!field.dateRangeMax || (date.getTime() != field.dateRangeMax.getTime()))) {
		            var start = field.up('form').getForm().findField(field.startDateField);
		            var newDate = new Date(date.getTime() - 24*60*60*1000);
		            start.setMaxValue(newDate);
		            field.dateRangeMax = newDate;
		        }
		        else if (field.endDateField && (!field.dateRangeMin || (date.getTime() != field.dateRangeMin.getTime()))) {
		            var end = field.up('form').getForm().findField(field.endDateField);
		            var newDate = new Date(date.getTime() + 24*60*60*1000);
		            end.setMinValue(newDate);
		            field.dateRangeMin = newDate;
		        }
		        /*
		         * Always return true since we're only using this vtype to set the
		         * min/max allowed values (these are tested for after the vtype test)
		         */
		        return true;
		    },
		    daterangeText: EwayLocale.machine.quittingNotice.dateRangeText
        });
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 400,
					labelWidth: 110,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.terminalId,
					xtype : 'field_deviceCode',
					regexText : EwayLocale.vtype.bankOrgCode,
					maxLength : 20,
					blankText:EwayLocale.machine.quittingNotice.click,
					allowBlank : false
				},{
					fieldLabel : '<font color="red">*</font>' + EwayLocale.machine.quittingNotice.stopTime,
					displayField : 'display',
					valueField : 'value',
					xtype : 'datefield',
					format : 'Y-m-d',
					name : 'stopTime',
					editable : false,
					vtype : 'daterange',
					minValue : new Date(),
					width : 400,
					allowBlank : false,
					endDateField : 'openTime',
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
					fieldLabel :  EwayLocale.machine.quittingNotice.openTime,
					displayField : 'display',
					valueField : 'value',
					width : 400,
					xtype : 'datefield',
					editable : false,
					format : 'Y-m-d',
					name : 'openTime',
					vtype : 'daterange',					
					editable:false,
					startDateField : 'stopTime',
					listeners : {
						blur : {
				            fn: function(This, options){
				            	var value = this.getValue();
				            	if (!value) {
				            		var startField = this.up('form').getForm().findField(this.startDateField);
				            	}
				            }
						}
					}

				},{fieldLabel : '<font color="red">*</font>' + EwayLocale.machine.quittingNotice.currentStatus,
					xtype : 'field_devStatus',
					allowBlank : true,
					value : 'DISABLED',
					hidden: true,
					editable : false
					
				},{
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.quittingNotice.closeType,
					xtype : 'field_stopType',
					allowBlank : false,
					value : '1',
					editable : false
				},{
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.quittingNotice.responsibilityName,
					xtype : 'textfield',
					maxLength :20,
					name : 'responsibilityName',
					allowBlank : false
				},{
					fieldLabel : EwayLocale.machine.quittingNotice.stopReason,
					xtype : 'textarea',
					name : 'stopReason',
					maxLength :60,
					autoScroll : true
				/*},{
					fieldLabel : '<font color="red">*</font> 设置时间',
					xtype : 'datetimefield',
					name : 'setTime',
					format : 'Y-m-d H:i:s',
					allowBlank : false*/
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'confirm'
				}, {
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: EwayLocale.button.cancle,
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});