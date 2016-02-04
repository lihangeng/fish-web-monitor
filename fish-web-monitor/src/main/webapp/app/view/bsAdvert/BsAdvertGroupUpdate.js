/**
 * 设备组信息修改窗口：
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
				}, {
					xtype : 'common_orgComboOrgTree',
					fieldLabel : '<font color="red">*</font>'+EwayLocale.machine.atmGroup.orgName,
					allowBlank : false,
					emptyText : EwayLocale.combox.select,
					name : 'orgName',
					hiddenValue : 'orgId',
					editable : false,
					filters : '{"type" : "0"}',
					rootVisible : ewayUser.getOrgType() != "" && ewayUser.getOrgType() == '0' ? true : false,
					readOnly:true
				},{
					fieldLabel : '组类型',
					xtype : 'radiogroup',
					anchor : '69%',
					items : [ {
						boxLabel : '通用',
						name : 'groupType',
						checked : true,
						inputValue : 0
					}, {
						boxLabel : '默认',
						name : 'groupType',
						inputValue : 1
					}]
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