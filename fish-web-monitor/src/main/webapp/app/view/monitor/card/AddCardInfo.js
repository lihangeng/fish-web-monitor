Ext.define('Eway.view.monitor.card.AddCardInfo', {
			extend : 'Ext.window.Window',
			alias : 'widget.card_AddCardInfo',

			requires : ['Eway.view.field.card.AccountNo',
						'Eway.view.field.card.CardStatus',
						'Eway.view.field.card.TerminalId',
						'Eway.view.field.card.CardRetainDate',
						'Eway.view.field.card.CardStatusComboBox',
						'Ext.ux.form.DateTimeField'],

			title : EwayLocale.monitor.business.card.addTitle,
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
													combo.setValue('0');
												}
											},
											readOnly : true,
											hidden:true
										}, {
											fieldLabel: '<font color="red">*</font> '+EwayLocale.commen.terminalId,
											xtype : 'card_TerminalId',
											allowBlank : false,
											regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,19}$/,
											regexText: EwayLocale.vtype.terminalId
										}, {
											fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.transaction.card,
											xtype : 'card_AccountNo',
											allowBlank : false,
											regex : /^\d{13,19}$/,
											regexText : EwayLocale.tip.cardNo
										}, {
											fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.card.time,
											xtype : 'datetimefield',
											name: 'cardRetainTime',
											format: 'Y-m-d H:i:s',
											editable : false,
											value : Ext.Date.add(new Date(), Ext.Date.DAY, 0),
											maxValue : Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d H:i:s'),"Y-m-d H:i:s"),
											allowBlank : false
										}, {
											fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.card.reason,
											xtype : 'textarea',
											grow:true,
											name : 'reason',
											allowBlank : false,
											maxLength: 50,
											maxLengthText : EwayLocale.monitor.business.card.cardRegex+'50'
										}, {
											fieldLabel: '<font color="red">*</font> '+EwayLocale.monitor.business.card.cardHolder,
											xtype:'textfield',
											name : 'cardDistributionBank',
											allowBlank: false,
											maxLength: 40,
											maxLengthText : EwayLocale.monitor.business.card.cardRegex+'40'
										}
										],
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