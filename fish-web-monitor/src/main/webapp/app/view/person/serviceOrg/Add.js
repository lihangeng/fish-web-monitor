/**
 * 组织增加窗口：
 */
Ext.define('Eway.view.person.serviceOrg.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.ser_organization_add',

	title: EwayLocale.person.serviceOrg.addServiceTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: ['Eway.view.field.person.OrganizationType',
				'Eway.view.common.OrgComboOrgTree'],

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
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.serviceOrg.code,
					xtype: 'field.code',
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:EwayLocale.vtype.bankOrgCode,
					allowBlank: false
				}, {
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.serviceOrg.name,
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>'+EwayLocale.person.serviceOrg.upgradeService,
					emptyText: EwayLocale.combox.select,
					allowBlank: false,
					editable : false,
					name : 'parent',
					filters : '{"type" : "1"}',
					rootVisible : true,
					hiddenValue : 'parentId'
			 	},{
					xtype : 'hiddenfield',
					name :'parentId'
				},{
					xtype:'textfield',
					fieldLabel : EwayLocale.person.serviceOrg.address,
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel:EwayLocale.person.serviceOrg.zip,
					allowBlank: true,
					name :'zip',
					regex: /^[\d]{6}$/,
					regexText:EwayLocale.vtype.zip,
			        hideTrigger: false,
			        keyNavEnabled: false,
			        mouseWheelEnabled: false
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : EwayLocale.person.serviceOrg.description,
					autoScroll : true,
					maxLength :40,
					allowBlank : true
				}],
				buttonAlign:'center',
				buttons: [{
					xtype : 'button',
					text: EwayLocale.button.confirm,
					action: 'add'
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