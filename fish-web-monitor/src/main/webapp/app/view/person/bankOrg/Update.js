/**
 * 机构信息修改窗口：
 */
Ext.define('Eway.view.person.bankOrg.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_organization_update',

	title: Eway.locale.person.bankOrg.updateBankTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: ['Eway.view.field.person.OrganizationState',
			   'Eway.view.common.OrgComboOrgTree',
				'Eway.view.field.person.OrganizationLevel'],

	initComponent: function() {
		var counter=0;
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
					allowBlank: false,
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:Eway.locale.vtype.bankOrgCode
				}, {
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankOrg.name,
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.person.bankOrg.upgradeOrg,
					emptyText: Eway.locale.combox.select,
					allowBlank: false,
					editable : false,
					name : 'parent',
					filters : '{"type" : "0"}',
					rootVisible : true,
					hidden : true,
					hiddenValue : 'parentId'
				},{
					xtype : 'hiddenfield',
					name :'parentId'
				},{
					xtype : 'field.organizationLevel',
					name:'orgLevel',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.person.bankOrg.orgLevel,
					editable : false,
					store:'person.organization.OrganizationLevelDict',
					allowBlank:false
				},{
					xtype:'textfield',
					fieldLabel : Eway.locale.person.bankOrg.address,
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel:Eway.locale.person.bankOrg.zip,
					allowBlank: true,
					name :'zip',
					regex: /^[0-9]{6}$/,
					regexText:Eway.locale.vtype.zip
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : Eway.locale.person.bankOrg.description,
					maxLength :40,
					autoScroll : true,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					action: 'update'
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
	//	this.up('form').down('common_orgComboOrgTree').setDisabled(true);
	},

	onOver: function() {
		this.up('window').close();
	}
});