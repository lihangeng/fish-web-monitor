Ext.define('Eway.view.monitor.card.CardInfoFilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.card_cardInfoFilterForm',

	requires : [
			'Eway.view.field.card.CardStatusComboBox',
			'Eway.view.field.card.InOutComboBox',
			'Eway.view.field.card.StartDate', 'Eway.view.field.card.EndDate',
			'Eway.view.field.card.AccountNo',
			'Eway.view.field.card.TerminalId',
			'Eway.view.field.card.DeviceAtmVendorComboBox',
			'Eway.view.field.card.DeviceTypeComboBox',
			'Eway.view.common.OrgComboOrgTree',
			'Ext.ux.form.DateTimeField',
            'Eway.lib.Util'],
	height : 88,
	layout : 'column',
	hideLabel : false,
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						columnWidth : .3,
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
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								},{
									xtype : 'combobox',
									fieldLabel : EwayLocale.machine.device.onBankSignal,
									emptyText :  EwayLocale.combox.select,
									labelAlign : 'right',
									name : 'inOut',
									hiddenName : 'inOut',
									editable : false,
									store: 'machine.DeviceAwayFlagComboBox',
									valueField : 'value',
									displayField : 'display',
									queryMode : 'local'
								}]
					}, {
						columnWidth : .3,
						items : [
						         {
							xtype : 'card_TerminalId',
							labelAlign : 'right'
					        	},{
									xtype : 'card_AccountNo',
									regex: /^\d{13,19}$/,
									regexText: EwayLocale.tip.cardNo,
									msgTarget : 'side',
									labelAlign : 'right'
								}]

					}, {

						columnWidth : .4,
						items : [{
									xtype : 'field_card_DeviceTypeComboBox',
									width : 328,
									labelAlign : 'right'
								}, {
									xtype : 'fieldcontainer',
									fieldLabel : EwayLocale.monitor.business.card.time,
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
										name : 'startDate',
										editable : false,
										vtype : 'daterange',
										width : 108,
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
										width : 108,
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