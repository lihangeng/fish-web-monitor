
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

	title: EwayLocale.person.servicePer.addServicePerTitle,
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
					labelAlign: 'right',
					msgTarget : 'side',
					labelWidth: 150,
					anchor : '90%'
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
					xtype : 'field.state',
					fieldLabel : EwayLocale.commen.state,
					value : '1',
					allowBlank : false,
					editable : false
				},{
					fieldLabel : EwayLocale.commen.gender,
				    xtype : 'radiogroup',
					anchor : '69%',
					items : [ {
						boxLabel : '男',
						name : 'gender',
						inputValue : 0
					}, {
						boxLabel : '女',
						name : 'gender',
						inputValue : 1
					}]
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
					allowBlank: true,
					name :'phone',
					vtype:'telephone'
				},{
					xtype : 'field.email',
					allowBlank : true
				},{
					xtype:'field_personRemark',
					maxLength : 200
				}],
				buttonAlign:'center',
				buttons: [{
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
	},

	onOver: function() {
		this.up('window').close();
	}
});