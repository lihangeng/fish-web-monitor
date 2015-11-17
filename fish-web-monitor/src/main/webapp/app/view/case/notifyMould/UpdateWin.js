Ext.define('Eway.view.case.notifyMould.UpdateWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.notifyMould_UpdateWin',

	title : EwayLocale.cases.notifyMould.updateMessageContentConfiguration,
	modal : true,
	resizable : false,
	constrainHeader : true,

	initComponent : function() {
		var notifyway = Ext.create('Eway.store.case.NotifyWay');
		var notifyType = Ext.create('Eway.store.case.NotifyType');
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				maximizable : true,
				defaults : {
					width : 400,
					labelWidth : 100,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [ {
					xtype : 'displayfield',
					name : 'classifyName',
					fieldLabel : EwayLocale.cases.faultClassify.faultClassifyName
				}, {
					xtype : 'combobox',
					name : 'notifyType',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.cases.notifyMould.noticeType,
					store : notifyType,
					queryMode : 'local',
					displayField : 'name',
					valueField : 'value',
					editable : false,
					allowBlank : false
				}, {
					xtype : 'combobox',
					name : 'notifyWay',
					fieldLabel : '<font color="red">*</font>' +EwayLocale.cases.faultClassify.faultInformWay,
					store : notifyway,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					editable : false,
					allowBlank : false
				}, {
					xtype : 'checkboxgroup',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.cases.notifyMould.noticeValue,
					columns : 2,
					vertical : true,
					allowBlank : false,
					blankText : EwayLocale.cases.notifyMould.necessaryOption,
					items : [ {
						boxLabel : EwayLocale.commen.terminalId,
						name : 'notifySet',
						inputValue : 'terminalId'
					}, {
						boxLabel : EwayLocale.cases.notifyMould.faultType,
						name : 'notifySet',
						inputValue : 'faultClassify'
					}, {
						boxLabel : EwayLocale.cases.caseFault.faultModule,
						name : 'notifySet',
						inputValue : 'faultMod'
					}, {
						boxLabel : EwayLocale.cases.notifyMould.applyStatus,
						name : 'notifySet',
						inputValue : 'appStatus'
					} ]
				} ],
				buttonAlign : 'center',
				buttons : [ {
					xtype : 'button',
					text : EwayLocale.cases.confirm,
					action : 'confirm'
				}, {
					xtype : 'button',
					text : EwayLocale.cases.cancel,
					handler : this.onOver
				} ]
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