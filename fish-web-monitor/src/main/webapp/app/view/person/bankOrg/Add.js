/**
 * 组织增加窗口：
 */
Ext.define('Eway.view.person.bankOrg.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_organization_add',

	title: Eway.locale.person.bankOrg.addBankOrgTitle,
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
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankOrg.code,
					xtype: 'field.code',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText: Eway.locale.vtype.bankOrgCode,
					allowBlank: false
				}, {
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankOrg.name,
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankOrg.upgradeOrg,
					emptyText: Eway.locale.combox.select,
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
					fieldLabel : '<font color="red">*</font>'+Eway.locale.person.bankOrg.orgLevel,
					allowBlank:false,
					value:0
				},{
					xtype:'textfield',
					fieldLabel : Eway.locale.person.bankOrg.address,
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel: Eway.locale.person.bankOrg.zip,
					allowBlank: true,
					name :'zip',
					regex: /^[\d]{6}$/,
					regexText: Eway.locale.vtype.zip,
			        hideTrigger: false,
			        keyNavEnabled: false,
			        mouseWheelEnabled: false
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : Eway.locale.person.bankOrg.description,
					autoScroll : true,
					maxLength :40,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					xtype : 'button',
					text: Eway.locale.button.confirm,
					action: 'add'
				}, {
					text: Eway.locale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: Eway.locale.button.cancle,
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