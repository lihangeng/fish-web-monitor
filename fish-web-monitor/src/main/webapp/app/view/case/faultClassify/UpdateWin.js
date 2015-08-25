
Ext.define('Eway.view.case.faultClassify.UpdateWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.faultClassify_UpdateWin',

	title: '更改故障类型配置',
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
					fieldLabel: '故障分类名称'
				},{
					xtype : 'combobox',
					name : 'responsorType',
					fieldLabel : '<font color="red">*</font> 故障责任人类型',
					store : response,
					queryMode: 'local',
    				displayField: 'name',
   				 	valueField: 'value',
   				 	editable : false,
   				 	allowBlank : false
				},{
					xtype : 'textfield',
					name : 'resolveHour',
					fieldLabel : '<font color="red">*</font> 故障规定关闭时间间隔（单位:小时）',
					regex : /^\d{0,9}(\.\d*)?$/,
					regexText: '由数字‘0-9’,‘.’组成',
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'upgrade',
					fieldLabel : '<font color="red">*</font> 最高升级次数',
					regex : /^[\d]{0,5}$/,
					regexText: '由数字‘0-9’组成',
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'notifyTimes',
					fieldLabel : '重复通知次数',
					regex : /^[1-9][\d]{0,4}$/,
					regexText: '通知次数不能为0,由数字‘0-9’组成,1-5位'
				},{
					xtype : 'combobox',
					name : 'notifyWay',
					fieldLabel : '<font color="red">*</font> 故障通知方式',
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

	onReset: function() {
		this.up('form').getForm().reset();
	},

	onOver: function() {
		this.up('window').close();
	}
});