
Ext.define('Eway.view.machine.atmMove.AtmMove', {
	extend: 'Ext.window.Window',
	alias: 'widget.atmMove_atmMove',

	requires: ['Eway.view.field.atmMove.Date',
		 		'Eway.view.common.OrgComboOrgTree'],

	title: '移动设备并产生移机记录',
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
					width: 400,
					labelWidth: 80,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
					xtype : 'textfield',
					name : 'terminalId',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmMove.terminalId,
					readOnly: true,
					allowBlank : false
				},{
					xtype : 'textfield',
					name : 'address',
					fieldLabel : Eway.locale.machine.atmMove.address,
					readOnly: true,
					allowBlank : true
				},{
					xtype : 'textfield',
					name : 'targetAddress',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmMove.targetAddress,
					maxLength : 50,
					allowBlank : false
				},{
//					xtype : 'textfield',
//					name : 'orgName',
//					fieldLabel : '<font color="red">*</font> 源机构',
//					readOnly: true,
//					allowBlank : false
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmMove.orgName,
					allowBlank : false,
					emptyText : Eway.locale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					readOnly: true,
					allowBlank : false,
					rootVisible : ewayUser.getOrgType() != ""? true : false,
					filters : '{"type" : "0"}',
					msgTarget : 'side'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'orgId'
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font> '+Eway.locale.machine.atmMove.targetOrganization,
					allowBlank : false,
					emptyText : Eway.locale.combox.select,
					name : 'targetOrganization',
					hiddenValue : 'targetOrganizationId',
					editable : false,
					allowBlank : false,
					rootVisible : ewayUser.getOrgType() != ""? true : false,
					filters : '{"type" : "0"}',
					msgTarget : 'side'
				},{
					style : 'padding-top:0px',
					xtype : 'hiddenfield',
					name : 'targetOrganizationId'
				}, {
					xtype : 'textfield',
					name : 'responsibility',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmMove.responsibility,
					maxLength :20,
					msgTarget : 'side',
					allowBlank : false
				},{
					xtype : 'field_date',
					fieldLabel : '<font color="red">*</font>'+Eway.locale.machine.atmMove.date,
					allowBlank : false,
					editable : false,
					value : new Date(),
					msgTarget : 'side'
				},{
					xtype : 'textarea',
					name : 'notice',
					fieldLabel : Eway.locale.machine.atmMove.notice,
					autoScroll : true,
					maxLength : 50,
					msgTarget : 'side'
				}],
				buttonAlign : 'center',
				buttons: [{
					text: Eway.locale.button.confirm,
					action: 'confirm'
				}, {
					text: Eway.locale.button.cancle,
					handler: this.onOver
				}]
			}
		});

		this.callParent(arguments);
	},
	onOver: function() {
		this.up('window').close();
	}
});