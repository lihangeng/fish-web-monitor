Ext.define('Eway.view.case.notifyMould.UpdateWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.notifyMould_UpdateWin',

	title : '更改短信内容配置',
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
					fieldLabel : '故障分类名称'
				}, {
					xtype : 'combobox',
					name : 'notifyType',
					fieldLabel : '<font color="red">*</font> 通知类型',
					store : notifyType,
					queryMode : 'local',
					displayField : 'name',
					valueField : 'value',
					editable : false,
					allowBlank : false
				}, {
					xtype : 'combobox',
					name : 'notifyWay',
					fieldLabel : '<font color="red">*</font> 故障通知方式',
					store : notifyway,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					editable : false,
					allowBlank : false
				}, {
					xtype : 'checkboxgroup',
					fieldLabel : '<font color="red">*</font> 通知参数',
					columns : 2,
					vertical : true,
					allowBlank : false,
					blankText : '此项为必选项',
					items : [ {
						boxLabel : '设备号',
						name : 'notifySet',
						inputValue : 'terminalId'
					}, {
						boxLabel : '故障类型',
						name : 'notifySet',
						inputValue : 'faultClassify'
					}, {
						boxLabel : '故障模块',
						name : 'notifySet',
						inputValue : 'faultMod'
					}, {
						boxLabel : '应用状态',
						name : 'notifySet',
						inputValue : 'appStatus'
					} ]
				} ],
				buttonAlign : 'center',
				buttons : [ {
					xtype : 'button',
					text : '确认',
					action : 'confirm'
				}, {
					xtype : 'button',
					text : '取消',
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