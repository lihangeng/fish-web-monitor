/**
 * 机构信息修改窗口：
 */
Ext.define('Eway.view.person.bankOrg.Move', {
	extend : 'Ext.window.Window',
	alias : 'widget.bank_organization_move',

	title : EwayLocale.person.bankOrg.moveBankTitle,
	modal : true,
	resizable : false,
	constrainHeader : true,

	requires : [ 'Eway.view.field.person.OrganizationState',
			'Eway.view.common.OrgComboOrgTree',
			'Eway.view.field.person.OrganizationLevel' ],

	initComponent : function() {
		var counter = 0;
		Ext.apply(this, {
			items : {
				xtype : 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults : {
					width : 400,
					labelWidth : 80,
					labelAlign : 'right',
					msgTarget : 'side'
				},
				items : [ {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>'+EwayLocale.person.bankOrg.upgradeOrg,
					emptyText : EwayLocale.combox.select,
					allowBlank : false,
					editable : false,
					name : 'parent',
					filters : '{"type" : "0"}',
					rootVisible : true,
					hiddenValue : 'parentId'
				}, {
					xtype : 'hiddenfield',
					name : 'parentId'
				} ],
				fbar : [ {
					text : EwayLocale.button.confirm,
					iconCls : 'sureBtn',
					action : 'move'
				}, {
					text : EwayLocale.button.reset,
					handler : this.onReset,
					hidden : true
				}, {
					text : EwayLocale.button.back,
					iconCls : 'returnBtn',
					handler : this.onOver
				} ]
			}
		});

		this.callParent(arguments);
	},

	onReset : function() {
		this.up('form').getForm().reset();
		// this.up('form').down('common_orgComboOrgTree').setDisabled(true);
	},

	onOver : function() {
		this.up('window').close();
	}
});