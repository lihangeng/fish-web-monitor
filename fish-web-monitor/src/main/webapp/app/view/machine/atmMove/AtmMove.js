
Ext.define('Eway.view.machine.atmMove.AtmMove', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmMove_atmMove',

	requires: ['Eway.view.field.atmMove.Date',
		 		'Eway.view.common.OrgComboOrgTree'],

	title: '移动设备并产生移机记录',
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
					width: 400,
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype : 'textfield',
					name : 'terminalId',
					fieldLabel : '<font color="red">*</font> 设备号',
					readOnly: true,
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'address',
					fieldLabel : '源地址',
					readOnly: true,
					allowBlank : true
				},{
					xtype : 'textfield',
					name : 'targetAddress',
					fieldLabel : '<font color="red">*</font>目标地址',
					maxLength : 50,
					allowBlank : false
				},{
//					xtype : 'textfield',
//					name : 'orgName',
//					fieldLabel : '<font color="red">*</font> 源机构',
//					readOnly: true,
//					allowBlank : false
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> 源机构',
					allowBlank : false,
					emptyText : '--请选择--',
					name : 'orgName',
					hiddenValue : 'orgId',
					readOnly: true,
					allowBlank : false,
					rootVisible : ewayUser.getOrgType() != ""? true : false,
					filters : '{"type" : "0"}',
					msgTarget : 'side'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> 目标机构',
					allowBlank : false,
					emptyText : '--请选择--',
					name : 'targetOrganization',
					hiddenValue : 'targetOrganizationId',
					editable : false,
					allowBlank : false,
					rootVisible : ewayUser.getOrgType() != ""? true : false,
					filters : '{"type" : "0"}',
					msgTarget : 'side'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'targetOrganizationId'
				}, {
					xtype : 'textfield',
					name : 'responsibility',
					fieldLabel : '<font color="red">*</font> 负责人',
					maxLength :20,
					msgTarget : 'side',
					allowBlank : false
				},{
					xtype : 'field_date',
					fieldLabel : '<font color="red">*</font> 日期',
					allowBlank : false,
					editable : false,
					value : new Date(),
					msgTarget : 'side'
				},{
					xtype : 'textarea',
					name : 'notice',
					fieldLabel : '备注',
					autoScroll : true,
					maxLength : 50,
					msgTarget : 'side'
				}],
				buttonAlign : 'center',
				buttons: [{
					text: '确认',
					action: 'confirm'
				}, {
					text: '取消',
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},
	onOver: function() {
		this.up('window').close();
	}
});