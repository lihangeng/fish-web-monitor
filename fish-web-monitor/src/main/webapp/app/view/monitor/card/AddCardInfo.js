Ext.define('Eway.view.monitor.card.AddCardInfo', {
			extend : 'Ext.window.Window',
			alias : 'widget.card_AddCardInfo',

			requires : ['Eway.view.field.card.AccountNo',
						'Eway.view.field.card.CardStatus',
						'Eway.view.field.card.TerminalId',
						'Eway.view.field.card.CardRetainDate',
						'Eway.view.field.card.CardStatusComboBox',
						'Ext.ux.form.DateTimeField'],

			title : '增加吞卡信息',
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
											readOnly : true
										}, {
											fieldLabel: '<font color="red">*</font> 设备号',
											xtype : 'card_TerminalId',
											allowBlank : false,
											regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,19}$/,
											regexText: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’，只能以字母或数字开头{1-20}。'
										}, {
											fieldLabel: '<font color="red">*</font> 卡号',
											xtype : 'card_AccountNo',
											allowBlank : false,
											regex : /^\d{13,19}$/,
											regexText : '由数字‘0-9’组成,13到19位'
										}, {
											fieldLabel: '<font color="red">*</font> 吞卡时间',
											xtype : 'datetimefield',
											name: 'cardRetainTime',
											format: 'Y-m-d H:i:s',
											editable : false,
											value : Ext.Date.add(new Date(), Ext.Date.DAY, 0),
											maxValue : Ext.Date.add(Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d') + " 23:59:59","Y-m-d H:i:s")),
											allowBlank : false
										}, {
											fieldLabel: '<font color="red">*</font> 吞卡原因',
											xtype : 'textarea',
											grow:true,
											name : 'reason',
											allowBlank : false,
											maxLength: 50,
											maxLengthText : '允许的最大长度为50'
										}, {
											fieldLabel: '<font color="red">*</font> 发卡行',
											xtype:'textfield',
											name : 'cardDistributionBank',
											allowBlank: false,
											maxLength: 40,
											maxLengthText : '允许的最大长度为40'
										}
										],
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