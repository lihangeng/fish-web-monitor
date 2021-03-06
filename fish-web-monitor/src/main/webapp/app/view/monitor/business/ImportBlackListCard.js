Ext.define('Eway.view.monitor.business.ImportBlackListCard', {
			extend : 'Ext.window.Window',
			alias : 'widget.business_ImportBlackListCard',

			title : EwayLocale.monitor.business.blackList.importTitle,
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
											buttonText : EwayLocale.version.View.versionFileButton,
											fieldLabel : EwayLocale.monitor.business.blackList.importFile,
											emptyText:EwayLocale.monitor.business.blackList.chooseFileRegex,
											width : 400,
											allowBlank: false, 
											name :'file',
											regex : /^([\w|\W]*)(\.xlsx)|([\w|\W]*)(\.xls)$/,
											regexText:EwayLocale.monitor.business.blackList.fileRegex
										}],
								buttons : [{
											text : EwayLocale.monitor.business.blackList.tempDownload,
											iconCls :'sureBtn',
											action: 'down'
										}, {
											text : EwayLocale.monitor.business.blackList.importNow,
											iconCls :'sureBtn',
											action: 'import'
										}, {
											text : EwayLocale.button.back,
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