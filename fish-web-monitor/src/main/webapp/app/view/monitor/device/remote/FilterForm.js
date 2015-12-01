Ext.define('Eway.view.monitor.device.remote.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.monitor_device_remote_filterform',
	
	requires : ['Eway.view.common.OrgComboOrgTree'],
	
	height : 35,
	layout : 'column',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				labelAlign : 'right'
			},
			items : [ {
				columnWidth : .25,
				xtype : 'textfield',
				fieldLabel : EwayLocale.commen.terminalId,
				name : 'terminalId'
			}, {
				style : 'padding-top:0px',
				xtype : 'hiddenfield',
				name : 'organization'
			}, {
				columnWidth : .25,
				xtype : 'common_orgComboOrgTree',
				fieldLabel : EwayLocale.machine.atmGroup.orgName,
				emptyText : EwayLocale.combox.select,
				name : 'orgName',
				hiddenValue : 'organization',
				editable : false,
				filters : '{"type" : "0"}',
				rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
			}, {
				columnWidth : .5,
				xtype : 'fieldcontainer',
				fieldLabel : EwayLocale.monitor.remoteCommand.form.date,
				layout : 'hbox',
				defaults : {
					hideLabel : true
				},
				items : [ {
					fieldLabel : 'endDate',
					displayField : 'display',
					valueField : 'value',
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					name : 'startDate',
					vtype : 'daterange',
					width : 150,
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
					value : EwayLocale.monitor.business.transaction.toNum
				}, {
					fieldLabel : 'minute',
					displayField : 'display',
					valueField : 'value',
					width : 150,
					xtype : 'datefield',
					format : 'Y-m-d',
					editable : false,
					name : 'endDate',
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
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});