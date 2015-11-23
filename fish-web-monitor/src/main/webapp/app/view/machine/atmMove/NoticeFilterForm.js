
Ext.define('Eway.view.machine.atmMove.NoticeFilterForm', {
	extend: 'Eway.view.base.FilterForm',
	alias: 'widget.atmMove_noticeFilterForm',
	requires : [ 'Eway.view.common.OrgComboOrgTree',
	             'Eway.lib.Util' ],
	layout : 'column',
	height : 70,
	initComponent: function() {
		Ext.apply(this, {
			items : [{
				columnWidth : .60,

				items : [{
					xtype : 'textfield',
					labelAlign : 'right',
					fieldLabel : EwayLocale.machine.atmMove.terminalId,
					vtype : 'terminalId',
					width : 300,
					name : 'terminalId',
					msgTarget : 'side'
				},{
					xtype : 'fieldcontainer',
					fieldLabel : EwayLocale.machine.atmMove.date,
					labelWidth : 62,
					labelAlign : 'right',
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
						editable : false,
						name : 'startDate',
						vtype : 'daterange',
						width : 110,
						editable : false,
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
						value : EwayLocale.machine.atmMove.to//"至"
					}, {
						fieldLabel : 'minute',
						displayField : 'display',
						valueField : 'value',
						width : 110,
						xtype : 'datefield',
						format : 'Y-m-d',
						name : 'endDate',
						editable : false,
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
					}]
				}]
			},{
				columnWidth : .40,
				items : [{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmMove.orgName,
					labelAlign : 'right',
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					width: 250,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : EwayLocale.machine.atmMove.targetOrganization,
					labelAlign : 'right',
					emptyText : EwayLocale.combox.select,
					name : 'targetOrganization',
					hiddenValue : 'targetOrganizationId',
					editable : false,
					width: 250,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'targetOrganizationId'
				}]
			}]
		});

		this.callParent(arguments);
	}
});