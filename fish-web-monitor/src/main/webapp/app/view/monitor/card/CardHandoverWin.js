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

	title : '移交卡片',
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
									fieldLabel: '吞卡时间',
									format : 'Y-m-d H:i:s',
									name: 'cardRetainTime',
									allowBlank : false,
									disabled : true
									
									
								}, {
									fieldLabel: '<font color="red">*</font> 吞卡原因',
									xtype : 'textarea',
									grow:true,
									name : 'reason',
									allowBlank : false,
									disabled : true
								}, {
									fieldLabel: '<font color="red">*</font> 发卡行',
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
									fieldLabel : '<font color="red">*</font> 处理机构',
									allowBlank : false,
									emptyText : '--请选择--',
									name : 'orgName',
									hiddenValue : 'orgGuid',
									editable : false,
									filters : '{"type" : "0"}',
									rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false
								}],
						buttonAlign : 'center',
						buttons : [{
									text : '确认',
									action : 'confirm'
								}, {
									text : '取消',
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