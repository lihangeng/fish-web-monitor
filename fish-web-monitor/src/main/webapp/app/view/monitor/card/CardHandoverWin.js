Ext.define('Eway.view.monitor.card.CardHandoverWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.card_CardHandoverWin',

	requires : [
			'Eway.view.field.card.AccountNo',
			'Eway.view.field.card.CardStatus',
			'Eway.view.field.card.TerminalId',
			'Eway.view.field.card.CardRetainDate',
			'Eway.view.field.card.CustomerName',
			'Eway.view.field.card.CustomerPapers',
			'Eway.view.field.card.CustomerPhone',
			'Eway.view.field.card.CardTypeComboBox',
//			'Eway.view.field.card.OrganizationComboBox',
			'Eway.view.field.card.CardStatusComboBox',
			'Eway.view.common.OrgComboOrgTree',
			'Ext.ux.form.DateTimeField'
			],

	title : EwayLocale.monitor.business.card.transferCard,
	modal : true,
	resizable : false,
	constrainHeader : true,
	maximizable: true,
	layout : 'fit',
	width: 400,

	initComponent : function() {
		Ext.apply(this, {
					items : {
						xtype : 'form',
						bodyStyle : 'padding: 10px 10px 10px 0px',
						trackResetOnLoad : true,
						selectOnFocus : true,
						defaults : {
							labelAlign : 'right',
							msgTarget : 'side',
							anchor : '100%'
						},
						items : [{
									xtype : 'card_cardStatusComboBox',
									listeners : {
										afterRender : function(combo) {
											combo.setValue('3');
										}
									},
									readOnly : true
								}, {
									xtype : 'card_TerminalId',
									allowBlank : false,
									disabled : true
								}, {
									xtype : 'card_AccountNo',
									allowBlank : false,
									disabled : true
								}, {
									xtype: 'datetimefield',
									fieldLabel: EwayLocale.monitor.business.card.time,
									format : 'Y-m-d H:i:s',
									name: 'cardRetainTime',
									allowBlank : false,
									disabled : true
									
									
								}, {
									fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.card.reason,
									xtype : 'textarea',
									grow:true,
									name : 'reason',
									allowBlank : false,
									disabled : true
								}, {
									fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.card.cardHolder,
									name : 'cardDistributionBank',
									xtype:'field',
									allowBlank : false,
									disabled : true
								},{
									style : 'padding-top:0px',
									xtype : 'hiddenfield',
									name : 'orgGuid'
								}, {
									xtype : 'common_orgComboOrgTree',
									fieldLabel : '<font color="red">*</font> '+EwayLocale.monitor.business.card.processOrg,
									allowBlank : false,
									emptyText : EwayLocale.combox.select,
									name : 'orgName',
									hiddenValue : 'orgGuid',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								}],
						buttonAlign : 'center',
						buttons : [{
									text : EwayLocale.button.confirm,
									action : 'confirm'
								}, {
									text : EwayLocale.button.cancle,
									handler : this.onOver
								}]
					}
				});

		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
	},

	onOver : function() {
		this.up('window').close();
	}
});