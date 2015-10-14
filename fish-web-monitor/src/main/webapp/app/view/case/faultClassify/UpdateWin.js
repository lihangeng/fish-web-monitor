
Ext.define('Eway.view.case.faultClassify.UpdateWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.faultClassify_UpdateWin',

	title: Eway.locale.cases.faultClassify.updateFaultTypeConfiguration,
	modal: true,
	resizable: false,
	constrainHeader: true,

	initComponent: function() {
		var response = Ext.create('Eway.store.case.ResponseType');
		var notifyway = Ext.create('Eway.store.case.NotifyWay');
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				maximizable: true,
				defaults: {
					width: 400,
					labelWidth: 120,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype: 'displayfield',
					name : 'classifyName',
					fieldLabel: Eway.locale.cases.faultClassify.faultClassifyName
				},{
					xtype : 'combobox',
					name : 'responsorType',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.cases.faultClassify.faultresponsorType,
					store : response,
					queryMode: 'local',
    				displayField: 'name',
   				 	valueField: 'value',
   				 	editable : false,
   				 	allowBlank : false
				},{
					xtype : 'textfield',
					name : 'resolveHour',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.cases.faultClassify.faultCloseInterval,
					regex : /^\d{0,9}(\.\d*)?$/,
					regexText: Eway.locale.cases.faultClassify.number,
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'upgrade',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.cases.faultClassify.upGradeTimes,
					regex : /^[\d]{0,5}$/,
					regexText: Eway.locale.cases.faultClassify.number,
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'notifyTimes',
					fieldLabel : Eway.locale.cases.caseFault.notifyRepeatTimes,
					regex : /^[1-9][\d]{0,4}$/,
					regexText: Eway.locale.cases.faultClassify.informNumber
				},{
					xtype : 'combobox',
					name : 'notifyWay',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.cases.faultClassify.faultInformWay,
					store : notifyway,
					queryMode: 'local',
    				displayField: 'display',
   				 	valueField: 'value',
   				 	editable : false,
   				 	allowBlank : false
				}],
				buttonAlign : 'center',
				buttons : [ {
					xtype : 'button',
					text : Eway.locale.cases.confirm,
					action : 'confirm'
				}, {
					xtype : 'button',
					text : Eway.locale.cases.cancel,
					handler : this.onOver
				} ]
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