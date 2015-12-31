
Ext.define('Eway.view.machine.quittingNotice.FilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.quittingNotice_FilterForm',

	stores : ['Eway.store.monitor.card.CardFilter'],
	requires: ['Eway.view.field.quittingNotice.StopType',
	           'Eway.view.field.quittingNotice.DeviceCode',
	           'Eway.lib.Util'],

	layout : 'column',
	height: 90,
		defaults : {
		border : false
	},
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .4,
				layout : 'anchor',
				defaults : {
					anchor : '75%',
					xtype : 'textfield',
					labelAlign : 'right',
					labelWidth : 120
				},
				items : [{
					xtype : 'field_deviceCode',
					labelAlign : 'right'
				},{
					xtype : 'textfield',
					labelAlign : 'right',
					name : 'responsibilityName',
					fieldLabel : EwayLocale.machine.quittingNotice.responsibilityName
				}]
			},{
				columnWidth : .5,
				layout : 'anchor',
				defaults : {
					anchor : '90%',
					xtype : 'textfield',
					labelAlign : 'right'
				},
				items : [ {
						editable : false,
						labelAlign : 'right',
						xtype : 'field_stopType',
						anchor : '52%',
					},{
						xtype : 'fieldcontainer',
						fieldLabel : EwayLocale.monitor.business.card.endTime,
						layout : 'hbox',
						defaults : {
							hideLabel : true
						},
						items : [{
							fieldLabel : 'endDateTime',
							displayField : 'display',
							valueField : 'value',
							xtype : 'datefield',
							format : 'Y-m-d',
							name : 'startDate',
							editable : false,
							vtype : 'daterange',
							editable:false,
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
						}, {
							xtype : 'displayfield',
							value : EwayLocale.machine.quittingNotice.to
						}, {
							fieldLabel : 'minute',
							displayField : 'display',
							valueField : 'value',
							xtype : 'datefield',
							editable : false,
							format : 'Y-m-d',
							name : 'endDate',
							vtype : 'daterange',
							editable:false,
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
						}]
					}]
			}]
		});

		this.callParent(arguments);
	}
});
