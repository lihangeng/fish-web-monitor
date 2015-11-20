
Ext.define('Eway.view.person.bankPer.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.bank_person_update',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.Birthday',
	           'Eway.view.common.OrgComboOrgTree',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.Email',
	           'Eway.view.field.person.State',
	           'Eway.view.field.person.PersonJobComboBox',
	           'Eway.view.field.PersonRemark'],

	title: EwayLocale.person.bankPer.updateBankPerTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,
	width:500,

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 40px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					anchor:'100%',
					labelWidth: 100,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					fieldLabel : '<font color="red">*</font> '+EwayLocale.commen.name,
					xtype : 'field.username',
					maxLength : 20,
					allowBlank : false
				},{
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.bankPer.organizationName,
					allowBlank: false,
					name : 'organizationName',
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false,
					hiddenValue : 'organizationId',
					editable : false
			 	},{
					fieldLabel: '<font color="red">*</font> '+EwayLocale.commen.mobile,
					xtype : 'field.mobile',
					vtype:'mobile',
					allowBlank : false
				},{
					xtype : 'field_person_personJobComboBox',
					fieldLabel : '<font color="red">*</font> '+EwayLocale.commen.personJobName,
					allowBlank: false
			 	},{
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name:'jobNum',
					maxLength : 20,
					allowBlank : true
				},{
					xtype : 'field.state',
					fieldLabel : EwayLocale.commen.state,
					value : '1',
					allowBlank : false,
					editable : false
				},{
				    xtype : 'field.gender',
				    fieldLabel : EwayLocale.commen.gender,
				    emptyText: EwayLocale.combox.select,
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
					fieldLabel : EwayLocale.commen.phone,
					name :'phone',
					vtype:'telephone'
				},{
					xtype : 'field.email',
					allowBlank : true
				},{
					xtype:'field_personRemark'
				}],
				buttonAlign : 'center',
				fbar: [{
					text: EwayLocale.button.confirm,
					action: 'update'
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
	},

	onOver: function() {
		this.up('window').close();
	}
});