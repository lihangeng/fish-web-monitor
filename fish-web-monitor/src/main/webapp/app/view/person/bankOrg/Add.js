/**
 * 组织增加窗口：
 */
Ext.define('Eway.view.person.bankOrg.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_organization_add',

	title: EwayLocale.person.bankOrg.addBankOrgTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: ['Eway.view.field.person.OrganizationType',
				'Eway.view.common.OrgComboOrgTree',
				'Eway.view.field.person.OrganizationLevel'],

	initComponent: function() {
		var me = this;
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
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.bankOrg.code,
					xtype: 'field.code',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText: EwayLocale.vtype.bankOrgCode,
					allowBlank: false
				}, {
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.bankOrg.name,
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.bankOrg.upgradeOrg,
					emptyText: EwayLocale.combox.select,
					editable : false,
					allowBlank: false,
					name : 'parent',
					filters : '{"type" : "0"}',
					rootVisible : true,
					hiddenValue : 'parentId'
				},{
					xtype : 'hiddenfield',
					value:0,
					name :'parentId'
				},{
					xtype : 'field.organizationLevel',
					name:'orgLevel',
					store:'person.organization.OrganizationLevelDict',
					editable : false,
					fieldLabel : '<font color="red">*</font>'+EwayLocale.person.bankOrg.orgLevel,
					allowBlank:false,
					value:0
				},{
					xtype:'textfield',
					fieldLabel : EwayLocale.person.bankOrg.address,
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel: EwayLocale.person.bankOrg.zip,
					allowBlank: true,
					name :'zip',
					regex: /^[\d]{6}$/,
					regexText: EwayLocale.vtype.zip,
			        hideTrigger: false,
			        keyNavEnabled: false,
			        mouseWheelEnabled: false
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : EwayLocale.person.bankOrg.description,
					autoScroll : true,
					maxLength :40,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					xtype : 'button',
					text: EwayLocale.button.confirm,
					id : 'SaveBtn',
					action: 'add',
					handler : function() {
	　　　　　　　　　　 　　　　　　　　 Ext.getCmp('SaveBtn').setDisabled(true);　 //点击“保存”按钮的时候，“保存”按钮置灰，不可点击
	　　　　　　　　　　　 }
				}, {
					text: EwayLocale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: EwayLocale.button.cancle,
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},

	onReset: function() {
		this.up('form').getForm().reset();
		this.up('form').down('common_orgComboOrgTree').setDisabled(true);
	},

	onOver: function() {
		this.up('window').close();
	}
});