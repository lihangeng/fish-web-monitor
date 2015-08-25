/**
 * 厂商信息修改窗口：
 */
Ext.define('Eway.view.person.serviceOrg.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.ser_organization_update',

	title: '更改维护商信息',
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: ['Eway.view.field.person.OrganizationState',
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
					fieldLabel : '<font color="red">*</font> 厂商编号',
					xtype: 'field.code',
					allowBlank: false,
					regex: /^[a-zA-Z0-9][a-zA-Z0-9-_\.]{0,19}$/,
					regexText:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',
					validator : function(){
						return true;
					}
				}, {
					fieldLabel : '<font color="red">*</font> 厂商名称',
					xtype: 'field.name',
					maxLength : 40,
					allowBlank: false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>上级厂商',
					emptyText: '--请选择--',
					allowBlank: false,
					editable : false,
					name : 'parent',
					filters : '{"type" : "1"}',
					rootVisible : true,
					hidden : true,
					hiddenValue : 'parentId'
				},{
					xtype : 'hiddenfield',
					name :'parentId'
				},{
					xtype:'textfield',
					fieldLabel : '厂商地址',
					maxLength : 60,
					allowBlank: true,
					name :'address'
				},{
					xtype:'textfield',
					fieldLabel:'邮政编码',
					allowBlank: true,
					name :'zip',
					regex: /^[\d]{6}$/,
					regexText:'只能输入6个‘0-9’的数字！',
			        hideTrigger: true,
			        keyNavEnabled: false,
			        mouseWheelEnabled: false
				},{
					xtype : 'textarea',
					name : 'description',
					fieldLabel : '厂商描述',
					maxLength :40,
					autoScroll : true,
					allowBlank : true
				}],
				buttonAlign:'center',
				fbar: [{
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
	},

	onOver: function() {
		this.up('window').close();
	}
});