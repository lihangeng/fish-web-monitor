
Ext.define('Eway.view.person.servicePer.Update', {
	extend: 'Ext.window.Window',
	alias: 'widget.ser_person_update',

	requires: ['Eway.view.field.person.Code',
	           'Eway.view.field.person.UserName',
	           'Eway.view.field.person.Gender',
	           'Eway.view.field.person.Birthday',
	           'Eway.view.common.OrgComboOrgTree',
	           'Eway.view.field.person.Mobile',
	           'Eway.view.field.person.Email',
	           'Eway.view.field.person.State',
	           'Eway.view.field.PersonRemark'],

	title: EwayLocale.person.servicePer.updateServicePerTitle,
	modal: true,
	resizable: false,
	constrainHeader: true,
	width:500,
	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 10px 30px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					labelWidth: 150,
					anchor : '90%',
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
					fieldLabel : '<font color="red">*</font> '+EwayLocale.person.serviceOrg.shortName,
					allowBlank: false,
					name : 'organizationName',
					filters : '{"type" : "1"}',
					rootVisible : Eway.user.getOrgType() != "" && Eway.user.getOrgType() == '1' ? true : false,
					hiddenValue : 'organizationId',
					editable : false
			 	},{
					fieldLabel: '<font color="red">*</font> '+EwayLocale.commen.mobile,
					xtype : 'field.mobile',
					vtype:'mobile',
					allowBlank : false
				},{
					fieldLabel : EwayLocale.commen.jobNum,
					xtype : 'textfield',
					name:'jobNum',
					maxLength : 20,
					allowBlank : true
				},{
					xtype : 'hiddenfield',
					name :'organizationId'
				},{
					xtype : 'field.birthday',
						editable : false
				},{
					xtype : 'field.state',
					fieldLabel : EwayLocale.commen.state,
					allowBlank : true,
					allowBlank : false,
					editable : false
				},,{
					xtype : 'textfield',
					fieldLabel : EwayLocale.commen.phone,
					allowBlank: true,
					vtype:'telephone',
					name :'phone'
				},{
					fieldLabel : EwayLocale.commen.gender,
				    xtype : 'radiogroup',
					anchor : '69%',
					items : [ {
						boxLabel : EwayLocale.commen.comboxGender.male,
						name : 'gender',
						inputValue : 0
					}, {
						boxLabel : EwayLocale.commen.comboxGender.female,
						name : 'gender',
						inputValue : 1
					}]
				},{
					xtype : 'field.email'
				},{
					xtype:'field_personRemark',
					maxLength : 200
				}],
				buttonAlign:'center',
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