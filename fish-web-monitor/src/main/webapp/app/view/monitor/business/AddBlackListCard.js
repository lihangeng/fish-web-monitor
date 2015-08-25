Ext.define('Eway.view.monitor.business.AddBlackListCard', {
			extend : 'Ext.window.Window',
			alias : 'widget.business_AddBlackListCard',

			title : '添加黑名单卡',
			modal : true,
			resizable : false,
			constrainHeader : true,

			initComponent : function() {
				Ext.apply(this, {
							items : {
								xtype : 'form',
								bodyStyle : 'padding: 10px 10px 30px 10px',
								trackResetOnLoad : true,
								selectOnFocus : true,
								defaults : {
									width : 250,
									labelWidth : 80,
									labelAlign : 'right',
									msgTarget : 'side'
								},
								items : [{
											xtype:'textfield',
											fieldLabel : '<font color="red">*</font> 交易卡号',
											width : 300,
											allowBlank: false, 
											name :'cardNo',
											regex: /^[0-9]{13,19}$/,
											regexText:'只能输入13到19个数字！'
										},{
											xtype:'textfield',
											fieldLabel : '<font color="red">*</font> 用户姓名',
											width : 300,
											allowBlank: false, 
											regex : /^[^\s]+.*$/, // 不能以空格开头和全是空格
											name :'userName',
											maxLength : 20,
											regexText:'不能以空格开头！'
										},{
											xtype:'textfield',
											fieldLabel : '<font color="red">*</font> 所属银行',
											width : 300,
											allowBlank: false,
											regex : /^[^\s]+.*$/, // 不能以空格开头和全是空格
											name :'organization',
											maxLength : 60,
											regexText:'不能以空格开头！'
										}],
										buttonAlign : 'center',
										buttons : [{
													text : '确认',
													action : 'add'
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