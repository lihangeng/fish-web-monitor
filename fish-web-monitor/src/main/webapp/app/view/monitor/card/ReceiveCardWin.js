
Ext.define('Eway.view.monitor.card.ReceiveCardWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.card_ReceiveCardWin',
	
	requires: [
	           'Eway.view.field.card.AccountNo',
	           'Eway.view.field.card.CardStatus',
	           'Eway.view.field.card.TerminalId',
	           'Eway.view.field.card.CardRetainDate',
	           'Eway.view.field.card.CustomerName',
	           'Eway.view.field.card.CustomerPapers',
	           'Eway.view.field.card.CustomerPhone',
	           'Eway.view.field.card.CardTypeComboBox',
	           'Eway.view.field.card.CardStatusComboBox',
	           'Ext.ux.form.DateTimeField'],
	
	title: Eway.locale.monitor.business.card.accGetCard,
	modal: true,
	resizable: false,
	constrainHeader: true,
	maximizable: true,	
	layout : 'fit',
	width: 400,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 10px 0px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					labelAlign: 'right',
					msgTarget : 'side',
					anchor : '100%'
				},
				items: [{
//					xtype : 'card_CardStatus',
					xtype : 'card_cardStatusComboBox',
					listeners : {
						afterRender : function(combo){
							combo.setValue('1');
						}
					},
					readOnly : true
				},{
					xtype : 'card_TerminalId',
					allowBlank : false,
					disabled : true
				},{
					xtype : 'card_AccountNo',
					allowBlank : false,
					disabled : true
				},{
					xtype: 'datetimefield',
					fieldLabel: Eway.locale.monitor.business.card.time,
					format : 'Y-m-d H:i:s',
					name: 'cardRetainTime',
					allowBlank : false,
					disabled : true
				},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.reason,
					xtype : 'textarea',
					name : 'reason',
					allowBlank : false,
					disabled : true
				},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.cardHolder,
					name : 'cardDistributionBank',
					xtype:'field',
					allowBlank : false,
					disabled : true
				},
				{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.customerName,
					xtype : 'card_CustomerName',
					allowBlank : false,
//					regex: /^[\u4E00-\u9FA5\a-zA-Z][\u4E00-\u9FA5\.\a-zA-Z]{0,10}$/,
//					regexText: '输入姓名，英文，.,汉字，0到10位'
					maxLength : 10,
					maxLengthText : Eway.locale.monitor.business.card.cardRegex+'10'
				},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.customerPhone,
					xtype : 'card_CustomerPhone',
					allowBlank : false,
					regex:/(^[0-9]{3,4}[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/,
					regexText: Eway.locale.tip.phone
				},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.paperType,
					xtype : 'card_CardTypeComboBox',
					allowBlank : false,
					editable : false,
					listeners: {
						select : function(){
							var customerPapers = this.up('form').down('field[name="customerPapers"]');
							if(this.value == 1){
								customerPapers.regex = /(^(\d{15}|\d{17}[\dxX])$)/;
								customerPapers.regexText = Eway.locale.tip.business.card.idCardRegex;
							}else if(this.value == 2){
								customerPapers.regex = /(^(\d{15}|\d{17}[\dxX])$)/;
								customerPapers.regexText = Eway.locale.tip.business.card.accountRegex;
							}else if(this.value == 3){
								customerPapers.regex = /(^(\d{15}|\d{17}[\dxX])$)/;
								customerPapers.regexText = Eway.locale.tip.business.card.driveCardRegex;
							}else if(this.value == 4){
								customerPapers.regex = /(^(\d{15}|\d{17}[\dxX])$)/;
								customerPapers.regexText = Eway.locale.tip.business.card.passportRegex;
							}else if(this.value == 5){
								customerPapers.regex = /^[\u4E00-\u9FA5]{0,5}[0-9]{0,10}$/;
								customerPapers.regexText = Eway.locale.tip.business.card.soldierRegex;
							}else if(this.value == 6){
								customerPapers.regex = /(^(\d{7}|\d{8})$)/;
								customerPapers.regexText = Eway.locale.tip.business.card.soldierCard;
							}else if(this.value == 7){
								customerPapers.regex = /^\d{12,15}$/;
								customerPapers.regexText = Eway.locale.tip.business.card.busnessPaper;
							}else if(this.value == 8){
								customerPapers.regex = /^\d{15}$/;
								customerPapers.regexText = Eway.locale.tip.business.card.busnessCode;
							}else if(this.value == 9){
								customerPapers.regex = /^\d{15}$/;
								customerPapers.regexText = Eway.locale.tip.business.card.taxPaper;
							}
						}
					}
				},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.monitor.business.card.paperCode,
					xtype : 'card_CustomerPapers',
					allowBlank : false,
					regex:/^$/,
					regexText: ''
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					action: 'confirm'
				},{
					text: Eway.locale.button.back,
					handler: this.onOver
				}]
			}
		});
		
		this.callParent(arguments);
	},
	
	onReset: function() {
		this.up('form').getForm().reset();
	},
	
	onOver: function() {
		this.up('window').close();
	}
});