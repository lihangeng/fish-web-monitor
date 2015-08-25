
Ext.define('Eway.view.person.servicePer.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.ser_person_add',

	requires: [
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.Birthday',
	           'Eway.view.common.OrgComboOrgTree',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.Email',
	           'Eway.view.field.person.State',
	           'Eway.view.field.PersonRemark'],

	title: '增加维护人员信息',
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
					width: 350,
					labelWidth: 60,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font> 姓名',
					xtype : 'field.username',
					maxLength : 20,
					allowBlank : false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> 厂商',
					allowBlank: false,
					name : 'organizationName',
					filters : '{"type" : "1"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '1' ? true : false,
					hiddenValue : 'organizationId',
					editable : false
			 	},{
					fieldLabel: '<font color="red">*</font> 手机',
					xtype : 'field.mobile',
					vtype:'mobile',
					allowBlank : false
				},{
					fieldLabel : '工号',
					xtype : 'textfield',
					name:'jobNum',
					maxLength : 20,
					allowBlank : true
				},{
					xtype : 'field.state',
					fieldLabel : '状态',
					value : '1',
					allowBlank : false,
					editable : false
				},{
				    xtype : 'field.gender',
				    fieldLabel : '性别',
				    emptyText: '--请选择--',
				    allowBlank : false,
				    value : 'MALE',
					editable : false
				},{
					xtype : 'hiddenfield',
					name :'organizationId'
				},{
					xtype : 'field.birthday',
					allowBlank : true,
					editable : false
				},{
					xtype : 'textfield',
					fieldLabel : '固话',
					allowBlank: true,
					name :'phone',
					vtype:'telephone'
				},{
					xtype : 'field.email',
					allowBlank : true
				},{
					xtype:'field_personRemark'
				}],
				buttonAlign:'center',
				buttons: [{
					text: '确认',
					action: 'add'
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