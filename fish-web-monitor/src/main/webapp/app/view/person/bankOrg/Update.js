/**
 * 机构信息修改窗口：
 */
Ext.define('Eway.view.person.bankOrg.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_organization_update',

	title: '更改银行机构信息',
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
					fieldLabel : '<font color="red">*</font> 机构编号',
					xtype: 'field.code',
					allowBlank: false,
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！'
				}, {
					fieldLabel : '<font color="red">*</font> 机构名称',
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>上级机构',
					emptyText: '--请选择--',
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
					fieldLabel : '<font color="red">*</font>机构级别',
					editable : false,
					store:'person.organization.OrganizationLevelDict',
					allowBlank:false
				},{
					xtype:'textfield',
					fieldLabel : '机构地址',
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel:'邮政编码',
					allowBlank: true,
					name :'zip',
					regex: /^[0-9]{6}$/,
					regexText:'只能输入6数字‘0-9’！'
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : '机构描述',
					maxLength :40,
					autoScroll : true,
					allowBlank : true
				}],
				buttonAlign : 'center',
				buttons: [{
					text: '确认',
					action: 'update'
				}, {
					text: '重置',
					handler: this.onReset,
					hidden : true
				}, {
					text: '取消',
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