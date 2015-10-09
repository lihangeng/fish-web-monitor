
Ext.define('Eway.view.person.bankPer.Add', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_person_add',

	requires: [
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.Birthday',
	           'Eway.view.common.OrgComboOrgTree',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.Email',
	           'Eway.view.field.person.State',
	           'Eway.view.field.person.PersonJobComboBox',
	           'Eway.view.field.PersonRemark'],

	title: Eway.locale.person.bankPer.addBankPerTitle,
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
					fieldLabel : '<font color="red">*</font> '+Eway.locale.commen.name,
					xtype : 'field.username',
					maxLength : 20,
					allowBlank : false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.person.bankPer.organizationName,
					allowBlank: false,
					name : 'organizationName',
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false,
					hiddenValue : 'organizationId',
					editable : false
			 	},{
					fieldLabel: '<font color="red">*</font> '+Eway.locale.commen.mobile,
					xtype : 'field.mobile',
					vtype:'mobile',
					allowBlank : false
				},{
					xtype : 'field_person_personJobComboBox',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.commen.personJobName,
					value : '0005',
					allowBlank: false
			 	},{
					fieldLabel : Eway.locale.commen.jobNum,
					xtype : 'textfield',
					name:'jobNum',
					maxLength : 20,
					allowBlank : true
				},{
					xtype : 'field.state',
					fieldLabel : Eway.locale.commen.state,
					value : '1',
					allowBlank : false,
					editable : false
				},{
				    xtype : 'field.gender',
				    fieldLabel : Eway.locale.commen.gender,
				    emptyText: Eway.locale.combox.select,
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
					fieldLabel : Eway.locale.commen.phone,
					name :'phone',
					vtype:'telephone'
				},{
					xtype : 'field.email',
					allowBlank : true
				},{
					xtype:'field_personRemark'
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					//iconCls :'sureBtn',
					action: 'add'
				}, {
					text: Eway.locale.button.reset,
					handler: this.onReset,
					hidden : true
				}, {
					text: Eway.locale.button.cancle,
					//iconCls :'returnBtn',
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