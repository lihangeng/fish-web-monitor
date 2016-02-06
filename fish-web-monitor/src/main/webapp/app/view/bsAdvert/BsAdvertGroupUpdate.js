/**
 * 广告组信息修改窗口：
 */
Ext.define('Eway.view.bsAdvert.BsAdvertGroupUpdate', {
	extend: 'Ext.window.Window',
	alias: 'widget.bsAdvertGroup_update',

	title: '修改广告组信息',
	modal: true,
	resizable: false,
	constrainHeader: true,

	requires: ['Eway.view.common.OrgComboOrgTree'],

	initComponent: function() {
		Ext.apply(this, {
			items : {
				xtype: 'form',
				bodyStyle : 'padding: 10px 30px 10px 10px',
				trackResetOnLoad : true,
				selectOnFocus : true,
				defaults: {
					width: 350,
					labelAlign: 'right',
					msgTarget : 'side'
				},
				items: [{
						style : 'padding-top:0px',
						xtype : 'hiddenfield',
						name : 'orgId'
					}, {
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.groupName,
					xtype : 'textfield',
					name : 'groupName',
					maxLength : 30,
					msgTarget : 'side',
					allowBlank : false
				}],
				buttonAlign : 'center',
				buttons: [{
					text: EwayLocale.button.confirm,
					action: 'update'
				}, {
					text: EwayLocale.button.back,
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