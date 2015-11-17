
Ext.define('Eway.view.monitor.business.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.business_update',
	
	title: EwayLocale.monitor.business.blackList.updateTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 250,
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype:'textfield',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.monitor.business.transaction.card,
					width : 300,
					allowBlank: false, 
					name :'cardNo',
					readOnly :true,
					regex: /^[0-9]{13,19}$/,
					regexText:EwayLocale.tip.cardNo
				},{
					xtype:'textfield',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.monitor.business.transaction.userName,
					width : 300,
					allowBlank: false, 
					regex : /^[^\s]+.*$/, // 不能以空格开头和全是空格
					name :'userName',
					maxLength : 20,
					regexText:EwayLocale.tip.blankBegin
				},{
					xtype:'textfield',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.monitor.business.blackList.cardBank,
					width : 300,
					allowBlank: false, 
					regex : /^[^\s]+.*$/, // 不能以空格开头和全是空格
					name :'organization',
					maxLength : 60,
					regexText:EwayLocale.tip.blankBegin
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
	
	onOver: function() {
		this.up('window').close();
	}
});