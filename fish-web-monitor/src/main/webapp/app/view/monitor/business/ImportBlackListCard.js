Ext.define('Eway.view.monitor.business.ImportBlackListCard', {
			extend : 'Ext.window.Window',
			alias : 'widget.business_ImportBlackListCard',

			title : '批量导入黑名单卡',
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
									width : 400,
									labelWidth : 60,
									labelAlign : 'right',
									msgTarget : 'side'
								},
								items : [{
											xtype:'filefield',
											buttonText : '浏览...',
											fieldLabel : '导入文件',
											emptyText:'请选择导入文件,只支持.xls和.xlsx格式的文件',
											width : 400,
											allowBlank: false, 
											name :'file',
											regex : /^([\w|\W]*)(\.xlsx)|([\w|\W]*)(\.xls)$/,
											regexText:'只能导入.xls格式和.xlsx格式的文件'
										}],
								buttons : [{
											text : '模版下载',
											iconCls :'sureBtn',
											action: 'down'
										}, {
											text : '导入',
											iconCls :'sureBtn',
											action: 'import'
										}, {
											text : '返回',
											iconCls :'returnBtn',
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